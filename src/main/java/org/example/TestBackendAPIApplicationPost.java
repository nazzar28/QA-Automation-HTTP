package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestBackendAPIApplicationPost {
    private String host;
    private int port;
    private String route;
    private Socket socket;
    private String answer;

    public TestBackendAPIApplicationPost(String host, int port, String route){
        System.out.println("http://" + host + ":" + port + route);
        this.host = host;
        this.port = port;
        this.route = route;
        answer = "";
    }

    public void testFirst(){
        System.out.println("Test First Started");
        try {
            socket = new Socket(host, port);
        }catch (UnknownHostException une){
            System.out.println("Not connected: " + une);
        }catch (IOException ioe){
            System.out.println("IOException: " + ioe);
        }
        System.out.println("Test First Ended");
    }

    public void testSecond(){
        System.out.println("Test Second Started");
        String jsonData = """
        {
            "title": "About Java",
            "description": "Java"
        }
                """;
        sendMessage(jsonData);
        readMessage();
        System.out.println("Test Second Ended");
    }

    public void sendMessage(String text){
        try{
            String message = text;
            byte[] data = message.getBytes();
            int fileLength = (int)message.length();

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println("POST " + route + " HTTP/1.1");
            out.println("Content-Type: " + "application/json");
            out.println("User-Agent: Nazar Talantbekov");
            out.println("Accept: */*");
            out.println("Cash-Control: no-cache");
            out.println("Host: 195.38.164.139:8877");
            out.println("Content-Length " + fileLength);
            out.println();
            out.flush();

            BufferedOutputStream dataOut = new BufferedOutputStream(socket.getOutputStream());
            dataOut.write(data, 0, fileLength);
            dataOut.flush();
        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }

    public String readMessage(){
        try {
            InputStream inputStream = socket.getInputStream();
            while(true){
                int unicode = inputStream.read();
                char symbol = (char)unicode;
                answer = answer + symbol;
                if(inputStream.available() == 0){
                    break;
                }
            }
        } catch (IOException ioe){
            System.out.println(ioe);
        }
        return answer;
    }

    public String getAnswerFromBackend(){
        return answer;
    }
}
