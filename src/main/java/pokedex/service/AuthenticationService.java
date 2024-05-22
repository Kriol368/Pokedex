package pokedex.service;

import jakarta.transaction.Transactional;
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


    // New combined method
    @Transactional
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
            createInitialRegistersForTrainer(newTrainer);
            return "Registration successful";
        }
    }

    @Transactional
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
