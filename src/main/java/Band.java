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
  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
      String bandsVenuesTable = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(bandsVenuesTable)
        .addParameter("band_id", this.getId())
        .addParameter("venue_id", band.getId())
        .executeUpdate();
    }
  }

  // public List<Book> getBooks() {
  //   try(Connection con = DB.sql2o.open()){
  //     String joinQuery = "SELECT book_id FROM authors_books WHERE author_id = :author_id";
  //     List<Integer> bookIds = con.createQuery(joinQuery)
  //       .addParameter("author_id", this.getId())
  //       .executeAndFetch(Integer.class);
  //
  //     List<Book> books = new ArrayList<Book>();
  //
  //     for (Integer bookId : bookIds) {
  //       String bookQuery = "SELECT * FROM books WHERE id = :bookId";
  //       Book book = con.createQuery(bookQuery)
  //         .addParameter("bookId", bookId)
  //         .executeAndFetchFirst(Book.class);
  //       books.add(book);
  //     }
  //     return books;
  //   }
  // }

}
