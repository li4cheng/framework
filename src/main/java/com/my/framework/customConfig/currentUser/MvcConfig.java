package com.my.framework.customConfig.currentUser;

import com.my.framework.repository.AuthorityRepository;
import com.my.framework.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public MvcConfig(UserRepository userRepository, AuthorityRepository authorityRepository, JPAQueryFactory jpaQueryFactory) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver(userRepository, authorityRepository, jpaQueryFactory);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }
}
