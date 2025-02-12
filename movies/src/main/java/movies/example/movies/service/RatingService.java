package movies.example.movies.service;


import lombok.Data;
import movies.example.movies.entity.Movie;
import movies.example.movies.entity.Rating;
import movies.example.movies.entity.User;
import movies.example.movies.repository.MovieRepository;
import movies.example.movies.repository.RatingRepository;
import movies.example.movies.repository.UserRepository;
import movies.example.movies.request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private  MovieRepository movieRepository;
    @Autowired
    private  UserRepository userRepository;

    public Rating saveRating(RatingRequest ratingRequest) {


        Movie movie = movieRepository.findById(ratingRequest.getMovie_id())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        User user = userRepository.findById(ratingRequest.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setUser(user);
        rating.setRating(ratingRequest.getRating());
        rating.setMessage(ratingRequest.getMessage());

        Rating savedRating = ratingRepository.save(rating);




        //         fetch all rating that belong to the new rating movie
        //        and mac count for all it rating
        //         the calc the avg of this
        //        update the rating val of the movie

        updateMovieRating(ratingRequest.getMovie_id());


        return savedRating;
    }


    private void updateMovieRating(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Double avgRating = ratingRepository.findAverageRatingByMovieId(movieId);



        avgRating = Math.round(avgRating * 10.0) / 10.0;
        movie.setRating_value(avgRating);
//        System.out.println(avgRating);
        movieRepository.save(movie);
    }


}
