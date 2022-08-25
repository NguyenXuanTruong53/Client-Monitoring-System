//package Server;
//
//import Client.ReadClient;
//
//import javax.imageio.IIOException;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Test_server {
//    private int port;
//    public static ArrayList<Socket> ListSk;
//    public Server(int port){
//        this.port = port;
//    }
//    private void execute()throws IIOException{
//        ServerSocket server = new ServerSocket(port);
//        WriteServer write = new WriteServer();
//        write.start();
//        while(true){
//            Socket socket = server.accept();
//            Test_server.ListSk.add(socket);
//            ReadClient read = new ReadClient(socket);
//            read.start();
//
//        }
//    }
//}
//class ReadServer extends Thread{
//    private Socket server;
//    public ReadServer(Socket server){
//        this.server = server;
//    }
//    @Override
//    public void run(){
//        DataInputStream dis = null;
//        try{
//            dis = new DataInputStream(server.getInputStream());
//            while(true){
//                String sms = dis.readUTF();
//                System.out.println(sms);
//            }
//        }catch (Exception e){
//            try{
//                dis.close();
//                server.close();
//            }catch (IIOException ex){
//                System.out.println("Ngat ket noi server");
//            }
//        }
//    }
//}
//class WriteServer extends Thread{
//
//    @Override
//    public void run(){
//        DataOutputStream dos = null;
//        Scanner sc = new Scanner(System.in);
//        while (true){
//            String sms = sc.nextLine();
//        }
//    }
//}
