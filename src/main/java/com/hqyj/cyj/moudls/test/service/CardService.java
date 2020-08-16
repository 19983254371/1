package com.hqyj.cyj.moudls.test.service;

import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.test.entity.Card;

public interface CardService {
    Result<Card> insertCard(Card card);
}
