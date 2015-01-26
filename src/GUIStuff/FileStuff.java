package GUIStuff;

import ProcessPolynomials.Polynomial;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * Created by NickRadonic on 1/25/15.
 */
public class FileStuff {
    private Boolean inputFileSelected = false;
    private Boolean outputFileSelected = false;
    private Boolean formattedDataStored = false;
    private String sourceFile, destFile;
    private ArrayList<Polynomial> polynomials = new ArrayList<>();

    public String processInputFile(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Raw Polynomial File", "txt");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("."));

        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            sourceFile = chooser.getSelectedFile().getName();
            System.out.println("You chose to open this file: " + sourceFile);
        }
        File file = chooser.getSelectedFile();
        ArrayList<String> gameData = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String s;
            while ((s=br.readLine())!=null){
                //inputPolys.add(s);
                Polynomial poly = new Polynomial(s);
                if (poly.isValid() != null) {
                    polynomials.add(poly);
                }
            }

            br.close();
            fr.close();
        }
        catch (IOException ex)
        {System.out.println("File opening error "+chooser.getSelectedFile().getName()+" "+ex.toString()); }
        inputFileSelected = true;
        return sourceFile;
    }

    public String getOutputFile(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Formatted Polynomial File", "txt");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("."));

        int returnVal = chooser.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            destFile = chooser.getSelectedFile().getName();
            System.out.println("You chose to open this file: " + destFile);
        }
        File file = chooser.getSelectedFile();
        ArrayList<String> gameData = new ArrayList<String>();
        outputFileSelected = true;

        return destFile;
    }

    public String getSourceFileName(){
        return sourceFile;
    }

    public String getDestFileName(){
        return destFile;
    }

    public ArrayList<Polynomial> getPolynomials(){
        return polynomials;
    }

    public Boolean getFileStatus(){
        return inputFileSelected && outputFileSelected;
    }

    public Boolean writeFormattedPolynomials(){
        System.out.println("Write formatted polynomials\n");
        try {
            FileWriter fw = new FileWriter(destFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Polynomial p : polynomials){
                if(p.isValid()) {
                    String s = p.toString();
                    bw.write(s + "\n");
                }
            }

            bw.close();
            fw.close();
        } catch (Exception ex){
            System.out.println("File IO exception "+ex);
        }
        return true;
    }

}

