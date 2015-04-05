package com.ossean.datamonitor;

import java.util.Date;

public class DesTable {
	private String website;
	private String category;
	private int day_crawler;
	private int week_crawler;
	private int month_crawler;
	private int day_extractor;
	private int week_extractor;
	private int month_extractor;
	private int flow_num;
	private int match_num;
	private Date new_menos;
	private int week_new_menos;
	
	public DesTable(String website , String catgory){
		this.website = website;
		this.category = catgory;
	}
	


	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCatgory() {
		return category;
	}
	public void setCatgory(String catgory) {
		this.category = catgory;
	}
	public int getDay_crawler() {
		return day_crawler;
	}
	public void setDay_crawler(int day_crawler) {
		this.day_crawler = day_crawler;
	}
	public int getWeek_crawler() {
		return week_crawler;
	}
	public void setWeek_crawler(int week_crawler) {
		this.week_crawler = week_crawler;
	}
	public int getMonth_crawler() {
		return month_crawler;
	}
	public void setMonth_crawler(int month_crawler) {
		this.month_crawler = month_crawler;
	}
	public int getDay_extractor() {
		return day_extractor;
	}
	public void setDay_extractor(int day_extractor) {
		this.day_extractor = day_extractor;
	}
	public int getWeek_extractor() {
		return week_extractor;
	}
	public void setWeek_extractor(int week_extractor) {
		this.week_extractor = week_extractor;
	}
	public int getMonth_extractor() {
		return month_extractor;
	}
	public void setMonth_extractor(int month_extractor) {
		this.month_extractor = month_extractor;
	}
	public int getFlow_num() {
		return flow_num;
	}
	public void setFlow_num(int flow_num) {
		this.flow_num = flow_num;
	}
	public int getMatch_num() {
		return match_num;
	}
	public void setMatch_num(int match_num) {
		this.match_num = match_num;
	}
	public Date getNew_menos() {
		return new_menos;
	}
	public void setNew_menos(Date new_menos) {
		this.new_menos = new_menos;
	}
	public int getWeek_new_menos() {
		return week_new_menos;
	}
	public void setWeek_new_menos(int week_new_menos) {
		this.week_new_menos = week_new_menos;
	}
	public int getMonth_new_menos() {
		return month_new_menos;
	}
	public void setMonth_new_menos(int month_new_menos) {
		this.month_new_menos = month_new_menos;
	}
	private int month_new_menos;

	
}
