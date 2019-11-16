package m19.app.main;

import m19.core.LibraryManager;
import m19.core.exception.InvalidArgumentException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

// FIXME import iother core concepts
// FIXME import ui concepts

/**
 * 4.1.3. Advance the current date.
 */
public class DoAdvanceDate extends Command<LibraryManager> {
    /**
     * @param receiver
     */
    Input<Integer> input;
    public DoAdvanceDate(LibraryManager receiver) {
        super(Label.ADVANCE_DATE, receiver);
        input = _form.addIntegerInput(Message.requestDaysToAdvance());
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() {
        _form.parse();

        try {
            _receiver.advanceDays(input.value());
        } catch (InvalidArgumentException e) {
            // FIXME
        }

    }

}
