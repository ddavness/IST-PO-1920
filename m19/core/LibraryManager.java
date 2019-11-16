package m19.core;

import m19.core.exception.MissingFileAssociationException;
import m19.core.exception.BadEntrySpecificationException;
import m19.core.exception.ImportFileException;


import java.io.*;
import java.util.List;

/**
 * The façade class.
 */
public class LibraryManager {
    private Library _library;
    private String _file;


    public LibraryManager() {
        _library = new Library();
    }

    /**
     * @see Library#getCurrentDate()
     */
    public int getCurrentDate() {
        return _library.getCurrentDate();
    }

    /**
     * @see Library#advanceDays()
     */
    public void advanceDays(int daysToAdvance) {
        _library.advanceDays(daysToAdvance);
    }

    /**
     * Serialize the persistent state of this application.
     * 
     * @throws MissingFileAssociationException if the name of the file to store the persistent
     *         state has not been set yet.
     * @throws IOException if some error happen during the serialization of the persistent state
     */
    public void save() throws MissingFileAssociationException, IOException {
        // Assumes that the _filename property is already defined (e.g. non-null)

        if (_file == null) {
            throw new MissingFileAssociationException();
        }

        saveAs(_file);
    }

    /**
     * Serialize the persistent state of this application into the specified file.
     * 
     * @param filename the name of the target file
     *
     * @throws MissingFileAssociationException if the name of the file to store the persistent
     *         is not a valid one.
     * @throws IOException if some error happen during the serialization of the persistent state
     */
    public void saveAs(String filename) throws IOException {
        // We'll throw this error so that someone else deals with it.

        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename));
        objOut.writeObject(_library);
        objOut.close();

        _file = filename;
    }

    /**
     * Recover the previously serialized persitent state of this application.
     * 
     * @param filename the name of the file containing the perssitente state to recover
     *
     * @throws IOException if there is a reading error while processing the file
     * @throws FileNotFoundException if the file does not exist
     * @throws ClassNotFoundException if the parser is unable to convert the content to an object
     */
    public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(filename));

        _library = (Library) objInStream.readObject();

        objInStream.close();
    }

    /**
     * Set the state of this application from a textual representation stored into a file.
     * 
     * @param datafile the filename of the file with the textual represntation of the state of this application.
     * @throws ImportFileException if it happens some error during the parsing of the textual representation.
     */
    public void importFile(String datafile) throws ImportFileException {
        _library = new Library();
        try {
            _library.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }


    public void addUser(User user) {
        _library.addUser(user);
    }

    /**
     * 
     * @param id of the user to find
     * @return a reference to User or a null reference if not found.
     */
    public User getUser(int id) {
        return _library.getUser(id);
    }


    /**
     * 
     * @return sorted list of users.
     */
    public List<User> getAllUsers() {
        return _library.getAllUsers();
    }

    public List<Work> getAllWorks() {
        return _library.getAllWorks();
    }
}
