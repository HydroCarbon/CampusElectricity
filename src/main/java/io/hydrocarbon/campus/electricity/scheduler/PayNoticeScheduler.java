package io.hydrocarbon.campus.electricity.scheduler;

import io.hydrocarbon.campus.electricity.entity.electricity.ElectricityRecordEntity;
import io.hydrocarbon.campus.electricity.repository.electricity.ElectricityRecordRepository;
import io.hydrocarbon.campus.electricity.repository.electricity.PaymentRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Component
@RequiredArgsConstructor
public class PayNoticeScheduler {

    private final PaymentRecordRepository paymentRecordRepository;

    private final ElectricityRecordRepository electricityRecordRepository;

    @Scheduled(cron = "0 * * * * ?")
    public void payNoticeScan() {
        List<ElectricityRecordEntity> electricityRecordEntityList = electricityRecordRepository.findAll();
    }
}
