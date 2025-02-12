package movies.example.movies.repository;

import movies.example.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface MovieRepository extends JpaRepository<Movie,Long> {
    public List<Movie> findByTitleAndId(String title , Long id);
    public Optional<Movie> findById(Long id);
    public Optional<Movie> findByTitle(String title);
    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Movie> findByTitleLike(@Param("title") String title);
//    public  void  deleteBymdbID(String imdbID);
}
