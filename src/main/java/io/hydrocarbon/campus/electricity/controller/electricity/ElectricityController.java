package io.hydrocarbon.campus.electricity.controller.electricity;

import io.hydrocarbon.campus.electricity.entity.electricity.ElectricityRecordEntity;
import io.hydrocarbon.campus.electricity.entity.electricity.PaymentRecordEntity;
import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import io.hydrocarbon.campus.electricity.enums.Role;
import io.hydrocarbon.campus.electricity.param.electricity.PaymentRecordParam;
import io.hydrocarbon.campus.electricity.param.electricity.RecordQueryParam;
import io.hydrocarbon.campus.electricity.param.request.electricity.ElectricityRecordRequest;
import io.hydrocarbon.campus.electricity.param.request.electricity.PaymentRecordRequest;
import io.hydrocarbon.campus.electricity.param.response.Response;
import io.hydrocarbon.campus.electricity.param.response.electricity.ElectricityRecordResponse;
import io.hydrocarbon.campus.electricity.param.response.electricity.PaymentRecordResponse;
import io.hydrocarbon.campus.electricity.service.electricity.ElectricityService;
import io.hydrocarbon.campus.electricity.util.UserUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@RestController
@RequestMapping("/electricity")
@RequiredArgsConstructor
@Slf4j
public class ElectricityController {

    private final ElectricityService electricityService;

    /**
     * 添加电表记录
     *
     * @return 电表记录
     */
    @PutMapping("/record/add")
    public Response<UUID> addRecord(@RequestBody ElectricityRecordRequest request) {
        return Response.success(electricityService.addRecord(request));
    }

    /**
     * 获取电表记录
     *
     * @param recordId 电表记录 ID
     * @return 电表记录
     */
    @GetMapping("/record/{recordId}")
    public Response<ElectricityRecordResponse> getRecord(@PathVariable UUID recordId) {
        ElectricityRecordEntity recordEntity = electricityService.getRecord(recordId);
        return Response.success(ElectricityRecordResponse.fromEntity(recordEntity));
    }

    /**
     * 查询电表记录
     *
     * @param roomId    房间 ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNo    页码
     * @param pageSize  每页大小
     * @return 电表记录
     */
    @GetMapping("/record/list")
    public Response<Page<ElectricityRecordResponse>> list(@RequestParam(required = false)
                                                          UUID roomId,
                                                          @RequestParam(required = false)
                                                          LocalDateTime startTime,
                                                          @RequestParam(required = false)
                                                          LocalDateTime endTime,
                                                          @RequestParam(value = "page", defaultValue = "0")
                                                          Integer pageNo,
                                                          @RequestParam(value = "size", defaultValue = "10")
                                                          Integer pageSize) {
        RecordQueryParam param = RecordQueryParam.builder()
                .roomId(roomId)
                .startTime(startTime)
                .endTime(endTime)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .build();

        UserEntity currentUser = UserUtil.getCurrentUser();
        Assert.notNull(currentUser, "用户未登录");
        if (!Role.isAdmin(currentUser.getRole())) {
            param.setUserId(currentUser.getId());
        }

        Page<ElectricityRecordEntity> recordEntityPage = electricityService.pageQueryRecord(param);
        Page<ElectricityRecordResponse> resultPage = recordEntityPage.map(ElectricityRecordResponse::fromEntity);
        return Response.success(resultPage);
    }

    /**
     * 查询所有缴费记录
     *
     * @param roomId    房间 ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNo    页码
     * @param pageSize  每页大小
     * @return 缴费记录
     */
    @GetMapping("/payment/list")
    public Response<Page<PaymentRecordResponse>> listAllPaymentRecord(@RequestParam(required = false)
                                                                      UUID roomId,
                                                                      @RequestParam(required = false)
                                                                      LocalDateTime startTime,
                                                                      @RequestParam(required = false)
                                                                      LocalDateTime endTime,
                                                                      @RequestParam(value = "page", defaultValue = "0")
                                                                      Integer pageNo,
                                                                      @RequestParam(value = "size", defaultValue = "10")
                                                                      Integer pageSize) {
        PaymentRecordParam param = PaymentRecordParam.builder()
                .roomId(roomId)
                .startTime(startTime)
                .endTime(endTime)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .build();

        Page<PaymentRecordEntity> paymentRecordEntityPage = electricityService.pageQueryPaymentRecord(param);
        Page<PaymentRecordResponse> resultPage = paymentRecordEntityPage.map(PaymentRecordResponse::fromEntity);
        return Response.success(resultPage);
    }

    /**
     * 缴费
     *
     * @param request 缴费请求
     * @return 缴费记录 ID
     */
    @PutMapping("/record/pay")
    public Response<UUID> payRecord(@RequestBody @Valid
                                    PaymentRecordRequest request) {
        return Response.success(electricityService.pay(request));
    }
}
