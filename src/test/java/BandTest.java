import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiatesCorrectly_true() {
    Band newBand = new Band("U2");
    assertEquals(true, newBand instanceof Band);
  }

  @Test
  public void getName_bandInstantiatesWithName_String() {
    Band newBand = new Band("U2");
    assertEquals("U2", newBand.getName());
  }
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBandNamesAretheSame_true() {
    Band inputBand = new Band("U2");
    Band bandInDatabase = new Band("U2");
    assertTrue(inputBand.equals(bandInDatabase));
  }

  @Test
  public void save_savesBandIntoDatabase_true() {
    Band newBand = new Band("U2");
    newBand.save();
    assertTrue(Band.all().get(0).equals(newBand));
  }

  @Test
  public void save_assignsIdToBand() {
    Band newBand = new Band("U2");
    newBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(newBand.getId(), savedBand.getId());
  }

  @Test
  public void find_findBandInDatabase_true() {
    Band newBand = new Band("U2");
    newBand.save();
    Band savedBand = Band.find(newBand.getId());
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void delete_deletesBand_true() {
    Band newBand = new Band("U2");
    newBand.save();;
    int newBandId = newBand.getId();
    newBand.delete();
    assertEquals(null, Band.find(newBandId));
  }

  @Test
  public void update_updatesBandName_true() {
    Band newBand = new Band("U2");
    newBand.save();
    newBand.update("Pearl Jam");
    assertEquals("Pearl Jam", Band.find(newBand.getId()).getName());
  }

  @Test
  public void addVenue_addsVenueToBand_true() {
    Band existingBand = new Band("U2");
    existingBand.save();
    Venue existingVenue = new Venue("Soldier Field");
    existingVenue.save();
    existingBand.addVenue(existingVenue);
    Venue savedVenue = existingBand.getVenues().get(0);
    assertTrue(existingVenue.equals(savedVenue));
  }

  @Test
  public void getVenues_returnsAllVenues_List() {
    Band existingBand = new Band("U2");
    existingBand.save();
    Venue existingVenue = new Venue("Soldier Field");
    existingVenue.save();
    existingBand.addVenue(existingVenue);
    List savedVenues = existingBand.getVenues();
    assertEquals(1, savedVenues.size());
  }

}
