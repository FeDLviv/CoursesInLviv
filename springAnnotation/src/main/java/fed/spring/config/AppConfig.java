package fed.spring.config;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import fed.spring.beans.Client;

@Configuration
@PropertySource("classpath:client.properties")
@EnableAspectJAutoProxy
public class AppConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	private Environment environment;

	@Bean
	public Date newDate() {
		return new Date();
	}

	@Bean
	public DateFormat dateFormat() {
		return DateFormat.getDateTimeInstance();
	}

	@Bean
	public Client client() {
		Client client = new Client();
		client.setId(environment.getRequiredProperty("id"));
		client.setFullName(environment.getRequiredProperty("name"));
		client.setGreeting(environment.getProperty("greeting"));
		return client;
	}

}