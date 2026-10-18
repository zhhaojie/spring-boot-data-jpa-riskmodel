package com.example.riskmodel;

import com.example.riskmodel.domain.MchtAmtCountModel;
import com.example.riskmodel.domain.MchtAmtCountModelRepository;
import com.example.riskmodel.domain.MchtBlackListModel;
import com.example.riskmodel.domain.MchtBlackListModelRepository;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class RiskModelLoadApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(RiskModelLoadApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 101; i++) {
            MchtAmtCountModel model = MchtAmtCountModel.builder()
                    .mchtNo("mchtNo." + i)
                    .payAmt(1000L)
                    .day1Count(100L + i)
                    .day2Count(200L + i)
                    .build();
            mchtAmtCountModelRepository.save(model);
        }

        for (int i = 0; i < 101; i++) {
            MchtBlackListModel model = MchtBlackListModel.builder()
                    .mchtNo("mchtNo." + i)
                    .beginDate(LocalDate.now())
                    .build();
            mchtBlackListModelRepository.save(model);
        }
    }

    @Resource
    MchtAmtCountModelRepository mchtAmtCountModelRepository;

    @Resource
    MchtBlackListModelRepository mchtBlackListModelRepository;


}
