package service;

import model.ItemMeniu;
import repository.ItemMeniuRepository;

public class ItemMeniuService implements CRUDService<ItemMeniu> {
    private ItemMeniuRepository itemMeniuRepository;
    private static ItemMeniuService instance;

    ItemMeniuService() {
        this.itemMeniuRepository = new ItemMeniuRepository();
    }

    public static ItemMeniuService getInstance() {
        if (instance == null) {
            instance = new ItemMeniuService();
        }
        return instance;
    }

    @Override
    public int create(ItemMeniu itemMeniu) {
        return itemMeniuRepository.addItemMeniu(itemMeniu);
    }

    @Override
    public ItemMeniu read(int id) {
        return itemMeniuRepository.getItemMeniuById(id);
    }

    @Override
    public void update(ItemMeniu itemMeniu) {
        itemMeniuRepository.updateItemMeniu(itemMeniu);
    }

    @Override
    public void delete(int id) {
        itemMeniuRepository.deleteItemMeniu(id);
    }

    public void createTable() {
        itemMeniuRepository.createTable();
    }



}
