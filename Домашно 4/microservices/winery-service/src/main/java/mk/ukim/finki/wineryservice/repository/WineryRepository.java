package mk.ukim.finki.wineryservice.repository;


import mk.ukim.finki.wineryservice.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WineryRepository extends JpaRepository<Winery, Long> {
    List<Winery> findAllByName(String name); // Метод за филтрирање на винарии според име
    
    List<Winery> findAllByid(Long id); // Метод за филтрирање на винарии според id

    List<Winery> findAll(); // Метод за добивање на листа со сите винарии

    Optional<Winery> findById(Long id); // Метод за добивање на винарија според id
}
