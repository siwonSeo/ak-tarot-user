package com.tarot.service;

import com.tarot.auth.CustomUserDetails;
import com.tarot.dto.request.RequestTarotCard;
import com.tarot.entity.user.UserBaseInterpretation;
import com.tarot.repository.UserBaseInterpretationRepository;
import com.tarot.repository.UserBaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserBaseInterpretationRepository userBaseInterpretationRepository;
    @Transactional
    public void saveUserConsult(int cardCount , Boolean isReverseOn
            , Character categoryCode, List<RequestTarotCard.TarotCardSearch> params){
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(customUserDetails != null) {
                userBaseInterpretationRepository.save(new UserBaseInterpretation(
                        customUserDetails.getId()
                        , cardCount
                        , isReverseOn
                        , categoryCode
                        , params
                ));
            }
        }catch (Exception e){
            log.debug("상담이력 저장 오류:{}",e.getMessage());
        }

    }
}
