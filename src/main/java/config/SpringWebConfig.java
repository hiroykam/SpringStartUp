package config;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import dao.UsersDAO;
import dao.UsersDAOImpl;

 
@EnableWebMvc
@Configuration
@ComponentScan({ "controller" })
public class SpringWebConfig extends WebMvcConfigurerAdapter {
	
	@Bean(name="templateResolver")
	public ServletContextTemplateResolver getTemplateResolver() {
    	ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
    	templateResolver.setPrefix("/WEB-INF/templates/");
    	templateResolver.setSuffix(".html");
    	templateResolver.setTemplateMode("XHTML");
    	return templateResolver;
    }
	@Bean(name ="templateEngine")	    
    public SpringTemplateEngine getTemplateEngine() {
    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    	templateEngine.setTemplateResolver(getTemplateResolver());
    	return templateEngine;
    }
    @Bean(name="viewResolver")
    public ThymeleafViewResolver getViewResolver(){
    	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); 
    	viewResolver.setTemplateEngine(getTemplateEngine());
    	return viewResolver;
    }
    
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/berserker");
        dataSource.setUsername("root");
        dataSource.setPassword("");
         
        return dataSource;
    }
	
	@Bean
    public UsersDAO getContactDAO() {
        return new UsersDAOImpl(getDataSource());
    }
 
}
