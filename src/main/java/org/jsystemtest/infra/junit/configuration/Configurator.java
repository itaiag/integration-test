package org.jsystemtest.infra.junit.configuration;

import org.jsystemtest.infra.junit.report.ReporterI;
import org.jsystemtest.infra.junit.report.ReportersManager;
import org.jsystemtest.infra.junit.utils.BeanUtils;

public class Configurator {

	private static Configurator instance;

	public static Configurator getInstance() {
		if (null == instance) {
			instance = new Configurator();
		}
		return instance;
	}

	private Configurator() {
		configureSystem();
	}

	private void configureSystem() {
		final String reporterClasses = IntegrationProperites.getInstance().getOptionValue(FrameworkOptions.REPORTER_CLASSES);
		if (reporterClasses != null) {
			for (String reportClass : reporterClasses.split(";")) {
				Object reporter = BeanUtils.createInstanceFromString(reportClass, ReporterI.class);
				if (reporter != null) {
					ReportersManager.getInstance().addReporter((ReporterI) reporter);
				}
			}
		}
	}

}
