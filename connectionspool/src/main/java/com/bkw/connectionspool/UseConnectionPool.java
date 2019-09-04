package com.bkw.connectionspool;

import android.util.Log;

/**
 * 测试连接池
 *
 * @author bkw
 */
public class UseConnectionPool {

    public void useConnectionPool(ConnectionPool connectionPool,String host, int port) {
        //模拟
        HttpConnection connection = connectionPool.getConnection(host, port);
        if (connection == null) {
            connection = new HttpConnection(host, port);
            Log.e("TAG","useConnectionPool 创建一个连接对象");
        }else{
            Log.e("TAG","useConnectionPool 复用一个连接对象");
        }
        //模拟请求
//        Socket socket = connection.socket;
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            writer.write("POST /v3/weather/weatherInfo HTTP/1.1\r\n");
//            writer.write("Content-Length: 48\r\n");
//            writer.write("Host: restapi.amap.com\r\n");
//            writer.write("Content-Type: application/x-www-form-urlencoded\r\n\r\n");
//            writer.write("city=110101&key=13cb58f5884f9749287abbead9c658f2");
//            writer.flush();
//
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String str = null;
//            while ((str = reader.readLine()) != null) {
//                if (reader.readLine().length() == -1) {
//                    break;
//                }
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //把连接对象加入到连接池
//        connection.lastUseTime=System.currentTimeMillis();
        connectionPool.putConnection(connection);

    }
}
