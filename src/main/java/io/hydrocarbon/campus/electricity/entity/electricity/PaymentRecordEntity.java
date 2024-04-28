package io.hydrocarbon.campus.electricity.entity.electricity;

import io.hydrocarbon.campus.electricity.entity.BaseEntity;
import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment_record")
public class PaymentRecordEntity extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "uuid not null")
    private UUID userId;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity user;

    @NotNull
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @NotNull
    @Column(name = "payment_time", nullable = false)
    private OffsetDateTime paymentTime;
}
