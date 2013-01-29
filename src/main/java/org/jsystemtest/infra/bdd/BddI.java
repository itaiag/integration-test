package org.jsystemtest.infra.bdd;


public interface BddI {

	@Step(description = "Short explanation for the GIVEN part")
	abstract void given() throws Exception;

	@Step(description = "Short explanation for the WHEN part")
	abstract void when() throws Exception;

	@Step(description = "Short explanation for the THEN part")
	abstract void then() throws Exception;
}
