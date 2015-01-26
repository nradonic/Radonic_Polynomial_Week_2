package ProcessPolynomials;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by NickRadonic on 1/25/15.
 */
public class Polynomial{
    private String name;

    private ArrayList<Integer> powerAndCoeffs = new ArrayList<>();
    int coeffsPointer;

    public Polynomial(String name){
        this.name = name;
    }

    public void addPolyCoeffs(Integer power, Integer coeff){
        Boolean foundPower = false;
        for (int i=0; i < powerAndCoeffs.size(); i+=2){
            if (powerAndCoeffs.get(i) == power){
                int total = coeff + powerAndCoeffs.get(i+1);
                foundPower = true;
                powerAndCoeffs.set(i + 1, total);
            }
        }
        if(!foundPower){
            powerAndCoeffs.add(power);
            powerAndCoeffs.add(coeff);
            for (int i = powerAndCoeffs.size()-2; i> 0; i -=2){
                if(powerAndCoeffs.get(i) > powerAndCoeffs.get(i-2)){
                    int intP = powerAndCoeffs.get(i);
                    powerAndCoeffs.set(i, powerAndCoeffs.get(i-2));
                    powerAndCoeffs.set(i-2, intP);

                    int intC = powerAndCoeffs.get(i+1);
                    powerAndCoeffs.set(i+1, powerAndCoeffs.get(i-1));
                    powerAndCoeffs.set(i-1, intC);
                }
            }
        }
    }

    public String toString(){
        String result = name + " = ";
        for (int i=0; i< powerAndCoeffs.size(); i+=2){
            result += powerAndCoeffs.get(i) + " " + powerAndCoeffs.get(i+1) + " ";
        }
        return result;
    }

    public String getName(){
        return name;
    }

    public void resetArgsPointer(){
        coeffsPointer = 0;
    }

    public Boolean hasNext(){
        return coeffsPointer < powerAndCoeffs.size();
    }

    public Integer getNext(){
        Integer temp = powerAndCoeffs.get(coeffsPointer);
        coeffsPointer++;
        return temp;
    }

}
