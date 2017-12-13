package fed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleMessage {

	@Autowired
	private Message msg;

	public void printMessage() {
		System.out.println(msg.getMessage());
	}
}
