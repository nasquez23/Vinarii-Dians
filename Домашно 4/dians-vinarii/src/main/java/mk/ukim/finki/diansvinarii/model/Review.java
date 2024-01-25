package mk.ukim.finki.diansvinarii.model;

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

    @ManyToOne
    private Vinarija winery;

    @DateTimeFormat()
    private LocalDateTime timestamp;

    @ManyToOne
    private User createdBy;

    public Review(int score, String description, Vinarija winery, LocalDateTime timestamp, User createdBy) {
        this.score = score;
        this.description = description;
        this.winery = winery;
        this.timestamp = timestamp;
        this.createdBy = createdBy;
    }

}
