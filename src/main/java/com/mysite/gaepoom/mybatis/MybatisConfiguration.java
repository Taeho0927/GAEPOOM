package com.mysite.gaepoom.mybatis;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@MapperScan("com.mysite.gaepoom.mappers")
public class MybatisConfiguration {
    // 커넥션 풀 및 MyBatis 에 필요한 요소를 메모리에 할당 및 관리, xml 과 java 연동에 필요한 경로를 관리함.
    private final ApplicationContext applicationContext;
    // @Bean
    // @Configuration 또는 @Component 가 작성된 클래스의 메서드에 사용
    // 메서드의 실행결과(리턴값)을 스프링 컨테이너에 등록
    // 객체명은 메서드의 이름으로 자동 생성되며, 직접 설정하려면 @Bean(name="객체명")으로 사용

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari") // 상위 경로를 고정
    public HikariConfig hikariConfig() { return new HikariConfig(); }
    // application.properties 에 작성된 JDBC datasource 정보 설정

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    } // DataSource 객체에 DBMS 정보 설정

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        // 세션 팩토리 객체 생성
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 위에서 설정한 datasource 객체를 세션 팩토리 설정에 전달
        sqlSessionFactoryBean.setDataSource(dataSource());

        // SQL 쿼리를 작성할 xml 경로 설정
        sqlSessionFactoryBean.setMapperLocations(
                applicationContext.getResources("classpath*:/mappers/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(
                applicationContext.getResource("classpath:/config/config.xml"));

        try{
            // Session Factory 생성
            SqlSessionFactory factory = sqlSessionFactoryBean.getObject();

            // 카멜 표기법으로 자동 변환
            factory.getConfiguration().setMapUnderscoreToCamelCase(true);

            return  factory;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
