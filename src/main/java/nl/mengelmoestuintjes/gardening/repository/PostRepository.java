package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByTitleContainingAndPublished(String title, boolean published);
    Iterable<Post> findAllByCategory(PostCategory category);
    Iterable<Post> findByPublished(boolean published);
    Iterable<Post> findAllByCategoryAndPublished(PostCategory category, boolean published);
    Iterable<Post> findAllBySummaryContainingAndPublishedTrue(String summary);
}
