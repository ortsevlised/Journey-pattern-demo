package actions;

import jline.internal.Log;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;

public class EnterValueIntoSpecialInputBox implements Task {

    private final Target list = Target.the("the options in the dropdown list").locatedBy("{0} div[id^='ui-select-choices']");
    private Target inputBox;
    private String value;
    private Target div;

    public EnterValueIntoSpecialInputBox(String div, String value) {
        this.div = Target.the("the div containing the input box").locatedBy(div);
        this.inputBox = Target.the("the input box").locatedBy(div + " input[ng-model='$select.search']");
        this.value = value;
    }

    @Step("{0} enters the #value:")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(div),
            Enter.theValue(value).into(inputBox)
        );
        try {
            list.of(div.getCssOrXPathSelector()).resolveFor(actor).waitUntilClickable();
        }catch (org.openqa.selenium.NoSuchElementException e){
            Log.info("This contact name didn't bring any result");
        }
        Hit.the(Keys.ENTER).into(inputBox).performAs(actor);
    }
}
