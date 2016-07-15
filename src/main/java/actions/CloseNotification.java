package actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CloseNotification implements Performable {
    private Target alert = Target.the("the alert covering main options").locatedBy("#crm-notification-container");
    private Target closeAlert = Target.the("the close alert button").locatedBy(".ui-notify-cross");

    public static Performable ifOpen() {
        return instrumented(CloseNotification.class);
    }

    @Step("if open {0} closes #alert")
    @Override
    public <T extends Actor> void performAs(T actor) {
        if (alert.resolveFor(actor).isDisplayed()) {
            actor.attemptsTo(Click.on(closeAlert));
            alert.resolveFor(actor).waitUntilNotVisible();
        }
    }

}
