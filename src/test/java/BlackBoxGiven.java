package test.java;

import main.java.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

    private Class<Cart> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Cart>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Cart0.class},
            {Cart1.class},
            {Cart2.class},
            {Cart3.class},
            {Cart4.class},
            {Cart5.class}
        };
        return Arrays.asList(classes);
    }

    private Cart createCart(int age) throws Exception {
        Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
        return constructor.newInstance(age);
    }
    /**
     * Calculates the final cost after all savings and tax has been applied. Also checks
     * that the user is of age to purchase alcohol if it is in their cart at checkout. Sales tax is always AZ tax.
     *
     * Calculation is based off of the following prices and deals:
     * Dairy -> $3
     * Meat -> $10
     * Produce -> $2 or 3 for $5
     * Alcohol -> $8
     * Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     *      case "AZ":
                newTotal = totalBT * .08;
                break;
            case "CA":
                newTotal = totalBT * .09;
                break;
            case "NY":
                newTotal = totalBT * .1;
            case "CO":
                newTotal = totalBT * .07;
     */
    

    Cart cart1;
    double cart1Expected;
    
    Cart cart2;
    double cart2Expected;
    
    Cart cart3;
    double cart3Expected;
    
    Cart cart4;
    double cart4Expected;
    
    Cart cart5;
    double cart5Expected;
    
    Cart cart6;
    double cart6Expected;
    
    Cart cart7;
    double cart7Expected;
    
    Cart cart8;
    double cart8Expected;
    
    Cart cart9;
    double cart9Expected;
    
    Cart cart10;
    double cart10Expected;


    @org.junit.Before
    public void setUp() throws Exception {

        // all carts should be set up like this
    	
    	//CART 1
        // cart with a little bit of everything but no edge cases. 3 alcohol, 3 dairy, 4 meat, 4 FF, 2 produce.
        cart1 = createCart(40);
        for (int i = 0; i < 3; i++) {
            cart1.addItem(new Alcohol());
        }
        for(int i = 0; i < 3; i++) {
            cart1.addItem(new Dairy());
        }
        for(int i = 0; i < 4; i++) {
            cart1.addItem(new Meat());
        }
        for(int i = 0; i < 4; i++) {
        	cart1.addItem(new FrozenFood());
        }
        for(int i = 0; i < 2; i++) {
        	cart1.addItem(new Produce());
        }

        cart1Expected = 95.04;
        
        //CART 2
        //cart created with an age 15 shopper (same cart as cart1)
        cart2 = createCart(15);
        for (int i = 0; i < 2; i++) {
            cart2.addItem(new Alcohol());
        }
        for(int i = 0; i < 3; i++) {
            cart2.addItem(new Dairy());
        }
        for(int i = 0; i < 4; i++) {
            cart2.addItem(new Meat());
        }
        
        cart2Expected = 70.2;
        //should throw exception
        
      //CART 3  
      //cart created with 2 produce and nothing else
        cart3 = createCart(25);
        for(int i = 0; i < 2; i++) {
        	cart3.addItem(new Produce());
        }
     
        cart3Expected = 4.32;
        
      //CART 4
    	//cart created with 6 produce and nothing else
    	cart4 = createCart(25);
    	for(int i = 0; i < 6; i++) {
    		cart4.addItem(new Produce());
    	}
    	cart4Expected = 10.8;
    	
    	
    	//CART 5
    	//cart created with no alcohol and >1 FF
    	cart5 = createCart(40);
    	for(int i = 0; i < 5; i++) {
    		cart5.addItem(new FrozenFood());
    	}
    	cart5Expected = 27;
    	
    	//CART 6
    	//cart created with no FF and >1 alcohol
    	cart6 = createCart(40);
    	for(int i = 0; i < 5; i++) {
    		cart6.addItem(new Alcohol());
    	}
    	cart6Expected = 43.2;
    	
    	//CART 7
    	//cart created with zero items
    	cart7 = createCart(40);
    	cart7Expected = 0;
    	
    	//CART 8
    	//cart created with only frozen food and alcohol (5 of each). Also 21 y/o
    	cart8 = createCart(21);
    	for(int i = 0; i < 5; i++) {
    		cart8.addItem(new Alcohol());
    		cart8.addItem(new FrozenFood());
    	}
    	cart8Expected = 54;
    	
    	//CART 9
    	//4 produce
    	cart9 = createCart(0);
    	for(int i = 0; i < 4; i++) {
    		cart9.addItem(new Produce());
    		cart9.addItem(new FrozenFood());
    	}
    	cart9Expected = 29.16;
    	
    	//CART 10
    	//20 of each item
    	cart10 = createCart(40);
    	for(int i = 0; i < 20; i++) {
    		cart10.addItem(new Meat());
    		cart10.addItem(new Produce());
    		cart10.addItem(new FrozenFood());
    		cart10.addItem(new Alcohol());
    		cart10.addItem(new Dairy());
    	}
    	cart10Expected = 533.52;
    
    }
 
    

    // cipher part 3
    @Test
    public void calcCostCart1() throws UnderAgeException {
        double amount = cart1.calcCost();
        assertEquals(cart1Expected, amount, .01);
    }
    
    //cipher part 5
    @Test(expected = UnderAgeException.class)
    public void calcCostCart2() throws UnderAgeException {
    	double amount = cart2.calcCost();
    	assertEquals(cart2Expected, amount, .01);
    }
    
    @Test
    public void calcCostCart3() throws UnderAgeException {
    	double amount = cart3.calcCost();
    	assertEquals(cart3Expected, amount, .01);
    }
    
    //cipher part 1
    @Test
    public void calcCostCart4() throws UnderAgeException {
    	double amount = cart4.calcCost();
    	assertEquals(cart4Expected, amount, .01);
    }
    
    @Test
    public void calcCostCart5() throws UnderAgeException {
    	double amount = cart5.calcCost();
    	assertEquals(cart5Expected, amount, .01);
    }
    
    @Test
    public void calcCostCart6() throws UnderAgeException {
    	double amount = cart6.calcCost();
    	assertEquals(cart6Expected, amount, .01);
    }
    
    @Test
    public void calcCostCart7() throws UnderAgeException {
    	double amount = cart7.calcCost();
    	assertEquals(cart7Expected, amount, .01);
    }
    
    //cipher part 2
    @Test
    public void calcCostCart8() throws UnderAgeException {
    	double amount = cart8.calcCost();
    	assertEquals(cart8Expected, amount, .01);
    }
    
    @Test
    public void calcCostCart9() throws UnderAgeException {
    	double amount = cart9.calcCost();
    	assertEquals(cart9Expected, amount, .01);
    }
    
    //cipher part 4
    @Test
    public void calcCostCart10() throws UnderAgeException {
    	double amount = cart10.calcCost();
    	assertEquals(cart10Expected, amount, .01);
    }
    
}