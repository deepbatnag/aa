package com.nagarro.advancejavaweek2.main;

import java.io.File;
import java.nio.file.attribute.FileTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nagarro.advancejavaweek2.exceptions.InvalidGender;
import com.nagarro.advancejavaweek2.exceptions.InvalidOutputPreference;
import com.nagarro.advancejavaweek2.exceptions.InvalidSize;
import com.nagarro.advancejavaweek2.helper.FactoryProvider;
import com.nagarro.advancejavaweek2.io.Input;
import com.nagarro.advancejavaweek2.loadfiles.LoadFiles;
import com.nagarro.advancejavaweek2.searchTshirt.SearchTshirt;
import com.nagarro.advancejavaweek2.watchchanges.WatchChanges;

public class Main {
	static Set<String> fileName = new HashSet<String>();
	public static boolean hasChanges = false;
	static int count = 1;
	public static String color, gender, size, outputPreference;
	public static String path = new File("").getAbsolutePath() + File.separator + "/src/main/resources/csv-files";

	public static void main(String[] args) {

		LoadFiles ld = new LoadFiles(fileName);
		Timer t = new Timer();
		t.schedule(ld, 1000, 5000);

		WatchChanges wc = new WatchChanges();
		Thread t2 = new Thread(wc);
		t2.start();
		inputChoice();
	}

	@SuppressWarnings("resource")
	public static void inputChoice() {
		try {

			Scanner scan = new Scanner(System.in);
			Input.getColor();
			Input.getSize();
			Input.getGender();
			Input.getPreference();
			SessionFactory factory = FactoryProvider.getFactory();
			Session session = factory.openSession();
			SearchTshirt tshirt = new SearchTshirt();
			if (count == 1 || hasChanges == true) {
				count++;
				for (String filePath : fileName) {
					tshirt.addToDB(filePath, session);
				}
			}
			tshirt.dbToList(session, color, size, gender);
			tshirt.updateView(outputPreference);
			session.close();
			FactoryProvider.closeFactory();
			anotherTshirt();
			scan.close();
		} catch (Exception e) {
			System.out.println("Invalid Input Type!");
			inputChoice();
		}
	}

	private static void anotherTshirt() {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to search for other Tshirt ?");
		System.out.println("1. Yes   2. No");
		int choice = scan.nextInt();
		if (choice == 1) {
			inputChoice();
		} else {
			System.out.println("Thanks!!");
			System.exit(0);
		}
	}

	public static boolean isAlpha(String s) {
		return s != null && s.chars().allMatch(Character::isLetter);
	}
}
