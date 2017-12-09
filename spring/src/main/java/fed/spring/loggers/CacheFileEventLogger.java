package fed.spring.loggers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import fed.spring.beans.Event;

public class CacheFileEventLogger extends FileEventLogger {

	private int cacheSize;
	private List<Event> cache;

	public CacheFileEventLogger(String fileName, int cacheSize) {
		super(fileName);
		this.cacheSize = cacheSize;
		cache = new ArrayList<Event>(cacheSize);
	}

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
