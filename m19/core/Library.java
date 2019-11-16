package m19.core;

import java.io.Serializable;
import java.io.IOException;

import m19.core.exception.MissingFileAssociationException;
import m19.core.exception.BadEntrySpecificationException;

import m19.core.Work;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// FIXME import other system types
// FIXME import project (core) types if needed

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201901101348L;

  // FIXME define attributes
  private List<User> _users;
  private List<Work> _works;
  private List<Rule> _rules;


  public Library() {
    _users = new ArrayList<>();
    _works = new ArrayList<>();
    _rules = new ArrayList<>();
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

  // FIXME define methods

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
    // FIXME test this method
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
    Collections.sort(allWorks, WorkComparator);
    return allWorks;
  }

}
