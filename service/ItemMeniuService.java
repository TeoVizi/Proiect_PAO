package service;

import model.ItemMeniu;
import repository.ItemMeniuRepository;

public class ItemMeniuService extends BaseService<ItemMeniu> {
    private ItemMeniuRepository itemMeniuRepository;

    private ItemMeniuService() {
        this.itemMeniuRepository = new ItemMeniuRepository();
    }

    @Override
    public void create(ItemMeniu itemMeniu) {
        itemMeniuRepository.addItemMeniu(itemMeniu);
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

    public void initialize() {
        createTable();
        for (int i = 1; i <= 5; i++) {
            ItemMeniu itemMeniu = new ItemMeniu("Item" + i, "Description" + i, i * 10.0);
            create(itemMeniu);
        }
    }
}
