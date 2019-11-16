package m19.app.main;

import m19.core.LibraryManager;
import m19.app.exception.*;
import java.io.*;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.DialogException;

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<LibraryManager> {

    private Input<String> _userInput;

    /**
     * @param receiver
     */
    public DoOpen(LibraryManager receiver) {
        super(Label.OPEN, receiver);
        _userInput = _form.addStringInput(Message.openFile());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        _form.parse();
        String fname = _userInput.value();

        try {
            // Apply the filename and import it
            _receiver.load(fname);

        } catch (FileNotFoundException | ClassNotFoundException fnfe) {
            throw new FileOpenFailedException(fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
