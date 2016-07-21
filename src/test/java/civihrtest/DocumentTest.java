package civihrtest;

import actions.*;
import models.DocumentDetails;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import questions.Document;
import questions.DocumentList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static actions.FillUpDocumentForm.*;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;


@RunWith(SerenityRunner.class)

public class DocumentTest {
    @Managed
    private WebDriver theBrowser;
    DocumentDetails documentDetails;
    DocumentDetails anotherDocumentDetails;
    private final Actor theUser = Actor.named("Civi Admin");
    private final String ADMIN_USERNAME = "civihr_admin";
    private final String ADMIN_PASSWORD = "civihr_admin";
    private final List<String> documentTypes = Arrays.asList("Joining Document 1", "Joining Document 2", "Joining Document 3", "Exiting document 1", "Exiting document 2", "Exiting document 3");
    private final String documentType = documentTypes.get(new Random().nextInt(documentTypes.size()));
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String DUE_DATE_TODAY = LocalDate.now().format(formatter);
    private final String DUE_DATE_TOMORROW = LocalDate.now().plusDays(1).format(formatter);

    @Before
    public void the_user_logs_in_and_goes_to_documents() throws Exception {
        theUser.can(BrowseTheWeb.with(theBrowser));
        givenThat(theUser).wasAbleTo(
                NavigateTo.civiHrUrl(),
                Login.with(ADMIN_USERNAME, ADMIN_PASSWORD),
                GoTo.task("Documents"),
                CloseNotification.ifOpen(),
                DeleteDocument.forContact(VALID_CONTACT_ID));
    }

    @Test
    public void adding_a_new_document_using_only_mandatory_fields() throws Exception {
        documentDetails = new DocumentDetails(documentWithMandatoryFieldsOnly());

        when(theUser).attemptsTo(
                Click.on(addDocumentBtn),
                FillUpDocumentForm.with(documentDetails),
                SaveDocument.usingSaveButton()
        );
        then(theUser).should(
                seeThat(Document.isOnTheDocumentsList(documentDetails), is(true)),
                seeThat(DocumentList.size(), is(1))
        );
    }

    @Test
    public void adding_multiple_documents_using_save_and_new_functionality() throws Exception {
        documentDetails = new DocumentDetails(documentWithMandatoryFieldsOnly());
        anotherDocumentDetails = new DocumentDetails(anotherDocument());

        when(theUser).attemptsTo(
                Click.on(addDocumentBtn),
                FillUpDocumentForm.with(documentDetails),
                SaveDocument.usingSaveAndNewButton(),
                FillUpDocumentForm.with(anotherDocumentDetails),
                SaveDocument.usingSaveButton()
        );

        then(theUser).should(
                seeThat(Document.isOnTheDocumentsList(documentDetails), Matchers.is(true)),
                seeThat(Document.isOnTheDocumentsList(anotherDocumentDetails), Matchers.is(true)),
                seeThat(DocumentList.size(), is(2))
        );
    }

    @Test
    public void save_button_is_disabled_when_missing_mandatory_fields() throws Exception {
        documentDetails = new DocumentDetails(documentMissingMandatoryFields());

        when(theUser).attemptsTo(
                Click.on(addDocumentBtn),
                FillUpDocumentForm.with(documentDetails)
        );

        then(theUser).should(seeThat(WebElementQuestion.stateOf(saveBtn), not(isEnabled())));
    }


    private HashMap<String, String> documentMissingMandatoryFields() {
        HashMap<String, String> documentMissingMandatoryFields = new HashMap<>();
        documentMissingMandatoryFields.put("contactId", VALID_CONTACT_ID);
        documentMissingMandatoryFields.put("assignee", VALID_ASSIGNEE);
        documentMissingMandatoryFields.put("documentType", documentType);
        return documentMissingMandatoryFields;
    }


    private HashMap<String, String> documentWithMandatoryFieldsOnly() {
        HashMap<String, String> documentWithMandatoryFieldsOnly = new HashMap<>();
        documentWithMandatoryFieldsOnly.put("contactId", VALID_CONTACT_ID);
        documentWithMandatoryFieldsOnly.put("assignee", VALID_ASSIGNEE);
        documentWithMandatoryFieldsOnly.put("documentType", documentType);
        documentWithMandatoryFieldsOnly.put("dueDate", DUE_DATE_TODAY);
        documentWithMandatoryFieldsOnly.put("expDate", null);
        documentWithMandatoryFieldsOnly.put("assignment", null);
        documentWithMandatoryFieldsOnly.put("status", null);
        documentWithMandatoryFieldsOnly.put("details", null);
        documentWithMandatoryFieldsOnly.put("fileToUpload", null);
        return documentWithMandatoryFieldsOnly;
    }

    private HashMap<String, String> anotherDocument() {
        HashMap<String, String> anotherDocument = new HashMap<>();
        anotherDocument.put("contactId", VALID_CONTACT_ID);
        anotherDocument.put("assignee", VALID_ASSIGNEE);
        anotherDocument.put("documentType", documentType);
        anotherDocument.put("dueDate", DUE_DATE_TOMORROW);
        anotherDocument.put("expDate", null);
        anotherDocument.put("assignment", null);
        anotherDocument.put("status", null);
        anotherDocument.put("details", null);
        anotherDocument.put("fileToUpload", null);
        return anotherDocument;
    }
}
