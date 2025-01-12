package vn.hoidanit.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpMessageConverterAuthenticationSuccessHandler.AuthenticationSuccess;

import jakarta.servlet.DispatcherType;
import vn.hoidanit.laptopshop.service.CustomUserDetailsService;
import vn.hoidanit.laptopshop.service.UserService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() 
        {
            return new BCryptPasswordEncoder();
        }
// login 
    @Bean
        public UserDetailsService userDetailsService(UserService userService)
        {
            return new CustomUserDetailsService(userService);
        }
// Nạp tất cả các thông tin để Spring thực hiện BCryptPasswordEncoder() và CustomUserDetailsService(userService) cho phần Login
   @Bean
        public DaoAuthenticationProvider authProvider(
                PasswordEncoder passwordEncoder,
                UserDetailsService userDetailsService) 
        {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService);
            authProvider.setPasswordEncoder(passwordEncoder);
            authProvider.setHideUserNotFoundExceptions(false);
            return authProvider;
        }
    
        @Bean
        public AuthenticationSuccessHandler customSuccessHandler(){
            return new CustomSuccessHandler();
        }
        

    @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            // v6 cú phám lamda
            http
            .authorizeHttpRequests(authorize -> authorize
            .dispatcherTypeMatchers(DispatcherType.FORWARD,
                DispatcherType.INCLUDE) 
            .permitAll()
            .requestMatchers("/","/login", "/client/**", "/css/**", "/product/**","/js/**",
                            "/images/**").permitAll()
            
            .requestMatchers("/admin/**").hasRole("ADMIN") // chỉ cho admin mới vào đc trang admin
            .anyRequest().authenticated()) //permitAll() là cho tất cả mọi người đi hết,không cần xác thực

            .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .failureUrl("/login?error")
            .successHandler(customSuccessHandler())
            .permitAll());
            return http.build();
        }
}
