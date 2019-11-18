package m19.app.works;

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

    private Input<Integer> _workID;

    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(Label.SHOW_WORK, receiver);
        _workID = _form.addIntegerInput(Message.requestWorkId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        int index = _workID.value();
        Work work = _receiver.getWork(index);

        if (work == null) {
            throw new NoSuchWorkException(index);
        }

        _display.add(work.getDescription());
        _display.display();
    }

}
