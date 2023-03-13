package com.nagarro.advancejavaweek2.io;

import java.util.Scanner;

import com.nagarro.advancejavaweek2.exceptions.InvalidGender;
import com.nagarro.advancejavaweek2.exceptions.InvalidOutputPreference;
import com.nagarro.advancejavaweek2.exceptions.InvalidSize;
import com.nagarro.advancejavaweek2.main.Main;

public class Input {
	static Scanner scan = new Scanner(System.in);

	public static void getColor() {
		try {
			System.out.println("Enter Color of the Tshirt: ");
			Main.color = scan.next().toLowerCase();
			String color = Main.color;
			if (!isAlpha(color)) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(e + "Invalid Input Type!");
			getColor();
		}
	}

	public static void getSize() {
		try {
			System.out.println("Enter size :");
			System.out.println("Select: S, M, L, XL, XXL");
			Main.size = scan.next().toLowerCase();
			String size = Main.size;
			if (!size.equals("s") && !size.equals("m") && !size.equals("l") && !size.equals("xl")
					&& !size.equals("xxl")) {
				throw new InvalidSize("Size can be only: S, M, L, XL, XXL");
			}
			if (!isAlpha(size)) {
				throw new Exception();
			}
		} catch (InvalidSize e) {
			System.out.println("Size can be only: S, M, L, XL, XXL!");
			getSize();
		} catch (Exception e) {
			System.out.println(e + "Invalid Input Type!");
			getSize();
		}
	}

	public static void getGender() {
		try {
			System.out.println("Enter Gender: ");
			System.out.println("Select: M (male), F (female) or U (unisex)");
			Main.gender = scan.next().toLowerCase();
			String gender = Main.gender;
			if (!gender.equals("m") && !gender.equals("f") && !gender.equals("u")) {
				throw new InvalidGender("Invalid Gender type");
			}
			if (!isAlpha(gender)) {
				throw new Exception();
			}
		} catch (InvalidGender e) {
			System.out.println("Gender can be only: M, F or U!");
			getGender();
		} catch (Exception e) {
			System.out.println(e + "Invalid Input Type!");
			getGender();
		}
	}

	public static void getPreference() {
		try {
			System.out.println("Filter results by: ");
			System.out.println("Price, Rating or Both");
			Main.outputPreference = scan.next().toLowerCase();
			String outputPreference = Main.outputPreference;
			if (!outputPreference.equals("price") && !outputPreference.equals("rating")
					&& !outputPreference.equals("both")) {
				throw new InvalidOutputPreference("Invalid!");
			}
			if (!isAlpha(outputPreference)) {
				throw new Exception();
			}
		} catch (InvalidOutputPreference e) {
			System.out.println("Output Preference can be only: Price, Rating or Both!");
			getPreference();
		} catch (Exception e) {
			System.out.println(e + "Invalid Input Type!");
			getPreference();
		}
	}

	public static boolean isAlpha(String s) {
		return s != null && s.chars().allMatch(Character::isLetter);
	}

}
