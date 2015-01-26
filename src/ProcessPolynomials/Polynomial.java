package ProcessPolynomials;

import java.util.ArrayList;

/**
 * Created by NickRadonic on 1/25/15.
 */
public class Polynomial {
    private String name;
    private String rawString;
    private Boolean validPolynomial = false;

    private ArrayList<Integer> powerAndCoeffs = new ArrayList<>();
    int coeffsPointer;

    public Polynomial(String rawString) {
        this.rawString = rawString.trim();
        validPolynomial = processRawPolyString();
    }

    public Polynomial(String name, Boolean createEmptyPoly){
        this.name = name;
        validPolynomial = true;
        addPolyCoeffs(0, 0);
    }

    public void addPolyCoeffs(Integer power, Integer coeff) {
        Boolean foundPower = false;
        for (int i = 0; i < powerAndCoeffs.size(); i += 2) {
            if (powerAndCoeffs.get(i) == power) {
                int total = coeff + powerAndCoeffs.get(i + 1);
                foundPower = true;
                powerAndCoeffs.set(i + 1, total);
            }
        }
        if (!foundPower) {
            powerAndCoeffs.add(power);
            powerAndCoeffs.add(coeff);
            for (int i = powerAndCoeffs.size() - 2; i > 0; i -= 2) {
                if (powerAndCoeffs.get(i) > powerAndCoeffs.get(i - 2)) {
                    int intP = powerAndCoeffs.get(i);
                    powerAndCoeffs.set(i, powerAndCoeffs.get(i - 2));
                    powerAndCoeffs.set(i - 2, intP);

                    int intC = powerAndCoeffs.get(i + 1);
                    powerAndCoeffs.set(i + 1, powerAndCoeffs.get(i - 1));
                    powerAndCoeffs.set(i - 1, intC);
                }
            }
        }
    }

    public String toString() {
        String result = name + " = ";
        for (int i = 0; i < powerAndCoeffs.size(); i += 2) {
            result += powerAndCoeffs.get(i) + " " + powerAndCoeffs.get(i + 1) + " ";
        }
        return result.trim();
    }

    public String getName() {
        return name;
    }

    public void resetArgsPointer() {
        coeffsPointer = 0;
    }

    public Boolean hasNext() {
        return coeffsPointer < powerAndCoeffs.size();
    }

    public Integer getNext() {
        Integer temp = powerAndCoeffs.get(coeffsPointer);
        coeffsPointer++;
        return temp;
    }

    private Boolean processRawPolyString() {
        int eqPosn = rawString.indexOf("=");
        if (eqPosn == -1) {
            return false;
        } else {
            String[] sa = rawString.split("=");
            if (sa.length == 2) { // look for proper construction of string
                //polysOut.add(s);
                name = sa[0].trim();
                String[] residuals = sa[1].replaceAll("  +", " ").trim().split(" ");
                if (residuals.length % 2 == 1) {
                    return false;
                } // odd number of coefficients
                for (String s : residuals) {
                    powerAndCoeffs.add(Integer.valueOf(s));
                }
                pairSortCoeffs();
                return true;
            } else {
                return false;
            }
        }
    }

    private void pairSortCoeffs() {
        for (int i = 0; i < powerAndCoeffs.size(); i += 2) {
            for (int j = i + 2; j < powerAndCoeffs.size(); j += 2) {
                int temp1 = powerAndCoeffs.get(i);
                int temp2 = powerAndCoeffs.get(j);
                if (temp2 > temp1) {
                    powerAndCoeffs.set(j, temp1);
                    powerAndCoeffs.set(i, temp2);
                    temp1 = powerAndCoeffs.get(i + 1);
                    temp2 = powerAndCoeffs.get(j + 1);
                    powerAndCoeffs.set(i + 1, temp2);
                    powerAndCoeffs.set(j + 1, temp1);

                }
            }
        }
    }

    public Boolean isValid() {
        return validPolynomial;
    }

    public String getFormattedPolyString() {
        String tempResult = "";
        for (int i = 0; i < powerAndCoeffs.size(); i += 2) {
            Boolean negativeCoeff = false;
            Boolean zeroCoeff = false;
            Boolean zeroPower = false;
            Boolean onePower = false;
            int power = 0;

            if (powerAndCoeffs.get(i) == 0) {
                zeroPower = true;
            }
            if (powerAndCoeffs.get(i) == 1) {
                onePower = true;
            }

            int temp = powerAndCoeffs.get(i + 1);
            if (temp < 0) {
                negativeCoeff = true;
            }
            if (temp == 0) {
                zeroCoeff = true;
            }

            if (!zeroCoeff) {
                if (i > 0 && !negativeCoeff) {
                    tempResult += " + ";
                } else {
                    tempResult += " ";
                }
                if (zeroPower == false) {
                    tempResult += powerAndCoeffs.get(i + 1) + "X";
                    if (!onePower) {
                        tempResult += "^" + powerAndCoeffs.get(i);
                    }
                } else {
                    tempResult += powerAndCoeffs.get(i + 1);
                }
            }

            if (tempResult.compareToIgnoreCase("") == 0) {
                tempResult = "0";
            }
        }
        return name + " =" + tempResult;
    }
}