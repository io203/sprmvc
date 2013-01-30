package com.nhncorp.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nhncorp.myapp.test.MyTask;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final int TASKS = 10;
	 

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testConcurrent(  Model model ) {
		logger.info("Welcome test ");
		
		System.out.println("BEGIN");
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch doneLatch = new CountDownLatch(TASKS);

        try {
            for (int i = 0; i < TASKS; i++) {
                service.execute(new MyTask(doneLatch, i));
            }
            System.out.println("AWAIT");
            
            doneLatch.await();
        } catch (InterruptedException e) {
        
        } finally {
            service.shutdown();
            System.out.println("END");
        }

		model.addAttribute("serverTime", "test" );
		
		return "home";
    }
}
