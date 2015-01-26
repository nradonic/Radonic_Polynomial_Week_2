package GUIStuff;

import javax.swing.*;

/**
 * Created by NickRadonic on 1/25/15.
 */
public class PolyForm {
    private JPanel panel1;
    private JButton selectInputCoefficientFIleButton;
    private JButton selectOutputFormattedPolynomialButton;
    private JLabel selectedCoefficientFileName;
    private JButton storeButton;
    private JTable table1;
    private JLabel selectedFormattedPolynomialFileName;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JScrollPane scroller1;
    private JScrollPane scroller2;
    private JLabel resultLabel;
    private JTextField poly1TextField;
    private JTextField poly2TextField;
    private JTextField p1LabelTextField;
    private JTextField p2LabelTextField;
    private JTextField operation;


    public JPanel getRootPanel(){
        return panel1;
    }

    public void setCoefficientFile(String coeffFile){
        selectedCoefficientFileName.setText(coeffFile);
    }

    public void setOutputFile(String outputFileName){
        selectedFormattedPolynomialFileName.setText(outputFileName);
    }

    public JTable getTableLink(){
        return table1;
    }

    public JButton getSourceFileButton(){
        return selectInputCoefficientFIleButton;
    }

    public JButton getDestFileButton(){
        return selectOutputFormattedPolynomialButton;
    }

    public JButton getStoreButton(){
        return storeButton;
    }

    public JTextArea getInputJTextArea(){
        return textArea1;
    }

    public JTextArea getOutputJTextArea(){
        return textArea2;
    }

    public JTextField getPoly1Name() {
        return poly1TextField;
    }

    public JTextField getPoly2Name() {
        return poly2TextField;
    }

    public void setPoly1Text(String textField1) {
        this.p1LabelTextField.setText(textField1);
    }

    public void setPoly2Text(String textField2) {
        this.p2LabelTextField.setText(textField2);
    }

    public JTextField getOperation() {
        return operation;
    }

    public void setResultLabel(String resultLabel) {
        this.resultLabel.setText(resultLabel);
    }
}
