package at.htl.library.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "L_BOOK")
@XmlRootElement
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_ID")
    private Long id;
    @Column(name = "B_NAME")
    private String bookName;
    //@Column(name = "B_PUBLISHER")
    @ManyToOne(cascade = CascadeType.ALL)
    private Publisher publisher;
    //@Column(name = "B_AUTHOR")
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;
    @Column(name = "B_LENT")
    private int lent;

    public Book(String bookName, Publisher publisher, Author author, int lent) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.author = author;
        this.lent = lent;
    }
    public Book() {

    }

    public int getLent() {
        return lent;
    }

    public void setLent(int lent) {
        this.lent = lent;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

