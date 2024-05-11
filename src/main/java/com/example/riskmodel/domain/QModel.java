package com.example.riskmodel.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 该类用于派生出其它的数据模型（从数据库或者redis缓存当中）
 */
@Getter
@Setter
@Data
public class QModel {

    public String mchtNo;

    public String txCode;

    public Integer cycle;

    public Integer tradeType;


    // 这个类要作为所有数据模型访问的Root根，因为它的各种属性值必须能够低成本地获取到。

}
