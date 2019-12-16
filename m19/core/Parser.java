package m19.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

import m19.core.exception.BadEntrySpecificationException;

public class Parser {

    private Library _library;
    private int _worksLoaded;

    Parser(Library lib) {
        _library = lib;
        _worksLoaded = 0;
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
            parseDvd(components, line);
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

    private void parseDvd(String[] components, String line) throws BadEntrySpecificationException {
        if (_worksLoaded >= 10) {
            return;
        }
        _worksLoaded++;

        if (components.length != 7) {
            throw new BadEntrySpecificationException("Wrong number of fields (6) in " + line);
        }

        Dvd Dvd = new Dvd(_library.getNextWorkId(), _library, components[1], components[2], Integer.parseInt(components[3]),
            Category.valueOf(components[4]), components[5],
            Integer.parseInt(components[6]));

        _library.addWork(Dvd);
    }

    private void parseBook(String[] components, String line) throws BadEntrySpecificationException {
        if (_worksLoaded >= 10) {
            return;
        }
        _worksLoaded++;

        if (components.length != 7){
            throw new BadEntrySpecificationException("Wrong number of fields (6) in " + line);
        }

        Book book = new Book(_library.getNextWorkId(), _library, components[1], components[2], Integer.parseInt(components[3]),
            Category.valueOf(components[4]), components[5],
            Integer.parseInt(components[6]));

        _library.addWork(book);
    }

    private void parseUser(String[] components, String line) throws BadEntrySpecificationException {
        if (components.length != 3) {
            throw new BadEntrySpecificationException("Wrong number of fields (2) in " + line);
        }

        try {
            _library.registerUser(components[1], components[2]);
        }
        catch (IllegalArgumentException iae) {
            System.err.println(line);
            iae.printStackTrace();
            throw new BadEntrySpecificationException("Failed to register the user.");
        };
    }

}