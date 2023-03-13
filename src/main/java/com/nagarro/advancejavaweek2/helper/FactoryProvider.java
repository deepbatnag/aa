package com.nagarro.advancejavaweek2.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {
	public static SessionFactory factory;

	public static SessionFactory getFactory() {
		factory = new Configuration().configure().buildSessionFactory();

		return factory;
	}

	public static void closeFactory() {
		factory.close();
	}
}
