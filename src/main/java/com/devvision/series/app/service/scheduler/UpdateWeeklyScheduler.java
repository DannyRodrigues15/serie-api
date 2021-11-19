package com.devvision.series.app.service.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.devvision.series.app.service.UpdateSeriesService;

@Configuration
@EnableScheduling
public class UpdateWeeklyScheduler {
	
	Logger logger = LoggerFactory.getLogger(UpdateWeeklyScheduler.class);
	
	@Autowired
	private UpdateSeriesService updateSeriesService;
	
	// Segundos, Minutes, Hours, Day-of-month, Month, Day-of-week
	@Scheduled(cron = "0 30 00 * * 1")
	public void updateWeeklyTest() {
		updateSeriesService.updateSeries();
	}

}
