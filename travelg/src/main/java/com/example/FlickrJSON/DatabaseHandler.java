package com.example.FlickrJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatabaseHandler {

    public static void main(String[] args) throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec("java -jar C:\\Users\\Muerte\\Documents\\GitHub\\TravelGuide-DatabaseHandler\\TravelGuide-DatabaseHandler\\TravelGuide-DatabaseHandler\\out\\artifacts\\TravelGuide_DatabaseHandler_jar\\TravelGuide-DatabaseHandler.jar " + "Athens");
        proc.waitFor();
        InputStream in = proc.getInputStream();
        String result = getStringFromInputStream(in);
        System.out.println(result);
    }


    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
