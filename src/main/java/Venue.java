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

//   public String getName() {
//     return name;
//   }
//
//   public int getId() {
//     return id;
//   }
//
//
//   public static List<Book> all() {
//     String sql = "SELECT id, name FROM books";
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery(sql).executeAndFetch(Book.class);
//     }
//   }
//
//   @Override
//   public boolean equals(Object otherBook) {
//     if (!(otherBook instanceof Book)) {
//       return false;
//     } else {
//       Book newBook =  (Book) otherBook;
//       return this.getName().equals(newBook.getName()) &&
//              this.getId() == newBook.getId();
//     }
//   }
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO books (name) VALUES (:name)";
//       this.id = (int) con.createQuery(sql, true)
//         .addParameter("name", this.name)
//         .executeUpdate()
//         .getKey();
//     }
//   }
//
//   public static Book find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM books WHERE id=:id";
//       Book book = con.createQuery(sql)
//         .addParameter("id", id)
//         .executeAndFetchFirst(Book.class);
//       return book;
//     }
//   }
//   public void update(String newBook) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "UPDATE books SET name = :name WHERE id = :id";
//       con.createQuery(sql)
//         .addParameter("name", newBook)
//         .addParameter("id", this.id)
//         .executeUpdate();
//     }
//   }
//
//   public void delete() {
//     try(Connection con = DB.sql2o.open()) {
//       String deleteQuery = "DELETE FROM books WHERE id = :id;";
//       con.createQuery(deleteQuery)
//         .addParameter("id", this.getId())
//         .executeUpdate();
//
//       String joinDeleteQuery = "DELETE FROM authors_books WHERE book_id = :bookId";
//         con.createQuery(joinDeleteQuery)
//           .addParameter("bookId", this.getId())
//           .executeUpdate();
//     }
//   }
//
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
//   public void delete() {
//     try(Connection con = DB.sql2o.open()) {
//       String deleteQuery = "DELETE FROM venues WHERE id = :id;";
//         con.createQuery(deleteQuery)
//           .addParameter("id", this.getId())
//           .executeUpdate();
//
//       String joinDeleteQuery = "DELETE FROM bands_venues WHERE venue_id = :venueId";
//         con.createQuery(joinDeleteQuery)
//           .addParameter("venueId", this.getId())
//           .executeUpdate();
//       }
//     }
//     public void update(String newVenue) {
//       try(Connection con = DB.sql2o.open()) {
//         String sql = "UPDATE venues SET name = :name WHERE id = :id";
//         con.createQuery(sql)
//           .addParameter("name", newVenue)
//           .addParameter("id", this.id)
//           .executeUpdate();
//       }
//     }
}
