package org.jsystemtest.infra.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Wrapper for the TestNG HTML report
 * 
 * @author Itai Agmon
 * 
 */
public class Reporter extends org.testng.Reporter {
	static {
		// Allowing adding HTML to the report
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	}

	public enum Style {
		BOLD("b"), ITALIC("i");

		private final String value;

		private Style(String value) {
			this.value = value;
		}

	}

	public enum Color {
		RED, BLUE, YELLOW, GREEN
	}

	/**
	 * Appending <code>s</code> to the report
	 * 
	 * @param s
	 */
	public static void log(String s) {
		log(s, true);
	}

	public static void log(final String s, Style style) {
		if (null != style) {
			System.out.println(s);
			logHtml(appendStyleParagraph(s, style), false);
		} else {
			log(s);
		}

	}

	private static String appendStyleParagraph(String s, Style style) {
		final StringBuilder sb = new StringBuilder();
		sb.append("<p>");
		sb.append("<").append(style.value).append(">");
		sb.append(s);
		sb.append("</").append(style.value).append(">");
		sb.append("</p>");
		return sb.toString();
	}

	private static String appendColorParagraph(String s, Color color) {
		final StringBuilder sb = new StringBuilder();
		sb.append("<p style='color:").append(color.name()).append("'>");
		sb.append(s);
		sb.append("</p>");
		return sb.toString();

	}

	public static void log(final String s, Style style, Color color) {
		String newS = s;
		if (null != color) {
			newS = appendColorParagraph(newS, color);
		}
		if (null != style) {
			newS = appendStyleParagraph(newS, style);
		}
		if (style != null || color != null) {
			logHtml(newS, false);
			System.out.println(s);
		} else {
			log(s);
		}
	}

	public static void logHtml(String htmlS, boolean logToStandardOut) {
		setEscapeHtml(false);
		log(htmlS, logToStandardOut);
		setEscapeHtml(true);

	}

	/**
	 * Appending <code>htmlS</code> as HTML code to the report
	 * 
	 * @param htmlS
	 */
	public static void logHtml(String htmlS) {
		logHtml(htmlS, true);
	}

	/**
	 * Copy file to the report and add link. If another file is alrady exists in
	 * the reports folder with the same name the old file will be deleted.
	 * 
	 * @param title
	 *            The title to appear as link to the file
	 * @param file
	 *            The file to copy to the report
	 */
	public static void logFile(String title, final File file) {
		if (null == file || !file.exists() || !file.isFile()) {
			// File is not exist
			return;
		}

		// Creating parent folder
		final File parentFolder = new File(new File(getCurrentTestResult().getTestContext().getOutputDirectory()).getParent()
				+ File.separator + "html");
		if (!parentFolder.exists()) {
			if (!parentFolder.mkdirs()) {
				log("Failed to create folder for logging file");
			}
		}

		// Copying the file to the parent folder
		final File newFile = new File(parentFolder, file.getName());
		if (newFile.exists()) {
			newFile.delete();
		}
		try {
			FileUtils.copyFile(file, newFile);
		} catch (IOException e1) {
			log("Failed copying file " + file.getAbsolutePath());
		}

		// Creating link
		if (null == title || title.isEmpty()) {
			title = file.getName();
		}
		System.out.println(title);
		logHtml("<a href='" + newFile.getName() + "'>" + title + "</a>",false);
	}

}
