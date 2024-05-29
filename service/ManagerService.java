package service;

import model.Manager;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    private static List<Manager> managers = new ArrayList<>();

    public static void adaugaManager(Manager manager) {
        managers.add(manager);
    }

    public static Manager gasesteManager(String username) {
        for (Manager manager : managers) {
            if (manager.getUsername().equals(username)) {
                return manager;
            }
        }
        return null;
    }
}
