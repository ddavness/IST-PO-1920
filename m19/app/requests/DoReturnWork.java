package m19.app.requests;

import m19.core.*;
import m19.core.exception.UserNotFoundException;
import m19.core.exception.WorkNotFoundException;
import m19.core.exception.RequestNotFoundException;

import m19.app.exception.NoSuchUserException;
import m19.app.exception.NoSuchWorkException;
import m19.app.exception.WorkNotBorrowedByUserException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.DialogException;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Command<LibraryManager> {
    private Input<Integer> _userId;
    private Input<Integer> _workId;
    private Input<Boolean> _wishesToPayFine;

    /**
     * @param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(Label.RETURN_WORK, receiver);
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.clear();
        _userId = _form.addIntegerInput(Message.requestUserId());
        _workId = _form.addIntegerInput(Message.requestWorkId());

        _form.parse();

        try {
            User user = _receiver.getUser(_userId.value());
            Work work = _receiver.getWork(_workId.value());
            Request request = user.requestedWork(work);

            user.returnWork(request);

            int fine = user.getAccruedFine();
            if (fine > 0) {
                _display.addLine(Message.showFine(user.getId(), fine));
                _display.display();
                _form.clear();
                _wishesToPayFine = _form.addBooleanInput(Message.requestFinePaymentChoice());
                _form.parse();

                if (_wishesToPayFine.value()) {
                    _receiver.payFine(_userId.value());
                }
            }
        } catch (UserNotFoundException unfe) {
            throw new NoSuchUserException(unfe.getRequestedId());
        } catch (WorkNotFoundException wnfe) {
            throw new NoSuchWorkException(wnfe.getRequestedId());
        } catch (RequestNotFoundException rnfe) {
            throw new WorkNotBorrowedByUserException(rnfe.getWorkId(), rnfe.getUserId());
        }
    }

}
