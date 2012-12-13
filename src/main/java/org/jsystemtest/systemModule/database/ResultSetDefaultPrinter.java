package org.jsystemtest.systemModule.database;

import java.util.List;
import java.util.Map;

import org.jsystemtest.infra.report.ReportersManager;

public class ResultSetDefaultPrinter implements ResultSetPrinter {

	protected ReportersManager report = ReportersManager.getInstance(); 
	private static final String EOL = "\n";
	private static final String SEPARATOR = "  ";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rsa.fa.blackbox.integration.database.ResultSetPrinter#print(java.util.List)
	 */
	@Override
	public void print(final List<Map<String, Object>> resultList) {
		if (resultList == null || resultList.size() == 0) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		boolean headerRow = true;
		for (Map<String, Object> row : resultList) {
			if (headerRow) {
				headerRow = false;
				for (String header : row.keySet()) {
					sb.append(header).append(SEPARATOR);
				}
				sb.append(EOL);
			}
			for (Object column : row.values()) {
				sb.append(column).append(SEPARATOR);
			}
			sb.append(EOL);
		}
		report.report(sb.toString());
	}
}
