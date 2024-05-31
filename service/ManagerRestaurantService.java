package service;

import model.Restaurant;
import repository.ManagerRestaurantRepository;

import java.util.List;

public class ManagerRestaurantService {
    private static ManagerRestaurantService instance;
    private ManagerRestaurantRepository managerRestaurantRepository;

    private ManagerRestaurantService() {
        this.managerRestaurantRepository = new ManagerRestaurantRepository();
    }

    public static ManagerRestaurantService getInstance() {
        if (instance == null) {
            instance = new ManagerRestaurantService();
        }
        return instance;
    }

    public void createTable() {
        managerRestaurantRepository.createTable();
    }

    public void addManagerRestaurant(int managerId, int restaurantId) {
        managerRestaurantRepository.addManagerRestaurant(managerId, restaurantId);
    }

    public void removeManagerRestaurantByRestaurantId(int restaurantId) {
        managerRestaurantRepository.removeManagerRestaurantByRestaurantId(restaurantId);
    }

    public List<Restaurant> getRestaurantsByManagerId(int managerId) {
        return managerRestaurantRepository.getRestaurantsByManagerId(managerId);
    }
}
