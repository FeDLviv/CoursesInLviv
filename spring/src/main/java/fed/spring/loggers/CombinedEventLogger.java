package fed.spring.loggers;

import java.util.Collection;

import fed.spring.beans.Event;

public class CombinedEventLogger implements EventLogger {

	private Collection<EventLogger> loggers;

	public CombinedEventLogger(Collection<EventLogger> loggers) {
		this.loggers = loggers;
	}

	public void logEvent(Event event) {
		for (EventLogger eventLogger : loggers) {
			eventLogger.logEvent(event);
		}
	}

}