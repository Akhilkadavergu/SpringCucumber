package com.bdd.automation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.bdd.automation.model.RunTimeTestData;
import com.bdd.automation.scenarioTest.AbstractBeans;
import com.bdd.automation.scenarioTest.ReportInstance;

public abstract class AbstractUtil extends AbstractBeans {
	@Autowired
	ReportInstance reportInstance;
	@Autowired
	Environment environment;
}
