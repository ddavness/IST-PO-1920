package m19.app.users;

import m19.app.exception.NoSuchUserException;
import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.core.User;
import m19.core.exception.NotFoundException;
import m19.core.Notification;

/**
 * 4.2.3. Show notifications of a specific user.
 */
public class DoShowUserNotifications extends Command<LibraryManager> {

    Input<Integer> _userId;

    /**
     * @param receiver
     */
    public DoShowUserNotifications(LibraryManager receiver) {
        super(Label.SHOW_USER_NOTIFICATIONS, receiver);
        _userId = _form.addIntegerInput(Message.requestUserId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();

        try {
            User user = _receiver.getUser(_userId.value());

            for (Notification notification: user.getNotifications()) {
                _display.addLine(notification.getMessage());
                
            }
            _display.display();
        } catch (NotFoundException nfe) {
            throw new NoSuchUserException(nfe.getRequestedId());
        }

    }

}
