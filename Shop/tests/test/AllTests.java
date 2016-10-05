package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dbTests.RelationalDbTest;
import dbTests.TxtDbTest;
import domainTests.ShopTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ShopTest.class,
    RelationalDbTest.class,
    TxtDbTest.class,
})
public class AllTests {
	
}
