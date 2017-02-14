package com.example.ideal.play_video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ideal on 2/9/2017.
 */

public class Test {

    public static void main(String[] args){
        System.out.println("Code started");
        String vidAddress = "https://drive.google.com/file/d/0BxFBWnPNFLCWU3lZYkVlNHRobEk/preview";
        //String finalAddress = null;
        try {
            String finalAddress = downloadUrl(vidAddress);
            System.out.println("Our dhaka url length "+finalAddress);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            String contentAsString = readIt(is);
            return contentAsString;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static String readIt(InputStream stream) throws IOException {
        System.out.println("Stream "+stream.toString());
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        System.out.println("reader"+reader.readLine());
        while ((line = reader.readLine()) != null) {
            if (line.contains("fmt_stream_map")) {
                sb.append(line + "\n");
                break;
            }
        }
        reader.close();
        System.out.println("sb "+sb.toString());
        String result = decode(sb.toString());
        String[] url = result.split("\\|");
        return result;
    }
    public static String decode(String in) {
        String working = in;
        System.out.println("working"+working);
        int index;
        index = working.indexOf("\\u");
        while (index > -1) {
            int length = working.length();
            if (index > (length - 6)) break;
            int numStart = index + 2;
            int numFinish = numStart + 4;
            String substring = working.substring(numStart, numFinish);
            int number = Integer.parseInt(substring, 16);
            String stringStart = working.substring(0, index);
            String stringEnd = working.substring(numFinish);
            working = stringStart + ((char) number) + stringEnd;
            System.out.println("while"+working);
            index = working.indexOf("\\u");
        }
        return working;
    }

}
