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
  public void equals_returnsTrueIfNamesAretheSame_true() {
    Band inputBand = new Band("U2");
    Band bandInDatabase = new Band("U2");
    assertTrue(inputBand.equals(bandInDatabase));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Band newBand = new Band("U2");
    newBand.save();
    assertTrue(Band.all().get(0).equals(newBand));
  }

  @Test
  public void save_assignsIdToObject() {
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
    // @Test
    // public void addBand_addsBandToVenue() {
    //   Band newBand = new Band("U2");
    //   newBand.save();
    //   Venue newVenue = new Venue("Soldier Field");
    //   newVenue.save();
    //   newVenue.addBand(newBand);
    //   Band savedBand = newVenue.getBands().get(0);
    //   assertTrue(newBand.equals(savedBand));
    // }

  //  @Test
  //  public void getBands_returnsAllBands_List() {
  //    Author myAuthor = new Author("Household chores");
  //    myAuthor.save();
  //    Book myBook = new Book("Mow the lawn");
  //    myBook.save();
  //    myAuthor.addBook(myBook);
  //    List savedBooks = myAuthor.getBooks();
  //    assertEquals(1, savedBooks.size());
  //  }

}
