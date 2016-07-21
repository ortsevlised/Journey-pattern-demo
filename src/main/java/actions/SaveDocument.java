package actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static actions.WaitForLoading.waitForLoading;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SaveDocument implements Task {

    public static final Target saveAndNewBtn = Target.the("the save and new button").locatedBy("[ng-click^='openNew = true']");
    public static final Target saveBtn = Target.the("the save button").locatedBy(".modal-footer .btn-primary");

    Target button;

    public SaveDocument(Target button) {
        this.button = button;
    }

    public static SaveDocument usingSaveButton() {
        return instrumented(SaveDocument.class, saveBtn);
    }

    public static SaveDocument usingSaveAndNewButton() {
        return instrumented(SaveDocument.class, saveAndNewBtn);
    }

    @Step("{0} saves the document")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(button),
                waitForLoading()
        );
    }
}
