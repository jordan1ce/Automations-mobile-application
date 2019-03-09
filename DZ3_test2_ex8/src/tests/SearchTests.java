package tests;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
public class SearchTests extends CoreTestCase {

    @Test
    public void testPresentOfTitle(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        SearchPageObject.NotFindTitle();
    }
}


