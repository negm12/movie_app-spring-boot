package movies.example.movies.controller;

import lombok.Data;
import movies.example.movies.entity.Rating;
import movies.example.movies.repository.RatingRepository;
import movies.example.movies.request.RatingRequest;
import movies.example.movies.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;



    @PostMapping()
    public Rating saveRating (@RequestBody RatingRequest ratingRequest){
//        System.out.println("making rate");
//        System.out.println(ratingRequest.getMessage() + ratingRequest.getMovie_id() + ratingRequest.getUser_id());
        return ratingService.saveRating(ratingRequest);
    }
}
