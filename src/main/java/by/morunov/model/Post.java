package by.morunov.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Alex Morunov
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column
    private String title;

    @Column
    private String text;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String title, String text, User user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return time;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @PrePersist
    public void setDate() {
        time = LocalDateTime.now();
    }
    @PreUpdate
    public void setDateOnUpdate() {
        time = LocalDateTime.now();
    }
}
