package actions;

import models.DocumentDetails;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import java.time.LocalDate;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FillUpDocumentForm implements Performable {

    public static final Target addDocumentBtn = Target.the("the add document button").located(By.partialLinkText("Add Document"));
    public static final Target selectDocumentType = Target.the("the document type option").locatedBy("[ng-model='document.activity_type_id']");
    public static final Target selectDueDate = Target.the("the document due date").locatedBy("#ct-document-due");
    public static final Target selectExpDate = Target.the("the document expiry date").locatedBy("#ct-document-exp");
    public static final Target documentFile = Target.the("the place where to upload the document").locatedBy("#ct-document-files");
    public static final Target addAssigneeLink = Target.the("the add assignee link").located(By.linkText("Add Assignee"));
    public static final Target addToAssignmentLink = Target.the("the place where to upload the document").located(By.linkText("Add To Assignment"));
    public static final Target showMoreBtn = Target.the("the place where to upload the document").locatedBy("//span[contains(text(),' Show more')]");
    public static final Target documentaDetails = Target.the("the document details text area").locatedBy("[id^=taTextElement");
    public static final Target cancelBtn = Target.the("the cancel button").locatedBy("[ng-click='cancel()']");
    public static final Target saveAndNewBtn = Target.the("the save and new button").locatedBy("[ng-click='openNew = true']");
    public static final Target saveBtn = Target.the("the save button").locatedBy(".modal-footer .btn-primary");
    public static final String VALID_CONTACT_ID = "Dr. Jina Blackwell";
    public static final String VALID_ASSIGNEE = "Dr. Jina Blackwell";
    public static final String ANOTHER_VALID_CONTACT_ID = "testing thing";
    public static final String ANOTHER_VALID_ASSIGNEE = "testing thing";
    private final String contactId = "[ng-model='document.target_contact_id[0]']";
    private static final String assignee = "[ng-show^='document.assignee_contact_id']";
    private final String assignment = "[ng-show='showAssignmentField']";
    private final String selectStatus = "[ng-show^='document.status_id']";
    public static final Target assigneeInput = Target.the("the assignee input box").locatedBy(assignee);
    private final DocumentDetails document;

    public FillUpDocumentForm(DocumentDetails document) {
        this.document = document;
    }

    public static FillUpDocumentForm with(DocumentDetails document) {
        return instrumented(FillUpDocumentForm.class, document);
    }

    @Step("{0} fills up document form")
    @Override
    public <T extends Actor> void performAs(T actor) {
        LocalDate.now();

        if (document.getContactId() != null) {
            Enters.theValue(document.getContactId()).into(contactId).performAs(actor);
        }
        if (document.getDocumentType() != null) {
            SelectFromOptions.byValue(document.getDocumentType()).from(selectDocumentType).performAs(actor);
        }
        if (document.getDueDate() != null) {
            Enter.theValue(document.getDueDate()).into(selectDueDate).performAs(actor);
        }
        if (document.getAssignee() != null) {
            if (assigneeInput.resolveFor(actor).getAttribute("class").contains("hide")) {
                Click.on(addAssigneeLink).performAs(actor);
            }
            Enters.theValue(document.getAssignee()).into(assignee).performAs(actor);
        }

        Click.on(showMoreBtn).performAs(actor);

        if (document.getExpDate() != null) {
            Enter.theValue(document.getExpDate()).into(selectExpDate).performAs(actor);
        }

        if (document.getAssignment() != null) {
            actor.attemptsTo(
                    Click.on(addToAssignmentLink),
                    Enters.theValue(document.getAssignment()).into(assignment));
        }

        if (document.getStatus() != null) {
            Enters.theValue(document.getStatus()).into(selectStatus).performAs(actor);
        }

        if (document.getDetails() != null) {
            Enter.theValue(document.getDetails()).into(documentaDetails).performAs(actor);
        }

    }
}
