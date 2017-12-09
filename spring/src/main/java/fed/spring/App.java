package fed.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fed.spring.beans.Client;
import fed.spring.beans.Event;
import fed.spring.loggers.EventLogger;

public class App {

	private Client client;
	private EventLogger eventLogger;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		App app = (App) context.getBean("app");

		Event event = context.getBean(Event.class);
		app.logEvent(event, "Some event for user 1");

		event = context.getBean(Event.class);
		app.logEvent(event, "Some event for user 2");

		context.close();
	}

	public App(Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
	}

	public void logEvent(Event event, String msg) {
		String message = msg.replaceAll(client.getId(), client.getFullName());
		event.setMsg(message);
		eventLogger.logEvent(event);
	}

}
