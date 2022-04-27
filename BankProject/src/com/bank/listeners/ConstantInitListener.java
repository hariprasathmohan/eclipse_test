package com.bank.listeners;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bank.database.AccountDao;
import com.bank.database.FixedDepositDAO;
import com.bank.database.LoanDAO;
import com.bank.database.RecurringDepositDAO;
import com.bank.utils.Utils;

public class ConstantInitListener implements ServletContextListener {
	ScheduledExecutorService scheduler;
	
    public void contextDestroyed(ServletContextEvent sce)  { 
         scheduler.shutdown();
    }
    
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext ctx=sce.getServletContext();
    	Utils.FD_INTEREST = Double.parseDouble(ctx.getInitParameter("fdInterest"));
    	Utils.RD_INTEREST = Double.parseDouble(ctx.getInitParameter("rdInterest"));
    	Utils.LOAN_INTEREST = Double.parseDouble(ctx.getInitParameter("loanInterest"));
    	Utils.ACCOUNT_INTEREST = Double.parseDouble(ctx.getInitParameter("accountInterest"));
    	Utils.connectionClass = ctx.getInitParameter("connectionClass");
    	Utils.connectionUrl = ctx.getInitParameter("connectionDriver");
    	Utils.connectionUsername = ctx.getInitParameter("connectionUserName");
    	Utils.connectionPassword = ctx.getInitParameter("connectionPassword");
    	
    	scheduler = Executors.newScheduledThreadPool(4);
    	scheduler.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				FixedDepositDAO fixeddb = new FixedDepositDAO();
				fixeddb.autoUpdate();
				LoanDAO loandb = new LoanDAO();
				loandb.autoUpdate();
				RecurringDepositDAO recurringdb = new RecurringDepositDAO();
				recurringdb.autoUpdate();
				if(Integer.parseInt(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime())) == 1) {
					AccountDao accountdb = new AccountDao();
					accountdb.autoUpdate();
				}
			}
		}, 0, 1, TimeUnit.DAYS);
    }	
}
