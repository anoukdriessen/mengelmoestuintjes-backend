package nl.mengelmoestuintjes.gardening.model;

import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {

    /*
      Quote bestaat uit:
      - naam van de auteur
      - text
      TODO link implementeren naar profiel / social

      alleen moderators kunnen quotes toevoegen in hun dashboard
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String text;

    public Quote(){}
    public Quote(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public int getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }
    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setText(String text) {
        this.text = text;
    }
}
