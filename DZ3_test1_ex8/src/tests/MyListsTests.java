package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.ArticlePageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Java is the best";

    @Test
    public void testSaveTwoArticlesToMyList(){

        //Сохраняем первую статью
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElementFirstArticle();
        String first_article_title = ArticlePageObject.getFirstArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);

        } else{
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closedArticle();

        //Сохраняем вторую стать
        SearchPageObject.initSearchInput();
        SearchPageObject.clickByArticleWithSubstring("Java (software platform)");
        ArticlePageObject.waitForTitleElementSecondArticle();
        String second_article_title = ArticlePageObject.getSecondArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);

        } else{
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closedArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        //Переходим в избранное (My List)
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }
        if(Platform.getInstance().isIos()){
            MyListPageObject.clickAuthCloseButton();
        }

        MyListPageObject.swipeByArticleToDelete(first_article_title);
        // Написал метод, котрый считает количество элементов по типу = XCUIElementTypeCell. Т.е один элеиент это одна сохроненная статья.
        // Если кол-вл статей = 1, то все ок, считаем, что вторая удалилась.
        MyListPageObject.countOfArticles();
       // MyListPageObject.waitForSearchResult(second_article_title);
        MyListPageObject.waitForArticleLeftToDisappearByTitle(first_article_title);
    }
}


