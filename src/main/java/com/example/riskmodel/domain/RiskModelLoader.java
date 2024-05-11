package com.example.riskmodel.domain;

/**
 * 定义数据模型的加载接口，QModel 可以是一个基础对象，它很容易获得。
 *
 * @param <T>
 */
public interface RiskModelLoader<T> {

    String LOAD_METHOD = "loadModel";

    default boolean match(QModel qModel) {
        return false;
    }

    T loadModel(QModel qModel);

}
