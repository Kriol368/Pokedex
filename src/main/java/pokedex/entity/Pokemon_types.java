package pokedex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon_types")
public class Pokemon_types {

        @EmbeddedId
        Pokemon_types_key id;

        @ManyToOne
        @MapsId("pokemon_id")
        @JoinColumn(name = "pokemon_id")
        Pokemon pokemon;

        @ManyToOne
        @MapsId("type_id")
        @JoinColumn(name = "type_id")
        Type type;

        int slot;

        // standard constructors, getters, and setters

        public int getSlot() {
                return slot;
        }

        public void setSlot(int slot) {
                this.slot = slot;
        }

        public Type getType() {
                return type;
        }

        public void setType(Type type) {
                this.type = type;
        }

        public Pokemon getPokemon() {
                return pokemon;
        }

        public void setPokemon(Pokemon pokemon) {
                this.pokemon = pokemon;
        }

        public Pokemon_types_key getId() {
                return id;
        }

        public void setId(Pokemon_types_key id) {
                this.id = id;
        }
}
