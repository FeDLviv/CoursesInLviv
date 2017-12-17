package fed.spring.loggers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fed.spring.beans.Event;

@Component("dbLogger")
public class DBLogger implements EventLogger {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		jdbcTemplate.update(
				"CREATE TABLE IF NOT EXISTS event(id int UNSIGNED NOT NULL AUTO_INCREMENT, msg varchar(255) NULL, date TIMESTAMP, PRIMARY KEY (id))");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Total events in the DB: " + getTotalEvents());

		List<Event> allEvents = getAllEvents();
		System.out.println("All DB events:");
		for (Event event : allEvents) {
			System.out.println(event);
		}
	}

	public void logEvent(Event event) {
		jdbcTemplate.update("INSERT INTO event (msg, date) VALUES (?, ?)", event.getMsg(), event.getDate());
		System.out.println("Saved to DB event");
	}

	public int getTotalEvents() {
		Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM event", Integer.class);
		return (count != null) ? count.intValue() : 0;
	}

	public List<Event> getAllEvents() {
		List<Event> list = jdbcTemplate.query("SELECT * FROM event", new RowMapper<Event>() {

			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Date date = rs.getDate("date");
				String msg = rs.getString("msg");
				Event event = new Event(new Date(date.getTime()), DateFormat.getDateTimeInstance());
				event.setMsg(msg);
				return event;
			}
		});
		return list;
	}

}
