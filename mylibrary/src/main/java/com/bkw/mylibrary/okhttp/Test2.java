package com.bkw.mylibrary.okhttp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;

import javax.net.ssl.SSLSocketFactory;

public class Test2 {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入网址，然后回车");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line = bufferedReader.readLine();

        Socket socket = null;

        URL url = new URL("https://" + line);
        String hostName = url.getHost();

        int port = 0;
        if (url.getProtocol().equalsIgnoreCase("HTTP")) {
            port = 80;
            socket = new Socket(hostName, port);
        } else if (url.getProtocol().equalsIgnoreCase("HTTPS")) {
            port = 443;
            socket = SSLSocketFactory.getDefault().createSocket(hostName, port);
        }

        if (socket == null) {
            System.out.print("error");
            return;
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("GET / HTTP/1.1\r\n");
        writer.write("Host:  " + hostName + "\r\n\r\n");
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
}
