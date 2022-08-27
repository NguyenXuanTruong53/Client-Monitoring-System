import MultithreadedSocketServer.MultithreadedSocketServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FormServer extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private JTable table_server = new JTable(model);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormServer frame = new FormServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(24, 34, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(24, 69, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(103, 34, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(103, 34, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("New label");
		lblNewLabel_3_1.setBounds(103, 69, 46, 14);
		contentPane.add(lblNewLabel_3_1);

		JButton btnKetNoi = new JButton("Kết nối");
//		run_server1();
		btnKetNoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run_server1();
			}
		});
		btnKetNoi.setBounds(44, 102, 89, 23);
		contentPane.add(btnKetNoi);

		model.addColumn("Col1");
		model.addColumn("Col2");
		model.addColumn("Col3");
		model.addColumn("Col4");
		model.addRow(new Object[]{"v1", "v2", "v3", "v4"});
//		table_server = new JTable();
		table_server.setBounds(24, 161, 388, 89);
		contentPane.add(table_server);

	}
	public void run_server1(){

		try{

			ServerSocket server=new ServerSocket(8888);

			int counter=0;
			System.out.println("Server Started ....");
			Socket serverClient=server.accept();  //server accept the client connection request
			System.out.println(" >> " + "Client No:" + counter + " started!");
			model.insertRow(1, new Object[]{1, "Client No:" + counter, "connect succecss", "ket noi thanh cong"});
			ServerClientThread sct = new ServerClientThread(serverClient,counter); //send  the request to a separate thread
			sct.start();
//			while(true){
//				counter++;
//				Socket serverClient=server.accept();  //server accept the client connection request
//				System.out.println(" >> " + "Client No:" + counter + " started!");
//				model.insertRow(1, new Object[]{1, "Client No:" + counter, "connect succecss", "ket noi thanh cong"});
//				ServerClientThread sct = new ServerClientThread(serverClient,counter); //send  the request to a separate thread
//				sct.start();
//			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
class ServerClientThread extends Thread {
	Socket serverClient;
	int clientNo;
	int squre;
	ServerClientThread(Socket inSocket,int counter){
		serverClient = inSocket;
		clientNo=counter;
	}
	public void run(){
		try{
			DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
			String clientMessage="", serverMessage="";
			while(!clientMessage.equals("bye")){
				clientMessage=inStream.readUTF();
				System.out.println("From Client-" +clientNo+ ": Number is :"+clientMessage);
				squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
				serverMessage="From Server to Client-" + clientNo + " Square of " + clientMessage + " is " +squre;
				outStream.writeUTF(serverMessage);
				outStream.flush();
			}
			inStream.close();
			outStream.close();
			serverClient.close();
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			System.out.println("Client -" + clientNo + " exit!! ");
		}
	}
}

