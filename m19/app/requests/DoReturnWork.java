package m19.app.requests;

import m19.core.LibraryManager;
import m19.core.exception.UserNotFoundException;
import m19.core.exception.WorkNotFoundException;
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

        try {
            User user = _receiver.getUser(_userId.value());
            Work work = _receiver.getWork(_workId.value());
            Request request = user.requestedWork(work);

            // FIXME
            if (request == null)
                throw new WorkNotBorrowedByUserException(_workId.value(), _userId.value());

            user.returnWork(request);

            if (user.getAccruedFine() > 0) {
                _form2.parse(); // Ask if users wants to pay fine
                if (_wishesToPayFine.value()) {
                    _receiver.payFine(_userId.value());
                }
            }
        } catch (UserNotFoundException nufe) {
            throw new NoSuchUserException(nufe.getRequestedId());
        } catch (WorkNotFoundException nwfe) {
            throw new NoSuchWorkException(nwfe.getRequestedId());
        }

        // FIXME implement command
    }

}
