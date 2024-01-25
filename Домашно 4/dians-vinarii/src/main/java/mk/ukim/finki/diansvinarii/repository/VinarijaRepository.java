package mk.ukim.finki.diansvinarii.repository;

import mk.ukim.finki.diansvinarii.model.Vinarija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VinarijaRepository extends JpaRepository<Vinarija, Long> {
    List<Vinarija> findAllByName(String name);
    // ovoj metod sluzi za da filtrirame samo ime
    List<Vinarija> findAllByid(Long id);
    // ovoj metod sluzi za da filtrirame id

    List<Vinarija> findAll();
    // ovoj metod sluzi za da gi listame site

    Optional<Vinarija> findById(Long id);

}
