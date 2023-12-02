package mk.ukim.finki.diansvinarii.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "[vinarii]")
@NoArgsConstructor
@AllArgsConstructor
public class Vinarii {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String imeVinarija;
    String email;
    String broj;
    String website;
    String lokacija;

    public Vinarii(String imeVinarija, String email, String broj, String website, String lokacija) {
        this.imeVinarija = imeVinarija;
        this.email = email;
        this.broj = broj;
        this.website = website;
        this.lokacija = lokacija;
    }

    public void setImeVinarija(String imeVinarija) {
        this.imeVinarija = imeVinarija;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
}
