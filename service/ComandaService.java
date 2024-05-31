package service;

import model.Comanda;
import model.ItemComanda;
import model.ItemMeniu;
import repository.ComandaRepository;

public class ComandaService implements CRUDService<Comanda> {
    private ComandaRepository comandaRepository;
    private static ComandaService instance;

    public static ComandaService getInstance() {
        if (instance == null) {
            instance = new ComandaService();
        }
        return instance;
    }

    private ComandaService() {
        this.comandaRepository = new ComandaRepository();
    }

    @Override
    public int create(Comanda comanda) {
        return comandaRepository.addComanda(comanda);
    }

    @Override
    public Comanda read(int id) {
        return comandaRepository.getComandaById(id);
    }

    @Override
    public void update(Comanda comanda) {
        comandaRepository.updateComanda(comanda);
    }

    @Override
    public void delete(int id) {
        comandaRepository.deleteComanda(id);
    }

    public void createTable() {
        comandaRepository.createTable();
    }


}
