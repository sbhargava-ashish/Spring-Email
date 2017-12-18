package com.ashish.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ashish.util.Gateway;
import com.ashish.util.MailHelper;

public class SendEmailUsingFreeMarker {

	public static void main(String[] args) throws BeansException, IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		
		Map<String, Object> valuesInTempalate = new HashMap<String, Object>();

		Gateway gateway = new Gateway();
		gateway.setName("Name");
		gateway.setHbTime("22-June-2017 10:45:50:234 ");
		gateway.setCustomer("Citibank Inc. ");

		List<Gateway> gateways = new ArrayList<Gateway>();

		gateways.add(gateway);

		valuesInTempalate.put("startDate", new Date());
		valuesInTempalate.put("endDate", new Date());
		valuesInTempalate.put("gateway", gateways);

		valuesInTempalate.put("date", gateways);

		List<String> toAddressList = new ArrayList<String>();
		
		// TODO Please add the To address mails
		toAddressList.add("***********");

		System.out.println("***********************************************");
		
		context.getBean(MailHelper.class).sendTemplatedMimeMessage(valuesInTempalate, "mail.ftl", toAddressList);
		
		System.out.println("***********************************************");

		((AbstractApplicationContext) context).close();

	}

}
