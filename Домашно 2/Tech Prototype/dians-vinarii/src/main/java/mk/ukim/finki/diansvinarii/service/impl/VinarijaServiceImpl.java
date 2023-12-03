package mk.ukim.finki.diansvinarii.service.impl;

import mk.ukim.finki.diansvinarii.model.Vinarija;
import mk.ukim.finki.diansvinarii.repository.VinarijaRepository;
import mk.ukim.finki.diansvinarii.service.VinarijaService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class VinarijaServiceImpl implements VinarijaService {

    private final VinarijaRepository vinarijaRepository;

    public VinarijaServiceImpl(VinarijaRepository vinarijaRepository) {
        this.vinarijaRepository = vinarijaRepository;
        //initializeData();
    }

    @Override
    public List<Vinarija> findAllByName(String name, Long id) {
        if (name != null && id == null) {
            return vinarijaRepository.findAllByName(name);
        } else if (name == null && id != null) {
            return vinarijaRepository.findAllByid(id);
        } else {
            return vinarijaRepository.findAll();
        }

    }

    @Override
    public List<Vinarija> listVinarii() {
        return vinarijaRepository.findAll();
    }

    @Override
    public Optional<Vinarija> findById(Long id) throws Exception {
        return Optional.ofNullable(vinarijaRepository.findById(id).orElseThrow(Exception::new));
    }

    @Override
    public void saveVinarija(Long id, String name, String phone, String website, Double longitude, Double latitude, String openHours, String closeHours) throws Exception {
        Vinarija vinarija = vinarijaRepository.findById(id).orElseThrow(Exception::new);

        vinarija.setName(name);
        vinarija.setPhone(phone);
        vinarija.setWebsite(website);
        vinarija.setLongitude(longitude);
        vinarija.setLatitude(latitude);
        vinarija.setOpenHours(openHours);
        vinarija.setCloseHours(closeHours);

        vinarijaRepository.save(vinarija);
    }

    @Override
    public void deleteVinarija(Long id) {
        vinarijaRepository.deleteById(id);
    }
    @Override
    public void editVinarija(Long id, String name, String phone, String website, Double longitude, Double latitude, String openHours, String closeHours) throws Exception {
        Vinarija vinarija = vinarijaRepository.findById(id).orElseThrow(Exception::new);

        vinarija.setName(name);
        vinarija.setPhone(phone);
        vinarija.setWebsite(website);
        vinarija.setLongitude(longitude);
        vinarija.setLatitude(latitude);
        vinarija.setOpenHours(openHours);
        vinarija.setCloseHours(closeHours);

        vinarijaRepository.save(vinarija);
    }

    private void initializeData(){
        try (Scanner scanner = new Scanner(new File("src/main/resources/wineries.csv"))) {
            // Preskokni naslovi
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            //Citaj csv
            while (scanner.hasNextLine()) {
                vinarijaRepository.save(Vinarija.csvItemToVinarija(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}