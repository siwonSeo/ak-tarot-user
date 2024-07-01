package com.tarot.controller;

import com.tarot.entity.user.UserBaseInterpretation;
import com.tarot.service.TarotService;
import com.tarot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
    private final UserService userService;
    private final TarotService tarotService;

    @GetMapping("/main")
    public String mainPage(Model model, @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("cardConsults", userService.getUserTarotCardConsults(pageable));
        return "/user/main";
    }

    @GetMapping("/consult/datail/{consultId}")
    public String consultDetail(Model model, @PathVariable("consultId") Integer consultId) {
        UserBaseInterpretation userBaseInterpretatio = userService.getUserBaseInterpretation(consultId);

        model.addAttribute("isReverseOn", userBaseInterpretatio.getIsReverseOn());
        model.addAttribute("category", tarotService.getCardCategorie(userBaseInterpretatio.getCategoryCode()));
        model.addAttribute("reading", tarotService.getTaroCardReading(userBaseInterpretatio.getCardCount()));
        model.addAttribute("cards", tarotService.getTaroCardConsultsBySelf(userBaseInterpretatio.getSearchCards()));
        return "selected";
    }

}
