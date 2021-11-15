package by.morunov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Alex Morunov
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    private LocalDateTime publicationDate;

    @PrePersist
    public void setDate() {
        publicationDate = LocalDateTime.now();
    }
    @PreUpdate
    public void setDateOnUpdate() {
        publicationDate = LocalDateTime.now();
    }
}
