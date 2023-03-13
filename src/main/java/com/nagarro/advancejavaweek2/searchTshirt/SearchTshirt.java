package com.nagarro.advancejavaweek2.searchTshirt;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.advancejavaweek2.constants.Constants;
import com.nagarro.advancejavaweek2.entity.TshirtEntity;
import com.nagarro.advancejavaweek2.io.Output;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class SearchTshirt {
	ArrayList<String> list;
	List<TshirtEntity> tshirtList;

	public void addToDB(String path, Session session) {
		try {
			FileReader filereader = new FileReader(path);
			CSVParser parser = new CSVParserBuilder().withSeparator('|').build();

			CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).withSkipLines(1).build();

			List<String[]> allData = csvReader.readAll();
			for (String[] line : allData) {
				list = new ArrayList<String>();
				for (String unitData : line) {

					list.add(unitData);
				}

				TshirtEntity isStoredInDB = (TshirtEntity) session.get(TshirtEntity.class, list.get(0));

				if (isStoredInDB == null && !list.isEmpty()) {
					Transaction trans = session.beginTransaction();
					TshirtEntity entity = new TshirtEntity(list.get(Constants.ID_INDEX), list.get(Constants.NAME_INDEX),
							list.get(Constants.COLOR_INDEX), list.get(Constants.GENDER_INDEX),
							list.get(Constants.SIZE_INDEX), Double.parseDouble(list.get(Constants.PRICE_INDEX)),
							Double.parseDouble(list.get(Constants.RATING_INDEX)),
							list.get(Constants.AVAILABILITY_INDEX));

					session.save(entity);

					trans.commit();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateView(String choice) {
		if (choice.equals("price")) {
			Collections.sort(tshirtList, new Comparator<TshirtEntity>() {
				public int compare(TshirtEntity o1, TshirtEntity o2) {
					return (int) (o1.getPrice() - o2.getPrice());
				}
			});
		} else if (choice.equalsIgnoreCase("rating")) {
			Collections.sort(tshirtList, new Comparator<TshirtEntity>() {

				public int compare(TshirtEntity o1, TshirtEntity o2) {
					return (int) (o2.getRating() - o1.getRating());
				}
			});
		} else {
			Collections.sort(tshirtList, Comparator.comparing(TshirtEntity::getPrice, (t1, t2) -> {
				if (t1 == t2)
					return 0;
				if (t1 > t2)
					return 1;
				return -1;
			}).thenComparing(TshirtEntity::getRating, (t1, t2) -> {
				if (t1 > t2)
					return -1;
				return 1;
			}));
		}

		Output op = new Output();
		op.outputTshirt(tshirtList);
	}

	public void dbToList(Session session, String color, String size, String gender) {
		Query query = session.createQuery(
				"from TshirtEntity where color = :color and size = :size and gender_recommendation = :gender and availability='y'");
		query.setParameter("color", color);
		query.setParameter("size", size);
		query.setParameter("gender", gender);
		tshirtList = query.list();
	}
}
