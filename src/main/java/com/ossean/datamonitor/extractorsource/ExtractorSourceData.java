package com.ossean.datamonitor.extractorsource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ExtractorSourceData {
	@Select("select count(*) from ${table} where ${timeCol} >= #{start} and ${timeCol} < #{end}")
	public int selectByTime(@Param("table") String table, @Param("timeCol") String timeCol, @Param("start") String start, @Param("end") String end);

}
