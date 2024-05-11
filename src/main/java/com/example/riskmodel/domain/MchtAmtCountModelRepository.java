package com.example.riskmodel.domain;

import org.springframework.data.repository.ListCrudRepository;

public interface MchtAmtCountModelRepository extends ListCrudRepository<MchtAmtCountModel, Long> {
    MchtAmtCountModel findByMchtNo(String mchtNo);
}
