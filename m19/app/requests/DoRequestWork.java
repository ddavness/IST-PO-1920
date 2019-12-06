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
import m19.core.exception.RuleNotSatisfiedException;
import m19.app.exception.NoSuchUserException;
import m19.app.exception.NoSuchWorkException;
import m19.app.exception.RuleFailedException;
import m19.app.requests.Message;

/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {

    Input<Integer> _userId;
    Input<Integer> _workId;

    // Is displayd or not based on user input at run time
    // asking for notification preference
    Form _form2 = new Form(); // Without title
    Input<Boolean> _reqRetNotPref;

    /**
     * @param receiver
     */
    public DoRequestWork(LibraryManager receiver) {
        super(Label.REQUEST_WORK, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
        _workId = _form.addIntegerInput(m19.app.requests.Message.requestWorkId());

        _reqRetNotPref = _form2.addBooleanInput(Message.requestReturnNotificationPreference());
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

        int nDays = user.getBehaviour().getNumDays(work);
        try {
            Request req = _receiver.requestWork(user, work);
            _display.popup(Message.workReturnDay(work.getId(), req.getReturnDate()));
        }

        catch (AllCopiesRequestedException acre) { // Tough luck for the User
            _form2.parse();
            // FIXME Implement
            // Do something with user's notification preference.
        }

        catch (RuleNotSatisfiedException rnse) { // Not rule 3
            throw new RuleFailedException(user.getId(), work.getId(), rnse.getViolatedRule());
        }

    }

}
