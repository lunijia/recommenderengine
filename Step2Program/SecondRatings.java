
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    //Write an additional SecondRatings constructor that has two String parameters named moviefile and ratingsfile.
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRating = new FirstRatings();
        this.myMovies = firstRating.loadMovies(moviefile);
        this.myRaters = firstRating.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        HashMap<String, Integer> raterAndRating = new HashMap<>();
        for(Rater rater: myRaters) {
            String raterID = rater.getID();
            if (!raterAndRating.containsKey(raterID)){
                raterAndRating.put(raterID, 1);
            } 
            else{
                raterAndRating.put(raterID, raterAndRating.get(raterID) + 1);
            }
        }
        return raterAndRating.size();
    }
    
    public ArrayList<Rater> getMyRaters(){
        return myRaters;
    }
    
    public ArrayList<Movie> getMyMovies(){
        return myMovies;
    }
    
    public double getAverageByID(String movieID, int minimalRaters){
        
        double averageRating = -1;
        int numOfRaters = 0;
        double totalRatingScores = 0;
        
        //ArrayList<Rater> ratersList = secondRatings.getMyRaters();
        for (Rater rater: myRaters) {
            ArrayList<Rating> ratingList = rater.getMyRatings();
            for (Rating rating: ratingList) {
                if (rating.getItem().equals(movieID)) {
                    numOfRaters++;
                    totalRatingScores += rating.getValue();
                }
            }
        }
        
        if (numOfRaters >= minimalRaters) {
            return averageRating = totalRatingScores/ numOfRaters;
        }
        else{
            return averageRating;
        } 
    } 
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgRatingList = new ArrayList<>();
        HashMap<String, Integer> movieAndNumRating = new HashMap<>();
        
        //ArrayList<Rater> ratersList = secondRatings.getMyRaters();
        for (Rater rater: myRaters) {
            ArrayList<Rating> ratingList = rater.getMyRatings();
            for (Rating rating: ratingList) {
                String movieID = rating.getItem();
                if (!movieAndNumRating.containsKey(movieID)) {
                    movieAndNumRating.put(movieID, 1);
                }
                else{
                    movieAndNumRating.put(movieID,movieAndNumRating.get(movieID) + 1);
                }
            }
        }
        
        for (String movieID: movieAndNumRating.keySet()) {
            if(movieAndNumRating.get(movieID) >= minimalRaters){
                double avgRating = getAverageByID(movieID, minimalRaters); 
                avgRatingList.add(new Rating(movieID,avgRating));
            }
        }
        
        return avgRatingList;
        
    }
    
    
    public String getTitle(String movieID) {
        String movieTitle = "The Movie Title is not found";
        for (Movie movie: myMovies) {
            if (movie.getID().equals(movieID)) {
                return movieTitle = movie.getTitle();
            }
        }
        return movieTitle;
    }
    
    public String getID(String title) {
        String movieID= "NO SUCH TITLE.";
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)){
                movieID = movie.getID();
            }
        }
        return movieID;
    }
}
