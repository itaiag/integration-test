package org.jsystemtest.infra.report;

import java.io.File;
import java.io.IOException;

import org.testng.TestNG;

public class Reporter extends org.testng.Reporter {

	static {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	}

	public static void log(String s) {
		log(s, true);
	}

	public static void logHtml(String htmlS) {
		setEscapeHtml(false);
		log(htmlS);
		setEscapeHtml(true);
	}

	public static void logFile(File file) {
		System.out.println(getCurrentTestResult().getTestContext().getOutputDirectory());
		File parent = new File(new File(getCurrentTestResult().getTestContext().getOutputDirectory()).getParent() + "html");
		parent.mkdirs();
		File newFile = new File(parent, "myfile.new");
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
