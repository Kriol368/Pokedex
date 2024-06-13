package pokedex.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pokedex.entity.*;
import pokedex.repository.*;

import java.util.List;

@Service
public class AuthenticationService {
    private final TrainerRepository trainerRepository;

    private final PokemonRepository pokemonRepository;

    private final RegisterRepository registerRepository;

    public AuthenticationService(TrainerRepository trainerRepository, PokemonRepository pokemonRepository, RegisterRepository registerRepository) {
        this.trainerRepository = trainerRepository;
        this.pokemonRepository = pokemonRepository;
        this.registerRepository = registerRepository;
    }


    // New combined method
    @Transactional
    public String loginOrRegister(String name, String password,TeamRepository teamRepository,PokemonTeamRepository pokemonTeamRepository, PokemonRepository pokemonRepository) {
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
            createInitialRegistersForTrainer(newTrainer, teamRepository, pokemonTeamRepository,pokemonRepository);
            return "Registration successful";
        }
    }

    @Transactional
    public void createInitialRegistersForTrainer(Trainer trainer, TeamRepository teamRepository, PokemonTeamRepository pokemonTeamRepository, PokemonRepository pokemonRepository) {
        System.out.println("Creating initial registers for trainer: " + trainer.getName());
        List<Pokemon> allPokemons = pokemonRepository.findAllByOrderByOrderAsc();
        for (Pokemon pokemon : allPokemons) {
            Register register = new Register(pokemon, trainer, 0);
            System.out.println(register.getPokemon() + " " + register.getTrainer());
            registerRepository.save(register);
            System.out.println("Register created for Pokemon: " + pokemon.getId() + " and Trainer: " + trainer.getId());
        }
        Team t = new Team(trainer);
        teamRepository.save(t);
        Pokemon p = pokemonRepository.findByIdentifier("ditto");

        PokemonTeam pt1 = new PokemonTeam(p,t,1);
        PokemonTeam pt2 = new PokemonTeam(p,t,2);
        PokemonTeam pt3 = new PokemonTeam(p,t,3);
        PokemonTeam pt4 = new PokemonTeam(p,t,4);
        PokemonTeam pt5 = new PokemonTeam(p,t,5);
        PokemonTeam pt6 = new PokemonTeam(p,t,6);
        pokemonTeamRepository.save(pt1);
        pokemonTeamRepository.save(pt2);
        pokemonTeamRepository.save(pt3);
        pokemonTeamRepository.save(pt4);
        pokemonTeamRepository.save(pt5);
        pokemonTeamRepository.save(pt6);
    }


}
