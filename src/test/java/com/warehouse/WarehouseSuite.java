package com.warehouse;

import com.warehouse.client.SampleTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Дима on 18.04.2017.
 *
 */

public class WarehouseSuite extends TestSuite
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("WH Tests");
        suite.addTestSuite(SampleTest.class);
        return suite;
    }
}
