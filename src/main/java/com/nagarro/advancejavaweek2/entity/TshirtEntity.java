package com.nagarro.advancejavaweek2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tshirts")
public class TshirtEntity {
	@Id
	private String id;
	private String name;
	private String color;
	@Column(name = "gender_recommendation")
	private String genderRecommendation;
	private String size;
	private double price;
	private double rating;
	private String availability;

	public TshirtEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TshirtEntity(String id, String name, String color, String genderRecommendation, String size, double price,
			double rating, String availability) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.genderRecommendation = genderRecommendation;
		this.size = size;
		this.price = price;
		this.rating = rating;
		this.availability = availability;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGenderRecommendation() {
		return genderRecommendation;
	}

	public void setGenderRecommendation(String genderRecommendation) {
		this.genderRecommendation = genderRecommendation;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "TshirtEntity [id=" + id + ", name=" + name + ", color=" + color + ", genderRecommendation="
				+ genderRecommendation + ", size=" + size + ", price=" + price + ", rating=" + rating
				+ ", availability=" + availability + "]";
	}

}
