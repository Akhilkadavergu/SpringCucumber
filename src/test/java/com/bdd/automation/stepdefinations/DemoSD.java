package com.bdd.automation.stepdefinations;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DemoSD extends AbstractStepDefinations {

	@Given("I want to write a step with precondition")
	public void i_want_to_write_a_step_with_precondition() throws Exception {
		demoWF.method1();
	}

	@Given("some other precondition")
	public void some_other_precondition() {
		System.out.println("123217371246712848");
	}

	@When("I complete action")
	public void i_complete_action() {
		System.out.println("123217371246712848");
	}

	@When("some other action")
	public void some_other_action() {
		System.out.println("123217371246712848");
	}

	@When("yet another action")
	public void yet_another_action() {
		System.out.println("123217371246712848");
	}

	@Then("I validate the outcomes")
	public void i_validate_the_outcomes() {
		System.out.println("123217371246712848");
	}

	@Then("check more outcomes")
	public void check_more_outcomes() {
		System.out.println("123217371246712848");
	}

}
