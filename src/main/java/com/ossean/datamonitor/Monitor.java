package com.ossean.datamonitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ossean.dao.DestSource;
import com.ossean.datamonitor.crawlersource.CrawlerSourceData;
import com.ossean.datamonitor.extractorsource.ExtractorSourceData;
import com.ossean.util.TxtRead;


@Component("Monitor")
public class Monitor extends TimerTask {

	@SuppressWarnings("restriction")
	@Resource
	private CrawlerSourceData crawlerSourceData;

	@SuppressWarnings("restriction")
	@Resource
	private ExtractorSourceData extractorSourceData;

	@SuppressWarnings("restriction")
	@Resource
	private  DestSource destSource;

	private List<String> txt = new ArrayList<String>();
	private List<String> tables = new ArrayList<String>();
	private List<String> category = new ArrayList<String>();
	private List<String> timeCol = new ArrayList<String>();

	private String end_time = "23:59:59";
	private String begin_time = "00:00:00";
	private String begin_day;
	private String begin_week;
	private String begin_month;
	private String crawler_tail = "_html_detail";
	private static String dest_table="destable";
	
	
	
	@Override
	public void run() {
		// 对抽取环节的监控
		String time_format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(time_format);
		String end = sdf.format(new Date());
		end = end + " " + end_time;
		begin_day = getTime(-1) + " " + begin_time;
		begin_week = getTime(-7) + " " + begin_time;
		begin_month = getTime(-30) + " " + begin_time;

		// 加载extract的配置文件
		getExtractorConfig("extractTables");
		checkDestItem(tables);
		for(int i = 0 ; i<tables.size();i++){
			DesTable dest = new DesTable(tables.get(i),category.get(i));
			dest.setDay_extractor(extractorSourceData.selectByTime(tables.get(i), timeCol.get(i), begin_day, end));	
			dest.setWeek_extractor(extractorSourceData.selectByTime(tables.get(i), timeCol.get(i), begin_week, end));
			dest.setMonth_extractor(extractorSourceData.selectByTime(tables.get(i), timeCol.get(i), begin_month, end));

			destSource.updateExtractorItem(dest_table, dest);
		}		
		
		//加载crawler的配置文件
		getCrawlerConfig("crawlTables");
		for(int i = 0 ; i<tables.size();i++){
			
			String tmp = tables.get(i);
			String table_name = tables.get(i).substring(0, tmp.length()-crawler_tail.length());
			DesTable dest = new DesTable(table_name,category.get(i));
			dest.setDay_crawler(extractorSourceData.selectByTime(tables.get(i), timeCol.get(i), begin_day, end));			
			dest.setWeek_crawler(extractorSourceData.selectByTime(tables.get(i), timeCol.get(i), begin_week, end));
			dest.setMonth_crawler(extractorSourceData.selectByTime(tables.get(i), timeCol.get(i), begin_month, end));

			destSource.updateCrawlerItem(dest_table, dest);
		}		
	}
	
	public void getExtractorConfig(String txt_name){
		txt.clear();
		tables.clear();
		category.clear();
		timeCol.clear();
		txt = TxtRead.read("./config/"+txt_name+".txt");
		for(String str:txt){
			String[] tmp = str.split(" ");
			tables.add(tmp[0]);
			category.add(tmp[1]);
			timeCol.add(tmp[2]);
		}		
	}
	
	public void getCrawlerConfig(String txt_name){
		txt.clear();
		tables.clear();
		category.clear();
		timeCol.clear();
		txt = TxtRead.read("./config/"+txt_name+".txt");
		for(String str:txt){
			String[] tmp = str.split(" ");
			tables.add(tmp[0]+crawler_tail);
			category.add(tmp[1]);
			timeCol.add(tmp[2]);
		}		
	}
	
	public void checkDestItem(List<String> tables){
		for(String website:tables){
			if(destSource.checkItem(dest_table, website)==0){
				destSource.addItem(dest_table, website);
			}			
		}
	}
	
	public static String getTime(int days) {
		String time_format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(time_format);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, days);
		return sdf.format(c.getTime());
	}

	public void begin() {
		Timer timer = new Timer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new Date());
		Date firstTime = null;
		try {
			firstTime = sdf.parse(now.substring(0, 10) + " 23:59:59");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timer.schedule(this, 0, 1000);
	}
	public static void main(String[] args) {
     	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
        Monitor m = (Monitor) applicationContext.getBean(Monitor.class);
//		Main m = new Main();
		m.begin();
	}
}
