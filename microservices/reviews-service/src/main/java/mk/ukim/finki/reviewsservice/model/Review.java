package mk.ukim.finki.reviewsservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "[Review]")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;

    @Column(length = 4000)
    private String description;

    private Long wineryId;
    private Long userId; //createdBy
    @DateTimeFormat()
    private LocalDateTime timestamp;

    public Review(int score, String description, Long wineryId, Long userId, LocalDateTime timestamp) {
        this.score = score;
        this.description = description;
        this.wineryId = wineryId;
        this.userId = userId;
        this.timestamp = timestamp;
    }

}
