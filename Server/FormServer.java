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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;

public class FormServer extends JFrame {
	private String message;
	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel model = new DefaultTableModel();
	private JTable table_server = new JTable(model);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FormServer frame = new FormServer();
		frame.setVisible(true);
		try{
			ServerSocket server=new ServerSocket(8888);
			int counter=0;
			System.out.println("Server Started ....");
			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get("C:/Test/abc");
			dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);

			System.out.println("Watch Service registered for dir: " + dir.getFileName());

			WatchKey key = null;
			while(true){
				counter++;
				Socket serverClient=server.accept();  //server accept the client connection request
				DefaultTableModel model = (DefaultTableModel) frame.table_server.getModel();
				model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});
//				frame.table_server.(1, new Object[]{1, "Client No:" + counter, "connect succecss", "ket noi thanh cong"});
				System.out.println(" >> " + "Client No:" + counter + " started!");


				while (true) {
					try {
						// System.out.println("Waiting for key to be signalled...");
						key = watcher.take();
					} catch (InterruptedException ex) {
						System.out.println("InterruptedException: " + ex.getMessage());
						return;
					}

					for (WatchEvent<?> event : key.pollEvents()) {
						// Retrieve the type of event by using the kind() method.
						WatchEvent.Kind<?> kind = event.kind();
						WatchEvent<Path> ev = (WatchEvent<Path>) event;
						Path fileName = ev.context();
						String strLog = "ttt";
						if (kind == StandardWatchEventKinds.ENTRY_CREATE) {

							strLog = "A new file was created" +" "+ fileName.getFileName();

							model.addRow(new Object[]{"C:/Test/abc", "\"Client No:\" + counter + \" ", "created", strLog});
							System.out.println(strLog);
							System.out.println(kind);

						} else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {

							strLog = "A new file %s was modified.%n" + fileName.getFileName();
							model.addRow(new Object[]{"C:/Test/abc", "\"Client No:\" + counter + \" ", "modified", strLog});
							System.out.printf("A file %s was modified.%n", fileName.getFileName());
						} else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
							strLog = "A new file %s was modified.%n" + fileName.getFileName();
							model.addRow(new Object[]{"C:/Test/abc", "\"Client No:\" + counter + \" ", "deleted", strLog});
							System.out.printf("A file %s was deleted.%n", fileName.getFileName());
						}
						ServerClientThread sct = new ServerClientThread(serverClient,counter,strLog); //send  the request to a separate thread
						sct.start();
					}

					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				}

			}
		}catch(Exception e){
			System.out.println(e);
		}
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
//			Socket serverClient=server.accept();  //server accept the client connection request
//			System.out.println(" >> " + "Client No:" + counter + " started!");
//			model.insertRow(1, new Object[]{1, "Client No:" + counter, "connect succecss", "ket noi thanh cong"});
//			ServerClientThread sct = new ServerClientThread(serverClient,counter); //send  the request to a separate thread
//			sct.start();
			while(true){
				counter++;
				Socket serverClient=server.accept();  //server accept the client connection request
				System.out.println(serverClient);
				System.out.println(" >> " + "Client No:" + counter + " started!");
				model.insertRow(1, new Object[]{1, "Client No:" + counter, "connect succecss", "ket noi thanh cong"});
//				ServerClientThread sct = new ServerClientThread(serverClient,counter); //send  the request to a separate thread
//				sct.start();
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
class ServerClientThread extends Thread {
	Socket serverClient;
	int clientNo;
	int squre;
	String data;
	ServerClientThread(Socket inSocket,int counter, String strLog){
		serverClient = inSocket;
		clientNo=counter;
		data=strLog;
	}
	public void run(){
		try{
			DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
			String clientMessage="", serverMessage="";
			clientMessage=inStream.readUTF();
			System.out.println("From Client-" +clientNo+ ": Number is :"+clientMessage);
			squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
			System.out.println(data);
			serverMessage=data;
			outStream.writeUTF(serverMessage);
			outStream.flush();
//			while(!clientMessage.equals("bye")){
//				clientMessage=inStream.readUTF();
//				System.out.println("From Client-" +clientNo+ ": Number is :"+clientMessage);
//				squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
//				serverMessage="From Server to Client-" + clientNo + " Square of " + clientMessage + " is " +squre;
//				outStream.writeUTF(serverMessage);
//				outStream.flush();
//			}
//			inStream.close();
//			outStream.close();
//			serverClient.close();
		}catch(Exception ex){
			System.out.println(ex);
		}
//		finally{
//			System.out.println("Client -" + clientNo + " exit!! ");
//		}
	}
}

