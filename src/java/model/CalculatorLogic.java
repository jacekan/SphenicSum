package model;

import java.util.ArrayList;

/**
 * Contains the algorithm of calculating the sum of n first sphenic numbers.
 * 
 */
public class CalculatorLogic
{
    /**
    * result of calculations.
    */
    private int result;

    /**
     * List for storing calculations.
     */
    private ArrayList<String> calculations;
    
    public CalculatorLogic()
    {
        this.result = 0;
        
        calculations = new ArrayList();
    }
	
    /**
     * Calculates the sum of n first sphenic numbers.
     *
     * @param numberOfSphenicNumbers number of first sphenic numbers.
     */
    public void calculateSum(int numberOfSphenicNumbers)
    {
        ArrayList<Integer> listOfSphenicNumbers = new ArrayList();

        int sphenicNumbersFound = 0;
        
        this.setResult(0);

        for(int naturalNumber = 1; sphenicNumbersFound < numberOfSphenicNumbers; naturalNumber ++) 
        {
            int product = 1;
            int temp = naturalNumber;
            int distinctPrimeFactors = 0;

            for(int factor = 2; factor <= temp; factor ++) 
            {
                int equalFactors = 0;

                while(temp % factor == 0) 
                {
                    temp = temp / factor;
                    equalFactors ++;
                }

                if(equalFactors == 1)
                {
                    product *= factor;
                    distinctPrimeFactors ++;
                }
            }

            if(product == naturalNumber && distinctPrimeFactors == 3) 
            {     
                Integer sphenicNumber = naturalNumber;
                
                listOfSphenicNumbers.add(sphenicNumber);

                sphenicNumbersFound ++;                
            }
        }
        
        listOfSphenicNumbers.forEach(sphenicNumber -> this.setResult(this.getResult() + sphenicNumber));
        
        calculations.add("sum" + " " + numberOfSphenicNumbers + " = " + this.getResult());
    }

    /**
     * Returns the result of the calculation.
     * 
     * @return result of the calculation.
     */
    public int getResult() 
    {
        return result;
    }
	
    /**
     * Sets the calculation result.
     *
     * @param result result of calculations.
     */
    public void setResult(int result) 
    {
        this.result = result;
    }

    /**
     * Returns the list of the calculation.
     * 
     * @return list of calculations.
     */
    public ArrayList<String> getCalculations() 
    {
        return calculations;
    }

    /**
     * Sets the calculation list.
     *
     * @param calculations list of calculations.
     */
    public void setCalculations(ArrayList<String> calculations) 
    {
        this.calculations = calculations;
    }
    
    /**
     * Returns the last calculation.
     *
     * @return last calculation.
     */
    public String lastCalculation()
    {
        return calculations.get(calculations.size() - 1);
    }

    /**
     * Returns the string representing the object.
     *
     * @return result of calculations in the form of text.
     */
    @Override
    public String toString() 
    {
        return String.valueOf(result);
    }  
}