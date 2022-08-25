//package Client;
//
//import javax.imageio.IIOException;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.net.InetAddress;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class Test_client {
//    private InetAddress host;
//    private int port;
//    public Client(InetAddress host, int port){
//        this.host = host;
//        this.port = port;
//    }
//    private void execute() throws IIOException{
//        Socket client = new Socket(host, port);
//        ReadClient read = new ReadClient(client);
//        read.start();
//        WriteClient write = new WriteClient(client);
//        write.start();
//
//    }
//}
//public class ReadClient extends Thread{
//    private Socket client;
//    public ReadClient(Socket client){
//        this.socket = client;
//    }
//    @Override
//    public void run(){
//        DataInputStream dis = null;
//        try{
//            dis = new DataInputStream(client.getInputStream());
//            while(true){
//                String sms = dis.readUTF();
//                System.out.println(sms);
//            }
//        }catch (Exception e){
//            try{
//                dis.close();
//                client.close();
//            }catch (IIOException ex){
//                System.out.println("Ngat ket noi server");
//            }
//        }
//    }
//}
//class WriteClient extends Thread{
//    private  Socket client;
//    public WriteClient(Socket client){
//        this.client = client;
//    }
//
//    @Override
//    public void run(){
//        DataOutputStream dos = null;
//        Scanner sc= null;
//        try {
//            dos = new DataOutputStream(client.getOutputStream());
//            sc = new Scanner(System.in);
//            while(true){
//                String sms = sc.nextLine();
//                dos.writeUTF(sms);
//            }
//        }catch (Exception e){
//            try{
//                dos.close();
//                client.close();
//            }catch (IIOException ex){
//                System.out.println("Ngat ket noi server");
//            }
//        }
//    }
//}
