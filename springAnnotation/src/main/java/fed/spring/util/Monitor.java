package fed.spring.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Monitor implements ApplicationListener<ApplicationEvent> {

	public void onApplicationEvent(ApplicationEvent ev) {
		System.out.println(ev.getClass().getSimpleName() + " -> " + ev.getSource().toString());
	}

}