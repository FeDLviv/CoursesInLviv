package fed.spring.loggers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import fed.spring.beans.Event;

public class FileEventLogger implements EventLogger {

	protected String fileName;

	public FileEventLogger(String fileName) {
		this.fileName = fileName;
	}

	private void init() throws IOException {
		File temp = new File(fileName);
		if (temp.exists() && !temp.canWrite()) {
			throw new IllegalArgumentException("Can't write to file " + fileName);
		} else if (!temp.exists()) {
			try {
				temp.createNewFile();
			} catch (Exception e) {
				throw new IllegalArgumentException("Can't create file", e);
			}

		}
	}

	public void logEvent(Event event) {
		try {
			Files.write(Paths.get(fileName), (event.toString() + System.lineSeparator()).getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

}
