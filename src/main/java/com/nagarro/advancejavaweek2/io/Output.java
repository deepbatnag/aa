package com.nagarro.advancejavaweek2.io;

//import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.nagarro.advancejavaweek2.entity.TshirtEntity;
//import com.nagarro.advancejavaweek2.tshirt.Tshirt;

public class Output {
	public void outputTshirt(List<TshirtEntity> queryList) {
		Scanner scan = new Scanner(System.in);
		for (TshirtEntity ts : queryList) {
			System.out.print(" " + ts.getId());
			System.out.print(" | " + ts.getName());
			System.out.print(" | " + ts.getColor());
			System.out.print(" | " + ts.getGenderRecommendation());
			System.out.print(" | " + ts.getSize());
			System.out.print(" | " + ts.getPrice());
			System.out.print(" | " + ts.getRating());
			System.out.println(" | " + ts.getAvailability());
		}
		if (queryList.isEmpty()) {
			System.out.println("No Matching Data found!");
		}
	}
}
