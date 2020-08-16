package com.hqyj.cyj.moudls.test.controller;


import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.test.entity.Card;
import com.hqyj.cyj.moudls.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    CardService cardService;

    /**
     * 127.0.0.1/api/card --post
     * {"cardNo":"csadasdawfdasf"}
     * */
    @PostMapping(value = "/card",consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card){
        return cardService.insertCard(card);
    }

}
