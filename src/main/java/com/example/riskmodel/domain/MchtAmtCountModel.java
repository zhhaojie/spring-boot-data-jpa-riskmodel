package com.example.riskmodel.domain;

import com.example.riskmodel.infrastructure.utility.ApplicationContextUtils;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MchtAmtCountModel implements ModelLoader<MchtAmtCountModel> {

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

    // ... 有限度的多属性（可能有数十个字段或几百个字段）

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
            MchtAmtCountModel byMchtNo = repository.findByMchtNo(qModel.mchtNo);
            return byMchtNo;
        }
        return null;
    }
}
