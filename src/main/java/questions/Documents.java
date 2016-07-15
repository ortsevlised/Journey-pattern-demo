package questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

public class Documents implements Question<Boolean> {

    private Target container = Target.the("the container div").locatedBy(".ct-documents-container");
    private Target tr = Target.the("the container div").locatedBy(".ct-documents-container tbody tr");


    private String[] documents   ;
    public Documents(String... documents) {
        this.documents = documents;
    }

    public static Documents isOnScreen(String errorMessage) {
        return new Documents(errorMessage);
    }

    public static Documents areOnScreen(String... errorMessage) {
        return new Documents(errorMessage);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<WebElementFacade> rows = tr.resolveAllFor(actor);
        boolean found = false;

        for (String document : documents) {
            found = false;
            for (WebElementFacade row : rows) {
                if (row.getText().equals(document)) {
                    found = true;
                    break;
                }
            }
            if (found == false) {
                break;
            }
        }
        return found;
    }
}