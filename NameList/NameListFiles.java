package NameList;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.LinkedList;

public class NameListFiles {
    public static void main(String[] args) {
        String inputString = "";
        int currentChar;
        LinkedList<Integer> input = new LinkedList<>();
        //File inputFile = new File("NameList/NamesList.txt");
        try (FileReader fReader = new FileReader("NameList/NamesList.txt")) {
            while ((currentChar=fReader.read())!=-1  ) {
                input.add(currentChar);
            }

            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println((int)' ');
        //System.out.println(input.get(5).intValue());
    }
}
