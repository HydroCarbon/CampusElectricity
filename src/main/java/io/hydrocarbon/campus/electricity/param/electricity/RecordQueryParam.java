package io.hydrocarbon.campus.electricity.param.electricity;

import io.hydrocarbon.campus.electricity.param.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RecordQueryParam extends PageParam {

    /**
     * 房间
     */
    private UUID roomId;

    /**
     * 用户 ID
     */
    private UUID userId;

    /**
     * 开始时间
     */
    private OffsetDateTime startTime;

    /**
     * 结束时间
     */
    private OffsetDateTime endTime;
}
