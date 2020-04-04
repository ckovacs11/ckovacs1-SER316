package test.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.Alcohol;
import main.java.Cart;
import main.java.Dairy;
import main.java.FrozenFood;
import main.java.Meat;
import main.java.Produce;
import main.java.UnderAgeException;
import main.java.Cart;
import main.java.Product;

public class CartTest {
	
	
	
	
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;
    Cart cart5;
    Cart cart6;
    

    @Before
    public void setUp() throws Exception {
    	
    	///////
    	
        cart1 = new Cart(21);
        for(int i = 0; i < 3; i++) {
        	cart1.addItem(new Produce());
        }
        cart1.addItem(new Alcohol());
        cart1.addItem(new FrozenFood());
        cart1.addItem(new Dairy());
        
        ///////
        
        cart2 = new Cart(18);
        for(int i = 0; i < 3; i++) {
        	cart2.addItem(new Produce());
        }
        cart2.addItem(new Alcohol());
        cart2.addItem(new FrozenFood());
        cart2.addItem(new Dairy());
                
        //////
        
        cart3 = new Cart(21);
        
        
        
        /////
        
        cart4 = new Cart(21);
        cart4.addItem(new Produce());
        cart4.addItem(new Alcohol());
        cart4.addItem(new FrozenFood());
        cart4.addItem(new Dairy());
        
        //////
        
        cart5 = new Cart(21);
        cart5.addItem(new FrozenFood());
        
        /////
        
        cart6 = new Cart(18);
        cart6.addItem(new Alcohol());
        cart6.addItem(new FrozenFood());
        
        
        
    }

    @After
    public void tearDown() throws Exception {
    }

  //cart1 will cover all nodes except the UnderAgeException node
    @Test
    public void amountSaved1() throws UnderAgeException {
        assertEquals(4, cart1.amountSaved(), .01);
    }
  //cart2 will cover the UnderAgeException node  
    @Test(expected = UnderAgeException.class)
    public void amountSaved2() throws UnderAgeException {
        assertEquals(4, cart2.amountSaved(), .01);
    }
  //cart3 will cover the edge that skips the for loop at the beginning   
    @Test
    public void amountSaved3() throws UnderAgeException {
        assertEquals(0, cart3.amountSaved(), .01);
    }
  //cart4 will cover the all edges except the Produce >= 3 decision edge  
    @Test
    public void amountSaved4() throws UnderAgeException {
        assertEquals(3, cart4.amountSaved(), .01);
    }
  //cart5 will cover the edge on line 141   
    @Test
    public void amountSaved5() throws UnderAgeException {
        assertEquals(0, cart5.amountSaved(), .01);
    }
  //cart6 will cover the exception on line 130  
    @Test(expected = UnderAgeException.class)
    public void amountSaved6() throws UnderAgeException {
        assertEquals(3, cart6.amountSaved(), .01);
    }

  //getTax1 will test the AZ tax  
    @Test
    public void getTax1() {
        assertEquals(1.2, cart4.getTax(15.0, "AZ"), .01);
    }
  //getTax2 will test the CA tax  
    @Test
    public void getTax2() {
        assertEquals(1.35, cart4.getTax(15.0, "CA"), .01);
    }
 //getTax3 will test the NY tax   
    @Test
    public void getTax3() {
        assertEquals(1.5, cart4.getTax(15.0, "NY"), .01);
    }
 //getTax4 will test the CO tax   
    @Test
    public void getTax4() {
        assertEquals(1.05, cart4.getTax(15.0, "CO"), .01);
    }
//getTax5 will test the tax when the starting total is 0	
    @Test
    public void getTax5() {
        assertEquals(0, cart3.getTax(0, "AZ"), .01);
    }
    
    @Test
    public void removeItem1() {
    	Product p = (Product) new Produce();
        assertTrue(cart4.removeItem(p));
    }
	
	
	
}
