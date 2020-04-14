package test.java;

import test.java.CartTest;

import test.java.CalcCostTest;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;

import org.junit.runners.Suite.SuiteClasses;






@RunWith(Suite.class)
@SuiteClasses({CalcCostTest.class, CartTest.class})
public class TestSuite {

}
