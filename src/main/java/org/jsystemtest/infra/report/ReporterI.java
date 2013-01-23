package org.jsystemtest.infra.report;

@Deprecated
public interface ReporterI {
	public enum Status {
		SUCCESS, FAILURE
	}

	public void report(String title, String message, Status status);

}
