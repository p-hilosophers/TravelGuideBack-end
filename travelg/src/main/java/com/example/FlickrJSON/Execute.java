package com.example.FlickrJSON;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Execute {

    public void test(List<String> cityList){

        StringBuilder arguments = new StringBuilder();

        for(String cityName : cityList){
            arguments.append(cityName).append(" ");
        }



        try {
            Process proc = Runtime.getRuntime().exec("java -jar C:\\Users\\Muerte\\Documents\\GitHub\\TravelGuide-DatabaseHandler\\TravelGuide-DatabaseHandler\\out\\artifacts\\TravelGuide_DatabaseHandler_jar\\TravelGuide-DatabaseHandler.jar " + arguments);
            proc.waitFor();

            InputStream err = proc.getErrorStream();

            if(err.available() != 0){
                byte c[]=new byte[err.available()];
                err.read(c,0,c.length);
                System.out.println(new String(c));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
