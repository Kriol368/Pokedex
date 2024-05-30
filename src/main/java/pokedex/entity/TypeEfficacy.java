package pokedex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_efficacy")
@IdClass(TypeEfficacyId.class) // Define the composite primary key class
public class TypeEfficacy {

    @Id
    @Column(name = "damage_type_id")
    private Integer damageTypeId;

    @Id
    @Column(name = "target_type_id")
    private Integer targetTypeId;

    @Column(name = "damage_factor")
    private Integer damageFactor;

    // Getters and Setters


    public Integer getDamageTypeId() {
        return damageTypeId;
    }

    public void setDamageTypeId(Integer damageTypeId) {
        this.damageTypeId = damageTypeId;
    }

    public Integer getTargetTypeId() {
        return targetTypeId;
    }

    public void setTargetTypeId(Integer targetTypeId) {
        this.targetTypeId = targetTypeId;
    }

    public Integer getDamageFactor() {
        return damageFactor;
    }

    public void setDamageFactor(Integer damageFactor) {
        this.damageFactor = damageFactor;
    }
}
