package service;

import model.Comanda;
import model.ItemComanda;
import model.ItemMeniu;
import repository.ComandaRepository;

public class ComandaService extends BaseService<Comanda> {
    private ComandaRepository comandaRepository;

    private ComandaService() {
        this.comandaRepository = new ComandaRepository();
    }

    @Override
    public void create(Comanda comanda) {
        comandaRepository.addComanda(comanda);
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

    public void initialize() {
        createTable();
        for (int i = 1; i <= 5; i++) {
            Comanda comanda = new Comanda();
            for (int j = 1; j <= 5; j++) {
                ItemMeniu itemMeniu = ItemMeniuService.getInstance(ItemMeniuService.class).read(j);
                ItemComanda itemComanda = new ItemComanda(itemMeniu, j);
                comanda.adaugaItemComanda(itemComanda);
            }
            create(comanda);
        }
    }
}
