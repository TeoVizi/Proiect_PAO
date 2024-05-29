package service;

import model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public static void adaugaRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public static boolean unicitateRestaurant(String numeRestaurant) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getNume().equalsIgnoreCase(numeRestaurant)) {
                return false;
            }
        }
        return true;
    }

    public static Restaurant gasesteRestaurant(String numeRestaurant) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getNume().equalsIgnoreCase(numeRestaurant)) {
                return restaurant;
            }
        }
        return null;
    }
}
