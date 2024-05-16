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
    private JSplitPane pokedex_split;
    private JList pokedex_list;
    private JList teamList;
    private JSplitPane teamSplit;
    private JSplitPane team_slots_split;
    private JSplitPane split_team_1;
    private JSplitPane split_team_2;
    private JSplitPane split_team_3;
    private JSplitPane split_team_4;

    // TrainerPanel

    private JPanel Trainer;

    private JLabel labelUser;
    private JLabel labelPassword;
    private JTextField username;
    private JPasswordField password;
    private JProgressBar ProgressBar;
    private JLabel labelProgressBar;
    private JButton loginButton;
    private JButton leftArrowButton;
    private JButton saveButton;
    private JButton rightArrowButton;
    private JPanel LoginPanel;
    private JPanel ProgressPanel;
    private JPanel CharacterPanel;
    private JPanel TeamPanel;
    private JLabel characterImage;
    private JLabel pokemon1;
    private JLabel pokemon2;
    private JLabel pokemon3;
    private JLabel pokemon4;
    private JLabel pokemon5;
    private JLabel pokemon6;

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
