package m19.app.users;

import m19.app.exception.NoSuchUserException;
import m19.app.exception.UserIsActiveException;
import m19.core.LibraryManager;
import m19.core.User;
import m19.core.exception.UserNotFoundException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.2.5. Settle a fine.
 */
public class DoPayFine extends Command<LibraryManager> {

    private Input<Integer> _userId;

    /**
     * @param receiver
     */
    public DoPayFine(LibraryManager receiver) {
        super(Label.PAY_FINE, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        try {
            User user = _receiver.getUser(_userId.value());
            if (user.isActive())
                throw new UserIsActiveException(_userId.value());

            _receiver.payFine(_userId.value());

        }
        catch (UserNotFoundException unfe) {
            throw new NoSuchUserException(_userId.value());
        }
    }

}
