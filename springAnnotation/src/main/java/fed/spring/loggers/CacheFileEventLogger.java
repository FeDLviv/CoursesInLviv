package fed.spring.loggers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fed.spring.beans.Event;

@Component
public class CacheFileEventLogger extends FileEventLogger {

	@Value("${cache.size:5}")
	private int cacheSize;
	private List<Event> cache;

	public CacheFileEventLogger() {
	}

	public CacheFileEventLogger(String fileName, int cacheSize) {
		super(fileName);
		this.cacheSize = cacheSize;
		cache = new ArrayList<Event>(cacheSize);
	}

	@PostConstruct
	public void initCache() {
		this.cache = new ArrayList<Event>(cacheSize);
	}

	@PreDestroy
	public void destroy() {
		if (!cache.isEmpty()) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				for (Event element : cache) {
					out.writeBytes(element.toString() + System.lineSeparator());
				}
				byte[] bytes = baos.toByteArray();
				Files.write(Paths.get(this.fileName), bytes, StandardOpenOption.APPEND);
			} catch (IOException e) {
				System.out.println("Exception: " + e.getMessage());
			}
		}
	}

	@Override
	public void logEvent(Event event) {
		cache.add(event);
		if (cache.size() == cacheSize) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(baos);
				for (Event element : cache) {
					out.writeUTF(element.toString() + System.lineSeparator());
				}
				byte[] bytes = baos.toByteArray();
				Files.write(Paths.get(this.fileName), bytes, StandardOpenOption.APPEND);
			} catch (IOException e) {
				System.out.println("Exception: " + e.getMessage());
			}
			cache.clear();
		}
	}

}
