package com.example.travelg.ClusteringAlgorith;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {

    public void createFile(String text) throws IOException
    {
        File file = new File("sights.txt");

        if(file.exists()){
            file.delete();
        }
        file.createNewFile();

        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
    }
}
