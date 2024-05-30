package service;

import model.Restaurant;
import repository.RestaurantRepository;

import java.util.List;

public class RestaurantService implements CRUDService<Restaurant> {
    private RestaurantRepository restaurantRepository;
    private static RestaurantService instance;

    RestaurantService() {
        this.restaurantRepository = new RestaurantRepository();
    }

    public static RestaurantService getInstance() {
        if (instance == null) {
            instance = new RestaurantService();
        }
        return instance;
    }

    @Override
    public void create(Restaurant restaurant) {
        restaurantRepository.addRestaurant(restaurant);
    }

    @Override
    public Restaurant read(int id) {
        return restaurantRepository.getRestaurantById(id);
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantRepository.updateRestaurant(restaurant);
    }

    @Override
    public void delete(int id) {
        restaurantRepository.deleteRestaurant(id);
    }

    public void createTable() {
        restaurantRepository.createTable();
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAllRestaurants();
    }
}
