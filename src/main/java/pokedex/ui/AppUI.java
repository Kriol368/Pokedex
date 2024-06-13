package pokedex.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex.audio.AudioPlayer;
import pokedex.entity.*;
import pokedex.repository.*;
import pokedex.service.AuthenticationService;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class AppUI extends JFrame {
    private AuthenticationService authenticationService;
    private PokemonRepository pokemonRepository; // Add this line
    private TrainerRepository trainerRepository;
    private TypeRepository typeRepository;
    private Pokemon_typesRepository pokemonTypesRepository;
    private MapRepository mapRepository;
    private RegisterRepository registerRepository;
    private TypeEfficacyRepository typeEfficacyRepository;
    private TeamRepository teamRepository;
    private PokemonTeamRepository pokemonTeamRepository;
    private Pokemon teamMember1 = null;
    private Pokemon teamMember2 = null;
    private Pokemon teamMember3 = null;
    private Pokemon teamMember4 = null;
    private Pokemon teamMember5 = null;
    private Pokemon teamMember6 = null;

    private JLabel trainerpanelImage;
    private AudioPlayer audioPlayer;
    private JTabbedPane mainPane;
    private JPanel panel1;
    private JPanel pokedex;
    private JPanel team;
    private JPanel map;
    private JPanel typeChart;
    private JPanel network;
    private JPanel TrainerPanel;
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
    private JTextField searchByNameTxt;
    private JButton searchButton;
    private JLabel nameLabel;
    private JPanel selectorPanel;
    private JPanel resultsPanel;
    private JLabel firstType;
    private JLabel secondType;
    private JButton type1;
    private JButton type2;
    private JButton type3;
    private JButton type4;
    private JButton type10;
    private JButton type13;
    private JButton type16;
    private JButton type5;
    private JButton type6;
    private JButton type7;
    private JButton type8;
    private JButton type9;
    private JButton type11;
    private JButton type12;
    private JButton type14;
    private JButton type15;
    private JButton type17;
    private JButton type18;
    private JButton type2_1;
    private JButton type2_2;
    private JButton type2_3;
    private JButton type2_4;
    private JButton type2_5;
    private JButton type2_6;
    private JButton type2_7;
    private JButton type2_8;
    private JButton type2_9;
    private JButton type2_10;
    private JButton type2_11;
    private JButton type2_12;
    private JButton type2_13;
    private JButton type2_14;
    private JButton type2_15;
    private JButton type2_16;
    private JButton type2_17;
    private JButton type2_18;
    private JLabel selectedType1;
    private JPanel typesPanel;
    private JLabel relatedType1;
    private JLabel relatedType2;
    private JLabel relatedType3;
    private JLabel relatedType4;
    private JLabel relatedType5;
    private JLabel relatedType6;
    private JLabel relatedType7;
    private JLabel relatedType8;
    private JLabel relatedType9;
    private JLabel relatedType10;
    private JLabel relatedType11;
    private JLabel relatedType12;
    private JLabel relatedType13;
    private JLabel relatedType14;
    private JLabel relatedType15;
    private JLabel relatedType16;
    private JLabel relatedType17;
    private JLabel relatedType18;
    private JLabel selectedType2;
    private JButton boton;
    private JPanel layered;
    private JLabel label;
    private JScrollPane pokedex_scroll;

    private int currentUserImageIndex = 1;
    private Trainer loggedInUser = null; // Track the logged-in user
    private int currentMapId = 0;
    private Map currentMapClass;

    @Autowired
    public AppUI(AuthenticationService authenticationService, PokemonRepository pokemonRepository, TrainerRepository trainerRepository, MapRepository mapRepository, TypeRepository typeRepository, Pokemon_typesRepository pokemonTypesRepository, RegisterRepository registerRepository, TypeEfficacyRepository typeEfficacyRepository, PokemonTeamRepository pokemonTeamRepository, TeamRepository teamRepository, AudioPlayer audioPlayer) throws IOException {
        this.authenticationService = authenticationService;
        this.pokemonRepository = pokemonRepository; // Initialize the repository
        this.trainerRepository = trainerRepository; // Initialize the trainer repository
        this.mapRepository = mapRepository;
        this.typeRepository = typeRepository;
        this.pokemonTypesRepository = pokemonTypesRepository;
        this.registerRepository = registerRepository;
        this.typeEfficacyRepository = typeEfficacyRepository;
        this.teamRepository = teamRepository;
        this.pokemonTeamRepository = pokemonTeamRepository;
        this.audioPlayer = audioPlayer;
        this.currentMapClass = null;
        setTitle("Pokedex");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        pokedex_list.setModel(new DefaultListModel<>());
        registedList.setModel(new DefaultListModel<>());
        loadPokedexData();
        setVisible(true);
        showMainPane();
        setpokemonTeamImages("1093", "1093", "1093", "1093", "1093", "1093");
        setTrainerImage("22");
        setSelectedPokemonImg("1093");
        setPokemonImage("1093");
        setPokedexTypeIcons("19", "20");
        setCurrentMapImage("0");
        updateCurrentMap();
        updateMapDetails();
        setTypeChartTypesIcons();
        setRelatedTypechartIcons();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton();
            }
        });

        // Add action listeners to the arrow buttons
        leftArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftArrowButton();
            }
        });

        rightArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightArrowButton();
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
                previousMapButton();
            }
        });
        nextMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextMapButton();
            }
        });
        pokedex_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                pokedexListContent(e);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButton();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterPokedexList(searchByNameTxt.getText().trim().toLowerCase());
            }
        });
        registedList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                registeredListContent(e);
            }
        });
        ActionListener type1Actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                selectedType1.setIcon(sourceButton.getIcon());
                updateEfficacy(sourceButton, selectedType1, selectedType2);
                // Aquí puedes agregar el comportamiento específico que deseas para todos los botones
            }
        };
        JButton[] buttons = {type1, type2, type3, type4, type5, type6, type7, type8, type9, type10, type11, type12, type13, type14, type15, type16, type17, type18};
        for (JButton button : buttons) {
            button.addActionListener(type1Actionlistener);
        }

        ActionListener type2Actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                selectedType2.setIcon(sourceButton.getIcon());
                updateEfficacy2(sourceButton, selectedType1, selectedType2);
                // Aquí puedes agregar el comportamiento específico que deseas para todos los botones
            }
        };
        JButton[] buttons2 = {type2_1, type2_2, type2_3, type2_4, type2_5, type2_6, type2_7, type2_8, type2_9, type2_10, type2_11, type2_12, type2_13, type2_14, type2_15, type2_16, type2_17, type2_18};
        for (JButton button : buttons2) {
            button.addActionListener(type2Actionlistener);
        }
        pokemon1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTeamMember(pokemon1Button, 1);
            }
        });
        pokemon2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTeamMember(pokemon2Button, 2);
            }
        });
        pokemon3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTeamMember(pokemon3Button, 3);
            }
        });
        pokemon4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTeamMember(pokemon4Button, 4);
            }
        });
        pokemon5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTeamMember(pokemon5Button, 5);
            }
        });
        pokemon6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTeamMember(pokemon6Button, 6);
            }
        });
        SaveTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeam();
            }
        });


    }

    private void registeredListContent(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Ensure the event is not fired multiple times
            Object selectedObject = registedList.getSelectedValue(); // Get the selected object
            if (selectedObject != null) { // Ensure an item is selected
                String selectedIdentifier = selectedObject.toString().split(" - ")[1];
                Pokemon selectedPokemon = pokemonRepository.findByIdentifier(selectedIdentifier.toLowerCase());
                if (selectedPokemon != null) {
                    // Set the Pokémon's identifier to the PokemonName panel
                    SelectedPokemonName.setText(capitalizeFirstLetter(selectedPokemon.getIdentifier()));
                    // Set the image to the order number of the selected Pokémon
                    String imagePath = setPokemonImageIcon(String.valueOf(selectedPokemon.getOrder()));
                    SelectedPokemonImg.setIcon(getScaledImage(imagePath, SelectedPokemonImg.getWidth(), SelectedPokemonImg.getHeight()));
                    List<Pokemon_types> pokemonTypes = pokemonTypesRepository.findByPokemonId(selectedPokemon.getId());
                    setTeamTypeIcons(Integer.toString(pokemonTypes.getLast().getType().getId()), Integer.toString(pokemonTypes.getFirst().getType().getId()));
                }
            }
        }
    }

    private void clearRegisteredList() {
        DefaultListModel model = (DefaultListModel) registedList.getModel();
        model.clear();
    }


    private void setTeamMember(JButton button, int index) {
        String selectedIdentifier = SelectedPokemonName.getText();
        Pokemon selectedPokemon = pokemonRepository.findByIdentifier(selectedIdentifier.toLowerCase());
        if (selectedPokemon != null) {
            String imagePath = setPokemonImageIcon(String.valueOf(selectedPokemon.getOrder()));
            button.setIcon(getScaledImage(imagePath, button.getWidth(), button.getHeight()));
            button.setText(null);
            switch (index) {
                case 1:
                    teamMember1 = selectedPokemon;
                    break;
                case 2:
                    teamMember2 = selectedPokemon;
                    break;
                case 3:
                    teamMember3 = selectedPokemon;
                    break;
                case 4:
                    teamMember4 = selectedPokemon;
                    break;
                case 5:
                    teamMember5 = selectedPokemon;
                    break;
                case 6:
                    teamMember6 = selectedPokemon;
                    break;
            }
        }

    }

    private void loadTeamImages() {
        if (loggedInUser != null) {
            String imagePath = setPokemonImageIcon(String.valueOf(teamMember1.getOrder()));
            pokemon1Button.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon1Button.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember2.getOrder()));
            pokemon2Button.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon2Button.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember3.getOrder()));
            pokemon3Button.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon3Button.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember4.getOrder()));
            pokemon4Button.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon4Button.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember5.getOrder()));
            pokemon5Button.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon5Button.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember6.getOrder()));
            pokemon6Button.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon6Button.setText(null);
        } else {
            setpokemonTeamImages("1093", "1093", "1093", "1093", "1093", "1093");
        }
    }

    private void loadTrainerTeamImages() {
        if (loggedInUser != null) {
            String imagePath = setPokemonImageIcon(String.valueOf(teamMember1.getOrder()));
            pokemon1.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon1.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember2.getOrder()));
            pokemon2.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon2.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember3.getOrder()));
            pokemon3.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon3.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember4.getOrder()));
            pokemon4.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon4.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember5.getOrder()));
            pokemon5.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon5.setText(null);
            imagePath = setPokemonImageIcon(String.valueOf(teamMember6.getOrder()));
            pokemon6.setIcon(getScaledImage(imagePath, pokemon1Button.getWidth(), pokemon1Button.getHeight()));
            pokemon6.setText(null);
        } else {
            setTeamImages("1093", "1093", "1093", "1093", "1093", "1093");
        }
    }

    private void loadTeamMembers() {
        Team t = teamRepository.findByTrainer(loggedInUser);
        List<PokemonTeam> pt = pokemonTeamRepository.findAllByTeam(t);

        for (PokemonTeam pokemonTeam : pt) {
            int slot = pokemonTeam.getSlot();
            Pokemon pokemon = pokemonTeam.getPokemon();
            switch (slot) {
                case 1:
                    teamMember1 = pokemon;
                    break;
                case 2:
                    teamMember2 = pokemon;
                    break;
                case 3:
                    teamMember3 = pokemon;
                    break;
                case 4:
                    teamMember4 = pokemon;
                    break;
                case 5:
                    teamMember5 = pokemon;
                    break;
                case 6:
                    teamMember6 = pokemon;
                    break;
                default:
                    // Handle invalid slot numbers
                    break;
            }
        }
    }


    private void saveTeam() {
        if (loggedInUser != null) {
            Team team = teamRepository.findByTrainer(loggedInUser);
            if (team == null) {
                Trainer t = trainerRepository.findById(loggedInUser.getId()).orElse(null);
                Team n = new Team(t);
                teamRepository.save(n);
            } else {
                JOptionPane.showMessageDialog(AppUI.this, "Team saved successfully.", "Team Saved", JOptionPane.INFORMATION_MESSAGE);
                pokemonTeamRepository.save(new PokemonTeam(teamMember1, team, 1));
                pokemonTeamRepository.save(new PokemonTeam(teamMember2, team, 2));
                pokemonTeamRepository.save(new PokemonTeam(teamMember3, team, 3));
                pokemonTeamRepository.save(new PokemonTeam(teamMember4, team, 4));
                pokemonTeamRepository.save(new PokemonTeam(teamMember5, team, 5));
                pokemonTeamRepository.save(new PokemonTeam(teamMember6, team, 6));
                loadTrainerTeamImages();
            }
        }
    }



    private void pokedexListContent(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Ensure the event is not fired multiple times
            Object selectedObject = pokedex_list.getSelectedValue(); // Get the selected object
            if (selectedObject != null) { // Ensure an item is selected
                // Get the selected Pokémon's identifier
                String selectedIdentifier = selectedObject.toString().split(" - ")[1];
                // Retrieve the selected Pokémon from the repository
                Pokemon selectedPokemon = pokemonRepository.findByIdentifier(selectedIdentifier.toLowerCase());
                if (selectedPokemon != null) {
                    // Set the species ID to the PokedexNumber panel
                    PokedexNumber.setText(String.format("%03d", selectedPokemon.getSpeciesId()));
                    // Set the Pokémon's identifier to the PokemonName panel
                    PokemonName.setText(capitalizeFirstLetter(selectedPokemon.getIdentifier()));
                    // Set the image to the order number of the selected Pokémon
                    String imagePath = setPokemonImageIcon(String.valueOf(selectedPokemon.getOrder()));
                    pokemonImage.setIcon(getScaledImage(imagePath, pokemonImage.getWidth(), pokemonImage.getHeight()));
                    List<Pokemon_types> pokemonTypes = pokemonTypesRepository.findByPokemonId(selectedPokemon.getId());
                    setPokedexTypeIcons(Integer.toString(pokemonTypes.getLast().getType().getId()), Integer.toString(pokemonTypes.getFirst().getType().getId()));

                    // Check if the Pokemon is registered
                    boolean isRegistered = isPokemonRegistered(selectedPokemon.getId());
                    if (isRegistered) {
                        registerButton.setText("Unregister");
                    } else {
                        registerButton.setText("Register");
                    }

                }
            }
        }
    }

    // Method to check if the Pokemon is registered
    private boolean isPokemonRegistered(int pokemonId) {
        // Implement logic to check if the Pokemon is registered
        // You can use your existing repository method or any other approach
        Register register = registerRepository.findByTrainerIdAndPokemonId(loggedInUser.getId(), pokemonId);
        if (register.getRegistered() == 0){
            return false;
        }
        return true;
    }

    private void registerButton() {
        if (loggedInUser != null) {
            Object selectedObject = pokedex_list.getSelectedValue();
            if (selectedObject != null) {
                String selectedIdentifier = selectedObject.toString().split(" - ")[1];
                Pokemon selectedPokemon = pokemonRepository.findByIdentifier(selectedIdentifier.toLowerCase());
                if (selectedPokemon != null) {
                    togglePokemonRegistration(loggedInUser.getId(), selectedPokemon.getId());
                }
            }
        }
    }


    private void nextMapButton() {
        // Increase the current map ID
        increaseCurrentMapId();
        // Update the UI to display the new current map
        setCurrentMapImage(String.valueOf(currentMapId));
        updateCurrentMap();
        updateMapDetails();
    }

    private void previousMapButton() {
        decreaseCurrentMapId();
        // Update the UI to display the new current map
        setCurrentMapImage(String.valueOf(currentMapId));
        updateCurrentMap();
        updateMapDetails();
    }

    private void rightArrowButton() {
        // Increase the image index and update the character image
        currentUserImageIndex++;
        if (currentUserImageIndex > 22) {
            currentUserImageIndex = 1; // Return to 1 if greater than 21
        }
        setTrainerImage(String.valueOf(currentUserImageIndex));
    }

    private void leftArrowButton() {
        // Decrease the image index and update the character image
        currentUserImageIndex--;
        if (currentUserImageIndex < 1) {
            currentUserImageIndex = 22; // Return to 21 if less than 1
        }
        setTrainerImage(String.valueOf(currentUserImageIndex));
    }


    private void loginButton() {
        if (loggedInUser == null) {
            // Perform login
            String enteredUsername = username.getText();
            String enteredPassword = new String(password.getPassword());

            // Perform login or registration
            String resultMessage = authenticationService.loginOrRegister(enteredUsername, enteredPassword, teamRepository,pokemonTeamRepository,pokemonRepository);

            // Display message based on the result
            JOptionPane.showMessageDialog(AppUI.this, resultMessage, "Authentication Result", JOptionPane.INFORMATION_MESSAGE);

            // If login is successful, load the trainer's image and update progress bar
            if (resultMessage.equals("Login successful")) {
                loggedInUser = trainerRepository.findByName(enteredUsername);
                if (loggedInUser != null) {
                    loadTrainerInfo(loggedInUser.getName());
                    loginButton.setText("Logout");
                    loadRegisteredPokemonData(loggedInUser.getId());
                    loadTeamMembers();
                    loadTeamImages();
                    loadTrainerTeamImages();
                    //music
                    playMusic(loggedInUser.getImage(), true);
                }
            }
        } else {
            // Perform logout
            loggedInUser = null;
            loginButton.setText("Login");
            clearUserSession();
            JOptionPane.showMessageDialog(AppUI.this, "Logged out successfully.", "Logout", JOptionPane.INFORMATION_MESSAGE);
            audioPlayer.stop();
        }
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

    private ImageIcon getScaledImageByIcon(Icon icon, int width, int height) {
        if (icon instanceof ImageIcon) {
            Image img = ((ImageIcon) icon).getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } else {
            // Create a BufferedImage to paint the Icon into
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            icon.paintIcon(null, g2d, 0, 0);
            g2d.dispose();

            // Scale the image
            Image img = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
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

    public void setTeamImages(String p1, String p2, String p3, String p4, String p5, String p6) {
        pokemon1Button.setIcon(getScaledImage(setPokemonImageIcon(p1), pokemon1.getWidth(), pokemon1.getHeight()));
        pokemon2Button.setIcon(getScaledImage(setPokemonImageIcon(p2), pokemon2.getWidth(), pokemon2.getHeight()));
        pokemon3Button.setIcon(getScaledImage(setPokemonImageIcon(p3), pokemon3.getWidth(), pokemon3.getHeight()));
        pokemon4Button.setIcon(getScaledImage(setPokemonImageIcon(p4), pokemon4.getWidth(), pokemon4.getHeight()));
        pokemon5Button.setIcon(getScaledImage(setPokemonImageIcon(p5), pokemon5.getWidth(), pokemon5.getHeight()));
        pokemon6Button.setIcon(getScaledImage(setPokemonImageIcon(p6), pokemon6.getWidth(), pokemon6.getHeight()));
    }

    public String setTrainerImageIcon(String ordernum) {
        return "src/main/resources/trainers/" + ordernum + ".png";
    }

    private void togglePokemonRegistration(int trainerId, int pokemonId) {
        Register register = registerRepository.findByTrainerIdAndPokemonId(trainerId, pokemonId);
        if (register != null) {
            int currentStatus = register.getRegistered();
            int newStatus = currentStatus == 1 ? 0 : 1;
            register.setRegistered(newStatus);
            registerRepository.save(register);
            loadRegisteredPokemonData(loggedInUser.getId());

            // Display success message based on the new registration status
            String successMessage = newStatus == 1 ? "Registered successfully!" : "Unregistered successfully!";
            JOptionPane.showMessageDialog(this, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
            updateProgressBar();
        } else {
            JOptionPane.showMessageDialog(this, "Registration record not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void setTrainerImage(String t1) {
        if (parseInt(t1) == 2 || parseInt(t1) == 12 || parseInt(t1) == 13 || parseInt(t1) == 16 || parseInt(t1) == 21) {
            characterImage.setSize(new Dimension(260, 600));
        } else if (parseInt(t1) == 6 || parseInt(t1) == 7 || parseInt(t1) == 10 || parseInt(t1) == 18) {
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

    public void setTeamTypeIcons(String t1, String t2) {
        SelectedPokemonType1.setIcon(getScaledImage(setTypeImage(t1), Type1.getWidth(), Type1.getHeight()));
        SelectedPokemonType2.setIcon(getScaledImage(setTypeImage(t2), Type2.getWidth(), Type2.getHeight()));
    }

    public void setTypeChartTypesIcons() {
        // Assuming `type1`, `type2`, ..., `type18` are already defined and initialized.
        // Creating an array of type instances
        JButton[] types = {type1, type2, type3, type4, type5, type6, type7, type8, type9, type10, type11, type12, type13, type14, type15, type16, type17, type18, type2_1, type2_2, type2_3, type2_4, type2_5, type2_6, type2_7, type2_8, type2_9, type2_10, type2_11, type2_12, type2_13, type2_14, type2_15, type2_16, type2_17, type2_18};
        int contador = 0;

        // Loop through each type and set the icon
        for (JButton type : types) {
            contador++;
            type.setIcon(getScaledImage(setTypeImage(Integer.toString(contador)), type.getWidth(), type.getHeight()));
            if (contador == 18) contador = 0;
        }
    }

    public void setRelatedTypechartIcons() {
        // Assuming `type1`, `type2`, ..., `type18` are already defined and initialized.
        // Creating an array of type instances
        JLabel[] types = {relatedType1, relatedType2, relatedType3, relatedType4, relatedType5, relatedType6, relatedType7, relatedType8, relatedType9, relatedType10, relatedType11, relatedType12, relatedType13, relatedType14, relatedType15, relatedType16, relatedType17, relatedType18};
        int icon = 0;
        // Loop through each type and set the icon
        for (JLabel type : types) {
            icon++;
            type.setIcon(getScaledImage(setTypeImage(Integer.toString(icon)), type.getWidth(), type.getHeight()));
        }
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
        List<Pokemon> pokedexEntries = pokemonRepository.findAllByOrderByOrderAsc();
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
            playMusic(trainer.getImage(), false);
        } else {
            JOptionPane.showMessageDialog(this, "Error saving image.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void playMusic(int image, boolean login) {
        if (!login) {
            audioPlayer.stop();
        }
        switch (image) {
            case 1:
            case 2:
                audioPlayer.play("src/main/resources/audio/1.wav");
                break;
            case 3:
            case 4:
            case 5:
                audioPlayer.play("src/main/resources/audio/2.wav");
                break;
            case 6:
            case 7:
                audioPlayer.play("src/main/resources/audio/3.wav");
                break;
            case 8:
            case 9:
                audioPlayer.play("src/main/resources/audio/4.wav");
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                audioPlayer.play("src/main/resources/audio/5.wav");
                break;
            case 14:
            case 15:
                audioPlayer.play("src/main/resources/audio/6.wav");
                break;
            case 16:
            case 17:
                audioPlayer.play("src/main/resources/audio/7.wav");
                break;
            case 18:
            case 19:
                audioPlayer.play("src/main/resources/audio/8.wav");
                break;
            case 20:
            case 21:
                audioPlayer.play("src/main/resources/audio/9.wav");
                break;
            case 22:
                audioPlayer.play("src/main/resources/audio/10.wav");
                break;
        }

    }

    private void loadRegisteredPokemonData(int trainerId) {
        ListModel<String> listModel = registedList.getModel(); // Retrieve the list model
        if (!(listModel instanceof DefaultListModel)) {
            // If the list model is not a DefaultListModel, create a new DefaultListModel and set it to the JList
            DefaultListModel<String> defaultListModel = new DefaultListModel<>();
            registedList.setModel(defaultListModel);
            listModel = defaultListModel; // Update the reference to the new DefaultListModel
        }

        DefaultListModel<String> model = (DefaultListModel<String>) listModel;
        model.clear();

        // Proceed with your existing logic to populate the model
        List<Register> registeredPokemon = registerRepository.findByTrainerId(trainerId);
        for (Register register : registeredPokemon) {
            if (register.getRegistered() == 1) {
                Pokemon pokemon = pokemonRepository.findById(register.getPokemon().getId()).orElse(null);
                if (pokemon != null) {
                    String entry = String.format("%03d - %s", pokemon.getSpeciesId(), capitalizeFirstLetter(pokemon.getIdentifier()));
                    model.addElement(entry);
                }
            }
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

    private void filterPokedexList(String searchText) {
        DefaultListModel<String> model = (DefaultListModel<String>) pokedex_list.getModel();
        model.clear();
        List<Pokemon> pokemons = (List<Pokemon>) pokemonRepository.findAll();
        List<Pokemon> filteredPokemons = pokemons.stream().filter(p -> p.getIdentifier().toLowerCase().startsWith(searchText)).toList();
        for (Pokemon pokemon : filteredPokemons) {
            model.addElement(pokemon.getSpeciesId() + " - " + capitalizeFirstLetter(pokemon.getIdentifier()));
        }
    }

    private void clearUserSession() {
        // Clear the user session details (UI elements) when logging out
        username.setText("");
        password.setText("");
        currentUserImageIndex = 1;
        setTrainerImage("22");
        progressBar.setValue(0);
        progressBar.setString("0%");
        loadTeamImages();
        loadTrainerTeamImages();
        clearRegisteredList();
        teamMember1 = null;
        teamMember2 = null;
        teamMember3 = null;
        teamMember4 = null;
        teamMember5 = null;
        teamMember6 = null;
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
            currentMapId = 0; // Wrap around to 1 if exceeding 22
        }
    }

    private void decreaseCurrentMapId() {
        currentMapId--;
        if (currentMapId < 0) {
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
            // Actualizar la etiqueta cityName con el nombre del mapa
            cityName.setText(currentMapClass.getName());

            // Formatear el texto de la descripción con saltos de línea y etiquetas HTML
            String formattedText = "<html>" + addLineBreaks(currentMapClass.getDescription(), 65) + "</html>";
            cityData.setText(formattedText);
        } else {
            // Manejar el caso cuando el mapa actual es nulo
            cityName.setText("Unknown Map");
            cityData.setText("No description available");
        }
    }

    private String addLineBreaks(String text, int lineLength) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        int length = text.length();
        int start = 0;

        while (start < length) {
            int end = Math.min(start + lineLength, length);

            if (end < length && text.charAt(end) != ' ') {
                int lastSpace = text.lastIndexOf(' ', end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }

            result.append(text, start, end).append("<br>");
            start = end + 1; // Mover el inicio a después del espacio
        }

        return result.toString().replaceAll("<br>$", ""); // Remover el último <br>
    }


    public void updateEfficacy(JButton selectedButton, JLabel t1, JLabel t2) {
        JButton[] types = {type1, type2, type3, type4, type5, type6, type7, type8, type9, type10, type11, type12, type13, type14, type15, type16, type17, type18,};
        int contador = 0;
        // Loop through each type and set the icon
        for (JButton type : types) {
            contador++;
            if (contador == 19) {
                break;
            }
            if (selectedButton == type) {
                t1.putClientProperty("contador", contador);
            }
        }
        updateRelations(selectedType1, selectedType2);
    }

    public void updateEfficacy2(JButton selectedButton, JLabel t1, JLabel t2) {
        JButton[] types = {type2_1, type2_2, type2_3, type2_4, type2_5, type2_6, type2_7, type2_8, type2_9, type2_10, type2_11, type2_12, type2_13, type2_14, type2_15, type2_16, type2_17, type2_18};
        int contador = 0;
        // Loop through each type and set the icon
        for (JButton type : types) {
            contador++;
            if (contador == 19) {
                break;
            }
            if (selectedButton == type) {
                t2.putClientProperty("contador", contador);
            }
        }
        updateRelations(selectedType1, selectedType2);
    }

    public void updateRelations(JLabel sT1, JLabel sT2) {
        JLabel[] types = {relatedType1, relatedType2, relatedType3, relatedType4, relatedType5, relatedType6, relatedType7, relatedType8, relatedType9, relatedType10, relatedType11, relatedType12, relatedType13, relatedType14, relatedType15, relatedType16, relatedType17, relatedType18};
        int damageType = 0;
        int targetType1 = 0;
        int targetType2 = 0;
        TypeEfficacy relation1 = null;
        TypeEfficacy relation2 = null;
        double relationtotal = 0;
        boolean esnulo1 = false;
        boolean esnulo2 = false;

        if (sT1.getClientProperty("contador") == null) {
            esnulo1 = true;
        } else {
            targetType1 = (int) sT1.getClientProperty("contador");
        }
        if (sT2.getClientProperty("contador") == null) {
            esnulo2 = true;
        } else {
            targetType2 = (int) sT2.getClientProperty("contador");
        }

        for (JLabel type : types) {
            damageType++;
            if (damageType == 19) {
                break;
            }
            if (esnulo1) {
                if (esnulo2) {
                    System.out.println("ambos son nulos");
                } else {
                    relation2 = typeEfficacyRepository.findTypeEfficacyByDamageTypeIdAndTargetTypeId(damageType, targetType2);
                    relationtotal = (double) relation2.getDamageFactor() / 100;
                }
            } else if (esnulo2) {
                relation1 = typeEfficacyRepository.findTypeEfficacyByDamageTypeIdAndTargetTypeId(damageType, targetType1);
                relationtotal = (double) relation1.getDamageFactor() / 100;
            } else {
                relation1 = typeEfficacyRepository.findTypeEfficacyByDamageTypeIdAndTargetTypeId(damageType, targetType1);
                relation2 = typeEfficacyRepository.findTypeEfficacyByDamageTypeIdAndTargetTypeId(damageType, targetType2);
                relationtotal = (double) relation1.getDamageFactor() / 100 * relation2.getDamageFactor() / 100;
                if (sT1.getClientProperty("contador") == sT2.getClientProperty("contador")) {
                    relationtotal = (double) relation1.getDamageFactor() / 100;
                }
            }
            type.setText("x" + relationtotal);
        }

    }
}


