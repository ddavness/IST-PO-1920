package m19.app.main;

import m19.core.LibraryManager;
import m19.app.exception.*;
import java.io.*;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;

// FIXME import other core concepts
// FIXME import other ui concepts

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<LibraryManager> {
    /**
     * @param receiver
     */
    public DoOpen(LibraryManager receiver) {
        super(Label.OPEN, receiver);
        // FIXME initialize input fields if needed
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        /* try {

        } catch (FileNotFoundException fnfe) {
            throw new FileOpenFailedException();
        } catch (ClassNotFoundException | IOException e) {
        e.printStackTrace();
        }*/
    }

}
