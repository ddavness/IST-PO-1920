package m19.app.main;

import java.io.IOException;

import m19.core.LibraryManager;
import m19.core.exception.MissingFileAssociationException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.DialogException;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<LibraryManager> {

    private Input<String> _fileName;

    /**
     * @param receiver
     */
    public DoSave(LibraryManager receiver) {
        super(Label.SAVE, receiver);
        _fileName = _form.addStringInput(Message.newSaveAs());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws DialogException {
        try {
            _receiver.save();
        } catch (MissingFileAssociationException e) {
            _form.parse();
            String fname = _fileName.value();
            try {
                _receiver.saveAs(fname);
            } catch (IOException ee) {
                ee.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
