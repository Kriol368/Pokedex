package pokedex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class register_key implements Serializable {
    @Column(name = "pokemon_id")
    Integer pokemon_id;

    @Column(name = "trainer_id")
    Integer trainer_id;
}