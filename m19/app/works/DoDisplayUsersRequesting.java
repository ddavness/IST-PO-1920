package m19.app.works;

import m19.app.exception.NoSuchWorkException;
import m19.core.LibraryManager;
import m19.core.exception.NotFoundException;
import m19.core.Work;
import m19.core.User;
import m19.core.Request;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Form;
import pt.tecnico.po.ui.Input;

import java.util.*;

/**
 * 4.3.4. Display all requests tied to a work.
 */
public class DoDisplayUsersRequesting extends Command<LibraryManager> {

    private Input<Integer> _workId;

    /**
     * @param receiver
     */
    public DoDisplayUsersRequesting(LibraryManager receiver) {
        super(Label.SHOW_REQUESTS, receiver);
        _workId = _form.addIntegerInput(Message.requestWorkId());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        int index = _workId.value();

        try {
            Work work = _receiver.getWork(index);
            Collection<Request> requests = work.getRequests();
            List<User> users = new ArrayList<>();
            for (Request r: requests) {
                users.add(r.getUser());
            }
            Collections.sort(users);

            for (User u: users) {
                _display.add(u.getDescription());
            }
            _display.display();
        } catch (NotFoundException nfe) {
            throw new NoSuchWorkException(nfe.getRequestedId());
        }
    }

}
