package fed.spring.beans;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Event {

	private static int ID = 0;

	private int id;
	private String msg;
	@Autowired
	@Qualifier("newDate")
	private Date date;
	@Autowired
	private DateFormat dateFormat;

	public Event() {
	}

	public Event(Date date, DateFormat dateFormat) {
		id = ++ID;
		this.date = date;
		this.dateFormat = dateFormat;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return id + " " + msg + " " + dateFormat.format(date);
	}

}
