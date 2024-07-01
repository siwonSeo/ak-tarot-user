package com.tarot.auth;

import com.tarot.entity.user.UserBase;
import com.tarot.repository.UserBaseRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
public class CustomOAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CookieProvider cookieProvider;

    @Autowired
    private UserBaseRepository userBaseRepository;

    @Value("${server.servlet.session.timeout}")
    private int TIMEOUT_SECOND;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("given_name");
        String picture = oAuth2User.getAttribute("picture");
        UserBase user = userBaseRepository.findByEmail(email).orElseGet(
            ()-> userBaseRepository.save(new UserBase(email, name, picture))
        );

        UserDetails userDetails = new CustomUserDetails(
                user.getId(),
                "",
                user.getEmail(),
                user.getName(),
                user.getPicture(),
                Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities())); //커스텀 정보 저장
        HttpSession session = request.getSession(false);
        log.info("###session{}:",session);
        if (session != null) {
            session.setMaxInactiveInterval(TIMEOUT_SECOND);
        }

        log.info("authentication:{}",SecurityContextHolder.getContext().getAuthentication().getDetails());

//        cookieProvider.setCookie(response, token); //쿠키사용 제거

//        Authentication auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(auth);

        // 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, "/");
    }
}