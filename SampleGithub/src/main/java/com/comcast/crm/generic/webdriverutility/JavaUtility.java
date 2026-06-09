package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber()
	{
		Random ranDom = new Random();
		int ranDomNumber=ranDom.nextInt(5000);
		return ranDomNumber;
	}
	
	public String getSystemDateYYYYDDMM()
	{
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");           //only mm can be capital
		String date=sdf.format(dateObj);
		return date;
	}
	
	public String getRequriedDateYYYYDDMM(int days)
	{
		Date da = new Date();  //this line is must else u will get error bcz it will take old date
		
		SimpleDateFormat sim=new SimpleDateFormat("YYYY-MM-dd");
		Calendar cal=sim.getCalendar();
		sim.format(da);                  //this line is imp
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate=sim.format(cal.getTime());	
		return reqDate;
	}

}
