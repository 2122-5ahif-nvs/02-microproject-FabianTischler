package at.htl.library.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;

@Entity
@Table(name = "L_AUTHOR")
@Schema(description = "Authors of the Books in the DB")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_ID")
    @Schema(required = true)
    private Long id;
    @Column(name = "A_FIRSTNAME")
    @JsonbProperty("author_firstname")
    private String authorFirstName;
    @Column(name = "A_LASTNAME")
    @JsonbProperty("author_lastname")
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
