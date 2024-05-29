package service;

import model.Manager;
import repository.ManagerRepository;

public class ManagerService extends BaseService<Manager> {
    private ManagerRepository managerRepository;

    private ManagerService() {
        this.managerRepository = new ManagerRepository();
    }

    @Override
    public void create(Manager manager) {
        managerRepository.addManager(manager);
    }

    @Override
    public Manager read(int id) {
        return managerRepository.getManagerById(id);
    }

    @Override
    public void update(Manager manager) {
        managerRepository.updateManager(manager);
    }

    @Override
    public void delete(int id) {
        managerRepository.deleteManager(id);
    }

    public void createTable() {
        managerRepository.createTable();
    }

    public Manager getManagerByUsernameAndPassword(String username, String password) {
        return managerRepository.getManagerByUsernameAndPassword(username, password);
    }

    public boolean emailExists(String email) {
        return managerRepository.emailExists(email);
    }

    public void initialize() {
        createTable();
        for (int i = 1; i <= 5; i++) {
            Manager manager = new Manager("Manager" + i, "manager" + i + "@example.com", "manager" + i, "password" + i);
            create(manager);
        }
    }
}
