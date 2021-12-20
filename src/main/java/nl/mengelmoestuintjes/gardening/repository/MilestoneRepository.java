package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
