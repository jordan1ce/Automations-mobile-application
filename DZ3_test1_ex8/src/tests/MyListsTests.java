package tests;

import lib.CoreTestCase;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.ArticlePageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveTwoArticlesToMyList(){

        //Сохраняем первую статью
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Java is the best";
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.closedArticle();

        //Сохраняем вторую стать
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (software platform)");
        ArticlePageObject.waitForTitleElement();
        String second_article_title2 = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addSecondArticleToMyList(name_of_folder);
        ArticlePageObject.closedArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        //Переходим в избранное (My List)
        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(first_article_title);
        MyListPageObject.waitForSearchResult(second_article_title2);
        MyListPageObject.waitForArticleLeftToDisappearByTitle(first_article_title);
    }
}

