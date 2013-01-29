package org.jsystemtest.unit;

import java.io.File;
import java.io.IOException;

import org.jsystemtest.infra.report.Reporter;
import org.jsystemtest.infra.report.Reporter.Color;
import org.jsystemtest.infra.report.Reporter.Style;
import org.junit.Test;

public class IntegrationTests {

	@Test
	public void testComplexHTMLReports() {
		Reporter.logHtml("This is more complex and a lot <b>bigger</b> line that should appear in the report in it original length ");
		Reporter.logHtml("<b>Bold </b>");
		Reporter.logHtml("<i>Italic </i>");
		Reporter.logHtml("Let's play with tables...");
		Reporter.logHtml("<table><tr><td>one cell</td><td>second cell</td></tr></table>");
		Reporter.logHtml("Skye is the largest island in the Inner Hebrides of Scotland. Its peninsulas radiate from a mountainous centre dominated by the Cuillins, the rocky slopes of which provide some of the most dramatic mountain scenery in the country. The island has been occupied since the Mesolithic period and its history includes a time of Norse rule and a long period of domination by Clan MacLeod (Dunvegan Castle, the clan's seat, pictured) and Clan Donald. The 18th-century Jacobite risings led to the breaking up of the clan system and subsequent Clearances that replaced entire communities with sheep farms. Resident numbers declined from over 20,000 in the early 19th century to just under 9,000 by the closing decade of the 20th century. The main industries are tourism, agriculture, fishing and whisky-distilling, and the largest settlement is Portree, known for its picturesque harbour. There are links to various nearby islands by ferry and, since 1995, to the mainland by a road bridge. The abundant wildlife includes the golden eagle, red deer and Atlantic salmon. Skye has provided the locations for various novels and feature films and is celebrated in poetry and song");
		Reporter.logHtml("<a href='https://www.google.com/'>Google</a>");
	}

	@Test
	public void testReportOutput() throws IOException {
		File myFile = new File("myFile.txt");
		myFile.createNewFile();
		Reporter.logFile("Link to my file", myFile);
	}

	@Test
	public void testReportColors() {
		Reporter.log("In red", null, Color.RED);
		Reporter.log("In blue", null, Color.BLUE);
		Reporter.log("In yellow", null, Color.YELLOW);
		Reporter.log("In green", null, Color.GREEN);

		Reporter.log("In red", Style.BOLD, Color.RED);
		Reporter.log("In blue", Style.BOLD, Color.BLUE);
		Reporter.log("In yellow", Style.BOLD, Color.YELLOW);
		Reporter.log("In green", Style.BOLD, Color.GREEN);

		Reporter.log("In red", Style.ITALIC, Color.RED);
		Reporter.log("In blue", Style.ITALIC, Color.BLUE);
		Reporter.log("In yellow", Style.ITALIC, Color.YELLOW);
		Reporter.log("In green", Style.ITALIC, Color.GREEN);

	}

	@Test
	public void testStyle() {
		Reporter.log("In bold", Style.BOLD);
		Reporter.log("In italic", Style.ITALIC);
	}

}
