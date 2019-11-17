package m19.app.requests;

import m19.core.LibraryManager;
import m19.core.Request;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import m19.core.User;
import m19.core.Work;
import m19.core.exception.RuleNotSatisfiedException;
import m19.app.exception.RuleFailedException;
import m19.app.requests.Message;

/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {

    Input<Integer> _userID;
    Input<Integer> _workID;

    /**
     * @param receiver
     */
    public DoRequestWork(LibraryManager receiver) {
        super(Label.REQUEST_WORK, receiver);
        _userID = _form.addIntegerInput(Message.requestUserId());
        _workID = _form.addIntegerInput(m19.app.requests.Message.requestWorkId());

    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        User user = _receiver.getUser(_userID.value());
        Work work = _receiver.getWork(_workID.value());
        //FIXME Ask for how many days
        int nDays = 5; //FIXME Change
        try {
            Request req = _receiver.requestWork(user, work, nDays);
            _display.add(Message.workReturnDay(work.getID(), req.getDeadine()));
        }
        catch (RuleNotSatisfiedException rnse) {
            // FIXME Say it didn't work
            throw new RuleFailedException(user.getID(), work.getID(), rnse.getViolatedRule());
        }

    }

}
