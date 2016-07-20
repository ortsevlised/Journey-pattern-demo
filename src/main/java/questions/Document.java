package questions;

import models.DocumentDetails;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

@Subject("the document was added to the document list")

public class Document implements Question<Boolean> {
    public static Target tr = Target.the("the container div").locatedBy(".ct-documents-container tbody tr");
    private Target nextButton = Target.the("the next page button").locatedBy(".pagination-next");

    private DocumentDetails document;

    public Document(DocumentDetails document) {
        this.document = document;
    }

    public static Document isOnTheDocumentsList(DocumentDetails document) {
        return new Document(document);
    }


    @Override
    public Boolean answeredBy(Actor actor) {
        String document = this.document.forReview();
        boolean found = isDocumentOnTheList(actor, document);
        if (!found) {
            if (nextButton.resolveFor(actor).isVisible()) {
                Click.on(nextButton).performAs(actor);
                found = isDocumentOnTheList(actor, document);
            }
        }
        return found;
    }

    private boolean isDocumentOnTheList(Actor actor, String documentRow) {
        List<WebElementFacade> rows = tr.resolveAllFor(actor);
        boolean found = false;

        for (WebElementFacade row : rows) {
            if (row.getText().equals(documentRow)) {
                found = true;
                break;
            }
        }
        return found;
    }
}