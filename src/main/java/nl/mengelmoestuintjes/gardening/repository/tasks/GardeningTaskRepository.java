package nl.mengelmoestuintjes.gardening.repository.tasks;

import nl.mengelmoestuintjes.gardening.model.tasks.GardeningTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardeningTaskRepository extends JpaRepository<GardeningTask, Integer> {

    Iterable<GardeningTask> findByDoneTrue();
    Iterable<GardeningTask> findAllByMonth(int month);

}
