import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

import java.util.ArrayList;

public class App {
  public static void main (String[] args){
//     staticFileLocation("/public");
//     String layout = "templates/layout.vtl";
//
//     get("/", (request, response) -> {
//     Map<String, Object> model = new HashMap<String, Object>();
//     model.put("template", "templates/index.vtl");
//     return new ModelAndView(model, layout);
//   }, new VelocityTemplateEngine());
//
//     get("/authors", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       model.put("authors", Author.all());
//       model.put("template", "templates/authors.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     get("/books", (request, response) -> {
//         HashMap<String, Object> model = new HashMap<String, Object>();
//         model.put("books", Book.all());
//         model.put("template", "templates/books.vtl");
//         return new ModelAndView(model, layout);
//       }, new VelocityTemplateEngine());
//
//     post("/books", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       String name = request.queryParams("name");
//       Book newBook = new Book(name);
//       newBook.save();
//       response.redirect("/books");
//       return null;
//     });
//
//     post("/authors", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       String name = request.queryParams("name");
//       Author newAuthor = new Author(name);
//       newAuthor.save(); // ***ADDED FOR DB VERSION***
//       response.redirect("/authors");
//       return null;
//     });
//
 // SHOW SEARCH BOOKS FORM
//      get("/books/search", (request, response) -> {
//      HashMap<String, Object> model = new HashMap<String, Object>();
//
//      model.put("allAuthors", Author.all());
//      model.put("template", "templates/book-search.vtl");
//        return new ModelAndView(model, layout);
//      }, new VelocityTemplateEngine());
//
// PROCESSES SEARCH FORM
//      post("/books/search", (request, response) -> {
//        HashMap<String, Object> model = new HashMap<String, Object>();
//       // List<String> searchedAuthorId = new ArrayList<String>();
//        String searchedByAuthorName = request.queryParams("book-search");
//        int authorIdThatBeingSearched = Integer.parseInt(request.queryParams("author_id"));
//       Author authorSearched = Author.getBooks.(Author.find(authorIdThatBeingSearched));
//        System.out.println(authorSearched);
//        //authorSearched.getBooks();
//        model.put("books", searchedAuthorId);
//        response.redirect("/books/search");
//        return null;
//      });
//
//     get("/books/:id", (request,response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       int book_id = Integer.parseInt(request.params(":id"));
//       Book book = Book.find(book_id);
//       model.put("book", book);
//       model.put("allAuthors", Author.all());
//       model.put("template", "templates/book.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     get("/authors/:id", (request,response) ->{
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       Author author = Author.find(Integer.parseInt(request.params(":id")));
//       model.put("author", author);
//       model.put("allBooks", Book.all());
//       model.put("template", "templates/author.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     post("/add_books", (request, response) -> {
//       int bookId = Integer.parseInt(request.queryParams("book_id"));
//       int authorId = Integer.parseInt(request.queryParams("author_id"));
//       Author author = Author.find(authorId);
//       Book book = Book.find(bookId);
//       author.addBook(book);
//       response.redirect("/authors/" + authorId);
//       return null;
//     });
//
//     post("/add_authors", (request, response) -> {
//       int bookId = Integer.parseInt(request.queryParams("book_id"));
//       int authorId = Integer.parseInt(request.queryParams("author_id"));
//       Author author = Author.find(authorId);
//       Book book = Book.find(bookId);
//       book.addAuthor(author);
//       response.redirect("/books/" + bookId);
//       return null;
//     });
// SHOW UPDATE BOOKS FORM - CLICK ON "a tag(href)"
//     get("/books/:id/edit", (request, response) -> {
//     HashMap<String, Object> model = new HashMap<String, Object>();
//       Book book = Book.find(Integer.parseInt(request.params(":id")));
//       model.put("book", book);
//       model.put("template", "templates/book-update.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//     // PROCESSES UPDATE BOOKS FORM
//     post("/books/:id/edit", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       Book oldBook = Book.find(Integer.parseInt(request.params(":id")));
//       String newBook = request.queryParams("book-update");
//       oldBook.update(newBook);
//       response.redirect("/books");
//       return null;
//     });
//
// DO THE DELETE BOOK ACTION
//     get("/books/:id/delete", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       Book book = Book.find(Integer.parseInt(request.params(":id")));
//       book.delete();
//       response.redirect("/books");
//       return null;
//     });
//
// SHOW UPDATE AUTHORS FORM - CLICK ON "a tag(href)"
//        get("/authors/:id/edit", (request, response) -> {
//        HashMap<String, Object> model = new HashMap<String, Object>();
//          Author author = Author.find(Integer.parseInt(request.params(":id")));
//          model.put("author", author);
//          model.put("template", "templates/author-update.vtl");
//          return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());
//
 // PROCESSES UPDATE AUTHORS FORM
//        post("/authors/:id/edit", (request, response) -> {
//          HashMap<String, Object> model = new HashMap<String, Object>();
//          Author oldAuthor = Author.find(Integer.parseInt(request.params(":id")));
//          String newAuthor = request.queryParams("author-update");
//          oldAuthor.update(newAuthor);
//          response.redirect("/authors");
//          return null;
//        });
//
// DO THE DELETE AUTHOR ACTION
//        get("/authors/:id/delete", (request, response) -> {
//          HashMap<String, Object> model = new HashMap<String, Object>();
//          Author author = Author.find(Integer.parseInt(request.params(":id")));
//          author.delete();
//          response.redirect("/authors");
//          return null;
//        });
  }
}
