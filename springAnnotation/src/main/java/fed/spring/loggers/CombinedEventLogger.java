package fed.spring.loggers;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import fed.spring.beans.Event;

@Component
public class CombinedEventLogger implements EventLogger {

	@Resource(name = "combinedLoggers")
	private Collection<EventLogger> loggers;

	public CombinedEventLogger() {
	}

	public CombinedEventLogger(Collection<EventLogger> loggers) {
		this.loggers = loggers;
	}

	public void logEvent(Event event) {
		for (EventLogger eventLogger : loggers) {
			eventLogger.logEvent(event);
		}
	}

}