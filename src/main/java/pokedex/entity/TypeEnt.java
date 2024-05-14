package pokedex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "types")
public class TypeEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String identifier;
}