package m19.app.works;

import java.util.List;
import m19.core.Work;
import m19.app.exception.NoSuchWorkException;
import m19.core.LibraryManager;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

/**
 * 4.3.1. Display work.
 */
public class DoDisplayWork extends Command<LibraryManager> {

    private Input<Integer> _input;

    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(Label.SHOW_WORK, receiver);
        _input = _form.addIntegerInput(Message.requestWorkId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        int index = _input.value();
        List<Work> works = _receiver.getAllWorks();

        if (works.size() <= index) {
            throw new NoSuchWorkException(index);
        }

        _display.add(works.get(index).getDescription());
        _display.display();
    }

}
