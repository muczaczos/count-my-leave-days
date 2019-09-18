package com.springboot.thymeleafdemo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class scheduledTask {
	
	  private static final Logger log = LoggerFactory.getLogger(scheduledTask.class);

	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	    @Scheduled(fixedRate = 5000)
	    public void reportCurrentTime() {
	    	System.err.println("server runing!!!");
	    	
	    	LocalDate today = LocalDate.now();
	        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
	        LocalDate yesterday = tomorrow.minusDays(2);
	        LocalDate beforeYesterday = tomorrow.minusDays(3);
	        
	        System.err.println(today.getYear());
	        System.err.println(beforeYesterday.getYear());
	        
	        if(today.getYear() - beforeYesterday.getYear() == 1) {
	        	System.err.println("trzeba dodac nową pulę urlopu!!");
  	        }
	    }

}
