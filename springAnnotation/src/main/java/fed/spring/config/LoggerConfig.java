package fed.spring.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import fed.spring.beans.EventType;
import fed.spring.loggers.EventLogger;

@Configuration
@PropertySource("classpath:db.properties")
public class LoggerConfig {

	@Autowired
	private Environment environment;

	@Resource(name = "consoleEventLogger")
	private EventLogger consoleEventLogger;

	@Resource(name = "fileEventLogger")
	private EventLogger fileEventLogger;

	@Resource(name = "cacheFileEventLogger")
	private EventLogger cacheFileEventLogger;

	@Resource(name = "combinedEventLogger")
	private EventLogger combinedEventLogger;

	@Bean
	public Collection<EventLogger> combinedLoggers() {
		Collection<EventLogger> loggers = new ArrayList<EventLogger>(2);
		loggers.add(consoleEventLogger);
		loggers.add(cacheFileEventLogger);
		return loggers;
	}

	@Bean
	public Map<EventType, EventLogger> loggerMap() {
		Map<EventType, EventLogger> map = new EnumMap<EventType, EventLogger>(EventType.class);
		map.put(EventType.INFO, consoleEventLogger);
		map.put(EventType.ERROR, combinedEventLogger);
		return map;
	}

	@Bean
	public EventLogger defaultLogger() {
		return cacheFileEventLogger;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(environment.getProperty("mysql.driverClassName"));
		ds.setUrl(environment.getRequiredProperty("mysql.url"));
		ds.setUsername(environment.getRequiredProperty("mysql.username"));
		ds.setPassword(environment.getRequiredProperty("mysql.password"));
		return ds;
	}

	@Autowired
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}