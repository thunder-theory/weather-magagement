package com.weathermagagement.security.config

import com.weathermagagement.security.TokenProvider
import com.weathermagagement.security.handler.JwtAccessDeniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val jwtAuthenticationEntryFilter: JwtAuthenticationEntryFilter,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
): WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/h2/**")
            .antMatchers("/swagger-ui/**")
            .antMatchers("/swagger-resources/**")
            .antMatchers("/v2/api-docs/**")
            .antMatchers("/error")
    }

    override fun configure(http: HttpSecurity) {


//        http
//            .csrf().disable()
//            .authorizeHttpRequests()
//            .antMatchers("/user/**").permitAll()
//            .anyRequest().authenticated()
//        http
//            .cors().configurationSource(configurationSource())
//            .and()
//            .exceptionHandling()
//            .authenticationEntryPoint(jwtAuthenticationEntryFilter)
//            .accessDeniedHandler(jwtAccessDeniedHandler)
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .headers()
//            .frameOptions()
//            .sameOrigin()
//            .and()
//            .apply(JwtSecurityConfig(tokenProvider))

        //==============

        http.authorizeHttpRequests()
            .antMatchers(HttpMethod.POST ,"/login").permitAll()
            .antMatchers(HttpMethod.POST ,"/signup").permitAll()
            .antMatchers(HttpMethod.GET , "/test").permitAll()
            .antMatchers("/v2/api-docs/**").permitAll()
            .antMatchers("/swagger-ui/index.html/**").permitAll()
            .anyRequest().authenticated()

        http.csrf().disable() //                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .cors().configurationSource(configurationSource())
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryFilter) // 우리가 만든 클래스로 인증 실패 핸들링
            .accessDeniedHandler(jwtAccessDeniedHandler) // 커스텀 인가 실패 핸들링
            .and() // embedded h2를 위한 설정
            .headers()
            .frameOptions()
            .sameOrigin()
            .and() // 세션을 사용하지 않기 때문에 STATELESS로 설정
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .apply<SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>>(JwtSecurityConfig(tokenProvider))
    }

    @Bean
    fun configurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("*")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}