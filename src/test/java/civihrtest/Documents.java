package civihrtest;

import actions.AddDocument;
import actions.GoTo;
import actions.Login;
import actions.NavigateTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;

/**
 * Created by jorge on 15/07/2016.
 */

@RunWith(SerenityRunner.class)

public class Documents {
    @Managed
    private WebDriver theBrowser;
    private Actor theUser = Actor.named("Civi Admin");
    private String ADMIN_USERNAME = "civihr_admin";
    private String ADMIN_PASSWORD = "civihr_admin";

    @Before
    public void authorCanBrowseTheWeb() throws Exception {
        theUser.can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    public void adding_and_removing_author_testing_save() throws Exception {
        givenThat(theUser).wasAbleTo(
                NavigateTo.civiHrUrl(),
                Login.with(ADMIN_USERNAME, ADMIN_PASSWORD),
                GoTo.task("Documents"),
                AddDocument.addDocument());
    }
}

