package com.example.travelg.Clustering;

import com.example.travelg.ClusteringAlgorith.FileCreator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class FileCreatorTest {

    @Rule  public ExpectedException thrown = ExpectedException.none();


    @Test
    public void createFileAndExists()
    {
        String fileName= "pav";
        FileCreator creator = new FileCreator();
        boolean exists = false;
        try{
        creator.createFile("",fileName);
        File file = new File("pav.txt");
        exists = file.exists();
        }
        catch (IOException e)
        {
            System.out.println("Problem with the file creation");
        }

        assertEquals(exists,true);
    }

    @Test
    public void createFileAndWrite()
    {
        String fileName= "pav";
        FileCreator creator = new FileCreator();
        Scanner fileScanner;
        String actual = "Life is good";
        String textFile ="";

        try{
            creator.createFile(actual,fileName);
            File file = new File("pav.txt");
            fileScanner = new Scanner(file);
            textFile = fileScanner.nextLine();
        }
        catch (IOException e)
        {
            System.out.println("Problem with the file creation");
        }

        assertEquals(textFile,actual);

    }
}
