package com.warehouse;

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
        return suite;
    }
}
