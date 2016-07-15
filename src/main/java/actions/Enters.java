package actions;

import net.serenitybdd.screenplay.Performable;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Enters {

    private String value;

    public Enters(String value) {
        this.value = value;
    }

    public static Enters theValue(String value) {
        return new Enters(value);
    }

    public Performable into(String target) {
        return instrumented(EnterValueIntoSpecialInputBox.class, target, value);
    }

}
