package com.websystique.springsecurity.configuration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.websystique.springsecurity.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    //private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/application");
    
    /*public ResourceBundle  resourceBundle() throws MalformedURLException{
        File file = new File("application.properties");
        URL[] urls = {file.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        return ResourceBundle.getBundle("application", Locale.getDefault(), loader);
    }*/
    @Bean
    public MessageSource messageSource() { 
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("locale");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    } 
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws MalformedURLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.websystique.springsecurity.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        //dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        //dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        //dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/vk_informer_db?useUnicode=true&amp;connectionCollation=utf8_general_ci&amp;characterSetResults=utf8&amp;characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
    
    private Properties hibernateProperties() throws MalformedURLException {
        Properties properties = new Properties();
        //properties.put("hibernate.dialect", messageSource().getMessage("hibernate.dialect", null, Locale.getDefault()));
        //properties.put("hibernate.show_sql", messageSource().getMessage("hibernate.show_sql", null, Locale.getDefault()));
        //properties.put("hibernate.format_sql", messageSource().getMessage("hibernate.format_sql", null, Locale.getDefault()));
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql")); 
        return properties;        
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

