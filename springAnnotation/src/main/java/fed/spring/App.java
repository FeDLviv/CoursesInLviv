package fed.spring;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

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
	@Resource(name = "defaultLogger")
	private EventLogger defaultLogger;
	@Resource(name = "loggerMap")
	private Map<EventType, EventLogger> loggers;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class, LoggerConfig.class);
		context.scan("fed.spring");
		context.refresh();

		App app = (App) context.getBean("app");

		Client client = context.getBean(Client.class);
		System.out.println("Client says: " + client.getGreeting());

		Event event = context.getBean(Event.class);
		app.logEvent(EventType.INFO, event, "Some event for user 1");

		event = context.getBean(Event.class);
		app.logEvent(EventType.ERROR, event, "Some event for user 2 (error)");

		event = context.getBean(Event.class);
		app.logEvent(null, event, "Some event for user 3");

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

}
