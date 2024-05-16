package pokedex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Pokemon_types_key {
    @Column(name = "pokemon_id")
    Integer pokemon_id;

    @Column(name = "type_id")
    Integer type_id;
}
