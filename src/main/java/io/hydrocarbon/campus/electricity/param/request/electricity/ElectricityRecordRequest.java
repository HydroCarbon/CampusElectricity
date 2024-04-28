package io.hydrocarbon.campus.electricity.param.request.electricity;

import io.hydrocarbon.campus.electricity.constants.Constants;
import io.hydrocarbon.campus.electricity.entity.electricity.ElectricityRecordEntity;
import io.hydrocarbon.campus.electricity.util.UserUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Data
public class ElectricityRecordRequest {
    private UUID roomId;

    private BigDecimal electricity;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public ElectricityRecordEntity toEntity() {
        ElectricityRecordEntity entity = new ElectricityRecordEntity();
        entity.setRoomId(roomId);
        entity.setElectricity(electricity);
        // 按照时间段计算电费
        BigDecimal cost = electricity.multiply(Constants.Price.ELECTRICITY_PRICE);
        entity.setCost(cost);
        entity.setStartTime(startTime);
        entity.setEndTime(endTime);
        entity.setCreatedBy(UserUtil.getCurrentUserId());
        entity.setUpdatedBy(UserUtil.getCurrentUserId());
        return entity;
    }
}
