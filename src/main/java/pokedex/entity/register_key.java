package pokedex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class register_key {
    @Column(name = "pokemon_id")
    Integer pokemon_id;

    @Column(name = "trainer_id")
    Integer trainer_id;
}