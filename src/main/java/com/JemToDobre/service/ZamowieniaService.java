package com.JemToDobre.service;

import com.JemToDobre.model.Pozycje_Zamowienia;
import com.JemToDobre.model.Zamowienia;
import com.JemToDobre.repository.ZamowieniaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ZamowieniaService {
    private final ZamowieniaRepository zamowieniaRepository;

    public ZamowieniaService(ZamowieniaRepository zamowieniaRepository) {
        this.zamowieniaRepository = zamowieniaRepository;
    }

    public Zamowienia createOrder() {
        Zamowienia zamowienie = new Zamowienia();
        zamowienie.setPozycjeZamowienia(new ArrayList<>());
        zamowienie.setLacznaCena(0.0);
        return zamowieniaRepository.save(zamowienie);
    }

    public void addItemToOrder(Zamowienia zamowienie, Pozycje_Zamowienia pozycjaZamowienia) {
        zamowienie.addPozycjaZamowienia(pozycjaZamowienia);
        zamowienie.setLacznaCena(zamowienie.getLacznaCena() + pozycjaZamowienia.getCena() * pozycjaZamowienia.getIlosc());
        zamowieniaRepository.save(zamowienie);
    }
}
