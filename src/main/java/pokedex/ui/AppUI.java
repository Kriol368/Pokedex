package pokedex.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex.repository.*;

import javax.swing.*;
import java.awt.*;

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
    private JList pokedex_list;
    private JList teamList;
    private JSplitPane teamSplit;
    private JSplitPane team_slots_split;
    private JSplitPane split_team_1;
    private JSplitPane split_team_2;
    private JSplitPane split_team_3;
    private JSplitPane split_team_4;
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
    private JPanel listpanel;
    private JLabel pokemonImage;
    private JButton registerButton;
    private JLabel PokemonName;
    private JLabel Typ1;
    private JLabel Type2;
    private JLabel PokedexNumber;

    @Autowired
    public AppUI(Pokemon_typesRepository pokemonTypesRepository, PokemonRepository pokemonRepository, RegisterRepository registerRepository, TeamRepository teamRepository, TrainerRepository trainerRepository,TypeRepository typeRepository) {
        setTitle("Pokedex");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // This line sets the application to fullscreen
        setLocationRelativeTo(null);
        setVisible(true);
        showMainPane();
        setpokemonTeamImages("1", "1", "1", "1", "1", "1");
        setTrainerImage("1");
    }
    public void showMainPane(){
        setContentPane(mainPane);
        validate();
        repaint();
    }
    private ImageIcon getScaledImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
    public String setPokemonImageIcon(String ordernum){
        return "src/main/resources/official_artwork/normal/" + ordernum + ".png";
    }
    public void setpokemonTeamImages (String p1 , String p2, String p3, String p4, String p5, String p6){
        pokemon1.setIcon(getScaledImage(setPokemonImageIcon(p1), pokemon1.getWidth(), pokemon1.getHeight()));
        pokemon2.setIcon(getScaledImage(setPokemonImageIcon(p2), pokemon2.getWidth(), pokemon2.getHeight()));
        pokemon3.setIcon(getScaledImage(setPokemonImageIcon(p3), pokemon3.getWidth(), pokemon3.getHeight()));
        pokemon4.setIcon(getScaledImage(setPokemonImageIcon(p4), pokemon4.getWidth(), pokemon4.getHeight()));
        pokemon5.setIcon(getScaledImage(setPokemonImageIcon(p5), pokemon5.getWidth(), pokemon5.getHeight()));
        pokemon6.setIcon(getScaledImage(setPokemonImageIcon(p6), pokemon6.getWidth(), pokemon6.getHeight()));
    }
    public String setTrainerImageIcon(String ordernum){
        return "src/main/resources/trainers/" + ordernum + ".png";
    }
    public void setTrainerImage (String t1){
        characterImage.setIcon(getScaledImage(setTrainerImageIcon(t1), characterImage.getWidth(), characterImage.getHeight()));

    }
}
