package mk.ukim.finki.diansvinarii.repository;

import mk.ukim.finki.diansvinarii.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAllByWinery_Id(Long id);
    public List<Review> findAllByWinery_IdOrderByScore(Long id);
    public List<Review> findAllByWinery_IdOrderByTimestampDesc(Long id);


}
