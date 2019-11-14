package m19.core;

/**
 * Date
 */
public class Date {

    int _currentDate;


    protected int getCurrentDate() {
        return _currentDate;
    }

    protected void advanceDay(int nDays) {
        _currentDate += nDays;
    }

}