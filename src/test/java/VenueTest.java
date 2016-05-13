import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Venue_instantiatesCorrectly_true(){
    Venue newVenue = new Venue("Soldier Field");
    assertEquals(true, newVenue instanceof Venue);
  }

  @Test
  public void getName_venueInstantiatesWithName_String(){
    Venue newVenue = new Venue("Soldier Field");
    assertEquals("Soldier Field", newVenue.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfVenueNamesAreTheSame_true() {
    Venue inputVenue = new Venue("Soldier Field");
    Venue venueInDatabase = new Venue("Soldier Field");
    assertTrue(inputVenue.equals(venueInDatabase));
  }

  @Test
  public void save_savesVenueIntoDatabase_true() {
    Venue newVenue = new Venue("Soldier Field");
    newVenue.save();
    assertTrue(Venue.all().get(0).equals(newVenue));
  }

  @Test
  public void save_assignsIdToObject() {
    Venue newVenue = new Venue("Soldier Field");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(newVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_findsVenuesInDatabase_True() {
    Venue newVenue = new Venue("Soldier Field");
    newVenue.save();
    Venue savedVenue = Venue.find(newVenue.getId());
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void delete_deletesVenue_true() {
    Venue newVenue = new Venue("Soldier Field");
    newVenue.save();
    int newVenueId = newVenue.getId();
    newVenue.delete();
    assertEquals(null, Venue.find(newVenueId));
  }
  @Test
  public void update_updatesVanueName_true() {
    Venue newVenue = new Venue("Soldier Field");
    newVenue.save();
    newVenue.update("United Center");
    assertEquals("United Center", Venue.find(newVenue.getId()).getName());
  }

  @Test
  public void addBand_addsBandToVenue_true() {
    Venue existingVenue = new Venue("Soldier Field");
    existingVenue.save();
    Band existingBand = new Band("U2");
    existingBand.save();
    existingVenue.addBand(existingBand);
    Band savedBand = existingVenue.getBands().get(0);
    assertTrue(existingBand.equals(savedBand));
  }

  @Test
  public void getBands_returnsAllBands_List() {
    Band existingBand = new Band("U2");
    existingBand.save();
    Venue existingVenue = new Venue("Soldier Field");
    existingVenue.save();
    existingVenue.addBand(existingBand);
    List savedBands = existingVenue.getBands();
    assertEquals(1, savedBands.size());
  }


}
