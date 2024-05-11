package com.example.riskmodel.application;

import com.example.riskmodel.domain.QModel;
import com.example.riskmodel.domain.RiskModelLoader;
import com.example.riskmodel.domain.finder.RiskModelsManager;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class TaskApplication {

    @Resource
    ThreadPoolTaskExecutor taskExecutor;


    @Scheduled(fixedDelay = 1000, initialDelay = 5000)
    public void simulateTask() throws Exception {
        // [0,102)
        int next = ThreadLocalRandom.current().nextInt(0, 102);

        // 构建基础查询Model
        QModel qModel = new QModel();
        qModel.mchtNo = "mchtNo." + next;
        qModel.txCode = "10";
        qModel.cycle = 1;

        // 过滤出某种标识的数据模型类对象
        List<Class<?>> riskModels = RiskModelsManager.getModels();

        // 每个类对象都指定唯一的Load入口。满足某种条件时才会执行Load方法。
        List<Future<?>> resultFutures = new ArrayList<>();
        for (Class<?> clazz : riskModels) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method loadMethod = clazz.getMethod(RiskModelLoader.LOAD_METHOD, QModel.class);

            // Object result = loadMethod.invoke(modelInstance, qModel);
            Future<?> futures = taskExecutor.submit(() -> loadMethod.invoke(instance, qModel));
            resultFutures.add(futures);
        }

        for (Future<?> resultFuture : resultFutures) {
            Object result = resultFuture.get(100, TimeUnit.MILLISECONDS);
            System.out.println("Result:" + result);
        }

        // 得到resultFutures 之后，再与风控规则进行匹配执行。


        // 执行的结果通过异步事件通知执行。
    }
}
