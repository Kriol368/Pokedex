package pokedex.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedex.audio.AudioPlayer;
import pokedex.entity.*;
import pokedex.repository.*;
import pokedex.service.AuthenticationService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
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
    private AudioPlayer audioPlayer;
    private JTabbedPane mainPane;
    private JPanel panel1;
    private JPanel pokedex;
    private JPanel team;
    private JPanel map;
    private JPanel typeChart;
    private JPanel Showdown;
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
    private JEditorPane showdown;
    private JScrollPane pokedex_scroll;

    private int currentUserImageIndex = 1;
    private Trainer loggedInUser = null; // Track the logged-in user
    private int currentMapId = 0;
    private Map currentMapClass;

    @Autowired
    public AppUI(AuthenticationService authenticationService, PokemonRepository pokemonRepository, TrainerRepository trainerRepository, MapRepository mapRepository, TypeRepository typeRepository, Pokemon_typesRepository pokemonTypesRepository, RegisterRepository registerRepository, AudioPlayer audioPlayer) {
        this.authenticationService = authenticationService;
        this.pokemonRepository = pokemonRepository; // Initialize the repository
        this.trainerRepository = trainerRepository; // Initialize the trainer repository
        this.mapRepository = mapRepository;
        this.typeRepository = typeRepository;
        this.pokemonTypesRepository = pokemonTypesRepository;
        this.registerRepository = registerRepository;
        this.audioPlayer = audioPlayer;
        this.currentMapClass = null;
        setTitle("Pokedex");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        pokedex_list.setModel(new DefaultListModel<>());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> registedList = new JList<>(listModel);
        loadPokedexData();
        setVisible(true);
        showMainPane();
        setpokemonTeamImages("1", "1", "1", "1", "1", "1");
        setTrainerImage("1");
        setSelectedPokemonImg("1");
        setPokemonImage("1");
        setPokedexTypeIcons("1", "2");
        setCurrentMapImage("0");
        updateCurrentMap();
        updateMapDetails();
        loadShowdownContent();

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
    }

    private void registeredListContent(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Ensure the event is not fired multiple times
            Object selectedObject = registedList.getSelectedValue(); // Get the selected object
            if (selectedObject != null && selectedObject instanceof Pokemon) { // Ensure an item is selected
                Pokemon selectedPokemon = (Pokemon) selectedObject;
                // Set the Pokémon's identifier to the PokemonName panel
                SelectedPokemonName.setText(selectedPokemon.getIdentifier());
                // Set the image to the order number of the selected Pokémon
                String imagePath = setPokemonImageIcon(String.valueOf(selectedPokemon.getOrder()));
                SelectedPokemonImg.setIcon(getScaledImage(imagePath, SelectedPokemonImg.getWidth(), SelectedPokemonImg.getHeight()));
            }
        }
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
                    PokedexNumber.setText(String.valueOf(selectedPokemon.getSpeciesId()));
                    // Set the Pokémon's identifier to the PokemonName panel
                    PokemonName.setText(selectedPokemon.getIdentifier());
                    // Set the image to the order number of the selected Pokémon
                    String imagePath = setPokemonImageIcon(String.valueOf(selectedPokemon.getOrder()));
                    pokemonImage.setIcon(getScaledImage(imagePath, pokemonImage.getWidth(), pokemonImage.getHeight()));
                    List<Pokemon_types> pokemonTypes = pokemonTypesRepository.findByPokemonId(selectedPokemon.getId());
                    setPokedexTypeIcons(Integer.toString(pokemonTypes.getLast().getType().getId()), Integer.toString(pokemonTypes.getFirst().getType().getId()));
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
        if (currentUserImageIndex > 21) {
            currentUserImageIndex = 1; // Return to 1 if greater than 21
        }
        setTrainerImage(String.valueOf(currentUserImageIndex));
    }

    private void leftArrowButton() {
        // Decrease the image index and update the character image
        currentUserImageIndex--;
        if (currentUserImageIndex < 1) {
            currentUserImageIndex = 21; // Return to 21 if less than 1
        }
        setTrainerImage(String.valueOf(currentUserImageIndex));
    }

    private void loginButton() {
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
                    loadRegisteredPokemonData(loggedInUser.getId());
                    //music
                    playMusic(loggedInUser.getImage());
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

    private void loadShowdownContent() {
        try {
            URL url = new URL("https://play.pokemonshowdown.com/");
            showdown = new JEditorPane();
            showdown.setEditable(false);
            showdown.setContentType("text/html");  // Ensure the content type is HTML
            showdown.setPage(url);

            JScrollPane scrollPane = new JScrollPane(showdown);
            setContentPane(scrollPane);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load Showdown content.", "Error", JOptionPane.ERROR_MESSAGE);
        }


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
            JOptionPane.showMessageDialog(this, "Registration status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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

    public void setTypeChartTypesIcons() {
        type1.setIcon(getScaledImage(type1.getIcon().toString(), type1.getWidth(), type1.getHeight()));
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
            playMusic(trainer.getImage());
        } else {
            JOptionPane.showMessageDialog(this, "Error saving image.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void playMusic(int image) {
        audioPlayer.stop();
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

}
