package ProcessPolynomials;

import java.util.ArrayList;

/**
 * Created by NickRadonic on 1/25/15.
 */
public class ProcessPolys {
    private ArrayList<Polynomial> polyList = new ArrayList<>();

    public Polynomial add(Polynomial p1, Polynomial p2){

        Polynomial p3 = getPolynomialForMath(p1, p2, "A"); // add
        while(p2.hasNext()){
            p3.addPolyCoeffs(p2.getNext(), p2.getNext());
        }
        return p3;
    }

    private Polynomial getPolynomialForMath(Polynomial p1, Polynomial p2, String signature) {
        Polynomial p3 = new Polynomial(p1.getName()+p2.getName()+signature, true);
        p1.resetArgsPointer();
        while(p1.hasNext()){
            p3.addPolyCoeffs(p1.getNext(), p1.getNext());
        }
        p2.resetArgsPointer();
        return p3;
    }

    public Polynomial sub(Polynomial p1, Polynomial p2){

        Polynomial p3 = getPolynomialForMath(p1, p2, "S"); // subtract
        while(p2.hasNext()){
            p3.addPolyCoeffs(p2.getNext(), -p2.getNext());
        }
        return p3;
    }
}
