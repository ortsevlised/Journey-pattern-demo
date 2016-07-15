package actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.targets.Target;

import static java.util.concurrent.TimeUnit.SECONDS;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Created by jorge on 15/07/2016.
 */

public class WaitForLoading implements Performable {
    Target spinner = Target.the("the loading spinner").locatedBy(".ct-spinner-img");

    public static WaitForLoading waitForLoading() {
        return instrumented(WaitForLoading.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        spinner.resolveFor(actor).withTimeoutOf(15, SECONDS).waitUntilNotVisible();
    }
}