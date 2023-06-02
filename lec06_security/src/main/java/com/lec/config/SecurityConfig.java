package com.lec.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BoardUserDetailService boardUserDetailService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
//		security.authorizeHttpRequests().antMatchers("/").permitAll();
//		security.authorizeHttpRequests().antMatchers("/member/**").authenticated();
//		security.authorizeHttpRequests().antMatchers("/manager/**").hasRole("MANAGER");
//		security.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
		
		security.authorizeHttpRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/member/**").authenticated()
			.antMatchers("/manager/**").hasRole("MANAGER")
			.antMatchers("/admin/**").hasRole("ADMIN");
		
		security.csrf().disable();
		security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);   
		// formLogin 메서드: form태그 기반의 로그인을 지원
		// 에러화면이 아닌 스프링부트가 제공하는 로그인 화면으로 이동
		// 스프링부트의 로그인 화면이 아닌 사용자가 정의한 로그인 화면을 제공할 경우
		// loginPage() 메서드를 추가
		
		security.exceptionHandling().accessDeniedPage("/accessDenied");  // 접근 권한 없음 페이지
		
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");  // 로그아웃 -> 로그인 페이지 
		
		security.userDetailsService(boardUserDetailService);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
//	@Autowired  // AuthenticationManagerBuilder 의존성 주입
//	private void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication()  // 로그인정보를 메모리에 저장하는 메서드
//			.withUser("manager")
//			.password("{noop}12345")  // {noop} : 스프링이 문자 그대로 비밀번호로 인식하게 하는 옵션
//			.roles("MANAGER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password("{noop}12345")  // {noop} : 스프링이 문자 그대로 비밀번호로 인식하게 하는 옵션
//			.roles("ADMIN");
//	
//	}
	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired  // AuthenticationManagerBuilder 의존성 주입
	private void authenticate(AuthenticationManagerBuilder auth) throws Exception {

		String sql1 = "select id username, concat('{noop}', password) password, "
						+ "true enabled from member where id=?";
		String sql2 = "select id, role from member where id=?";
		
		// Spring Security는 username, password 고정 기본값
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(sql1)
			.authoritiesByUsernameQuery(sql2);
		
	}
	
}















