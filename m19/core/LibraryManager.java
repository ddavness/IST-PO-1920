package m19.core;

import m19.core.exception.MissingFileAssociationException;
import m19.core.exception.BadEntrySpecificationException;
import m19.core.exception.ImportFileException;


import java.io.*;

// FIXME import other system types
// FIXME import other project (core) types

/**
 * The façade class.
 */
public class LibraryManager {

  private Library _library;  // FIXME initialize this attribute
  private String _file;

  // FIXME define other attributes

  // FIXME define contructor(s)
  
  // FIXME define methods

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

        ObjectOutputStream objOut = null;

        try { // still throws exception
            objOut = new ObjectOutputStream(new FileOutputStream(filename));
            objOut.writeObject(_library);
            _file = filename;
        }
        finally {
            if (objOut != null)
                objOut.close();

        }
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
        ObjectInputStream objInStream = null;
        
        
        try {
            objInStream = new ObjectInputStream(new FileInputStream(filename));
            _library = (Library) objInStream.readObject();
        }

        finally {
            if (objInStream != null)
                objInStream.close();
        }
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
            throw new ImportFileException();
        }
    }

  public int getCurrentDate() {
      return 42; //FIXME Implement and remove placeholder
  }
}
