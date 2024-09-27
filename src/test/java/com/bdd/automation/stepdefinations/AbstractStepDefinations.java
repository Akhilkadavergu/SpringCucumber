package com.bdd.automation.stepdefinations;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdd.automation.businessWorkflow.DemoBusinessWorkflow;
import com.bdd.automation.scenarioTest.AbstractBeans;
import com.bdd.automation.utils.SetupUtil;

public class AbstractStepDefinations extends AbstractBeans {
	@Autowired
	DemoBusinessWorkflow demoWF;
}
