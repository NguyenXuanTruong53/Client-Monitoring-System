import java.awt.BorderLayout;
import java.awt.EventQueue;
import Client.TCPClient;
import MultithreadedSocketServer.MultithreadedSocketServer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
//package com.gpcoder.watchservice;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//public class DataForm {
//	String rollNo, date, action, description;
//
//	public DataForm() {
//	}
//	public DataForm(String rollNo, String date, String action, String description) {
//		this.rollNo = rollNo;
//		this.date = date;
//		this.action = action;
//		this.description = description;
//	}
public class FormClient extends JFrame {
//	DefaultTableModel tableModel;
//	List<DataForm> FormList = new ArrayList<>();
//	tableModel = (DefaultTableModel) table.getModel();
	/**
	 * Creates new form StudentFrame
	 */
//	public StudentFrame() {
//		tableModel = (DefaultTableModel) table.getModel();
//
//
//	}
	private DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable(model);
	private JPanel contentPane;
	private Socket socket;
//	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormClient frame = new FormClient();
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
	public FormClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(258, 47, 129, 20);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(258, 96, 129, 20);
		contentPane.add(textPane_1);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(179, 53, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setBounds(179, 102, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent events) {
				String host = textPane.getText();
				String port = textPane_1.getText();
				int Port = Integer.parseInt(port);

//				int status = TCPClient.run_client(host, Port);
				try {
					socket = new Socket(host, Port);
					model.insertRow(1, new Object[]{1, "12", "connect succecss", "ket noi thanh cong"});
					DataInputStream inStream = new DataInputStream(socket.getInputStream());
					DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String clientMessage = "", serverMessage = "";
					clientMessage = br.readLine();
					outStream.writeUTF(clientMessage);
					outStream.flush();
					serverMessage = inStream.readUTF();
					model.insertRow(1, new Object[]{1, "12", serverMessage, "ket noi thanh cong"});
					System.out.println(serverMessage);
//					while (!clientMessage.equals("bye")) {
//
//
//						System.out.println("Enter number :");
//						clientMessage = br.readLine();
//						outStream.writeUTF(clientMessage);
//						outStream.flush();
//						serverMessage = inStream.readUTF();
//						System.out.println(serverMessage);
//
//					}
//					outStream.close();
//					outStream.close();
//					socket.close();
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		btnNewButton.setBounds(220, 139, 89, 23);
		contentPane.add(btnNewButton);
		String [] columnNames = {
				"NO",
				"Time",
				"Action",
				"Description"
		};
		Object [][] data = {
				{"NO","Time","Action","Description"}
		};


//		DefaultTableModel model = new DefaultTableModel();
//		JTable table = new JTable(model);
		model.addColumn("Col1");
		model.addColumn("Col2");
		model.addColumn("Col3");
		model.addColumn("Col4");

// Append a row
		model.addRow(new Object[]{"v1", "v2", "v3", "v4"});

//		table = new JTable();
//		int i = table.getSelectedRow();
//		table.setValueAt("1",0,1);
		table.setToolTipText("");
		table.setBounds(33, 180, 491, 92);
		contentPane.add(table);
	}
}
