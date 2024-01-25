package mk.ukim.finki.diansvinarii.controller;

import mk.ukim.finki.diansvinarii.model.Vinarija;
import mk.ukim.finki.diansvinarii.repository.VinarijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Validated
@CrossOrigin(origins="*")
public class VinarijaController {

    private final VinarijaRepository vinarijaRepository;

    @Autowired
    public VinarijaController(VinarijaRepository vinarijaRepository) {
        super();
        this.vinarijaRepository = vinarijaRepository;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vinarija>> getWineriesbySearch(@RequestParam (required = false) String query){
        if(query!=null && !query.trim().isEmpty()) return new ResponseEntity<>(vinarijaRepository.findAllByName(query), HttpStatus.OK);

        return new ResponseEntity<>(vinarijaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vinarija>> getAllWineries(){
        return new ResponseEntity<>(vinarijaRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
