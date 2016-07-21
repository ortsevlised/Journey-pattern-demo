package actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class SearchDocument implements Task {

    private Target filterDocumentLink = Target.the("the filter documents link").located(By.linkText("Filter Documents"));
    private static final String contactSearch = "[ng-model='filterParams.contactId']";
    private static final String assignmentTypeSearch = "[ng-model='filterParams.assignmentType']";
    private final String searchCriteria;
    private final String searchField;

    public SearchDocument(String searchCriteria, String searchField) {
        this.searchCriteria = searchCriteria;
        this.searchField = searchField;
    }

    public static SearchDocument byContactId(String contact) {
        return instrumented(SearchDocument.class, contact, contactSearch);
    }

    public static SearchDocument byAssigmentType(String assignmentType) {
        return instrumented(SearchDocument.class, assignmentType, assignmentTypeSearch);
    }

    @Step("{0} searches document by #searchCriteria")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(filterDocumentLink),
                Enters.theValue(searchCriteria).into(searchField));
    }
}

