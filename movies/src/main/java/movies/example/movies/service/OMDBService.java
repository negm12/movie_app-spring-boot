package movies.example.movies.service;

import movies.example.movies.entity.Movie;
import movies.example.movies.response.OMDBResponse;
import movies.example.movies.response.OMDBSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OMDBService {
    private static final String OMDB_API_URL = "http://www.omdbapi.com/?apikey=a0d92e53";

    @Autowired
    private RestTemplate restTemplate;

    public List<Movie> searchMovies(String title, String id) {
        try {
            if (title != null) {
//                System.out.println("title "+ title);
                return searchMoviesByTitle(title);
            }
            else if (id != null) {
//                System.out.println("id "+ id);
                return searchMovieById(id).map(List::of).orElse(List.of());
            }
        } catch (Exception e) {
            throw new RuntimeException("OMDB API error: " + e.getMessage());
        }
        return List.of();
    }

    private Optional<Movie> searchMovieById(String id) {
        try {
            String encodedId = URLEncoder.encode(id, StandardCharsets.UTF_8);
            String url = OMDB_API_URL + "&i=" + encodedId;
//            System.out.println("id " + url);
            OMDBResponse response = restTemplate.getForObject(url, OMDBResponse.class);

            if (response == null || !"True".equalsIgnoreCase(response.getResponse())) {
                return Optional.empty();
            }
            return Optional.of(convertToMovie(response));
        } catch (Exception e) {
            throw new RuntimeException("OMDB API error: " + e.getMessage());
        }
    }

    private List<Movie> searchMoviesByTitle(String title) {
        try {
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
            String url = OMDB_API_URL + "&s=" + encodedTitle;
//            System.out.println("title " + title);

            OMDBSearchResponse response = restTemplate.getForObject(url, OMDBSearchResponse.class);
//            OMDBResponse response = restTemplate.getForObject(url, OMDBResponse.class);


            if (response == null || response.getSearch() == null) {
                return List.of();
            }



            return response.getSearch().stream()
                    .map(this::convertToMovie)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("OMDB API error: " + e.getMessage());
        }
    }

    private Movie convertToMovie(OMDBResponse response) {
//        System.out.println("from mapper " + response.getTitle());
        Movie movie = new Movie();
        movie.setImdbID(response.getImdbID());
        movie.setTitle(response.getTitle());
        movie.setYear(response.getYear());
        movie.setRuntime(response.getRuntime());
        movie.setGenre(response.getGenre());
        movie.setDirector(response.getDirector());
        movie.setWriter(response.getWriter());
        movie.setActors(response.getActors());
        movie.setPlot(response.getPlot());
        movie.setLanguage(response.getLanguage());
        movie.setPoster(response.getPoster());
        return movie;
    }
}






















//package movies.example.movies.service;
//
//
//import movies.example.movies.entity.Movie;
//import movies.example.movies.response.OMDBResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Optional;
//
//@Service
//public class OMDBService {
//    private static final String OMDB_API_URL = "http://www.omdbapi.com/?apikey=a0d92e53";
//
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Optional<Movie> searchMovie(String title ,String id ) {
//        System.out.println("title " +title + " id "+id);
//
//        try{
//            String url = OMDB_API_URL;
////            System.out.println(encodedId + encodedTitle);
//            if (title !=null){
//                String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
//                url+="&t="+ encodedTitle;
////                System.out.println("title " + title==null);
//            } else if (id!=null){
//                String encodedId = URLEncoder.encode(id, StandardCharsets.UTF_8);
//                url+="&i="+ encodedId;
////                System.out.println("id  ");
//            }
////            System.out.println(url);
//
//
//            OMDBResponse response = restTemplate.getForObject(url, OMDBResponse.class);
//
//            if (response == null || !"True".equalsIgnoreCase(response.getResponse())) {
//                return Optional.empty();
//            }
//            return  Optional.of(convertToMovie(response));
//        }catch (Exception e){
//            throw new RuntimeException("OMDB API error: " + e.getMessage());
//        }
//
//    }
//
//    private Movie convertToMovie(OMDBResponse response) {
//        Movie movie = new Movie();
//        movie.setImdbID(response.getImdbID());
//        movie.setTitle(response.getTitle());
//        movie.setYear(response.getYear());
//        movie.setRuntime(response.getRuntime());
//        movie.setGenre(response.getGenre());
//        movie.setDirector(response.getDirector());
//        movie.setWriter(response.getWriter());
//        movie.setActors(response.getActors());
//        movie.setPlot(response.getPlot());
//        movie.setLanguage(response.getLanguage());
//        movie.setPoster(response.getPoster());
//        return movie;
//    }
//
//}
