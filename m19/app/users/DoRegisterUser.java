package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.*;

import m19.core.*;
import m19.app.users.Message;

/**
 * 4.2.1. Register new user.
 */
public class DoRegisterUser extends Command<LibraryManager> {
    Input<String> _userName;
    Input<String> _userEmail;

    /**
     * @param receiver
     */
    public DoRegisterUser(LibraryManager receiver) {
        super(Label.REGISTER_USER, receiver);
        _userName = _form.addStringInput(Message.requestUserName());
        _userEmail = _form.addStringInput(Message.requestUserEMail());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse(); // FIXME Handle exceptions

        User user = new User(_userName.toString(), _userEmail.toString());

        _receiver.addUser(user);

        _display.add(Message.userRegistrationSuccessful(user.getID()));
        _display.display();
    }

}
