package m19.core;

import java.io.Serializable;
import java.io.IOException;

import m19.core.exception.BadEntrySpecificationException;

import m19.core.Work;
import m19.core.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;

    private List<User> _users;
    private List<Work> _works;
    private List<Rule> _rules;
    private int _systemDate;

    public Library() {
        _users = new ArrayList<>();
        _works = new ArrayList<>();
        _rules = new ArrayList<>();
    }

    /**
     * Get the current system date of the library.
     *
     * @return the current system date of the manager.
     */
    int getCurrentDate() {
        return _systemDate;
    }

    /**
     * Advances the current date of the library, and updates all related statuses.
     *
     * @param daysToAdvance The number of days to advance. If this number is lower than 1, this method has no effect.
     */
    void advanceDays(int daysToAdvance) {
        if (daysToAdvance >= 0) {
            _systemDate += daysToAdvance;
        }

        // FIXME Apply updates
    }

    public void addUser(User user) {
        _users.add(user);
    }

    public void addWork(Work work) {
        _works.add(work);
    }

    public void addRule(Rule rule) {
        _rules.add(rule);
    }

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename
     *          name of the file to load
     * @throws BadEntrySpecificationException
     * @throws IOException
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Parser parser = new Parser(this);
        parser.parseFile(filename);
    }

    /**
     *
     * @param id the id of the user to fint
     * @return the User in the library or a null reference if not found.
     */
    User getUser(int id) {
        for (User u: _users)
            if (u.getID() == id)
                return u;

        return (User) null;
    }

    /**
     *
     * @return copy of list
     */
    List<User> getAllUsers() {
        // return java.util.Collections.unmodifiableList(_users);
        ArrayList<User> allUsers = new ArrayList<>(_users);
        Collections.sort(allUsers);
        return allUsers;
    }

    List<Work> getAllWorks() {
        ArrayList<Work> allWorks = new ArrayList<>(_works);
        class WorkComparator implements Comparator<Work> {
            @Override
            public int compare(Work w1, Work w2) {
                return w1.getID() - w2.getID();
            }
        }

        Collections.sort(allWorks, new WorkComparator());
        return allWorks;
    }

    public List<Request> getAllRequests() {
        ArrayList<Request> _requests = new ArrayList<>();
        for (User user: getAllUsers()) {
            _requests.addAll(user.getAllRequests());
        }
        return _requests;   
    }

}
