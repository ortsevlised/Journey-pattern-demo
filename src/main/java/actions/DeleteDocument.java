package actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import questions.Document;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteDocument implements Task {

    private Target confirmBtn = Target.the("the confirm button").locatedBy("[ng-click='confirm()']");
    private Target selectAllBtn = Target.the("the select all button").locatedBy("[ng-change='toggleAll(pagination.currentPage);']");
    private Target applyBtn = Target.the("the apply action button").locatedBy("[ng-click^='apply(action.selected']");
    private final String action = "[ng-model='action.selected']";
    private final String contactId;

    public DeleteDocument(String contactId) {
        this.contactId = contactId;
    }

    public static Performable forContact(String contactId) {
        return instrumented(DeleteDocument.class, contactId);
    }

    @Step("{0} deletes documents for #contactId ")
    @Override
    public <T extends Actor> void performAs(T actor) {
        while (Document.tr.resolveAllFor(actor).size() != 0) {
            actor.attemptsTo(
                    SearchDocument.byContactId(contactId),
                    Click.on(selectAllBtn),
                    Enters.theValue("Delete").into(action),
                    Click.on(applyBtn),
                    Click.on(confirmBtn),
                    WaitForLoading.waitForLoading()
            );
        }
    }
}