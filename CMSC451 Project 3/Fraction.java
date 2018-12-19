import java.text.DecimalFormat;

/**
 * Filename:    Fraction
 * Author:      Shafro Batyrov
 * Date:        07/01/2018
 * Description: Defines the Fraction object.
 */
public class Fraction implements Comparable<Fraction> {

    private double numerator;
    private double denominator;
    private double value;

    Fraction(String fraction) throws MalformedFractionException {
        String[] convert = fraction.split("/");     // converts fraction string to array
        if (convert.length != 2) throw new MalformedFractionException (fraction);    // throw error if fraction is invalid
        numerator = Integer.parseInt(convert[0]);        // set numerator
        denominator = Integer.parseInt(convert[1]);      // set denominator
        this.value = numerator / denominator;       // set the value to numerator/denominator
    }

//Gets the value of the fraction
    private Double getValue() {
        return this.value;
    }

  //Returns the String value of the fraction
    @Override
    public String toString() {
        DecimalFormat decForm = new DecimalFormat();
        return decForm.format(numerator) + "/" + decForm.format(denominator);
    }

  //Determines how fractions are compared
    @Override
    public int compareTo(Fraction fraction) {
        if (this.value == fraction.getValue()) {
            return 0;
        }
        return this.value > fraction.value ? 1 : -1;
    }
}