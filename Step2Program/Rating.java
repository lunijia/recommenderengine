
//The class Rating is also a POJO class for storing the data about one rating of an item.
// An immutable passive data object (PDO) to represent the rating data
public class Rating implements Comparable<Rating> {
    ///item - a String description of the item being rated (for this assignment you should use the IMDB ID of the movie being rated)
    private String item;
    //value - a double of the actual rating
    private double value;

    public Rating (String anItem, double aValue) {
        item = anItem;
        value = aValue;
    }

    // Returns item being rated
    public String getItem () {
        return item;
    }

    // Returns the value of this rating (as a number so it can be used in calculations)
    public double getValue () {
        return value;
    }

    // Returns a string of all the rating information
    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;
        
        return 0;
    }
}
