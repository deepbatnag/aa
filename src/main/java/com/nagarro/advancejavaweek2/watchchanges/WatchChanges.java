package com.nagarro.advancejavaweek2.watchchanges;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

import com.nagarro.advancejavaweek2.main.Main;

public class WatchChanges implements Runnable {
	 String folderPath = Main.path;

	public void watchDir() {

		try (WatchService service = FileSystems.getDefault().newWatchService()) {
			Map<WatchKey, Path> keyMap = new HashMap<>();
			Path path = Paths.get(folderPath);
			keyMap.put(path.register(service, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE), path);
			WatchKey watchKey = null;
			watchKey = service.take();
			for (WatchEvent<?> event : watchKey.pollEvents()) {
				Main.hasChanges = true;
			}
			watchKey.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		watchDir();
	}
}
