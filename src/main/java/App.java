import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;

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
        int hiddenBandId = Integer.parseInt(request.queryParams("band_id"));
        Band band = Band.find(hiddenBandId );
        int venueIdFromUserSelection = Integer.parseInt(request.queryParams("venue"));
        Venue selectedVenue = Venue.find(venueIdFromUserSelection);
        band.addVenue(selectedVenue);
        // CHECKBOX
        // String [] userSelections = request.queryParamsValues("venue");
        // for (String eachSelection : userSelections){
        //   Venue selectedVenue = Venue.find(Integer.parseInt(eachSelection));
        //   band.addVenue(selectedVenue);
        //   System.out.println(selectedVenue);
        // }
        //
        response.redirect("/bands/" + hiddenBandId);
        return null;
      });

      get("/venues/:id", (request,response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          int venueId = Integer.parseInt(request.params(":id"));
          Venue venue = Venue.find(venueId);
          model.put("venue", venue);
          model.put("allBands", Band.all());
          model.put("template", "templates/venue-single.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

          post("/add_bands", (request, response) -> {
            int hiddenVenueId = Integer.parseInt(request.queryParams("venue_id"));
            Venue venue = Venue.find(hiddenVenueId);
            int bandId = Integer.parseInt(request.queryParams("band_id"));
            Band band = Band.find(bandId);
            venue.addBand(band);
            response.redirect("/venues/" + hiddenVenueId);
            return null;
          });

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
// SHOW UPDATE VENUES FORM - CLICK ON "a tag(href)"
    get("/venues/:id/edit", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
      Venue venue = Venue.find(Integer.parseInt(request.params(":id")));
      model.put("venue", venue);
      model.put("template", "templates/venue-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
//     // PROCESSES UPDATE VENUES FORM
    post("/venues/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Venue oldVenue = Venue.find(Integer.parseInt(request.params(":id")));
      String newVenue = request.queryParams("venue-update");
      oldVenue.update(newVenue);
      response.redirect("/venues");
      return null;
    });
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
// SHOW UPDATE BANDS FORM - CLICK ON "a tag(href)"
       get("/bands/:id/edit", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
         Band band = Band.find(Integer.parseInt(request.params(":id")));
         model.put("band", band);
         model.put("template", "templates/band-update.vtl");
         return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());

 // PROCESSES UPDATE BANDS FORM
       post("/bands/:id/edit", (request, response) -> {
         HashMap<String, Object> model = new HashMap<String, Object>();
         Band oldBand = Band.find(Integer.parseInt(request.params(":id")));
         String newBand = request.queryParams("band-update");
         oldBand.update(newBand);
         response.redirect("/bands");
         return null;
       });
//
// DO THE DELETE BAND ACTION
       get("/bands/:id/delete", (request, response) -> {
         HashMap<String, Object> model = new HashMap<String, Object>();
         Band band = Band.find(Integer.parseInt(request.params(":id")));
         band.delete();
         response.redirect("/bands");
         return null;
       });
  }
}
