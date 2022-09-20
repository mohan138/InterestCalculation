package com.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listener extends TestListenerAdapter {
    public ExtentReports extent = new ExtentReports();
    public ExtentSparkReporter spark = new ExtentSparkReporter("./SparkReport/Spark.html");

    public void onTestSuccess(ITestResult result){
        extent.attachReporter(spark);
        extent.createTest("Tags")
                .assignCategory("MyTag")
                .pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");
        extent.flush();
    }
}
