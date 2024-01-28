package mk.ukim.finki.diansvinarii.repository;

import mk.ukim.finki.diansvinarii.model.Vinarija;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VinarijaRepository extends JpaRepository<Vinarija, Long> {
    List<Vinarija> findAllByName(String name); // Метод за филтрирање на винарии според име
    
    List<Vinarija> findAllByid(Long id); // Метод за филтрирање на винарии според id

    List<Vinarija> findAll(); // Метод за добивање на листа со сите винарии

    Optional<Vinarija> findById(Long id); // Метод за добивање на винарија според id
}
