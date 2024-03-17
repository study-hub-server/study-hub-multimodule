package com.example.webclient.api.user.resolver;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.webclient.api.user.dto.common.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Value("${jwt.secret}")
    private String SECRET;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == UserId.class;
    }

    @Override
    public UserId resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        try {
            String jwtToken = webRequest.getHeader("Authorization");
            System.out.println("토큰입니다 : " + jwtToken);
            if (jwtToken == null) {
                return new UserId(null);
            }
            jwtToken = jwtToken.replace("Bearer ", "");
            Long id = (JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("id")).asLong();
            return new UserId(id);
        } catch (Exception e) {
            return null;
        }
    }
}
