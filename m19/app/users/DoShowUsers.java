package m19.app.users;

import pt.tecnico.po.ui.Command;
import m19.core.*;

/**
 * 4.2.4. Show all users.
 */
public class DoShowUsers extends Command<LibraryManager> {

    /**
     * @param receiver
     */
    public DoShowUsers(LibraryManager receiver) {
        super(Label.SHOW_USERS, receiver);
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() {
        for (User user: _receiver.getAllUsers()) {
            _display.addLine(user.getDescription());
        }
        _display.display();
    }

}
