package m19.app.users;

import java.util.List;
import java.util.ArrayList;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

import m19.core.LibraryManager;
import m19.core.User;

/**
 * 4.2.p. Perform search according to miscellaneous criteria.
 */
public class DoPerformSearch extends Command<LibraryManager> {
    private Input<String> _searchTerm;

    /**
     * @param m
     */
    public DoPerformSearch(LibraryManager m) {
        super("Procurar por nome/email", m);
        _searchTerm = _form.addStringInput(Message.requestSearchTerm());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() {
        _form.parse();
        List<User> users = new ArrayList<>();
        for (User user: _receiver.getAllUsers()) {
            if (user.search(_searchTerm.value())) {
                users.add(user);
            }
        }

        _display.addLine("Encontrados " + users.size() + " utilizadore(s).");
        for (User u: users) {
            _display.addLine(u.getName());
        }
        _display.display();
    }

}
