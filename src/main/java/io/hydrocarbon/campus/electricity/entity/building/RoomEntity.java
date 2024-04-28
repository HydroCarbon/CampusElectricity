package io.hydrocarbon.campus.electricity.entity.building;

import io.hydrocarbon.campus.electricity.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "room")
public class RoomEntity extends BaseEntity {

    @Column(name = "building_id", columnDefinition = "uuid not null")
    private UUID buildingId;

    @ManyToOne(targetEntity = BuildingEntity.class)
    @JoinColumn(name = "building_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BuildingEntity building;

    @Size(max = 16)
    @NotNull
    @Column(name = "name", nullable = false, length = 16)
    private String name;
}
