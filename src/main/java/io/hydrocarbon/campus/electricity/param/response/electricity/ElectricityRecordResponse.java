package io.hydrocarbon.campus.electricity.param.response.electricity;

import io.hydrocarbon.campus.electricity.entity.electricity.ElectricityRecordEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-25
 */
@Data
public class ElectricityRecordResponse {

    private UUID id;

    private String building;

    private String room;

    private BigDecimal electricity;

    private BigDecimal cost;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public static ElectricityRecordResponse fromEntity(ElectricityRecordEntity entity) {
        if (entity == null) {
            return null;
        }

        ElectricityRecordResponse response = new ElectricityRecordResponse();
        response.setId(entity.getId());
        response.setBuilding(entity.getRoom().getBuilding().getName());
        response.setRoom(entity.getRoom().getName());
        response.setElectricity(entity.getElectricity());
        response.setCost(entity.getCost());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        return response;
    }
}
