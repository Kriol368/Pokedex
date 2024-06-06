package pokedex.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private int image;

    @OneToMany(mappedBy = "trainer")
    Set<Register> registers;
    @OneToOne(mappedBy = "trainer")
    private Team team;

    public Trainer() {
    }

    public Trainer(String name, String password) {
        this.name = name;
        this.password = password;
        this.image = 1;
        this.registers = new HashSet<>();
    }
    // Getters and setters for each field here

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Set<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(Set<Register> registers) {
        this.registers = registers;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
