package org.jsystemtest.systemModule.database;

import java.util.List;
import java.util.Map;

public interface ResultSetPrinter {

	public abstract void print(final List<Map<String, Object>> resultList);

}