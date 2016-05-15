import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

public class App {
  public static void main (String[] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

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

    // VENUE SEARCH BY BAND NAME
    get("/venues/search", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      if (request.queryParams().size() > 0){
        int bandSelectedId = Integer.parseInt(request.queryParams("bandSearch_id"));
        Band bandSearched = Band.find(bandSelectedId);
        List<Venue> listOfVenuesByBandName = bandSearched.getVenues();

        model.put("venues", listOfVenuesByBandName);
      }
      model.put("allBands", Band.all());
      model.put("template", "templates/venue-search.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // VENUE UPDATE
    get("/venues/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Venue venue = Venue.find(Integer.parseInt(request.params(":id")));
      model.put("venue", venue);
      model.put("template", "templates/venue-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // VENUE UPDATE - FORM
    post("/venues/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Venue oldVenue = Venue.find(Integer.parseInt(request.params(":id")));
      String newVenue = request.queryParams("venue-update");
      oldVenue.update(newVenue);
      response.redirect("/venues");
      return null;
    });

    // VENUE DELETE
    get("/venues/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Venue venue = Venue.find(Integer.parseInt(request.params(":id")));
      venue.delete();
      response.redirect("/venues");
      return null;
    });

    // BAND UPDATE
    get("/bands/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      model.put("band", band);
      model.put("template", "templates/band-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // BAND UPDATE - FORM
    post("/bands/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band oldBand = Band.find(Integer.parseInt(request.params(":id")));
      String newBand = request.queryParams("band-update");
      oldBand.update(newBand);
      response.redirect("/bands");
      return null;
    });

    // BAND DELETE
    get("/bands/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      band.delete();
      response.redirect("/bands");
      return null;
    });
  }
}
