package mk.ukim.finki.reviewsservice.service.impl;


import mk.ukim.finki.reviewsservice.model.Review;
import mk.ukim.finki.reviewsservice.repository.ReviewRepository;
import mk.ukim.finki.reviewsservice.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Добивање на сите коментари за одредена винарија
    public List<Review> findAllByWinery_Id(Long id){
        return reviewRepository.findAllByWineryId(id);
    }

    // Добивање на просечна оцена за одредена винарија
    public Double getWineryAverageScoreById(Long id){
        List<Review> reviews = reviewRepository.findAllByWineryId(id);
        return reviews.stream().map(Review::getScore).mapToInt(Integer::intValue).average().orElse(0);
    }

    // Добивање на N најнови коментари за винаријата
    public List<Review> getNMostRecentByWineryId(Long id, int n){
        return reviewRepository.findAllByWineryIdOrderByTimestampDesc(id).stream().limit(n).toList();
    }

    // Добивање на N најдобри коментари за винаријата
    public List<Review> getNBestByWineryId(Long id, int n){
        return reviewRepository.findAllByWineryIdOrderByScore(id).stream().limit(n).toList();
    }

    // Добивање на сите рецензии за винаријата, сортирани според оценката
    public List<Review> findAllByWinery_IdOrderByScore(Long id){
        return reviewRepository.findAllByWineryIdOrderByScore(id);
    }

    // Добивање на сите рецензии за винаријата, сортирани според датумот на објавување
    public List<Review> findAllByWinery_IdOrderByTimestampDesc(Long id){
        return reviewRepository.findAllByWineryIdOrderByTimestampDesc(id);
    }

    // Добивање на коментар според ID
    public Review findById(Long id){
        return reviewRepository.findById(id).orElse(null);
    }

    // Додавање на нов коментар
    public Review add(Review review){
        return reviewRepository.save(review);
    }

    public Review create(Long id, int score, String desc, LocalDateTime timestamp, Long userId) {
        Review review = new Review(score, desc, id, userId, timestamp);
        return reviewRepository.save(review);
    }

    // Бришење на коментар
    public void delete(Review review){
        reviewRepository.delete(review);
    }
}
