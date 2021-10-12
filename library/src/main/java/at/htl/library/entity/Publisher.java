package at.htl.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "L_PUBLISHER")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;
    @Column(name = "P_NAME")
    private String publisherName;

    public Publisher() {
    }

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Long getId() {
        return id;
    }
}
