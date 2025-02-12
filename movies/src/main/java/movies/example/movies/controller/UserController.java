package movies.example.movies.controller;

import lombok.Data;
import movies.example.movies.entity.Movie;
import movies.example.movies.service.MovieService;
import movies.example.movies.service.OMDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/movie")
public class UserController {
    @Autowired
    private OMDBService omdbService;
    @Autowired
    private MovieService movieService;

    @GetMapping()
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable Long id){
        try {
            return ResponseEntity.ok(movieService.getMovieById(id));
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchByTitle(@RequestParam String title){
        try {
            return ResponseEntity.ok(movieService.getMovieByTitle(title));
        }catch (Exception e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }

    }


}
