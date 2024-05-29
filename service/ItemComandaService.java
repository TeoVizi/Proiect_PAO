package service;

import model.ItemComanda;
import repository.ItemComandaRepository;

public class ItemComandaService extends BaseService<ItemComanda> {
    private ItemComandaRepository itemComandaRepository;

    private ItemComandaService() {
        this.itemComandaRepository = new ItemComandaRepository();
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

    public void initialize() {
        createTable();
        // Add sample itemComanda initialization logic
    }
}
