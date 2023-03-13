package com.nagarro.advancejavaweek2.loadfiles;

import java.io.File;
import java.util.Set;
import java.util.TimerTask;

import com.nagarro.advancejavaweek2.main.Main;

public class LoadFiles extends TimerTask {
	private Set<String> fileName;

	public LoadFiles(Set<String> fileName) {
		this.fileName = fileName;
	}

	public void run() {
		String path = Main.path;
		File[] files = new File(path).listFiles();
		for (File fileName : files) {
			String filePath = path + "/" + fileName.getName();
			this.fileName.add(filePath);
		}
	}
}
