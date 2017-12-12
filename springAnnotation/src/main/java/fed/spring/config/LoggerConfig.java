package fed.spring.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fed.spring.beans.EventType;
import fed.spring.loggers.EventLogger;

@Configuration
public class LoggerConfig {

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

}