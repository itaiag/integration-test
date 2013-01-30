package org.jsystemtest.integration;

import org.jsystemtest.infra.assertion.Assert;
import org.jsystemtest.infra.assertion.FindTextAssertion;
import org.testng.annotations.Test;

public class LogicAssertionTest {

	@Test
	public void testSuccessfulAssertion() throws Exception {
		Assert.assertLogic("outer text inner text outer text", new FindTextAssertion("inner text"));
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void testUnSuccessfulAssertion() throws Exception {
		Assert.assertLogic("outer text inn*er text outer text", new FindTextAssertion("inner text"));
	}

}
