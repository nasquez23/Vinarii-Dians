package mk.ukim.finki.diansvinarii.repository;

import mk.ukim.finki.diansvinarii.model.Vinarii;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VinariiRepository extends JpaRepository<Vinarii, Long> {
    List<Vinarii> findAllByName(String text);
    // ovoj metod sluzi za da filtrirame samo ime
    List<Vinarii> findAllByid(Long id);
    // ovoj metod sluzi za da filtrirame id

    List<Vinarii> findAll();
    // ovoj metod sluzi za da gi listame site

}
