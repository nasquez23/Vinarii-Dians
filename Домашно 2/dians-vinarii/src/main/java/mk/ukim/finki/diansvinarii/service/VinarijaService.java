package mk.ukim.finki.diansvinarii.service;

import mk.ukim.finki.diansvinarii.model.Vinarija;

import java.util.List;
import java.util.Optional;

public interface VinarijaService {

    List<Vinarija> findAllByName(String text, Long value);

    List<Vinarija> listVinarii();

    Optional<Vinarija> findById(Long id) throws Exception;

    void saveVinarija(Long id, String name, String phone, String website, Double longitude, Double latitude, String openHours, String closeHours) throws Exception;

    void editVinarija(Long id, String name, String phone, String website, Double longitude, Double latitude, String openHours, String closeHours) throws Exception;

    void deleteVinarija(Long id);
}
