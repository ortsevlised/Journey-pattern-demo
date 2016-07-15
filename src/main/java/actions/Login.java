package actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static actions.WaitForLoading.waitForLoading;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Created by jorge on 15/07/2016.
 */
public class Login implements Task {
    private Target usernameTxtField = Target.the("the username field").located(By.id("edit-name"));
    private Target passwordTxtField = Target.the("the password field").located(By.id("edit-pass"));
    private Target loginButton = Target.the("the submit button").located(By.id("edit-submit"));
    private static String username;
    private static String password;

    public static Login with(String username, String password) {
        return instrumented(Login.class, Login.username = username, Login.password = password);
    }

    @Step("{0} tries to log in as #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(usernameTxtField),
                Enter.theValue(password).into(passwordTxtField),
                Click.on(loginButton),
                waitForLoading()
        );
    }
}