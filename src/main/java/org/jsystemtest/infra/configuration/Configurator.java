package org.jsystemtest.infra.configuration;

import org.jsystemtest.infra.report.Reporter;
import org.jsystemtest.infra.report.ReportersManager;
import org.jsystemtest.infra.utils.BeanUtils;

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
				Object reporter = BeanUtils.createInstanceFromString(reportClass, Reporter.class);
				if (reporter != null) {
					ReportersManager.getInstance().addReporter((Reporter) reporter);
				}
			}
		}
	}

}
