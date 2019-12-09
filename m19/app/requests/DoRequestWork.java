package m19.app.requests;

import m19.core.LibraryManager;
import m19.core.Request;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Form;
import pt.tecnico.po.ui.Input;

import m19.core.User;
import m19.core.Work;

import m19.core.exception.AllCopiesRequestedException;
import m19.core.exception.UserNotFoundException;
import m19.core.exception.WorkNotFoundException;
import m19.core.exception.RuleNotSatisfiedException;

import m19.app.exception.NoSuchUserException;
import m19.app.exception.NoSuchWorkException;
import m19.app.exception.RuleFailedException;
import m19.app.requests.Message;

/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {
    
    private Input<Integer> _userId;
    private Input<Integer> _workId;
    
    // Is displayd or not based on user input at run time
    // asking for notification preference
    private Form _exceptionForm;
    private Input<Boolean> _reqRetNotifPref;

    /**
     * @param receiver
     */
    public DoRequestWork(LibraryManager receiver) {
        super(Label.REQUEST_WORK, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
        _workId = _form.addIntegerInput(m19.app.requests.Message.requestWorkId());

        // Auxiliary form
        _exceptionForm = new Form();
        _reqRetNotifPref = _exceptionForm.addBooleanInput(Message.requestReturnNotificationPreference());
    }


    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        User user = null;
        Work work = null;
        _form.parse();

        try {
            user = _receiver.getUser(_userId.value());
            work = _receiver.getWork(_workId.value());
            Request req = _receiver.requestWork(user, work);
            _display.popup(Message.workReturnDay(work.getId(), req.getReturnDate()));
        }
        catch (UserNotFoundException nufe) {
            throw new NoSuchUserException(nufe.getRequestedId());
        }
        catch (WorkNotFoundException nwfe) {
            throw new NoSuchWorkException(nwfe.getRequestedId());
        }
        catch (AllCopiesRequestedException acre) { // Tough luck for the User
            _exceptionForm.parse();
            // Do something with user's notification preference.

            if (_reqRetNotifPref.value()) {
                acre.getNotificationBroadcaster().subscribe(user);
            }
        }
        catch (RuleNotSatisfiedException rnse) { // Not rule 3
            throw new RuleFailedException(user.getId(), work.getId(), rnse.getViolatedRule());
        }

    }

}
