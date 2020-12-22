package com.my.framework.customConfig.currentUser;

import com.my.framework.customConfig.error.CustomException;
import com.my.framework.repository.AuthorityRepository;
import com.my.framework.repository.UserRepository;
import com.my.framework.security.SecurityUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final JPAQueryFactory jpaQueryFactory;


    public CurrentUserMethodArgumentResolver(UserRepository userRepository, AuthorityRepository authorityRepository, JPAQueryFactory jpaQueryFactory) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //判断方法参数是否带有@CurrentUser注解且参数类型为UserModel或其子类
        return methodParameter.hasParameterAnnotation(CurrentUser.class) && UserModel.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        if (SecurityUtils.getCurrentUserModel().isPresent()) {
            return SecurityUtils.getCurrentUserModel().get();
        } else {
            throw new CustomException("userModel获取失败");
        }
    }
}
