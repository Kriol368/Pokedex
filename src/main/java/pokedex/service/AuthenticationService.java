package pokedex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex.entity.Trainer;
import pokedex.repository.TrainerRepository;

@Service
public class AuthenticationService {

    @Autowired
    private TrainerRepository trainerRepository;

    public boolean authenticate(String name, String password) {
        Trainer trainer = trainerRepository.findByName(name);
        return trainer != null && trainer.getPassword().equals(password);
    }

    public boolean register(String name, String password) {
        Trainer existingTrainer = trainerRepository.findByName(name);
        if (existingTrainer != null) {
            return false; // User already exists
        } else {
            Trainer newTrainer = new Trainer(name, password);
            trainerRepository.save(newTrainer);
            return true; // Registration successful
        }
    }
}
