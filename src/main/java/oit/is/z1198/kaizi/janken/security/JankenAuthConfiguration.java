package oit.is.z1198.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class JankenAuthConfiguration {

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    // このクラスの下部にあるPasswordEncoderを利用して，ユーザのビルダ（ログインするユーザを作成するオブジェクト）を作成する
    UserBuilder users = User.builder();

    // UserBuilder usersにユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されている．
    // ハッシュ化されたパスワードを得るには，この授業のbashターミナルで下記のように末尾にユーザ名とパスワードを指定すると良い(要VPN)
    // $ sshrun htpasswd -nbBC 10 user1 p@ss
    UserDetails user1 = users
        .username("user1")
        .password("$2y$10$fN2yDYSVr2ULYUmDXGCZeeW8Y.BO7qy4CzdsO53ZZ3J/o9tR0I6Nm")
        .roles("USER")
        .build();
    UserDetails user2 = users
        .username("user2")
        .password("$2y$10$yRVmSzrgZVkV0IPp1c5sIu9SVpAMssfXoSoVwFDQO/ArKfINTYDgy")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user1, user2);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.formLogin();

    http.authorizeHttpRequests()
        .mvcMatchers("/janken/**").authenticated();

    http.logout().logoutSuccessUrl("/");
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
