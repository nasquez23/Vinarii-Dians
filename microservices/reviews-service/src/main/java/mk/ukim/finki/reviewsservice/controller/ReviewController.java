package mk.ukim.finki.reviewsservice.controller;

import mk.ukim.finki.reviewsservice.model.Review;
import mk.ukim.finki.reviewsservice.service.impl.ReviewServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/review")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    // Добивање на сите коментари за одредена винарија
    @GetMapping("/all/{id}")
    public List<Review> getReviewsByWineryId(@PathVariable Long id, @RequestParam (required = false) String orderBy){
        if(Objects.equals(orderBy, "timestamp")) 
            return reviewService.findAllByWinery_IdOrderByTimestampDesc(id); // Ако orderBy параметарот е "timestamp", сортирај според време

        if(Objects.equals(orderBy, "score")) 
            return reviewService.findAllByWinery_IdOrderByScore(id); // Ако orderBy параметарот е "score", сортирај според оцена

        return reviewService.findAllByWinery_Id(id); // Во спротивно, врати ги сите коментари за винаријата
    }

    // Добивање на најдобрите N коментари за одредена винарија
    @GetMapping(value = {"/best/{id}/{num}", "/best/{id}"})
    public List<Review> getBestNReviewsByWineryId(@PathVariable Long id, @PathVariable (required = false) Integer num){
        if(num == null) return reviewService.findAllByWinery_Id(id); // Ако num параметарот не е проследен, врати ги сите коментари за винаријата

        return reviewService.getNBestByWineryId(id, num); // Во спротивно, врати ги најдобрите N коментари за винаријата
    }

    // Добивање на просек на оцени за одредена винарија
    @GetMapping("/score/{id}")
    public double getWineryAverageScore(@PathVariable Long id){
        return reviewService.getWineryAverageScoreById(id);
    }

    // Додавање на нов коментар за винарија
    @PostMapping("/add/{id}")
    public Review addReview(@PathVariable Long id, @RequestBody Map<String, Object> requestBody) {
        String timestampString = (String) requestBody.get("timestamp");
        System.out.println(timestampString);
        LocalDateTime timestamp = LocalDateTime.parse(timestampString, DateTimeFormatter.ISO_DATE_TIME);
        int score = (int) requestBody.get("score");
        String desc = (String) requestBody.get("desc");
        Long userId = Long.valueOf((Integer) requestBody.get("userId"));

        return reviewService.create(id, score, desc, timestamp, userId);
    }

    // Добивање на коментар според ID
    @GetMapping("/get/{id}")
    public Review getReview(@PathVariable Long id){
        return reviewService.findById(id);
    }
}
