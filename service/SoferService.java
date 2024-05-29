package service;

import model.Sofer;
import repository.SoferRepository;

public class SoferService extends BaseService<Sofer> {
    private SoferRepository soferRepository;

    private SoferService() {
        this.soferRepository = new SoferRepository();
    }

    @Override
    public void create(Sofer sofer) {
        soferRepository.addSofer(sofer);
    }

    @Override
    public Sofer read(int id) {
        return soferRepository.getSoferById(id);
    }

    @Override
    public void update(Sofer sofer) {
        soferRepository.updateSofer(sofer);
    }

    @Override
    public void delete(int id) {
        soferRepository.deleteSofer(id);
    }

    public void createTable() {
        soferRepository.createTable();
    }

    public Sofer getSoferByUsernameAndPassword(String username, String password) {
        return soferRepository.getSoferByUsernameAndPassword(username, password);
    }

    public boolean emailExists(String email) {
        return soferRepository.emailExists(email);
    }

    public void initialize() {
        createTable();
        for (int i = 1; i <= 5; i++) {
            Sofer sofer = new Sofer("Driver" + i, "driver" + i + "@example.com", "driver" + i, "password" + i, "Location" + i);
            sofer.setDisponibilitate(i % 2 == 0);
            create(sofer);
        }
    }
}
