package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;

import m19.core.User;
import m19.core.exception.NotFoundException;
import pt.tecnico.po.ui.Input;
import m19.app.exception.NoSuchUserException;

/**
 * 4.2.2. Show specific user.
 */
public class DoShowUser extends Command<LibraryManager> {

    private Input<Integer> _userId;

    /**
     * @param receiver
     */
    public DoShowUser(LibraryManager receiver) {
        super(Label.SHOW_USER, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());

    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        int uid = _userId.value();

        try {
            User user = _receiver.getUser(uid);
            _display.addLine(user.getDescription());
            _display.display();
        } catch (NotFoundException nfe) {
            throw new NoSuchUserException(nfe.getRequestedId());
        }
    }

}
