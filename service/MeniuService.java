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
    private static Map<Restaurant, Meniu> meniuri = new HashMap<>();

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

    public static void adaugaItemMeniuRestaurant(Restaurant restaurant, ItemMeniu item) {
        Meniu meniu = meniuri.get(restaurant);
        if (meniu == null) {
            meniu = new Meniu();
            meniuri.put(restaurant, meniu);
        }
        meniu.adaugaItemMeniu(item);
        System.out.println("Preparatul '" + item.getNume() + "' a fost adaugat cu succes la meniul restaurantului '" + restaurant.getNume() + "'.");
    }

    public static boolean existaItemMeniuRestaurant(Restaurant restaurant, String numeItem) {
        if (restaurant != null) {
            Meniu meniu = meniuri.get(restaurant);
            if (meniu != null) {
                List<ItemMeniu> itemeMeniu = meniu.getListaItemiMeniu();
                for (ItemMeniu item : itemeMeniu) {
                    if (item.getNume().equalsIgnoreCase(numeItem)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static ItemMeniu gasesteItemMeniu(String numeItem) {
        for (Map.Entry<Restaurant, Meniu> entry : meniuri.entrySet()) {
            Meniu meniu = entry.getValue();
            if (meniu != null) {
                List<ItemMeniu> itemeMeniu = meniu.getListaItemiMeniu();
                for (ItemMeniu item : itemeMeniu) {
                    if (item.getNume().equalsIgnoreCase(numeItem)) {
                        return item;
                    }
                }
            }
        }
        return null;
    }

    public static void afiseazaMeniuRestaurant(Restaurant restaurant) {
        Meniu meniu = meniuri.get(restaurant);
        if (meniu != null && !meniu.getListaItemiMeniu().isEmpty()) {
            System.out.println("Meniul pentru restaurantul " + restaurant.getNume() + " este:");
            for (ItemMeniu item : meniu.getListaItemiMeniu()) {
                System.out.println("- " + item.getNume() + ": " + item.getDescriere() + " - " + item.getPret() + " lei");
            }
        } else {
            System.out.println("Nu exista meniu pentru restaurantul " + restaurant.getNume() + ".");
        }
    }

    public static Map<Restaurant, Meniu> getMeniuri() {
        return meniuri;
    }

    public ItemMeniu getItemMeniuById(int itemId) {
        return meniuRepository.getItemMeniuById(itemId);
    }
}
