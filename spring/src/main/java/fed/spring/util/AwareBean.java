package fed.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class AwareBean implements ApplicationContextAware, BeanNameAware, ApplicationEventPublisherAware {

	private ApplicationContext context;
	private String name;
	private ApplicationEventPublisher eventPublisher;

	public void init() {
		System.out.println(this.getClass().getSimpleName() + " -> My name is '" + name + "'");
		if (context != null) {
			System.out.println(this.getClass().getSimpleName() + " > My context is " + context.getClass());
		} else {
			System.out.println(this.getClass().getSimpleName() + " > Context is not set");
		}
		if (eventPublisher != null) {
			System.out
					.println(this.getClass().getSimpleName() + " > My eventPublisher is " + eventPublisher.getClass());
		} else {
			System.out.println(this.getClass().getSimpleName() + " > EventPublisher is not set");
		}
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	public void setBeanName(String name) {
		this.name = name;
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

}