package mk.ukim.finki.reviewsservice.service;

import mk.ukim.finki.reviewsservice.model.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {
    public List<Review> findAllByWinery_Id(Long id);

    public Double getWineryAverageScoreById(Long id);

    public List<Review> getNMostRecentByWineryId(Long id, int n);

    public List<Review> getNBestByWineryId(Long id, int n);

    public List<Review> findAllByWinery_IdOrderByScore(Long id);

    public Review add(Review review);

    public Review findById(Long id);
    
    public void delete(Review review);
    public Review create(Long id, int score, String desc, LocalDateTime timestamp, Long userId);
}
