package com.example.riskmodel;

import com.example.riskmodel.domain.*;
import com.example.riskmodel.domain.finder.RiskModelsManager;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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
            MchtAmtCountModel model = new MchtAmtCountModel()
                    .setMchtNo("mchtNo." + i)
                    .setPayAmt(1000L)
                    .setDay1Count(100L + i)
                    .setDay2Count(200L + i);
            mchtAmtCountModelRepository.save(model);
        }

        for (int i = 0; i < 101; i++) {
            MchtBlackListModel model = new MchtBlackListModel()
                    .setMchtNo("mchtNo." + i)
                    .setBeginDate(LocalDate.now());
            mchtBlackListModelRepository.save(model);
        }
    }

    @Resource
    MchtAmtCountModelRepository mchtAmtCountModelRepository;

    @Resource
    MchtBlackListModelRepository mchtBlackListModelRepository;


}
