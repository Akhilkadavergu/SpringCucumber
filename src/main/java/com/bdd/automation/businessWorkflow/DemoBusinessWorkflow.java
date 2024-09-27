package com.bdd.automation.businessWorkflow;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DemoBusinessWorkflow")
public class DemoBusinessWorkflow extends AbstractWorkFlow {
	public void method1() throws Exception {
		setupUtil.setUp("webOnly");
		System.out.println(webDriver.get());
		webDriver.get().get("https://www.google.com/");
		System.out.println("123217371246712848");
	}
}
