package SauceDemo2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class TestNGReportListener implements IReporter {

    @Override
    public void generateReport(
            java.util.List<XmlSuite> xmlSuites,
            java.util.List<ISuite> suites,
            String outputDirectory) {

        File reportDir = new File("test-output");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        File reportFile = new File(reportDir, "index.html");

        try (java.io.PrintWriter writer =
                     new java.io.PrintWriter(reportFile)) {

            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>TestNG Reports</title>");
            writer.println("<style>");
            writer.println("body { font-family: Arial; margin: 30px; }");
            writer.println("table { border-collapse: collapse; width: 100%; }");
            writer.println("th, td { border: 1px solid #ccc; padding: 10px; }");
            writer.println("th { background: #f2f2f2; }");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1>TestNG Reports</h1>");

            for (ISuite suite : suites) {

                writer.println("<h2>Suite: "
                        + suite.getName() + "</h2>");

                for (ISuiteResult suiteResult :
                        suite.getResults().values()) {

                    writer.println("<table>");
                    writer.println("<tr>");
                    writer.println("<th>Test Name</th>");
                    writer.println("<th>Status</th>");
                    writer.println("<th>Duration</th>");
                    writer.println("</tr>");

                    for (ITestResult result :
                            suiteResult.getTestContext()
                                    .getPassedTests()
                                    .getAllResults()) {

                        printResult(writer, result, "PASS");
                    }

                    for (ITestResult result :
                            suiteResult.getTestContext()
                                    .getFailedTests()
                                    .getAllResults()) {

                        printResult(writer, result, "FAIL");
                    }

                    for (ITestResult result :
                            suiteResult.getTestContext()
                                    .getSkippedTests()
                                    .getAllResults()) {

                        printResult(writer, result, "SKIPPED");
                    }

                    writer.println("</table>");
                }
            }

            writer.println("</body>");
            writer.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printResult(
            java.io.PrintWriter writer,
            ITestResult result,
            String status) {

        writer.println("<tr>");

        writer.println("<td>"
                + result.getName()
                + "</td>");

        writer.println("<td>"
                + status
                + "</td>");

        long duration =
                result.getEndMillis() - result.getStartMillis();

        writer.println("<td>"
                + duration
                + " ms</td>");

        writer.println("</tr>");
    }
}
