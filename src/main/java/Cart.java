package main.java;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected int userAge;
    private List<Product> cart;

    /**
     * Calculates the final cost after all savings and tax has been applied. Also checks
     * that the user is of age to purchase alcohol if it is in their cart at checkout.
     * Sales tax is always AZ tax.
     * Calculation is based off of the following prices and deals:
     * Dairy -> $3
     * Meat -> $10
     * Produce -> $2 or 3 for $5
     * Alcohol -> $8
     * Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     * If there is an alcohol product in the cart and the user is under 21, then an
     * UnderAgeException should be thrown.
     *
     * @return double totalCost
     * @throws UnderAgeException when userage < 21
     */
    public double calcCost() throws UnderAgeException {
        double subTotal = 0;
        int savings = 0;
        int produce = 0;
        int produceDealCounter = 0;
        int alcohol = 0;
        int frozenFood = 0;
        int alcFroDealCounter = 0;
        int dairy = 0;
        int meat = 0;

        //count items and get cost before savings
        for (int i = 0; i < cart.size(); i++) {



           if (cart.get(i).getClass() == Produce.class) {               	
                produce++;
                subTotal = subTotal + cart.get(i).getCost();

                if ((produce % 3) == 0) {
                    produceDealCounter++;
                }
            } else if (cart.get(i).getClass() == Alcohol.class) {
                alcohol++;
                subTotal = subTotal + cart.get(i).getCost();
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
                
            } else if (cart.get(i).getClass() == FrozenFood.class) {
                frozenFood++;
                subTotal = subTotal + cart.get(i).getCost();
                
            } else if (cart.get(i).getClass() == FrozenFood.class) {
                dairy++;
                subTotal = subTotal + cart.get(i).getCost();
                
            } else if (cart.get(i).getClass() == Meat.class) {
                meat++;
                subTotal = subTotal + cart.get(i).getCost();
                
            } else if (cart.get(i).getClass() == Dairy.class) {
                meat++;
                subTotal = subTotal + cart.get(i).getCost();
            }

        }

        subTotal = subTotal - produceDealCounter;

        //subtract out the alcFroDeal
        while (alcohol >= 1 && frozenFood >= 1) {
            alcFroDealCounter++;
            alcohol--;
            frozenFood--;
        }

        subTotal = subTotal - alcFroDealCounter*3;

        //add the tax
        subTotal = subTotal + getTax(subTotal, "AZ");

        return subTotal;


    }

    /**
    *  calculates how much was saved in the current shopping cart based on the deals
    * throws exception if alcohol is bought from underage person
    * TODO: Create node graph for this method in assign 4: create white box tests and fix the method
    * @throws UnderAgeException when userage is < 21
    **/
    public int amountSaved() throws UnderAgeException {
        int subTotal = 0;
        int costAfterSavings = 0;

        //all counters should be ints. Changed to int from double
        int produceCounter = 0;

        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;

        for (int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();

            //changed all of the class comparison statements. They weren't working correctly before.
            if (cart.get(i).getClass() == Produce.class) {

                produceCounter++;

                if (produceCounter >= 3) {
                    costAfterSavings -= 1;
                    produceCounter = 0;
                }
            } else if (cart.get(i).getClass() == Alcohol.class) {
                alcoholCounter++;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
                
            } else if (cart.get(i).getClass() == FrozenFood.class) {
                frozenFoodCounter++;
            } else if (cart.get(i).getClass() == Dairy.class) {
                dairyCounter++;
            } if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                //changed the "costAfterSavings + 3" to "costAfterSavings - 3"
                costAfterSavings = costAfterSavings - 3;
                alcoholCounter--;
                frozenFoodCounter--;
            }
        }

        return subTotal - costAfterSavings;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBt, String state) {
        double newTotal = 0;
        switch (state) {
            case "AZ":
                newTotal = totalBt * .08;
                break;
            case "CA":
                newTotal = totalBt * .09;
                break;
            case "NY":
                newTotal = totalBt * .1;
            //needed to insert a break here so it doesn't calculate the Colorado tax
                break;
            case "CO":
                newTotal = totalBt * .07;
                break;
            default:
                return totalBt;
        }
        return newTotal;
    }

    public void addItem(Product np) {
        cart.add(np);
    }

    //changed the method name is lower camelCase
    public boolean removeItem(Product productToRemove)
    {
        boolean test = false;
        for (int i = 0; i < cart.size(); i++) {
            //fixed the if conditional statement to compare the product classes correctly
            if (cart.get(i).getClass() == productToRemove.getClass()) {
                cart.remove(i);
                test = true;
                return test;
            }
        }
        return false;
    }

    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }
}
