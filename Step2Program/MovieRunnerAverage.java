
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @Wen Tang 
 * @06/16/2022I 
 */
import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings() {
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        SecondRatings secondRatings = new SecondRatings(moviefile, ratingsfile);
        
        System.out.println(" The number of movies in file "+ moviefile + " is " + secondRatings.getMovieSize());
        System.out.println(" The number of raters in file "+ ratingsfile + " is " + secondRatings.getRaterSize());
    
        int minimalRaters = 12;
        int countMovies = 0;
        ArrayList<Rating> averageRatingList = secondRatings.getAverageRatings(minimalRaters);
        //sort the movie by ratings
        Collections.sort(averageRatingList);
        for (Rating rating: averageRatingList){
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
            countMovies++;
        }
        System.out.println("There are "+ countMovies + " that are rater by at least "+ minimalRaters +" raters.");
    }
    
    public void getAverageRatingOneMovie(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        SecondRatings secondRatings = new SecondRatings(moviefile, ratingsfile);
        
        //String movieTitle = "The Maze Runner";
        //String movieTitle = "Moneyball";
        String movieTitle = "Vacation";
        String movieID = secondRatings.getID(movieTitle);
        if (movieID.equals("NO SUCH TITLE.")){
            System.out.println("This movie has no rating");
        }
        else{
            double averageRating = secondRatings.getAverageByID(movieID,3);
            System.out.println("The average rating of " + movieTitle + " is "+ averageRating);
        
        }
    }
    
    
/**
    private double getAverageByID(String movieID, int minimalRaters){
        
        double averageRating = -1;
        int numOfRaters = 0;
        double totalRatingScores = 0;
        
        ArrayList<Rater> ratersList = secondRatings.getMyRaters();
        for (Rater rater: ratersList) {
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
        
        ArrayList<Rater> ratersList = secondRatings.getMyRaters();
        for (Rater rater: ratersList) {
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
        ArrayList<Movie> movieList = secondRatings.getMyMovies();
        for (Movie movie: movieList) {
            if (movie.getID().equals(movieID)) {
                return movieTitle = movie.getTitle();
            }
        }
        return movieTitle;
    }
  */  
}
