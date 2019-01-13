package com.travelg.ClusteringAlgorith;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {

    public void createFile(String text,String fileName) throws IOException
    {
        File file = new File(fileName+".txt");

        if(file.exists()){
            file.delete();
        }
        file.createNewFile();

        BufferedWriter output = null;
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            if ( output != null ) {
                output.close();
            }
    }
}

