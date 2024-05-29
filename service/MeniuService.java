package service;

import model.Meniu;
import repository.MeniuRepository;

public class MeniuService extends BaseService<Meniu> {
    private MeniuRepository meniuRepository;

    private MeniuService() {
        this.meniuRepository = new MeniuRepository();
    }

    @Override
    public void create(Meniu meniu) {
        meniuRepository.addMeniu(meniu, meniu.getRestaurantId());
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

    public void initialize() {
        createTable();
        // Add sample meniu initialization logic
    }
}
