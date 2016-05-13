import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
// import java.util.List;
// import java.util.ArrayList;

public class App {
  public static void main (String[] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
//
    get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    get("/bands", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/bands-list.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String bandInput = request.queryParams("name");
      Band newBand = new Band(bandInput);
      newBand.save();
      response.redirect("/bands");
      return null;
    });

    get("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("venues", Venue.all());
      model.put("template", "templates/venues-list.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String venueInput = request.queryParams("venue-name");
      Venue newVenue = new Venue(venueInput);
      newVenue.save();
      response.redirect("/venues");
      return null;
    });
      get("/bands/:id", (request,response) ->{
        HashMap<String, Object> model = new HashMap<String, Object>();
        Band band = Band.find(Integer.parseInt(request.params(":id")));
        model.put("band", band);
        model.put("allVenues", Venue.all());
        model.put("template", "templates/band-single.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/add_venues", (request, response) -> {
        int bandId = Integer.parseInt(request.queryParams("band_id"));
        String[] venueId = Integer.parseInt(request.queryParamsValues("venue"));
        Band band = Band.find(bandId);
        Venue venue = Venue.find(venueId);
        band.addVenue(venue);
        response.redirect("/bands/" + bandId);
        return null;
      });

      //     post("/add_authors", (request, response) -> {
      //       int bookId = Integer.parseInt(request.queryParams("book_id"));
      //       int authorId = Integer.parseInt(request.queryParams("author_id"));
      //       Author author = Author.find(authorId);
      //       Book book = Book.find(bookId);
      //       book.addAuthor(author);
      //       response.redirect("/books/" + bookId);
      //       return null;
      //     });

    // get("/venues/:id", (request,response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     int book_id = Integer.parseInt(request.params(":id"));
    //     Book book = Book.find(book_id);
    //     model.put("book", book);
    //     model.put("allAuthors", Author.all());
    //     model.put("template", "templates/book.vtl");
    //     return new ModelAndView(model, layout);
    //   }, new VelocityTemplateEngine());
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
//
//
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
