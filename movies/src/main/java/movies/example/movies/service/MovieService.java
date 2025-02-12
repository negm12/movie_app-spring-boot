package movies.example.movies.service;

import lombok.Data;
import movies.example.movies.entity.Movie;
import movies.example.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
public class MovieService {

    @Autowired
    private  MovieRepository movieRepository;
    @Autowired
    private OMDBService omdbService;

//    public MovieService(MovieRepository movieRepository, OMDBService omdbService) {
//        this.movieRepository = movieRepository;
//        this.omdbService = omdbService;
//    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) throws Exception {
        return movieRepository.findById(id).orElseThrow(()->new Exception("this movie not found"));
    }

    public List<Movie> getMovieByTitle(String title) throws Exception {
        return movieRepository.findByTitleLike(title);
    }

    public Movie saveMovie(Movie movie)  {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id)  {
        movieRepository.findById(id).ifPresentOrElse(movieRepository::delete,
                ()-> {throw new RuntimeException("movie not found");});
    }

    public List<Movie> addMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    public void deleteMovies(List<Long> movieIds) {
        movieRepository.deleteAllById(movieIds);
    }


}
