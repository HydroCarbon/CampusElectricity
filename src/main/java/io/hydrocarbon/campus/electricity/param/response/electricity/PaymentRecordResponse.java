package io.hydrocarbon.campus.electricity.param.response.electricity;

import io.hydrocarbon.campus.electricity.entity.electricity.PaymentRecordEntity;
import lombok.Data;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Data
public class PaymentRecordResponse {


    public static PaymentRecordResponse fromEntity(PaymentRecordEntity paymentRecordEntity) {
        PaymentRecordResponse paymentRecordResponse = new PaymentRecordResponse();
        return paymentRecordResponse;
    }
}
