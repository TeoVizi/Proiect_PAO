package service;

import model.ItemComanda;
import repository.ItemComandaRepository;

public class ItemComandaService implements CRUDService<ItemComanda> {
    private ItemComandaRepository itemComandaRepository;
    private static ItemComandaService instance;
    ItemComandaService() {
        this.itemComandaRepository = new ItemComandaRepository();
    }

    public static ItemComandaService getInstance() {
        if (instance == null) {
            instance = new ItemComandaService();
        }
        return instance;
    }
    @Override
    public void create(ItemComanda itemComanda) {
        // Use appropriate comandaId
        // itemComandaRepository.addItemComanda(itemComanda, comandaId);
    }

    @Override
    public ItemComanda read(int id) {
        // Implement correctly
        return null;
    }

    @Override
    public void update(ItemComanda itemComanda) {
        // Implement correctly
    }

    @Override
    public void delete(int id) {
        itemComandaRepository.deleteItemComanda(id);
    }

    public void createTable() {
        itemComandaRepository.createTable();
    }


}
