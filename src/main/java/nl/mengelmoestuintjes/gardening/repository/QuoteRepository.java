package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

//    @Override
//    Page<Quote> findAll(Pageable pageable);

    Optional<Quote> findByIdOrderById(Long id);
}
