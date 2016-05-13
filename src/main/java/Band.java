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

  // public String getName() {
  //   return name;
  // }
  //
  // public int getId() {
  //   return id;
  // }
  //
  // public static List<Author> all() {
  //   String sql = "SELECT id, name FROM authors";
  //   try(Connection con = DB.sql2o.open()) {
  //     return con.createQuery(sql).executeAndFetch(Author.class);
  //   }
  // }
  //
  // @Override
  // public boolean equals(Object otherAuthor) {
  //   if (!(otherAuthor instanceof Author)) {
  //     return false;
  //   } else {
  //     Author newAuthor = (Author) otherAuthor;
  //     return this.getName().equals(newAuthor.getName()) &&
  //            this.getId() == newAuthor.getId();
  //   }
  // }
  //
  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO authors(name) VALUES (:name)";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("name", this.name)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
  //
  // public static Author find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM authors where id=:id";
  //     Author author = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Author.class);
  //     return author;
  //   }
  // }

  // public void addBook(Book book) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO authors_books (author_id, book_id) VALUES (:author_id, :book_id)";
  //     con.createQuery(sql)
  //       .addParameter("author_id", this.getId())
  //       .addParameter("book_id", book.getId())
  //       .executeUpdate();
  //   }
  // }
  //
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

  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String deleteQuery = "DELETE FROM authors WHERE id = :id;";
  //       con.createQuery(deleteQuery)
  //         .addParameter("id", this.getId())
  //         .executeUpdate();
  //
  //     String joinDeleteQuery = "DELETE FROM authors_books WHERE author_id = :authorId";
  //       con.createQuery(joinDeleteQuery)
  //         .addParameter("authorId", this.getId())
  //         .executeUpdate();
  //     }
  //   }
  //   public void update(String newAuthor) {
  //     try(Connection con = DB.sql2o.open()) {
  //       String sql = "UPDATE authors SET name = :name WHERE id = :id";
  //       con.createQuery(sql)
  //         .addParameter("name", newAuthor)
  //         .addParameter("id", this.id)
  //         .executeUpdate();
  //     }
  //   }
}
