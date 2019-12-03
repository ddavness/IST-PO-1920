package m19.app.works;

import java.util.ArrayList;

import m19.core.LibraryManager;
// FIXME import other core concepts
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.core.Work;

/**
 * 4.3.3. Perform search according to miscellaneous criteria.
 */
public class DoPerformSearch extends Command<LibraryManager> {

    // FIXME define input fields
    Input<String> _searchTerm;

    /**
     * @param m
     */
    public DoPerformSearch(LibraryManager m) {
        super(Label.PERFORM_SEARCH, m);
        _searchTerm = _form.addStringInput(Message.requestSearchTerm());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() {
        _form.parse();
        ArrayList<Work> works = new ArrayList<>();
        for (Work work: _receiver.getAllWorks()) {
            if (work.getTitle().contains(_searchTerm.value())) {
                works.add(work);
            }
        }

        works.sort((Work w1, Work w2) -> w1.getID() - w2.getID()); // sort by Id
        for (Work work: works) {
            _display.addLine(work.getDescription());
        }
        _display.display();
    }

}
