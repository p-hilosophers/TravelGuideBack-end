package com.example.DatabaseHandler;

import java.io.IOException;


public class DatabaseHandler {

    public void runDatabaseHandler(String jarPath) throws IOException{
        Process proc = Runtime.getRuntime().exec("java -jar " + jarPath + "TravelGuide-DatabaseHandler.jar");
    }

}
