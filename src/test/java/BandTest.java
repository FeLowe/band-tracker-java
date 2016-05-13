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
 //
 //  @Test
 //  public void getName_authorInstantiatesWithName_String() {
 //    Author myAuthor = new Author("Household chores");
 //    assertEquals("Household chores", myAuthor.getName());
 //  }
 //
 //  @Test
 //  public void all_emptyAtFirst() {
 //    assertEquals(Author.all().size(), 0);
 //  }
 //
 //   @Test
 //  public void equals_returnsTrueIfNamesAretheSame() {
 //    Author firstAuthor = new Author("Household chores");
 //    Author secondAuthor = new Author("Household chores");
 //    assertTrue(firstAuthor.equals(secondAuthor));
 //  }
 //
 //  @Test
 //  public void save_savesIntoDatabase_true() {
 //    Author myAuthor = new Author("Household chores");
 //    myAuthor.save();
 //    assertTrue(Author.all().get(0).equals(myAuthor));
 //  }
 //
 //  @Test
 //   public void save_assignsIdToObject() {
 //     Author myAuthor = new Author("Household chores");
 //     myAuthor.save();
 //     Author savedAuthor = Author.all().get(0);
 //     assertEquals(myAuthor.getId(), savedAuthor.getId());
 //   }
 //
 // @Test
 //  public void find_findAuthorInDatabase_true() {
 //    Author myAuthor = new Author("Household chores");
 //    myAuthor.save();
 //    Author savedAuthor = Author.find(myAuthor.getId());
 //    assertTrue(myAuthor.equals(savedAuthor));
 //  }
 //
 //  @Test
 //  public void addBook_addsBookToAuthor_true() {
 //    Author myAuthor = new Author("Household chores");
 //    myAuthor.save();
 //    Book myBook = new Book("Mow the lawn");
 //    myBook.save();
 //    myAuthor.addBook(myBook);
 //    Book savedBook = myAuthor.getBooks().get(0);
 //    assertTrue(myBook.equals(savedBook));
 //  }
 //
 //  @Test
 //  public void getBooks_returnsAllBooks_List() {
 //    Author myAuthor = new Author("Household chores");
 //    myAuthor.save();
 //    Book myBook = new Book("Mow the lawn");
 //    myBook.save();
 //    myAuthor.addBook(myBook);
 //    List savedBooks = myAuthor.getBooks();
 //    assertEquals(1, savedBooks.size());
 //  }
 //
 //  @Test
 //    public void update_updatesBandName_true() {
 //      Band newBand = new Band("U2");
 //      newBand.save();
 //      newBand.update("Pearl Jam");
 //      assertEquals("Pearl Jam", Band.find(newBand.getId()).getName());
 //    }
 //
 //    @Test
 //    public void delete_deletesBand_true() {
 //      Band newBand = new Band("U2");
 //      newBand.save();;
 //      int newBandId = newBand.getId();
 //      newBand.delete();
 //      assertEquals(null, Band.find(newBandId));
 //    }
}
