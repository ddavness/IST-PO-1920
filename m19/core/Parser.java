package m19.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;

import m19.core.exception.BadEntrySpecificationException;

public class Parser {

  private Library _library;

  Parser(Library lib) {
    _library = lib;
  }

    void parseFile(String filename) throws IOException, FileNotFoundException, BadEntrySpecificationException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                parseLine(line);
            }
        }
    }

  private void parseLine(String line) throws BadEntrySpecificationException {
    String[] components = line.split(":");

    switch(components[0]) {
      case "DVD":
        parseDVD(components, line);
        break;

      case "BOOK":
        parseBook(components, line);
        break;
      case "USER":
        parseUser(components, line);
        break;
        
      default:
        throw new BadEntrySpecificationException("Invalid type " + components[0] +
                                                " in line " + line);
    }
  }

  private void parseDVD(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 7)
      throw new BadEntrySpecificationException("Wrong number of fields (6) in " + line);
    DVD dvd = new DVD(components[1], components[2], Integer.parseInt(components[3]),
                      Category.valueOf(components[4]), components[5],
                      Integer.parseInt(components[6]));


    _library.addWork(dvd);
    
    // add dvd to _library
    //FIXME Make Parser actually do something
  }

  private void parseBook(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 7)
      throw new BadEntrySpecificationException("Wrong number of fields (6) in " + line);

    Book book = new Book(components[1], components[2], Integer.parseInt(components[3]),
                         Category.valueOf(components[4]), components[5],
                         Integer.parseInt(components[6]));
    
    // add book to _library
    _library.addWork(book);
  }

  private void parseUser(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 3)
      throw new BadEntrySpecificationException("Wrong number of fields (2) in " + line);
    User user = new User(components[1], components[2]);
    
    // add user to _library
    _library.addUser(user);

    //FIXME
    // Pode ser necessário ter um try-catch adicional neste método
  }

 }