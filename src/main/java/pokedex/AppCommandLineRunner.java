package pokedex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pokedex.repository.*;
import pokedex.ui.AppUI;

import java.awt.*;

@Component
public class AppCommandLineRunner implements CommandLineRunner {
    private final PokemonRepository pokemonRepository;
    private final Pokemon_typesRepository pokemonTypesRepository;
    private final RegisterRepository registerRepository;
    private final TeamRepository teamRepository;
    private final TrainerRepository trainerRepository;
    private final TypeRepository typeRepository;
    @Autowired
    public AppCommandLineRunner(PokemonRepository pokemonRepository, Pokemon_typesRepository pokemonTypesRepository, RegisterRepository registerRepository, TeamRepository teamRepository, TrainerRepository trainerRepository, TypeRepository typeRepository){
        this.pokemonRepository = pokemonRepository;
        this.pokemonTypesRepository = pokemonTypesRepository;
        this.registerRepository = registerRepository;
        this.teamRepository = teamRepository;
        this.trainerRepository = trainerRepository;
        this.typeRepository = typeRepository;
    }
    @Override
    public void run(String... args) {
        //This boots up the GUI.
        //EventQueue.invokeLater(()  ->  new AppUI(pokemonRepository,pokemonTypesRepository,registerRepository,teamRepository,trainerRepository,typeRepository).setVisible(true));
    }
}
