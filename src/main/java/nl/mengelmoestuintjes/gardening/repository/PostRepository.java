//package nl.mengelmoestuintjes.gardening.repository;
//
//import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;
//import nl.mengelmoestuintjes.gardening.model.posts.Post;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface PostRepository extends JpaRepository<Post, Integer> {
//    Iterable<Post> findByVisibleTrue();
//    Iterable<Post> findAllByAuthorAndVisible(String authorName, boolean visible);
//    Iterable<Post> findAllByCategoryAndVisible(PostCategory category, boolean visible);
//
//    Iterable<Post> findAllByAuthor(String authorName);
//    Iterable<Post> findAllByCategory(PostCategory category);
//}
