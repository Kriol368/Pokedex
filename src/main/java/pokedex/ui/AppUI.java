package pokedex.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex.repository.PokemonRepository;
import pokedex.service.AuthenticationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AppUI extends JFrame {
    private AuthenticationService authenticationService;
    private PokemonRepository pokemonRepository; // Add this line

    private JTabbedPane mainPane;
    private JPanel panel1;
    private JPanel pokedex;
    private JPanel team;
    private JPanel map;
    private JPanel typeChart;
    private JPanel network;
    private JPanel Trainer;
    private JList pokedex_list;
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
    private JLabel Type1;
    private JLabel Type2;
    private JLabel PokedexNumber;
    private JPanel DataPanel;
    private JButton pokemon1Button;
    private JButton pokemon2Button;
    private JButton pokemon3Button;
    private JButton pokemon4Button;
    private JButton pokemon5Button;
    private JButton pokemon6Button;
    private JList registedList;
    private JPanel InteractiveTeamPanel;
    private JPanel RegisteredPanel;
    private JButton SaveTeamButton;
    private JLabel SelectedPokemonName;
    private JLabel SelectedPokemonType1;
    private JLabel SelectedPokemonType2;
    private JLabel SelectedPokemonImg;
    private JPanel mapPanel;
    private JLabel currentMap;
    private JPanel dataMapPanel;
    private JButton previousMapButton;
    private JButton nextMapButton;
    private JLabel cityName;
    private JLabel cityData;

    private int currentUserImageIndex = 1;

    @Autowired
    public AppUI(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.pokemonRepository = pokemonRepository; // Initialize the repository
        setTitle("Pokedex");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        showMainPane();
        setpokemonTeamImages("1", "1", "1", "1", "1", "1");
        setTrainerImage("1");
        setSelectedPokemonImg("1");
        setPokemonImage("1");
        setPokedexTypeIcons("1", "2");
        setCurrentMapImage("0");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = username.getText();
                String enteredPassword = new String(password.getPassword());

                // Perform login or registration
                String resultMessage = authenticationService.loginOrRegister(enteredUsername, enteredPassword);

                // Display message based on the result
                JOptionPane.showMessageDialog(AppUI.this, resultMessage, "Authentication Result", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add action listeners to the arrow buttons
        leftArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Decrease the image index and update the character image
                currentUserImageIndex--;
                if (currentUserImageIndex < 1) {
                    currentUserImageIndex = 21; // Return to 21 if less than 1
                }
                setTrainerImage(String.valueOf(currentUserImageIndex));
            }
        });

        rightArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Increase the image index and update the character image
                currentUserImageIndex++;
                if (currentUserImageIndex > 21) {
                    currentUserImageIndex = 1; // Return to 1 if greater than 21
                }
                setTrainerImage(String.valueOf(currentUserImageIndex));
            }
        });
    }

    public void showMainPane() {
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

    public String setPokemonImageIcon(String ordernum) {
        return "src/main/resources/official_artwork/normal/" + ordernum + ".png";
    }

    public void setpokemonTeamImages(String p1, String p2, String p3, String p4, String p5, String p6) {
        pokemon1.setIcon(getScaledImage(setPokemonImageIcon(p1), pokemon1.getWidth(), pokemon1.getHeight()));
        pokemon2.setIcon(getScaledImage(setPokemonImageIcon(p2), pokemon2.getWidth(), pokemon2.getHeight()));
        pokemon3.setIcon(getScaledImage(setPokemonImageIcon(p3), pokemon3.getWidth(), pokemon3.getHeight()));
        pokemon4.setIcon(getScaledImage(setPokemonImageIcon(p4), pokemon4.getWidth(), pokemon4.getHeight()));
        pokemon5.setIcon(getScaledImage(setPokemonImageIcon(p5), pokemon5.getWidth(), pokemon5.getHeight()));
        pokemon6.setIcon(getScaledImage(setPokemonImageIcon(p6), pokemon6.getWidth(), pokemon6.getHeight()));
    }

    public String setTrainerImageIcon(String ordernum) {
        return "src/main/resources/trainers/" + ordernum + ".png";
    }

    public void setTrainerImage(String t1) {
        characterImage.setIcon(getScaledImage(setTrainerImageIcon(t1), characterImage.getWidth(), characterImage.getHeight()));
    }

    public void setSelectedPokemonImg(String t1) {
        SelectedPokemonImg.setIcon(getScaledImage(setPokemonImageIcon(t1), SelectedPokemonImg.getWidth(), SelectedPokemonImg.getHeight()));
    }

    public void setPokemonImage(String t1) {
        pokemonImage.setIcon(getScaledImage(setPokemonImageIcon(t1), pokemonImage.getWidth(), pokemonImage.getHeight()));
    }

    public String setTypeImage(String ordernum) {
        return "src/main/resources/icons/" + ordernum + ".png";
    }

    public void setPokedexTypeIcons(String t1, String t2) {
        Type1.setIcon(getScaledImage(setTypeImage(t1), Type1.getWidth(), Type1.getHeight()));
        Type2.setIcon(getScaledImage(setTypeImage(t2), Type2.getWidth(), Type2.getHeight()));
    }

    public String setCurrentMap(String ordernum) {
        return "src/main/resources/map/" + ordernum + ".jpg";
    }

    public void setCurrentMapImage(String t1) {
        currentMap.setIcon(getScaledImage(setCurrentMap(t1), currentMap.getWidth(), currentMap.getHeight()));
    }
}
