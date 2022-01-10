package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Iterable<Task> findByTypeAndDone(TaskType type, boolean isDone);
    Iterable<Task> findAllByTypeAndTitleContainingAndDone(TaskType type, String title, boolean isDone);
}
