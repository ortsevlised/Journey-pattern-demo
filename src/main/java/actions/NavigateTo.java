package actions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;

import java.util.Optional;

/**
 * Created by jorge on 15/07/2016.
 */
public class NavigateTo implements Performable {

    private static final String CIVI_HR_URL = Optional.ofNullable(System.getenv("CIVI_HR_URL")).orElse("http://civihr-staging.civihrhosting.co.uk");
    private final String url;

    public NavigateTo(String url) {
        this.url = url;
    }


    public static NavigateTo civiHrUrl() {
        return Instrumented.instanceOf(NavigateTo.class).withProperties(CIVI_HR_URL);
    }

    @Step("{0} open the #url")
    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).openAt(CIVI_HR_URL);
    }
}


