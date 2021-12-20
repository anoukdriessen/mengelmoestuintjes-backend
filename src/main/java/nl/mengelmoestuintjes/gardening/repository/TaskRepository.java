package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.tasks.Task;
import nl.mengelmoestuintjes.gardening.model.tasks.TypeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Iterable<Task> findByType(TypeTask type);
    Iterable<Task> findByDoneFalse();
    Iterable<Task> findByTypeAndDoneFalse(TypeTask type);
}
