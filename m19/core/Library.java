package m19.core;

import java.io.Serializable;
import java.io.IOException;

import m19.core.exception.MissingFileAssociationException;
import m19.core.exception.BadEntrySpecificationException;

import java.util.List;
import java.util.ArrayList;

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
  private Date _date;


  public Library() {
    _users = new ArrayList<>();
    _works = new ArrayList<>();
    _rules = new ArrayList<>();
    _date = new Date();
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

  public Date getDate() {
    return _date;
  }

  public void advanceDays(int nDays) {
    _date.advanceDay(nDays);
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

}
