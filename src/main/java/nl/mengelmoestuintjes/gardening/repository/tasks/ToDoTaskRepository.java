package nl.mengelmoestuintjes.gardening.repository.tasks;

import nl.mengelmoestuintjes.gardening.model.tasks.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ToDoTaskRepository extends JpaRepository<ToDoTask, Integer> {
    Iterable<ToDoTask> findByDoneTrue();
    Iterable<ToDoTask> findAllByDueDate(Date dueDate);
}
