package com.tarot.dto.response;

import com.tarot.dto.request.RequestTarotCard;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseUserTarotCardConsult(
          Integer id
        , Integer userId
        , int cardCount
        , Boolean isReverseOn
        , Character categoryCode
        , LocalDateTime createdAt
        , List<RequestTarotCard.TarotCardSearch> searchCards){
}
