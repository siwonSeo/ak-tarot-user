package com.tarot.dto.response;

import java.util.List;

public record ResponseTarotCardConsultTotal(
          Integer consultId
        , List<ResponseTarotCardConsult> responseTarotCardConsults) {
}
