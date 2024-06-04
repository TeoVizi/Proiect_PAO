package service;

import model.ItemMeniu;
import model.Meniu;
import model.Restaurant;
import repository.MeniuRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeniuService implements CRUDService<Meniu> {
    private MeniuRepository meniuRepository;
    private static MeniuService instance;


    MeniuService() {
        this.meniuRepository = new MeniuRepository();
    }

    public static MeniuService getInstance() {
        if (instance == null) {
            instance = new MeniuService();
        }
        return instance;
    }

    @Override
    public int create(Meniu meniu) {
       return meniuRepository.addMeniu(meniu, meniu.getRestaurantId());
    }

    @Override
    public Meniu read(int id) {
        return meniuRepository.getMeniuById(id);
    }

    @Override
    public void update(Meniu meniu) {
        meniuRepository.updateMeniu(meniu);
    }

    @Override
    public void delete(int id) {
        meniuRepository.deleteMeniu(id);
    }

    public void createTable() {
        meniuRepository.createTable();
    }

    public Meniu getMeniuByRestaurantId(int restaurantId) {
        return meniuRepository.getMeniuByRestaurantId(restaurantId);
    }


    public ItemMeniu getItemMeniuById(int itemId) {
        return meniuRepository.getItemMeniuById(itemId);
    }
}
