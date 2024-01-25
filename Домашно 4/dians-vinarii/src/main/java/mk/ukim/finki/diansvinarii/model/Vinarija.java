package mk.ukim.finki.diansvinarii.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "[Vinarija]")
@NoArgsConstructor
@AllArgsConstructor
public class Vinarija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String phone;
    String website;
    Double longitude;
    Double latitude;
    String openHours;
    String closeHours;

    public Vinarija(String name, String phone, String website, Double lon, Double lat, String openHours, String closeHours) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.longitude = lon;
        this.latitude = lat;
        this.openHours = openHours;
        this.closeHours = closeHours;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public void setCloseHours(String closeHours) {
        this.closeHours = closeHours;
    }

    public static Vinarija csvItemToVinarija(String line){
        String[] items = line.split(",");
        return new Vinarija(items[2], items[3], items[4], Double.parseDouble(items[0]), Double.parseDouble(items[1]), items[5], items[6]);
    }
}
