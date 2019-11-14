package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;

import m19.core.User;
import pt.tecnico.po.ui.Input;
import m19.app.exception.Message;

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
    _userID = _form.addIntegerInput("");// FIXME Add message

  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    User user = _receiver.getUser(_userID.value());

    if (user != null)
      _display.addLine(user.getDescription());

    else      
      _display.addLine(Message.noSuchUser(_userID.value()));

    _display.display();


  }

}
