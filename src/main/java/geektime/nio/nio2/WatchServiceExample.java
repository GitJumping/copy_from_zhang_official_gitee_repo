package geektime.nio.nio2;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatchServiceExample {
	
	public static void main(String[] args) throws IOException {
		String watchdir = System.getProperty("java.io.tmpdir");
		if (args.length >= 1) {
			watchdir = args[0];
		}
		FileSystem fsDefault = FileSystems.getDefault();
		WatchService ws = fsDefault.newWatchService();
		Path dir = fsDefault.getPath(watchdir);
		dir.register(ws, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		for (;;) {
			WatchKey key;
			try {
				key = ws.take();
			} catch (InterruptedException ie) {
				return;
			}
			for (WatchEvent event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if (kind == OVERFLOW) {
					System.out.println("overflow");
					continue;
				}
				WatchEvent ev = (WatchEvent) event;
				Path filename = (Path) ev.context();
				System.out.printf("%s: %s%n", ev.kind(), filename);
			}
			boolean valid = key.reset();
			if (!valid)
				break;
		}
	}
}
