package com.bkw.mylibrary.okhttp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;

import javax.net.ssl.SSLSocketFactory;

public class Test {

    public static void main(String[] args) {

//        try {
//            URL url = new URL("");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("POST");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        socketHttps();

//        try {
//            socketHttp();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            socketHttpPost();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void socketHttpPost() throws IOException {
        String path = "http://restapi.amap.com/v3/weather/weatherInfo";
        URL url = new URL(path);
        Socket socket = new Socket(url.getHost(), url.getDefaultPort());


        /**
         * POST /v3/weather/weatherInfo HTTP/1.1
         Content-Length: 48
         Host: restapi.amap.com
         Content-Type: application/x-www-form-urlencoded

         city=110101&key=13cb58f5884f9749287abbead9c658f2
         * */
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("POST /v3/weather/weatherInfo HTTP/1.1\r\n");
        writer.write("Host:  restapi.amap.com\r\n");
        writer.write("Content-Type: application/x-www-form-urlencoded\r\n");
        writer.write("Content-Length: 48\r\n\r\n");
        //请求体
        writer.write("city=110101&key=13cb58f5884f9749287abbead9c658f2");
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            String readLine = null;
            if ((readLine = reader.readLine()) != null) {
                System.out.println("响应的数据：" + readLine);
            } else {
                break;
            }
        }
    }

    private static void socketHttp() throws IOException {
        String path = "http://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2";
        URL url = new URL(path);
        Socket socket = new Socket(url.getHost(), url.getDefaultPort());


        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("GET /v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2 HTTP/1.1\r\n");
        writer.write("Host:  restapi.amap.com\r\n\r\n");
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            String readLine = null;
            if ((readLine = reader.readLine()) != null) {
                System.out.println("响应的数据：" + readLine);
            } else {
                break;
            }
        }
    }

    private static void socketHttps() {
        try {
            Socket socket = SSLSocketFactory.getDefault().createSocket("www.baidu.com", 443);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("GET / HTTP/1.1\r\n");
            writer.write("Host: www.baidu.com\r\n\r\n");
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String readLine = null;
                if ((readLine = reader.readLine()) != null) {
                    System.out.println("响应的数据：" + readLine);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
