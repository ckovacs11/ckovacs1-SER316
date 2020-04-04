package test.java;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import test.java.calcCostTest;
import test.java.CartTest;

@RunWith(Suite.class)
@SuiteClasses( {calcCostTest.class, CartTest.class})
public class TestSuite {

}
