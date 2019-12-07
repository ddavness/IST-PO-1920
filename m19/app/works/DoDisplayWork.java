package m19.app.works;

import m19.core.Work;
import m19.core.exception.NotFoundException;
import m19.app.exception.NoSuchWorkException;
import m19.core.LibraryManager;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

/**
 * 4.3.1. Display work.
 */
public class DoDisplayWork extends Command<LibraryManager> {

    private Input<Integer> _workId;

    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(Label.SHOW_WORK, receiver);
        _workId = _form.addIntegerInput(Message.requestWorkId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        int index = _workId.value();

        try {
            Work work = _receiver.getWork(index);

            _display.add(work.getDescription());
            _display.display();
        } catch (NotFoundException nfe) {
            throw new NoSuchWorkException(nfe.getRequestedId());
        }
    }

}
