package mk.ukim.finki.wineryservice.service;


import mk.ukim.finki.wineryservice.model.Winery;

import java.util.List;
import java.util.Optional;

public interface WineryService {

    List<Winery> findAllByName(String text, Long value);

    List<Winery> listVinarii();

    Optional<Winery> findById(Long id) throws Exception;

    void saveWinery(Long id, String name, String phone, String website, Double longitude, Double latitude, String openHours, String closeHours) throws Exception;

    void editWinery(Long id, String name, String phone, String website, Double longitude, Double latitude, String openHours, String closeHours) throws Exception;

    void deleteWinery(Long id);
}
