package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.*;

import m19.core.*;
import m19.app.users.Message;

// import m19.app.users.*; // FIXME
// FIXME import other core concepts
// FIXME import other ui concepts

/**
 * 4.2.1. Register new user.
 */
public class DoRegisterUser extends Command<LibraryManager> {
    Input<String> _userName;
    Input<String> _userEmail;

    // FIXME define input fields

    /**
     * @param receiver
     */
    public DoRegisterUser(LibraryManager receiver) {
        super(Label.REGISTER_USER, receiver);
        _userName = _form.addStringInput(Message.requestUserName());
        _userEmail = _form.addStringInput(Message.requestUserEMail());
        // FIXME initialize input fields
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {

        _form.parse(); // FIXME Handle exceptions

        User user = new User(_userName.toString(), _userEmail.toString());

        _receiver.addUser(user);

        // FIXME implement command
    }

}
