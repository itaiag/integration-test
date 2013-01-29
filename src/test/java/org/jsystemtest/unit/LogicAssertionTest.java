package org.jsystemtest.unit;

import org.jsystemtest.infra.assertion.Assert;
import org.jsystemtest.infra.assertion.FindTextAssertion;
import org.junit.Test;

public class LogicAssertionTest {

	@Test
	public void testSuccessfulAssertion() throws Exception {
		Assert.assertLogic("outer text inner text outer text", new FindTextAssertion("inner text"));
	}

	@Test(expected = AssertionError.class)
	public void testUnSuccessfulAssertion() throws Exception {
		Assert.assertLogic("outer text inn*er text outer text", new FindTextAssertion("inner text"));
	}

}
