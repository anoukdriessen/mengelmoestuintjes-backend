package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Iterable<Post> findAllByAuthor(String authorName);
//    Iterable<Post> findAllContainingIgnoreCase(String title);

//    @Query("SELECT * from posts p WHERE p.title LIKE %:p%")
//    Iterable<Post> searchByTitleLike(@Param("p") String p);

}
