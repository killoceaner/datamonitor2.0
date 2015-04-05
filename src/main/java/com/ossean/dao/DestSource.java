package com.ossean.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ossean.datamonitor.DesTable;

public interface DestSource {
	
	@Select("select count(*) from ${table} where website = #{website}")
	public int checkItem(@Param("table") String table ,@Param("website")String website);
	
	@Insert("insert into ${table}"+"(`website`)"+"values(#{website})")
	public void addItem(@Param("table") String table, @Param("website") String website);
	
	@Update("update ${table} set category=#{model.category},day_extractor=#{model.day_extractor},week_extractor=#{model.week_extractor},month_extractor=#{model.month_extractor} where website=#{model.website}")
	public void updateExtractorItem(@Param("table") String table, @Param("model") DesTable model);
	
	@Update("update ${table} set category=#{model.category},day_crawler=#{model.day_crawler},week_crawler=#{model.week_crawler},month_crawler=#{model.month_crawler} where website=#{model.website}")
	public void updateCrawlerItem(@Param("table") String table, @Param("model") DesTable model);
	
}
