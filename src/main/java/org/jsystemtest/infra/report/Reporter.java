package org.jsystemtest.infra.report;

public interface Reporter {
	public enum Status {
		SUCCESS, FAILURE
	}

	public void report(String title, String message, Status status);

}
