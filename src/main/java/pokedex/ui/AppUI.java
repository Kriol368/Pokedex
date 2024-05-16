package pokedex.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex.repository.*;

import javax.swing.*;
@Component
public class AppUI extends JFrame{
    private JTabbedPane mainPane;
    private JPanel panel1;
    private JPanel pokedex;
    private JPanel team;
    private JPanel map;
    private JPanel typeChart;
    private JPanel network;
    private JPanel Trainer;
    private JSplitPane pokedex_split;
    private JList pokedex_list;
    private JList teamList;
    private JSplitPane teamSplit;
    private JSplitPane team_slots_split;
    private JSplitPane split_team_1;
    private JSplitPane split_team_2;
    private JSplitPane split_team_3;
    private JSplitPane split_team_4;

    @Autowired
    public AppUI(Pokemon_typesRepository pokemonTypesRepository, PokemonRepository pokemonRepository, RegisterRepository registerRepository, TeamRepository teamRepository, TrainerRepository trainerRepository,TypeRepository typeRepository) {
        setTitle("Pokedex");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setVisible(true);
        showMainPane();
    }
    public void showMainPane(){
        setContentPane(mainPane);
        validate();
        repaint();
    }
}
