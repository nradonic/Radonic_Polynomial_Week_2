package GUIStuff;

import ProcessPolynomials.Polynomial;
import ProcessPolynomials.ProcessPolys;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by NickRadonic on 1/25/15.
 */
public class GUI extends JFrame implements ActionListener {
    private JPanel rootPanel;
    private JButton inputFileButton, outputFileButton, storeButton;
    private JTable polyTable;
    private PolyForm pf;
    private FileStuff fs;
    //private ArrayList<String> inputPolys, outputPolys, formattedPolys;

    private JTextField poly1Name, poly2Name, operation;
    private JTextArea inputTextArea, outputTextArea;

    private ArrayList<Polynomial> polyList = new ArrayList<>();

    public GUI(){
        super("Polynomial Display");
        pf = new PolyForm();
        rootPanel = pf.getRootPanel();

        inputFileButton = pf.getSourceFileButton();
        outputFileButton = pf.getDestFileButton();
        storeButton = pf.getStoreButton();

        inputTextArea = pf.getInputJTextArea();
        outputTextArea = pf.getOutputJTextArea();

        poly1Name = pf.getPoly1Name();
        poly2Name = pf.getPoly2Name();
        operation = pf.getOperation();

        fs = new FileStuff();
        addActionListeners();

        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void addActionListeners(){
        inputFileButton.addActionListener(this);
        outputFileButton.addActionListener(this);
        storeButton.addActionListener(this);
        poly1Name.addActionListener(this);
        poly2Name.addActionListener(this);
        operation.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        ProcessPolys pp = new ProcessPolys();
        if (e.getSource() == inputFileButton){
            fs.processInputFile();
            System.out.println("Input button");

            pf.setCoefficientFile(fs.getSourceFileName());
            polyList = fs.getPolynomials();
        }

        if (e.getSource()==outputFileButton){
            fs.getOutputFile();
            //System.out.println("Output button");
            pf.setOutputFile(fs.getDestFileName());
        }

        if (e.getSource()==storeButton){
            //System.out.println("Store button");
            if(fs.getFileStatus()){
                fs.writeFormattedPolynomials();
            }
        }

        fillPolyTextLists();

        if(e.getSource() == poly1Name || e.getSource() == poly2Name || operation == e.getSource()){
            String nameP1 = poly1Name.getText();
            Polynomial p1 = lookupPoly(nameP1);
            String fPoly1 = lookupFormattedPoly(nameP1);
            if (p1 != null){
                pf.setPoly1Text(fPoly1);
            }

            String nameP2 = poly2Name.getText();
            Polynomial p2 = lookupPoly(nameP2);
            String fPoly2 = lookupFormattedPoly(nameP2);
            pf.setPoly2Text( (p2 != null) ? fPoly2 : "");

            if(p1!=null && p2!=null) {
                String op = operation.getText();
                Polynomial p3 = null;

                switch (op) {
                    case "+": {
                        p3 = pp.add(p1, p2);
                        break;
                    }
                    case "-": {
                        p3 = pp.sub(p1, p2);
                        break;
                    }
                    default: {
                        p3 = new Polynomial("None");
                    }
                }
                pf.setResultLabel(p3.getFormattedPolyString());
                polyList.add(p3);
                fillPolyTextLists();
            }
        }
    }

    private void fillPolyTextLists() {
        inputTextArea.setText("");
        for(Polynomial p : polyList){
            String s = p.toString();
            inputTextArea.append(s+"\n");
        }
        outputTextArea.setText("");
        for(Polynomial p : polyList){
            String s = p.getFormattedPolyString();
            outputTextArea.append(s+"\n");
        }
    }

    private Polynomial lookupPoly(String name){
        Polynomial poly = null;
        for(Polynomial p : polyList){
            if ( name.compareToIgnoreCase(p.getName()) == 0){
                poly = p;
                break;
            }
        }
        return poly;
    }

    private String lookupFormattedPoly(String name){
        String poly = "";
        for(Polynomial p : polyList){
            if ( name.compareToIgnoreCase(p.getName()) == 0){
                poly = p.getFormattedPolyString();
                break;
            }
        }
        return poly;
    }

}
