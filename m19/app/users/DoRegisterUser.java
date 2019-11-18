package m19.app.users;

import pt.tecnico.po.ui.*;

import m19.core.*;

import m19.app.exception.UserRegistrationFailedException;
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
        _form.parse();

        try {
            int uid = _receiver.registerUser(_userName.value(), _userEmail.value());

            _display.add(Message.userRegistrationSuccessful(uid));
            _display.display();
        } catch (IllegalArgumentException e) {
            throw new UserRegistrationFailedException(_userName.value(), _userEmail.value());
        }
    }

}
