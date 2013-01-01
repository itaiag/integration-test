package org.jsystemtest.infra.assertion;

import org.testng.Reporter;

/**
 * Class for comparing between actual and expected states
 * 
 * @author Itai Agmon
 * 
 */
public class Assert extends org.testng.Assert {

	/**
	 * Execute the <code>logic</code> on the the <code>actual</code> object.
	 * 
	 * @param actual
	 *            Object to perform assertion on
	 * @param logic
	 *            Logic to operate on the actual object
	 * @throws Exception
	 *             If exception occurced during assertion
	 * @throws AssertionError
	 *             If assertion fails
	 */
	static public void assertLogic(final Object actual, final AbstractAssertionLogic logic) throws Exception {
		if (null == actual) {
			throw new IllegalArgumentException("Actual can't be null");
		}
		if (null == logic) {
			throw new IllegalArgumentException("logic can't be null");
		}
		if (!logic.getActualClass().isAssignableFrom(actual.getClass())) {
			Reporter.log("Actual type " + actual.getClass().getSimpleName() + " is not applicable for assertion logic", true);
			throw new IllegalStateException("Actual type " + actual.getClass().getSimpleName()
					+ " is not applicable for assertion logic");
		}
		logic.setActual(actual);
		try {
			logic.doAssertion();
			if (logic.isStatus()) {
				Reporter.log("Assertion success: " + logic.getTitle(), true);
			} else {
				Reporter.log("Assertion failure: " + logic.getTitle(), true);
			}
			if (logic.getMessage() != null) {
				Reporter.log(logic.getMessage(), true);
			}
			if (!logic.isStatus()) {
				throw new AssertionError(logic.getTitle());
			}
		} catch (Throwable t) {
			Reporter.log("Assertion process failed due to " + t.getMessage());
			throw new AssertionError("Assertion process failed ");
		}
	}

}
