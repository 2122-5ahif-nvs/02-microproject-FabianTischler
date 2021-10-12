package at.htl.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "L_AUTHOR")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_ID")
    private Long id;
    @Column(name = "A_FIRSTNAME")
    private String authorFirstName;
    @Column(name = "A_LASTNAME")
    private String authorLastName;
    @Column(name = "A_NATIONALITY")
    private String nationality;

    public Author() {
    }

    public Author(String authorFirstName, String authorLastName, String nationality) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.nationality = nationality;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.authorFirstName = nationality;
    }

    public Long getId() {
        return id;
    }
}
