package com.example.riskmodel.domain;

import com.example.riskmodel.ApplicationContextUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Async;

/**
 * 这是其中一个数据模型之一
 */
@Data
@RiskModel
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MchtAmtCountModel implements RiskModelLoader<MchtAmtCountModel> {

    @Id
    @GeneratedValue
    private Long id;

    // 数据模型的属性
    @Nonnull
    private String mchtNo;

    // 数据模型的属性
    private Long payAmt;

    // 数据模型的属性
    private Long day1Count;

    // 数据模型的属性
    private Long day2Count;

    @Override
    public boolean match(QModel qModel) {
        return qModel != null && !qModel.mchtNo.isEmpty();
    }

    /**
     * 满足什么条件再去执行load方法
     */
    @Override
    @Async
    public MchtAmtCountModel loadModel(QModel qModel) {
        if (match(qModel)) {
            MchtAmtCountModelRepository repository = ApplicationContextUtils.getApplicationContext().getBean(MchtAmtCountModelRepository.class);
            System.out.println("Loading MchtAmtCountModel ... via " + Thread.currentThread().getName());
            return repository.findByMchtNo(qModel.mchtNo);
        }
        return this;
    }
}