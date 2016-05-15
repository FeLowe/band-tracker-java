import org.sql2o.*; //for DB support
import org.junit.*; // for @Before and @After
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
    public DatabaseRule database = new DatabaseRule();

  @Test
    public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Band Tracker");

    }

  @Test
  public void bandIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Bands"));
    fill("#name").with("U2");
    submit(".btn");
    assertThat(pageSource()).contains("U2");
  }

   @Test
    public void venueIsCreatedTest() {
      goTo("http://localhost:4567/");
      click("a", withText("Venues"));
      fill("#venue-name").with("Soldier Field");
      submit("#btn-venue-submit");
      assertThat(pageSource()).contains("Soldier Field");
  }

  @Test
  public void BandDisplays() {
    Band existingBand = new Band("U2");
    existingBand.save();
    String url = String.format("http://localhost:4567/bands/%d", existingBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("U2");
  }

  // @Test
  // public void venueDisplays() {
  //   Venue existingVenue = new Venue("Soldier Field");
  //   existingVenue.save();
  //   String url = String.format("http://localhost:4567/venues/%d", existingVenue.getId());
  //   goTo(url);
  //   assertThat(pageSource()).contains("Soldier Field");
  // }

  @Test
  public void addVenueToBand() {
    Band existingBand = new Band("U2");
    existingBand.save();
    Venue existingVenue = new Venue("Soldier Field");
    existingVenue.save();

    String url = String.format("http://localhost:4567/bands/%d", existingBand.getId());
    goTo(url);

    fillSelect("#venue").withText("Soldier Field");
    submit("#btn-venue-submit");
    assertThat(pageSource()).contains("<li>");
    assertThat(pageSource()).contains("Soldier Field");
  }
  @Test
  public void updateBand() {
    Band existingBand = new Band("U2");
    existingBand.save();
    String bandPath = String.format("http://localhost:4567/bands/%d", existingBand.getId());
    goTo(bandPath);
    click("a", withText("Edit this band"));
    fill("#band-update").with("U3");
    submit("#btn-update-band");
    assertThat(pageSource()).doesNotContain("U2");
  }
  @Test
  public void deleteBand() {
    Band existingBand = new Band("U2");
    existingBand.save();
    String bandPath = String.format("http://localhost:4567/bands/%d", existingBand.getId());
    goTo(bandPath);
    click("a", withText("Delete this band"));
    String allBandssPath = String.format("http://localhost:4567/bands/");
    goTo(allBandssPath);
    assertThat(pageSource()).doesNotContain("U2");
  }

  @Test
  public void addBandToVenue() {
    Band existingBand = new Band("U2");
    existingBand .save();
    Venue existingVenue = new Venue("United Center");
    existingVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", existingVenue.getId());
    goTo(url);
    fillSelect("#band_id").withText("U2");
    submit("#btn-add-band");
    assertThat(pageSource()).contains("<li>");
    assertThat(pageSource()).contains("U2");
  }
  @Test
  public void updateVenue() {
    Venue existingVenue = new Venue("United Center");
    existingVenue.save();
    String venuePath = String.format("http://localhost:4567/venues/%d", existingVenue.getId());
    goTo(venuePath);
    click("a", withText("Edit this venue"));
    fill("#venue-update").with("Soldier Field");
    submit("#btn-update-venue");
    assertThat(pageSource()).doesNotContain("United Center");
  }

  // @Test
  // public void bookDelete() {
  //   Book myBook = new Book("Veronica Decides To Die");
  //   myBook.save();
  //   String bookPath = String.format("http://localhost:4567/books/%d", myBook.getId());
  //   goTo(bookPath);
  //   click("a", withText("Delete this book"));
  //   String allBooksPath = String.format("http://localhost:4567/books/");
  //   goTo(allBooksPath);
  //   assertThat(pageSource()).doesNotContain("Veronica Decides To Die");
  // }
  //
  //
  // @Test
  // public void bookSearchByAuthorName() {
  //   Author testAuthor = new Author("Paulo Coelho");
  //   testAuthor.save();
  //   Book testBook = new Book("The Alchemist");
  //   testBook.save();
  //   String url = String.format("http://localhost:4567/books/%d", testBook.getId());
  //   goTo(url);
  //   fillSelect("#author_id").withText("Paulo Coelho");
  //   submit(".btn");
  //   goTo("http://localhost:4567/");
  //   click("a", withText("Search for a book"));
  //   fill("#book-search").with("Paulo Coelho");
  //   submit("#search-button");
  //   assertThat(pageSource()).contains("The Alchemist");
  // }
}
