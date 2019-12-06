package m19.app.requests;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import m19.app.exception.NoSuchUserException;
import m19.app.exception.NoSuchWorkException;
import m19.app.exception.WorkNotBorrowedByUserException;
import m19.core.*;
import pt.tecnico.po.ui.*;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Command<LibraryManager> {
    Input<Integer> _userId;
    Input<Integer> _workId;
    Form _form2;
    Input<Boolean> _wishesToPayFine;

    /**
     * @param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(Label.RETURN_WORK, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
        _workId = _form.addIntegerInput(Message.requestWorkId());
        _form2 = new Form();
        _wishesToPayFine = _form2.addBooleanInput(Message.requestFinePaymentChoice());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        User user = _receiver.getUser(_userId.value());
        Work work = _receiver.getWork(_workId.value());
        if (user == null) { //FIXME this is ugly
            throw new NoSuchUserException(_userId.value());
        }
        if (work == null) {
            throw new NoSuchWorkException(_workId.value());
        }

        if (!user.hasRequestedWork(work))
            throw new WorkNotBorrowedByUserException(_workId.value(), _userId.value());
            
        if (user.getAccruedFine() > 0) {
            _form2.parse(); // Ask if users wants to pay fine
            // if (_wishesToPayFine.value())
                //FIXME pay fine

        }

        // FIXME implement command
    }

}
