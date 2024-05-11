package com.example.riskmodel.domain;

import org.springframework.data.repository.ListCrudRepository;

public interface MchtBlackListModelRepository extends ListCrudRepository<MchtBlackListModel, Long> {
    MchtBlackListModel findByMchtNo(String mchtNo);
}
