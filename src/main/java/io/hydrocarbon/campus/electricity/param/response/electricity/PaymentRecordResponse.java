package io.hydrocarbon.campus.electricity.param.response.electricity;

import io.hydrocarbon.campus.electricity.entity.electricity.PaymentRecordEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Data
public class PaymentRecordResponse {

    private UUID id;

    private UUID userId;

    private String username;

    private String building;

    private String room;

    private BigDecimal cost;

    private OffsetDateTime paymentTime;


    public static PaymentRecordResponse fromEntity(PaymentRecordEntity paymentRecordEntity) {
        PaymentRecordResponse paymentRecordResponse = new PaymentRecordResponse();
        paymentRecordResponse.setId(paymentRecordEntity.getId());
        paymentRecordResponse.setUserId(paymentRecordEntity.getUserId());
        paymentRecordResponse.setCost(paymentRecordEntity.getCost());
        paymentRecordResponse.setPaymentTime(paymentRecordEntity.getPaymentTime());
        paymentRecordResponse.setUsername(paymentRecordEntity.getUser().getUsername());
        paymentRecordResponse.setBuilding(paymentRecordEntity.getUser().getRoom().getBuilding().getName());
        paymentRecordResponse.setRoom(paymentRecordEntity.getUser().getRoom().getName());
        return paymentRecordResponse;
    }
}
