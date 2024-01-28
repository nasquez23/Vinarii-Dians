package mk.ukim.finki.wineryservice.service.impl;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wineryservice.model.Winery;
import mk.ukim.finki.wineryservice.repository.WineryRepository;
import mk.ukim.finki.wineryservice.service.WineryService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class WineryServiceImpl implements WineryService {

    private final WineryRepository wineryRepository;

    public WineryServiceImpl(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
        //initializeData();
    }

    @Override
    public List<Winery> findAllByName(String name, Long id) {
        if (name != null && id == null) {
            return wineryRepository.findAllByName(name);
        } else if (name == null && id != null) {
            return wineryRepository.findAllByid(id);
        } else {
            return wineryRepository.findAll();
        }
    }

    @Override
    public List<Winery> listVinarii() {
        return wineryRepository.findAll();
    }

    @Override
    public Optional<Winery> findById(Long id) throws Exception {
        return Optional.ofNullable(wineryRepository.findById(id).orElseThrow(Exception::new));
    }

    @Override
    public void saveWinery(Long id, String name, String phone, String website, Double longitude, Double latitude, String openHours, String closeHours) throws Exception {
        Winery Winery = wineryRepository.findById(id).orElseThrow(Exception::new);

        Winery.setName(name);
        Winery.setPhone(phone);
        Winery.setWebsite(website);
        Winery.setLongitude(longitude);
        Winery.setLatitude(latitude);
        Winery.setOpenHours(openHours);
        Winery.setCloseHours(closeHours);

        wineryRepository.save(Winery);
    }

    @Override
    public void deleteWinery(Long id) {
        wineryRepository.deleteById(id);
    }

    @Override
    public void editWinery(Long id, String name, String phone, String website, Double longitude, Double latitude, String openHours, String closeHours) throws Exception {
        Winery Winery = wineryRepository.findById(id).orElseThrow(Exception::new);

        Winery.setName(name);
        Winery.setPhone(phone);
        Winery.setWebsite(website);
        Winery.setLongitude(longitude);
        Winery.setLatitude(latitude);
        Winery.setOpenHours(openHours);
        Winery.setCloseHours(closeHours);

        wineryRepository.save(Winery);
    }

    @PostConstruct
    private void initializeData(){
        try (Scanner scanner = new Scanner(new File("src/main/resources/wineries.csv"))) {
            // Прескокни наслови
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            // Читај csv линија по линија
            while (scanner.hasNextLine()) {
                wineryRepository.save(Winery.csvItemToWinery(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}