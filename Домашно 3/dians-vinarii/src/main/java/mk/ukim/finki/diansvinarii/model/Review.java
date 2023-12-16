package mk.ukim.finki.diansvinarii.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private String description;

    @ManyToOne
    private Vinarija winery;

    @DateTimeFormat()
    private LocalDateTime timestamp;

    public Review(int score, String description, Vinarija winery, LocalDateTime timestamp) {
        this.score = score;
        this.description = description;
        this.winery = winery;
        this.timestamp = timestamp;
    }

    public Review() {

    }
}
