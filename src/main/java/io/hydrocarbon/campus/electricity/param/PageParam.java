package io.hydrocarbon.campus.electricity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author HydroCarbon
 * @since 2024-04-25
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

    /**
     * 页码
     */
    Integer pageNo;

    /**
     * 每页大小
     */
    Integer pageSize;

    public Pageable toPageable() {
        return PageRequest.of(pageNo, pageSize,
                Sort.by(Sort.Order.desc("createdAt")));
    }
}
