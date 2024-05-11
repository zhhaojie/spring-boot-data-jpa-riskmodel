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

import java.time.LocalDate;

/**
 * 这是其中一个数据模型之二
 */
@Data
@RiskModel
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MchtBlackListModel implements RiskModelLoader<MchtBlackListModel> {

    @Id
    @GeneratedValue
    private Long id;

    @Nonnull
    private String mchtNo;

    @Nonnull
    private LocalDate beginDate;

    private LocalDate endDate;

    @Override
    public boolean match(QModel qModel) {
        return qModel != null && !qModel.mchtNo.isEmpty();
    }

    @Override
    @Async
    public MchtBlackListModel loadModel(QModel qModel) {
        MchtBlackListModelRepository repository = ApplicationContextUtils.getApplicationContext().getBean(MchtBlackListModelRepository.class);
        System.out.println("Loading MchtBlackListModel ... via " + Thread.currentThread().getName());
        return repository.findByMchtNo(qModel.mchtNo);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

