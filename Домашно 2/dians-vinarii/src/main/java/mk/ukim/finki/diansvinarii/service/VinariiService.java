package mk.ukim.finki.diansvinarii.service;

import mk.ukim.finki.diansvinarii.model.Vinarii;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VinariiService{

    List<Vinarii> searchVinarii(String text, Long value);

    List<Vinarii> listVinarii();

    Optional<Vinarii> findById(Long id) throws Exception;

    void saveVinarija(Long id, String imeVinarija, String email, String broj, String website, String lokacija) throws Exception;

    void editVinarija(Long id, String imeVinarija, String email, String broj, String website, String lokacija) throws Exception;

    void deleteVinarija(Long id);
}
