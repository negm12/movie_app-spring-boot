package movies.example.movies.controller;


import lombok.Data;
import movies.example.movies.entity.Movie;
import movies.example.movies.service.MovieService;
import movies.example.movies.service.OMDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/movie")
public class AdminController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private OMDBService omdbService;


    @GetMapping("/search")
    public List<Movie> searchMovie(@RequestParam(required = false) String t,@RequestParam(required = false) String i){
        System.out.println("t "+t +" i "+ i);
        return omdbService.searchMovies(t,i);
    }



//    @GetMapping("/")
//    public List<Movie> getAllMovies(){
//        return movieService.getAllMovies();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getMovie(@PathVariable Long id){
//        try {
//            return ResponseEntity.ok(movieService.getMovieById(id));
//        }catch (Exception e){
//            return (ResponseEntity<?>) ResponseEntity.notFound();
//        }
//
//    }

    @PostMapping()
    public Movie addMovie(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    @PostMapping("/batch")
    public List<Movie> addMovies(@RequestBody List<Movie> movies){
        return movieService.addMovies(movies);
    }

    @DeleteMapping("/batch")
    public void deleteMovies(@RequestBody List<Long> moviesId){
         movieService.deleteMovies(moviesId);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long  id){
        movieService.deleteMovie(id);
    }
}
