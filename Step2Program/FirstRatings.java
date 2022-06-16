
/**
 * Write a description of FirstRatings here.
 * 
 * @Wen Tang
 * @06/11/2022
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource("data/"+filename);
        ArrayList<Movie> movieData = new ArrayList<Movie>();
        //If each line of the file represents separated data values, because its a CSV file, then the user can get a getCSVParser object to access that data more directly, using one of the getCSVParser methods.
        for (CSVRecord record : fr.getCSVParser()) {
            Movie newMovie = new Movie(record.get("id"),record.get("title"),record.get("year"), record.get("genre"),record.get("director"),
                                       record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));
            movieData.add(newMovie);
        }
        return movieData;
    }
    
    
    private HashMap<String, Integer> directorMovie(ArrayList<Movie> movieData){
        HashMap<String, Integer> directorNumMovies = new HashMap<>();
        for(Movie movie: movieData){
            String directorString = movie.getDirector();
            int start = 0;
            int end = 0;
            String individualDirector = "";
            if (directorString.contains(",")){
                while(directorString.contains(",")){
                    end = directorString.indexOf(",");
                    individualDirector = directorString.substring(start, end);
                    if (!directorNumMovies.containsKey(individualDirector)){
                        directorNumMovies.put(individualDirector, 1);
                    } 
                    else {
                        directorNumMovies.put(individualDirector, directorNumMovies.get(individualDirector) + 1);
                    }
                    directorString = directorString.substring(end + 1);  
                }
                if(!directorNumMovies.containsKey(directorString)) directorNumMovies.put(directorString, 1);
                else directorNumMovies.put(directorString,  directorNumMovies.get(directorString) + 1);
                    
            } 
            else{
                if (!directorNumMovies.containsKey(directorString)){
                    directorNumMovies.put(directorString, 1);
                } 
                else {
                    directorNumMovies.put(directorString, directorNumMovies.get(directorString) + 1);
                }
            }
        }
        return directorNumMovies;
    }
    
    
    public void testLoadMovies(){
        /*TEST loadmovies and related methods*/
        /************************************/
        //String filename = "ratedmovies_short_test.csv";
        String filename = "ratedmoviesfull.csv";
        ArrayList<Movie> movieData = loadMovies(filename);
        System.out.println("\nThe number of movies in file "+ filename + " is " + movieData.size());
        
        int countComedy = 0;
        for(Movie movie: movieData){
            if(movie.getGenres().contains("Comedy")){
                countComedy++;
            }
        }
        System.out.println("\nThe number of Comedy movies in file "+ filename + " is "+ countComedy);
        
        int countLengthMovieGreaterThan150 = 0;
        for(Movie movie: movieData){
            if(movie.getMinutes() > 150){
                countLengthMovieGreaterThan150++;
            }
        }
        System.out.println("\nThe number of movies whose length is > 150 mins in file "+ filename + " is "+ countLengthMovieGreaterThan150);
        
        
        Map<String, Integer> directorMovies = directorMovie(movieData);
        int max = 0;
        // using keySet() for iteration over keys
        for(String director : directorMovies.keySet()) {
            if(directorMovies.get(director) >= max ) {
                max = directorMovies.get(director);
            } 
        }
        System.out.println("\nThe maximum number of movies by any director is: " + max);
        
        for(String director : directorMovies.keySet()) {
            if(directorMovies.get(director) == max ) {
                System.out.println("\nThe director who made the maximum amount of movies is : " + director);
            } 
        }
    }

    
     
    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource("data/"+filename);
        ArrayList<Rater> raterData = new ArrayList<>();
       
        for (CSVRecord record : fr.getCSVParser()) {
            //create a new rater
            Rater newRater = new Rater(record.get("rater_id"));
            // add the rating to arraylist of this new rater
            newRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            raterData.add(newRater);
        }
        return raterData;
    }
 
    
    public void testLoadRaters(){
        //String filename = "ratings_short.csv";
        String filename = "ratings.csv";
        //String filename = "ratings.csv";
        ArrayList<Rater> raterData = loadRaters(filename);
        
        //Call the method loadRaters on the file ratings_short.csv and store the result in a local ArrayList variable. Print the total number of raters. 
        //Then for each rater, print the rater’s ID and the number of ratings they did on one line, followed by each rating (both the movie ID and the 
        //rating given) on a separate line. If you run your program on the file ratings_short.csv you will see there are five raters. After it looks like
        //it works, you may want to comment out the printing of each rater and their ratings. If you run your program on the file ratings.csv, you should get 1048 raters.
        HashMap<String, Integer> raterAndRating = new HashMap<>();
        for(Rater rater: raterData){
            if (!raterAndRating.containsKey(rater.getID())){
                raterAndRating.put(rater.getID(),1);
            }
            else{
                raterAndRating.put(rater.getID(),raterAndRating.get(rater.getID()) + 1);
            }
        }
        System.out.println("There are " + raterAndRating.size() + " raters in this file");
       

        //Add code to find the number of ratings for a particular rater you specify in your code. For example, if you run this code on the rater whose rater_id
        //is 2 for the file ratings_short.csv, you will see they have three ratings. 
        //String rater_toSearchFor = "2";
        String rater_toSearchFor = "193";
        if (raterAndRating.containsKey(rater_toSearchFor)) {
            System.out.println("\nThe Rater of rater_id of " + rater_toSearchFor + " has " + raterAndRating.get(rater_toSearchFor) + " ratings");
        } 
         
        //Add code to find the maximum number of ratings by any rater. Determine how many raters have this maximum number of ratings and who those raters are. 
        //If you run this code on the file ratings_short.csv, you will see rater 2 has three ratings, the maximum number of ratings of all the raters, and that
        //there is only one rater with three ratings.
        int maxNumOfRating = 0;
  
        for(String rater : raterAndRating.keySet()){
            if (raterAndRating.get(rater) >= maxNumOfRating) {
                maxNumOfRating = raterAndRating.get(rater);
            }
        }
        System.out.println("\nThe maximum number of ratings by any rater is: " + maxNumOfRating);
      
        for(String rater : raterAndRating.keySet()){
            if (raterAndRating.get(rater)== maxNumOfRating) {
                System.out.println("The number of raters who rated so many movies is " + rater);
            }
        }
        
        //Add code to find the number of ratings a particular movie has. If you run this code on the file ratings_short.csv for the movie “1798709”, 
        //you will see it was rated by four raters.
        String movie_toSearchFor = "1798709";
        int numOfRaters = 0;
        for(Rater rater : raterData){
            ArrayList<Rating> ratingArray = rater.getMyRatings();
            for(Rating rating: ratingArray) {
                if (rating.getItem().equals(movie_toSearchFor)){
                    numOfRaters++;
                }
            }
        }
        System.out.println("\nThe movie of movie_id of " + movie_toSearchFor + " has " + numOfRaters + " ratings");
        
        
        //Add code to determine how many different movies have been rated by all these raters. If you run this code on the file ratings_short.csv, 
        //you will see there were four movies rated.
        int numOfMovies = 0;
        HashSet<String> movieSet = new HashSet<>();
        for(Rater rater : raterData){
            ArrayList<Rating> ratingArray = rater.getMyRatings();
            for(Rating rating: ratingArray) {
                if (!movieSet.contains(rating.getItem())){
                    movieSet.add(rating.getItem());
                    numOfMovies++;
                }
            }
        }
        System.out.println("\nThe number of movies rated by all raters in this file is " + numOfMovies);  
        
    }
  
}

