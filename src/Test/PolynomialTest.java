package Test;

import ProcessPolynomials.Polynomial;

import static org.junit.Assert.*;

public class PolynomialTest {
    Polynomial poly;

    @org.junit.Before
    public void setUp() throws Exception {
        poly = new Polynomial("p1 = 3 5 0 8");
    }

    @org.junit.Test
    public void testAddPolyCoeffs() throws Exception {
        poly.addPolyCoeffs(4,7);
        String temp = poly.getFormattedPolyString();

        assertTrue(temp.contains("7X^4"));
    }

    @org.junit.Test
    public void testToString() throws Exception {
        assertTrue(poly.toString().contains("p1 = 3 5 0 8"));
    }

    @org.junit.Test
    public void testGetName() throws Exception {
        assertTrue(poly.getName().contains("p1"));
    }

    @org.junit.Test
    public void testResetArgsPointer() throws Exception {
        poly.resetArgsPointer();
        assertTrue(poly.getNext() == 3);
    }

    @org.junit.Test
    public void testHasNext() throws Exception {
        poly.resetArgsPointer();
        assertTrue(poly.hasNext() == true);
    }

    @org.junit.Test
    public void testGetNext() throws Exception {
        poly.resetArgsPointer();
        int temp1 = poly.getNext();
        int temp2 = poly.getNext();
        assertTrue(temp1 == 3 && temp2==5);
    }

    @org.junit.Test
    public void testGetValidPolynomial() throws Exception {
        assertTrue(poly.isValid()==true);
    }

    @org.junit.Test
    public void testGetFormattedPolyString() throws Exception {
        String s = poly.getFormattedPolyString();
        assertTrue(s.equalsIgnoreCase("p1 = 5X^3 + 8"));
    }

    @org.junit.Test
    public void testInternalCoefficientSorting(){
        poly.addPolyCoeffs(4,7);
        String temp = poly.getFormattedPolyString();

        assertTrue(temp.equalsIgnoreCase("p1 = 7X^4 + 5X^3 + 8"));
    }
}