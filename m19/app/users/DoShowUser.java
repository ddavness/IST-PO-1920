package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;

import m19.core.User;
import pt.tecnico.po.ui.Input;
import m19.app.exception.NoSuchUserException;

/**
 * 4.2.2. Show specific user.
 */
public class DoShowUser extends Command<LibraryManager> {

    Input<Integer> _userID;

    /**
     * @param receiver
     */
    public DoShowUser(LibraryManager receiver) {
        super(Label.SHOW_USER, receiver);
        _userID = _form.addIntegerInput(m19.app.users.Message.requestUserId());

    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        int uid = _userID.value();
        User user = _receiver.getUser(uid);

        if (user != null) {
            _display.addLine(user.getDescription());
        } else {
            throw new NoSuchUserException(uid);
        }
    }

}
