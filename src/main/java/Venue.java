import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private int id;
  private String name;


  public Venue(String name){
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }


  public static List<Venue> all() {
    String venueTable = "SELECT id, name FROM venues";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(venueTable).executeAndFetch(Venue.class);
    }
  }

  @Override
  public boolean equals(Object otherVenue) {
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue =  (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
             this.getId() == newVenue.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String venueTable = "INSERT INTO venues (name) VALUES (:name)";
      this.id = (int) con.createQuery(venueTable, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String venueTable = "SELECT * FROM venues WHERE id=:id";
      Venue venue = con.createQuery(venueTable)
        .addParameter("id", id)
        .executeAndFetchFirst(Venue.class);
      return venue;
    }
  }
    public void delete() {
      try(Connection con = DB.sql2o.open()) {
        String deleteVenueQuery = "DELETE FROM venues WHERE id = :id;";
        con.createQuery(deleteVenueQuery)
          .addParameter("id", this.getId())
          .executeUpdate();

        String deleteVenueInJointTableQuery = "DELETE FROM bands_venues WHERE venue_id = :venueId";
          con.createQuery(deleteVenueInJointTableQuery)
            .addParameter("venueId", this.getId())
            .executeUpdate();
      }
    }
        public void update(String newVenue) {
          try(Connection con = DB.sql2o.open()) {
            String venueTable = "UPDATE venues SET name = :name WHERE id = :id";
            con.createQuery(venueTable)
              .addParameter("name", newVenue)
              .addParameter("id", this.id)
              .executeUpdate();
          }
        }

  public void addBand(Band band) {
  try(Connection con = DB.sql2o.open()) {
    String bandVenuesTable = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
    con.createQuery(bandVenuesTable)
    .addParameter("venue_id", this.getId())
      .addParameter("band_id", band.getId())
      .executeUpdate();
  }
}

  public List<Band> getBands() {
    try(Connection con = DB.sql2o.open()){
      String bandVenuesTableJointTable = "SELECT band_id FROM bands_venues WHERE venue_id = :venue_id";
      List<Integer> bandIds = con.createQuery(bandVenuesTableJointTable )
        .addParameter("venue_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Band> bands = new ArrayList<Band>();

      for (Integer bandId : bandIds) {
        String bandTable = "SELECT * FROM bands WHERE id = :bandId";
        Band band = con.createQuery(bandTable)
          .addParameter("bandId", bandId)
          .executeAndFetchFirst(Band.class);
        bands.add(band);
      }
      return bands;
    }
  }

}
