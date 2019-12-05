package m19.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.*;

import m19.core.exception.BadEntrySpecificationException;
import m19.core.exception.RuleNotSatisfiedException;

import m19.core.rules.*;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;
    private int _nextUserID;
    private int _nextWorkID;

    private Map<Integer, User> _users;
    private Map<Integer, Work> _works;
    private List<Rule> _rules;
    private int _systemDate;

    public Library() {
        _users = new HashMap<>();
        _works = new HashMap<>();
        _rules = new ArrayList<>();

        _rules.add(new CheckRequestTwice(0, this));
        _rules.add(new CheckUserHasNotExceededWorkRequestLimit(1, this));
        _rules.add(new CheckUserIsNotSuspended(2, this)); // FIXME Change name
        _rules.add(new CheckWorkIsAvailable(3, this));
        _rules.add(new CheckWorkIsLowValue(4, this));
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

    /**
     * Attempts to register an user to the library.
     *
     * @param name The name of the user. String must be non-blank.
     * @param email The email of the user. String must be non-blank.
     * @return The id that the library assigned to the user
     * @throws IllegalArgumentException whenever the arguments provided are invalid.
     */
    int registerUser(String name, String email) throws IllegalArgumentException {
        int uid = getNextUserID();

        try {
            _users.put(uid, new User(uid, name, email));
        } catch (IllegalArgumentException arge) {
            // Undo changes and rethrow
            _nextUserID--;
            throw arge;
        }

        return uid;
    }

    /**
     * 
     * @param work reference to work to add to library
     */
    public void addWork(Work work) {
        _works.put(work.getID(), work);
    }

    /**
     * 
     * @param rule reference to rule to add to library
     */
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
        return _users.get(id);
    }

    /**
     *
     * @return copy of list of all users.
     * @@implNote It is a shallow copy.
     */
    List<User> getAllUsers() {
        // return java.util.Collections.unmodifiableList(_users);
        ArrayList<User> allUsers = new ArrayList<>(_users.values());
        Collections.sort(allUsers);
        return allUsers;
    }

    /**
     * 
     * @return the ID of the next user to be registered and and increments
     *         the next userID.
     */
    public int getNextUserID() {
        return _nextUserID++;
    }

    /**
     * 
     * @return the ID of the next work to be added to library and increments
     *         the next _workId.
     */

    public int getNextWorkID() {
        return _nextWorkID++;
    }

    /**
     * 
     * @param id of the work to find
     * @return reference to work in library or null reference if not found
     */

    public Work getWork(int id) {
        return _works.get(id);
    }


    /**
     * 
     * @return sorted list of all works in the library ordered by the
     *         natural order of the work IDs.
     */
    List<Work> getAllWorks() {
        ArrayList<Work> allWorks = new ArrayList<>(_works.values());
        class WorkComparator implements Comparator<Work> {
            @Override
            public int compare(Work w1, Work w2) {
                return w1.getID() - w2.getID();
            }
        }

        Collections.sort(allWorks, new WorkComparator());
        return allWorks;
    }

    /**
     * 
     * @return all the requests by all users in the library.
     */
    public List<Request> getAllRequests() {
        ArrayList<Request> requests = new ArrayList<>();
        for (User user: getAllUsers()) {
            requests.addAll(user.getAllRequests());
        }
        return requests;
    }

    /**
     * Adds request to user and work
     * 
     * @param user who want to request work
     * @param work to be requested
     * @param nDays length of the requested
     * @return Request reference
     * @throws RuleNotSatisfiedException
     */
    public Request requestWork(User user, Work work) throws RuleNotSatisfiedException {
        int returnDate = 5; //FIXME
        // FIXME implement with sate design pattern!!!

        /////////////   WARNING: REALLY BAD IMPLEMENTATION ///////////////////
        

        Request request = new Request(user, work, returnDate);
        for (Rule rule : _rules) {
            rule.check(request);
        }

        user.addRequest(request);
        work.addRequest(request);

        return request;
    }

}
