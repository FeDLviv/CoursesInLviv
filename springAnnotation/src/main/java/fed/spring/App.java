package fed.spring;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import fed.spring.aspects.StatisticsAspect;
import fed.spring.beans.Client;
import fed.spring.beans.Event;
import fed.spring.beans.EventType;
import fed.spring.config.AppConfig;
import fed.spring.config.LoggerConfig;
import fed.spring.loggers.EventLogger;

@Service
public class App {

	@Autowired
	private Client client;
	@Value("#{T(fed.spring.beans.Event).isDay() ? cacheFileEventLogger : consoleEventLogger}")
	private EventLogger defaultLogger;
	@Resource(name = "loggerMap")
	private Map<EventType, EventLogger> loggers;
	@Value("#{'Hello user ' + (systemProperties['os.name'].contains('Windows') ?  systemEnvironment['USERNAME'] : systemEnvironment['USER'])}")
	private String startMsg;
	@Autowired
	private StatisticsAspect statisticsAspect;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class, LoggerConfig.class);
		context.scan("fed.spring");
		context.refresh();

		App app = (App) context.getBean("app");

		System.out.println(app.startMsg);

		Event event = context.getBean(Event.class);
		app.logEvent(EventType.INFO, event, "Some event for user 1");

		event = context.getBean(Event.class);
		app.logEvent(EventType.ERROR, event, "Some event for user 2 (error)");

		event = context.getBean(Event.class);
		app.logEvent(null, event, "Some event for user 3");

		app.outputLoggingCounter();

		context.close();
	}

	public App() {
	}

	public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
		this.client = client;
		this.defaultLogger = defaultLogger;
		this.loggers = loggers;
	}

	public void logEvent(EventType type, Event event, String msg) {
		String message = msg.replaceAll(client.getId(), client.getFullName());
		event.setMsg(message);

		EventLogger logger = loggers.get(type);
		if (logger == null) {
			logger = defaultLogger;
		}

		logger.logEvent(event);
	}

	private void outputLoggingCounter() {
		if (statisticsAspect != null) {
			System.out.println("Loggers statistics. Number of calls: ");
			for (Entry<Class<?>, Integer> entry : statisticsAspect.getCounter().entrySet()) {
				System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
			}
		}
	}

}
