package actions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

import static actions.WaitForLoading.waitForLoading;

public class GoTo implements Performable {

    private Target clickTarget;
    Target spinner = Target.the("the loading spinner").locatedBy(".ct-spinner.ct-spinner-img");

    public GoTo(String task) {
        clickTarget = Target.the(task + " link").located(By.linkText(task));
    }

    public static Performable task(String task) {
        return Instrumented.instanceOf(GoTo.class).withProperties(task);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(clickTarget),
                waitForLoading());
    }
}