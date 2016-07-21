package actions;

import models.DocumentDetails;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EditDocument implements Task {

    public static  Target toggleBtn = Target.the("the toggle button").locatedBy(".ct-context-menu-toggle");
    public static  Target editBtn = Target.the("the edit button").locatedBy(".dropdown.open .fa-pencil");
    private final DocumentDetails documentDetails;

    public EditDocument(DocumentDetails documentDetails) {
        this.documentDetails = documentDetails;
    }

    public static EditDocument withThisNewDocumentDetails(DocumentDetails documentDetails) {
        return instrumented(EditDocument.class, documentDetails);
    }

    @Step("{0} edits document form")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(toggleBtn),
                Click.on(editBtn),
                FillUpDocumentForm.with(documentDetails)
        );
    }
}
