package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.targets.Target;

@Subject("the document list size")

public class DocumentList implements Question<Integer> {
    public static Target tr = Target.the("the container div").locatedBy(".ct-documents-container tbody tr");
    private Target nextButton = Target.the("the next page button").locatedBy(".pagination-next");

    public static DocumentList size() {
        return new DocumentList();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        int documentListSize = tr.resolveAllFor(actor).size();
        if (nextButton.resolveFor(actor).isVisible()) {
            Click.on(nextButton).performAs(actor);
            documentListSize += tr.resolveAllFor(actor).size();
        }
        return documentListSize;
    }
}