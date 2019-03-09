package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testFindSeveralElements() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);

        //Считаю кол-во элементов на странице
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        //Определяю, если элементов несколько, выполняется условие отмены поиска и сам факт отмены
        if (amount_of_search_results > 1) {
            SearchPageObject.clickCancelSearch();
            SearchPageObject.waitForArticleToDisappear();
        }
        else fail("We see one or less element on the page.");
    }
}

