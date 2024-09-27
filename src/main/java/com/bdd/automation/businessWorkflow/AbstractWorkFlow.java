package com.bdd.automation.businessWorkflow;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdd.automation.scenarioTest.AbstractBeans;
import com.bdd.automation.utils.SetupUtil;
import com.bdd.automation.utils.WebDriverUtil;

public abstract class AbstractWorkFlow extends AbstractBeans {
	@Autowired
	public WebDriverUtil webDriverUtil;
	@Autowired
	public SetupUtil setupUtil;
}
