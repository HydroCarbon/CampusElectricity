package io.hydrocarbon.campus.electricity.param.electricity;

import io.hydrocarbon.campus.electricity.param.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRecordParam extends PageParam {
    private UUID roomId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
