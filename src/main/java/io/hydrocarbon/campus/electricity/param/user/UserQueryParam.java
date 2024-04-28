package io.hydrocarbon.campus.electricity.param.user;

import io.hydrocarbon.campus.electricity.param.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryParam extends PageParam {

    /**
     * 姓名
     */
    private String name;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 楼栋
     */
    private String buildingName;

    /**
     * 房间
     */
    private String roomName;
}
