package civihrtest;

import actions.CloseNotification;
import actions.GoTo;
import actions.Login;
import actions.NavigateTo;
import models.DocumentDetails;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import questions.Document;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static actions.AddDocument.*;
import static actions.DeleteDocument.deleteDocument;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.is;


@RunWith(SerenityRunner.class)

public class DocumentTests {
    @Managed
    private WebDriver theBrowser;
    DocumentDetails document;
    private final Actor theUser = Actor.named("Civi Admin");
    private final String ADMIN_USERNAME = "civihr_admin";
    private final String ADMIN_PASSWORD = "civihr_admin";
    private final List<String> documentTypes = Arrays.asList("Joining Document 1", "Joining Document 2", "Joining Document 3", "Exiting document 1", "Exiting document 2", "Exiting document 3");
    private final String documentType = documentTypes.get(new Random().nextInt(documentTypes.size()));
    private final LocalDate DUE_DATE = LocalDate.now();

    @Before
    public void the_user_logs_in_and_goes_to_documents() throws Exception {
        document = new DocumentDetails(VALID_CONTACT_ID, VALID_ASSIGNEE, DUE_DATE, documentType);
        theUser.can(BrowseTheWeb.with(theBrowser));
        givenThat(theUser).wasAbleTo(
                NavigateTo.civiHrUrl(),
                Login.with(ADMIN_USERNAME, ADMIN_PASSWORD),
                GoTo.task("Documents"),
                CloseNotification.ifOpen(),
                deleteDocument(document));
    }

    @Test
    public void adding_a_new_document() throws Exception {
        when(theUser).attemptsTo(addDocument(document));
        then(theUser).should(seeThat(Document.isOnTheDocumentsList(document), is(true)));
    }
}

