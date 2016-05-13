import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;


public class Band {
  private int id;
  private String name;

  public Band(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Band> all() {
    String bandTable = "SELECT id, name FROM bands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(bandTable).executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object otherBand) {
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName()) &&
      this.getId() == newBand.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String bandTable = "INSERT INTO bands(name) VALUES (:name)";
      this.id = (int) con.createQuery(bandTable, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String bandTable = "SELECT * FROM bands where id=:id";
      Band band = con.createQuery(bandTable)
      .addParameter("id", id)
      .executeAndFetchFirst(Band.class);
      return band;
    }
  }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM bands WHERE id = :id;";
      con.createQuery(deleteQuery)
      .addParameter("id", this.getId())
      .executeUpdate();

      String joinDeleteQuery = "DELETE FROM bands_venues WHERE band_id = :bandId";
      con.createQuery(joinDeleteQuery)
      .addParameter("bandId", this.getId())
      .executeUpdate();
    }
  }

  public void update(String newBand) {
    try(Connection con = DB.sql2o.open()) {
      String bandTable = "UPDATE bands SET name = :name WHERE id = :id";
      con.createQuery(bandTable)
      .addParameter("name", newBand)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }
  public void addVenue(Venue venue) {
    try(Connection con = DB.sql2o.open()) {
      String bandsVenuesJointTable = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(bandsVenuesJointTable)
        .addParameter("band_id", this.getId())
        .addParameter("venue_id", venue.getId())
        .executeUpdate();
    }
  }

  public List<Venue> getVenues() {
    try(Connection con = DB.sql2o.open()){
      String bandsVenuesJointTable = "SELECT venue_id FROM bands_venues WHERE band_id = :band_id";
      List<Integer> venueIds = con.createQuery(bandsVenuesJointTable)
        .addParameter("band_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Venue> venues = new ArrayList<Venue>();

      for (Integer venueId : venueIds) {
        String venueTable = "SELECT * FROM venues WHERE id = :venueId";
        Venue venue = con.createQuery(venueTable)
          .addParameter("venueId", venueId)
          .executeAndFetchFirst(Venue.class);
        venues.add(venue);
      }
      return venues;
    }
  }

}
