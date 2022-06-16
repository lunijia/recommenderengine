
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

//The class Rater keeps track of one rater and all their ratings.
public class Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    public Rater(String id) {
        //myID - a unique String ID for this rater
        myID = id;
        //myRatings - an ArrayList of Ratings
        myRatings = new ArrayList<Rating>();
    }

    //A method addRating that has two parameters, a String named item and a double named rating. A new Rating is created and added to myRatings.
    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        
        return false;
    }

    //A method getID with no parameters to get the ID of the rater.
    public String getID() {
        return myID;
    }

    //A method getMyRating with no parameter to get Array of ratings of certain rater
    public ArrayList<Rating> getMyRatings(){
        return myRatings;
    }

    
    //A method getRating that has one parameter item. This method returns the double rating of this item if it is in myRatings. Otherwise this method returns -1.
    public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }

    //A method numRatings that returns the number of ratings this rater has.
    public int numRatings() {
        return myRatings.size();
    }

    //A method getItemsRated that has no parameters. This method returns an ArrayList of Strings representing a list of all the items that have been rated. 
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    }
}
