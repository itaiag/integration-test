package org.jsystemtest.integration;

import org.jsystemtest.infra.bdd.BddExecutor;
import org.jsystemtest.infra.bdd.BddI;
import org.jsystemtest.infra.bdd.Step;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class BDDExampleTest {

	@Test
	@Step(description = "General Test Description")
	public void bddTest() throws Exception {
		BddExecutor.run(new BddI() {
			@Override
			@Step(description = "Given the system is in some state")
			public void given() throws Exception {
				Reporter.log("In the given step");

			}

			@Override
			@Step(description = "When we are doing some stuff")
			public void when() throws Exception {
				Reporter.log("In the when step");

			}

			@Override
			@Step(description = "Then we expect some thing to be in some state")
			public void then() throws Exception {
				Reporter.log("In the then step");
			}

		});

	}

}