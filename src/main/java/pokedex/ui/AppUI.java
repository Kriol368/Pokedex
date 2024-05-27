package pokedex.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex.entity.Map;
import pokedex.entity.Pokemon;
import pokedex.repository.MapRepository;
import pokedex.repository.PokemonRepository;
import pokedex.repository.TrainerRepository;
import pokedex.service.AuthenticationService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import pokedex.entity.Trainer;

import static java.lang.Integer.parseInt;

@Component
public class AppUI extends JFrame {
    private AuthenticationService authenticationService;
    private PokemonRepository pokemonRepository; // Add this line
    private TrainerRepository trainerRepository;
    private MapRepository mapRepository;
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
    private JProgressBar progressBar;
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
    private JScrollPane pokedex_scroll;

    private int currentUserImageIndex = 1;
    private Trainer loggedInUser = null; // Track the logged-in user
    private int currentMapId = 0;
    private Map currentMapClass;
    @Autowired
    public AppUI(AuthenticationService authenticationService, PokemonRepository pokemonRepository, TrainerRepository trainerRepository,MapRepository mapRepository) {
        this.authenticationService = authenticationService;
        this.pokemonRepository = pokemonRepository; // Initialize the repository
        this.trainerRepository = trainerRepository; // Initialize the trainer repository
        this.mapRepository = mapRepository;
        this.currentMapClass = null;
        setTitle("Pokedex");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        pokedex_list.setModel(new DefaultListModel<>());
        loadPokedexData();
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
                if (loggedInUser == null) {
                    // Perform login
                    String enteredUsername = username.getText();
                    String enteredPassword = new String(password.getPassword());

                    // Perform login or registration
                    String resultMessage = authenticationService.loginOrRegister(enteredUsername, enteredPassword);

                    // Display message based on the result
                    JOptionPane.showMessageDialog(AppUI.this, resultMessage, "Authentication Result", JOptionPane.INFORMATION_MESSAGE);

                    // If login is successful, load the trainer's image and update progress bar
                    if (resultMessage.equals("Login successful")) {
                        loggedInUser = trainerRepository.findByName(enteredUsername);
                        if (loggedInUser != null) {
                            loadTrainerInfo(loggedInUser.getName());
                            loginButton.setText("Logout");
                        }
                    }
                } else {
                    // Perform logout
                    loggedInUser = null;
                    loginButton.setText("Login");
                    clearUserSession();
                    JOptionPane.showMessageDialog(AppUI.this, "Logged out successfully.", "Logout", JOptionPane.INFORMATION_MESSAGE);
                }
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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCurrentImage();
            }
        });
        previousMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseCurrentMapId();
                // Update the UI to display the new current map
                setCurrentMapImage(String.valueOf(currentMapId));
            }
        });
        nextMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Increase the current map ID
                increaseCurrentMapId();
                // Update the UI to display the new current map
                setCurrentMapImage(String.valueOf(currentMapId));
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
        if (parseInt(t1) == 2 || parseInt(t1) == 12 || parseInt(t1) == 13 || parseInt(t1) == 16 || parseInt(t1) == 21) {
            characterImage.setSize(new Dimension(260, 600));
        }else if (parseInt(t1) == 6 || parseInt(t1) == 7 || parseInt(t1) == 10 || parseInt(t1) == 18 ) {
            characterImage.setSize(new Dimension(240, 600));
        } else if (parseInt(t1) == 9) {
            characterImage.setSize(new Dimension(350, 600));
        } else if (parseInt(t1) == 14 || parseInt(t1) == 20) {
            characterImage.setSize(new Dimension(200, 600));
        } else {
            characterImage.setSize(new Dimension(300, 600));
        }

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
    public void loadPokedexData() {
        Font f = new Font("Arial", Font.PLAIN, 40);
        pokedex_list.setFont(f);
        List<Pokemon> pokedexEntries = pokemonRepository.findAllByOrderBySpeciesIdAscIdAsc();
        DefaultListModel<String> pokedexListModel = (DefaultListModel<String>) pokedex_list.getModel();
        pokedexListModel.clear();
        for (Pokemon pokemon : pokedexEntries) {
            pokedexListModel.addElement(pokemon.getSpeciesId() + " - " + capitalizeFirstLetter(pokemon.getIdentifier()));
        }
    }
    private void saveCurrentImage() {
        String username = this.username.getText();
        Trainer trainer = trainerRepository.findByName(username);
        if (trainer != null) {
            trainer.setImage(currentUserImageIndex);
            trainerRepository.save(trainer);
            JOptionPane.showMessageDialog(this, "Image saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error saving image.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTrainerInfo(String name) {
        Trainer trainer = trainerRepository.findByName(name);
        if (trainer != null) {
            currentUserImageIndex = trainer.getImage();
            setTrainerImage(String.valueOf(currentUserImageIndex));
            loggedInUser = trainer; // Ensure loggedInUser is updated
            updateProgressBar(); // Update the progress bar after loading the trainer

        }
    }
    private void clearUserSession() {
        // Clear the user session details (UI elements) when logging out
        username.setText("");
        password.setText("");
        currentUserImageIndex = 1;
        setTrainerImage("1");
        progressBar.setValue(0);
        progressBar.setString("0%");
    }
    private void updateProgressBar() {
        if (loggedInUser != null) {
            int trainerId = loggedInUser.getId();
            long registeredCount = trainerRepository.countRegisteredPokemons(trainerId);
            long totalCount = trainerRepository.countTotalPokemons(trainerId);

            int progressPercentage = totalCount > 0 ? (int) ((registeredCount * 100) / totalCount) : 0;
            progressBar.setValue(progressPercentage);
            progressBar.setString(progressPercentage + "%");
            progressBar.setStringPainted(true);
        }
    }
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    // Define methods to increase and decrease the current map ID
    private void increaseCurrentMapId() {
        currentMapId++;
        if (currentMapId > 22) {
            currentMapId = 1; // Wrap around to 1 if exceeding 22
        }
    }

    private void decreaseCurrentMapId() {
        currentMapId--;
        if (currentMapId < 1) {
            currentMapId = 22; // Wrap around to 22 if going below 1
        }
    }
    // Method to update the current map object based on the current map ID
    private void updateCurrentMap() {
        // Fetch the map object from the database based on the current map ID
        // Assuming you have a method to fetch map details by ID from the repository
        this.currentMapClass = mapRepository.findById(currentMapId).orElse(null);
    }

    // Modify the updateMapDetails() method to use the currentMap object
    private void updateMapDetails() {
        if (currentMapClass != null) {
            // Update the cityName label with the map name
            cityName.setText(currentMapClass.getName());

            // Update the cityData label with the map description
            cityData.setText(currentMapClass.getDescription());
        } else {
            // Handle the case when the current map is null
            cityName.setText("Unknown Map");
            cityData.setText("No description available");
        }
    }

}
