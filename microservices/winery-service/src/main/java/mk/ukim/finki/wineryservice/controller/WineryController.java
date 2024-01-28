package mk.ukim.finki.wineryservice.controller;

import mk.ukim.finki.wineryservice.model.Winery;
import mk.ukim.finki.wineryservice.repository.WineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Validated
@CrossOrigin(origins = "*")
public class WineryController {

    private final WineryRepository wineryRepository;

    @Autowired
    public WineryController(WineryRepository wineryRepository) {
        super();
        this.wineryRepository = wineryRepository;
    }

    // Метод за добивање на листа со винарии според барање
    @GetMapping("/search")
    public ResponseEntity<List<Winery>> getWineriesbySearch(@RequestParam (required = false) String query){
        if(query != null && !query.trim().isEmpty()) 
            return new ResponseEntity<>(wineryRepository.findAllByName(query), HttpStatus.OK); // Ако постои барање, врати листа со винарии што го задоволуваат

        return new ResponseEntity<>(wineryRepository.findAll(), HttpStatus.OK);  // Во спротивно, врати ги сите винарии
    }

    // Метод за добивање на листа со сите винарии
    @GetMapping("/all")
    public ResponseEntity<List<Winery>> getAllWineries(){
        return new ResponseEntity<>(wineryRepository.findAll(), HttpStatus.OK);
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
