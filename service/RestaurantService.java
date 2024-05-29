package service;

import model.Restaurant;
import repository.RestaurantRepository;

public class RestaurantService extends BaseService<Restaurant> {
    private RestaurantRepository restaurantRepository;

    private RestaurantService() {
        this.restaurantRepository = new RestaurantRepository();
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

    public void initialize() {
        createTable();
        for (int i = 1; i <= 5; i++) {
            Restaurant restaurant = new Restaurant("Restaurant" + i, "Address" + i, i * 5.0);
            create(restaurant);
        }
    }
}
