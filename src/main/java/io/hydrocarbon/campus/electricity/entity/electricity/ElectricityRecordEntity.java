package io.hydrocarbon.campus.electricity.entity.electricity;

import io.hydrocarbon.campus.electricity.entity.BaseEntity;
import io.hydrocarbon.campus.electricity.entity.building.RoomEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "electricity_record")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectricityRecordEntity extends BaseEntity {

    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @OneToOne(targetEntity = RoomEntity.class)
    @JoinColumn(name = "room_id", referencedColumnName = "id", insertable = false, updatable = false)
    private RoomEntity room;

    @NotNull
    @Column(name = "electricity", nullable = false)
    private BigDecimal electricity;

    @NotNull
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
}
