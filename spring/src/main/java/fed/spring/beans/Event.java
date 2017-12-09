package fed.spring.beans;

import java.text.DateFormat;
import java.util.Date;

public class Event {

	private static int ID = 0;

	private int id;
	private String msg;
	private Date date;
	private DateFormat dateFormat;

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
