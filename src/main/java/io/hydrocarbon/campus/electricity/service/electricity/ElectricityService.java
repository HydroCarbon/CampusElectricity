package io.hydrocarbon.campus.electricity.service.electricity;

import io.hydrocarbon.campus.electricity.entity.electricity.ElectricityRecordEntity;
import io.hydrocarbon.campus.electricity.entity.electricity.PaymentRecordEntity;
import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import io.hydrocarbon.campus.electricity.param.electricity.PaymentRecordParam;
import io.hydrocarbon.campus.electricity.param.electricity.RecordQueryParam;
import io.hydrocarbon.campus.electricity.param.request.electricity.ElectricityRecordRequest;
import io.hydrocarbon.campus.electricity.param.request.electricity.PaymentRecordRequest;
import io.hydrocarbon.campus.electricity.repository.electricity.ElectricityRecordRepository;
import io.hydrocarbon.campus.electricity.repository.electricity.PaymentRecordRepository;
import io.hydrocarbon.campus.electricity.repository.user.UserRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-25
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ElectricityService {

    private final ElectricityRecordRepository electricityRecordRepository;

    private final UserRepository userRepository;

    private final PaymentRecordRepository paymentRecordRepository;

    /**
     * 添加电费记录
     *
     * @param request 电费记录请求
     * @return 电费记录 ID
     */
    @Transactional(rollbackFor = Exception.class)
    public UUID addRecord(ElectricityRecordRequest request) {
        ElectricityRecordEntity recordEntity = electricityRecordRepository.save(request.toEntity());
        return recordEntity.getId();
    }

    /**
     * 获取电费记录
     *
     * @param recordId 电费记录 ID
     * @return 电费记录
     */
    public ElectricityRecordEntity getRecord(UUID recordId) {
        return electricityRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("电费记录不存在"));
    }

    /**
     * 分页查询电费记录
     *
     * @param param 查询参数
     * @return 电费记录分页
     */
    public Page<ElectricityRecordEntity> pageQueryRecord(RecordQueryParam param) {
        Assert.notNull(param, "查询参数不能为空");
        Optional<UserEntity> userEntity;
        if (Objects.nonNull(param.getUserId())) {
            userEntity = userRepository.findById(param.getUserId());
        } else {
            userEntity = Optional.empty();
        }

        Specification<ElectricityRecordEntity> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (param.getStartTime() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), param.getStartTime()));
            }
            if (param.getEndTime() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("endTime"), param.getEndTime()));
            }
            if (userEntity.isPresent()) {
                // predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("roomId"),
                //       userEntity.get().getRoom().getId()));
            }
            return predicate;
        };

        return electricityRecordRepository.findAll(specification, param.toPageable());
    }

    public Page<PaymentRecordEntity> pageQueryPaymentRecord(PaymentRecordParam param) {
        Assert.notNull(param, "查询参数不能为空");

        // TODO 查询人

        Specification<PaymentRecordEntity> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (param.getStartTime() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), param.getStartTime()));
            }
            if (param.getEndTime() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), param.getEndTime()));
            }
            return predicate;
        };

        return paymentRecordRepository.findAll(specification, param.toPageable());
    }

    public UUID pay(PaymentRecordRequest request) {
        PaymentRecordEntity paymentRecordEntity = paymentRecordRepository.save(request.toEntity());
        return paymentRecordEntity.getId();
    }
}
