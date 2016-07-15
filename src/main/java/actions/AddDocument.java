package actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static actions.WaitForLoading.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Created by jorge on 15/07/2016.
 */
public class AddDocument implements Task {

    public static final Target addDocumentBtn = Target.the("the add document button").located(By.partialLinkText("Add Document"));
    public static final Target selectDocumentType = Target.the("the document type select").locatedBy("[ng-model='document.activity_type_id']");
    public static final Target selectDueDate = Target.the("the document due date").locatedBy("#ct-document-due");
    public static final Target selectExpDate = Target.the("the document expiry date").locatedBy("#ct-document-exp");
    public static final Target documentFile = Target.the("the place where to upload the document").locatedBy("#ct-document-files");
    public static final Target addAssigneeLink = Target.the("the add assignee link").located(By.linkText("Add Assignee"));
    public static final Target addToAssignmentLink = Target.the("the place where to upload the document").located(By.linkText("Add To Assignment"));
    public static final Target showMoreBtn = Target.the("the place where to upload the document").locatedBy("//span[contains(text(),' Show more')]");
    public static final Target documentaDetails = Target.the("the document details text area").locatedBy("[id^=taTextElement");
    public static final Target selectStatus = Target.the("the status select").locatedBy("#document.status_id");
    public static final Target cancelBtn = Target.the("the cancel button").locatedBy("[ng-click='cancel()']");
    public static final Target saveAndNewBtn = Target.the("the save and new button").locatedBy("[ng-click='openNew = true']");
    public static final Target saveBtn = Target.the("the save button").locatedBy(".modal-footer .btn-primary");
    public static final String VALID_CONTACT_ID = "Dr. Jina Blackwell";
    public static final String VALID_ASSIGNEE = "Dr. Jina Blackwell";

    private final String contactId = "[ng-model='document.target_contact_id[0]']";
    private final String assignee = "[ng-show^='document.assignee_contact_id']";
    List<String> documentTypes = Arrays.asList("Joining Document 1", "Joining Document 2", "Joining Document 3", "Exiting document 1", "Exiting document 2", "Exiting document 3");


    public static Performable addDocument() {
        return instrumented(AddDocument.class);
    }

    @Step("{0} adds a document")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                CloseNotification.ifOpen(),
                Click.on(addDocumentBtn),
                Enters.theValue(VALID_CONTACT_ID).into(contactId)
        );

        actor.attemptsTo(
                SelectFromOptions.byValue(documentTypes.get(new Random().nextInt(documentTypes.size()))).from(selectDocumentType),
                Enter.theValue("11/02/2016").into(selectDueDate),
                Click.on(addAssigneeLink),
                Enters.theValue(VALID_ASSIGNEE).into(assignee));

        actor.attemptsTo(
                Click.on(saveBtn),
                waitForLoading()
        );
    }
}
