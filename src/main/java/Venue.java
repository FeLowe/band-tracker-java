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

//   public void addAuthor(Author author) {
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "INSERT INTO authors_books (author_id, book_id) VALUES (:author_id, :book_id)";
//     con.createQuery(sql)
//       .addParameter("author_id", author.getId())
//       .addParameter("book_id", this.getId())
//       .executeUpdate();
//   }
// }
//
//   public List<Author> getAuthors() {
//     try(Connection con = DB.sql2o.open()){
//       String joinQuery = "SELECT author_id FROM authors_books WHERE book_id = :book_id";
//       List<Integer> authorIds = con.createQuery(joinQuery)
//         .addParameter("book_id", this.getId())
//         .executeAndFetch(Integer.class);
//
//       List<Author> authors = new ArrayList<Author>();
//
//       for (Integer authorId : authorIds) {
//         String bookQuery = "Select * From authors WHERE id = :authorId";
//         Author author = con.createQuery(bookQuery)
//           .addParameter("authorId", authorId)
//           .executeAndFetchFirst(Author.class);
//         authors.add(author);
//       }
//       return authors;
//     }
//   }

}
