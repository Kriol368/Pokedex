package pokedex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedex.entity.Register;
import pokedex.entity.Trainer;
import pokedex.entity.Pokemon;
import pokedex.repository.RegisterRepository;
import pokedex.repository.TrainerRepository;
import pokedex.repository.PokemonRepository;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private RegisterRepository registerRepository;

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
            Trainer savedTrainer = trainerRepository.save(newTrainer);
            System.out.println("New trainer registered: " + savedTrainer.getName());
            createInitialRegistersForTrainer(savedTrainer); // Pass the trainer object
            return true; // Registration successful
        }
    }


    // New combined method
    public String loginOrRegister(String name, String password) {
        Trainer trainer = trainerRepository.findByName(name);
        if (trainer != null) {
            // Attempt login
            if (trainer.getPassword().equals(password)) {
                return "Login successful";
            } else {
                return "Invalid password";
            }
        } else {
            // Register new user
            Trainer newTrainer = new Trainer(name, password);
            trainerRepository.save(newTrainer);
            return "Registration successful";
        }
    }

    public void createInitialRegistersForTrainer(Trainer trainer) {
        System.out.println("Creating initial registers for trainer: " + trainer.getName());
        List<Pokemon> allPokemons = pokemonRepository.findAll();
        for (Pokemon pokemon : allPokemons) {
            Register register = new Register(pokemon, trainer, 0);
            System.out.println(register.getPokemon() + " " + register.getTrainer());
            registerRepository.save(register);
            System.out.println("Register created for Pokemon: " + pokemon.getId() + " and Trainer: " + trainer.getId());
        }
    }



}
