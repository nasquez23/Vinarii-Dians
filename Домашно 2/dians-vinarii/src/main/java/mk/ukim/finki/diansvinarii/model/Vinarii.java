package mk.ukim.finki.diansvinarii.model;

public class Vinarii {
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

    public String getImeVinarija() {
        return imeVinarija;
    }

    public void setImeVinarija(String imeVinarija) {
        this.imeVinarija = imeVinarija;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
}
