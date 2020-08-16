package com.hqyj.cyj.moudls.test.service.Impl;

import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.test.entity.Card;
import com.hqyj.cyj.moudls.test.repository.CardRepository;
import com.hqyj.cyj.moudls.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    @Transactional
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(Result.ResultStatus.SUCCESS.status,"Insert success.",card);
    }
}
