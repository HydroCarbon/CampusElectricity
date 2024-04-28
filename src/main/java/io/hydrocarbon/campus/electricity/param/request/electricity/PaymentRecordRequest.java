package io.hydrocarbon.campus.electricity.param.request.electricity;

import io.hydrocarbon.campus.electricity.entity.electricity.PaymentRecordEntity;
import io.hydrocarbon.campus.electricity.util.UserUtil;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Data
public class PaymentRecordRequest {

    @NotNull
    private BigDecimal amount;

    public PaymentRecordEntity toEntity() {
        PaymentRecordEntity paymentRecordEntity = new PaymentRecordEntity();
        paymentRecordEntity.setUserId(UserUtil.getCurrentUserId());
        paymentRecordEntity.setCost(amount);
        paymentRecordEntity.setPaymentTime(OffsetDateTime.now());
        paymentRecordEntity.setCreatedBy(UserUtil.getCurrentUserId());
        paymentRecordEntity.setUpdatedBy(UserUtil.getCurrentUserId());
        return paymentRecordEntity;
    }
}
