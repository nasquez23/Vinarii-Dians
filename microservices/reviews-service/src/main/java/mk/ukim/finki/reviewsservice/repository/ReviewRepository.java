package mk.ukim.finki.reviewsservice.repository;


import mk.ukim.finki.reviewsservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByWineryId(Long id); // Метод за добивање на листа со коментари за винарија според id
    public List<Review> findAllByWineryIdOrderByScore(Long id); // Метод за добивање на листа со коментари за винарија според оценка
    public List<Review> findAllByWineryIdOrderByTimestampDesc(Long id); // Метод за добивање на листа со коментари за винарија според датум на објавување
    public Optional<Review> findById(Long id); // Метод за добивање на коментар според id
}
