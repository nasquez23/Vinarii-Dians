package mk.ukim.finki.diansvinarii.service.impl;

import mk.ukim.finki.diansvinarii.model.Vinarii;
import mk.ukim.finki.diansvinarii.repository.VinariiRepository;
import mk.ukim.finki.diansvinarii.service.VinariiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VinariiServiceImpl implements VinariiService {

    private final VinariiRepository vinariiRepository;

    public VinariiServiceImpl(VinariiRepository vinariiRepository) {
        this.vinariiRepository = vinariiRepository;
    }

    @Override
    public List<Vinarii> searchVinarii(String text, Long value) {
        if (text != null && value == null) {
            return vinariiRepository.findAllByName(text);
        } else if (text == null && value != null) {
            return vinariiRepository.findAllByid(value);
        } else {
            return vinariiRepository.findAll();
        }

    }

    @Override
    public List<Vinarii> listVinarii() {
        return vinariiRepository.findAll();
    }

    @Override
    public Optional<Vinarii> findById(Long id) throws Exception {
        return Optional.ofNullable(vinariiRepository.findById(id).orElseThrow(Exception::new));
    }

    @Override
    public void saveVinarija(Long id, String imeVinarija, String email, String broj, String website, String lokacija) throws Exception {
        Vinarii vinarija = vinariiRepository.findById(id).orElseThrow(Exception::new);

        vinarija.setImeVinarija(imeVinarija);
        vinarija.setBroj(broj);
        vinarija.setEmail(email);
        vinarija.setWebsite(website);
        vinarija.setLokacija(lokacija);

        vinariiRepository.save(vinarija);
    }

    @Override
    public void deleteVinarija(Long id) {
        vinariiRepository.deleteById(id);
    }
    @Override
    public void editVinarija(Long id, String imeVinarija, String email, String broj, String website, String lokacija) throws Exception {
        Vinarii vinarija = vinariiRepository.findById(id).orElseThrow(Exception::new);

        vinarija.setImeVinarija(imeVinarija);
        vinarija.setBroj(broj);
        vinarija.setEmail(email);
        vinarija.setWebsite(website);
        vinarija.setLokacija(lokacija);

        vinariiRepository.save(vinarija);
    }
}