import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.net.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFrame;

/*
 * SegmentationForm.java
 *
 * Created on __DATE__, __TIME__
 */

/**
 *
 * @author  __USER__
 */
public class SpeechTagForm extends javax.swing.JFrame {

	/** Creates new form SegmentationForm */
	public SpeechTagForm() {
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		//this.setLocationRelativeTo(null); 
		int windowWidth = this.getWidth(); //获得窗口宽

		int windowHeight = this.getHeight(); //获得窗口高

		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包

		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸

		int screenWidth = screenSize.width; //获取屏幕的宽

		int screenHeight = screenSize.height; //获取屏幕的高
	
//		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		this.setLocation(350, 50);//设置窗口居中显示
		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton6 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		textField1 = new java.awt.TextField();
		jButton3 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("\u4e2d\u6587\u81ea\u7136\u8bed\u8a00\u5904\u7406\u5de5\u5177NLPView1.0\u2014\u2014\u8bcd\u6027\u6807\u6ce8                       By Yu Yuanyuan");
		 addWindowListener(new WindowAdapter() {  
			 	public void windowClosing(WindowEvent e) {  
			 	int a = JOptionPane.showConfirmDialog(null, "确定关闭该应用吗？", "温馨提示",      JOptionPane.YES_NO_OPTION); 
			 	if (a == 0) {       System.exit(0);  //关闭
			 	}   }   });
		 
		jButton1.setFont(new java.awt.Font("宋体", 0, 12));
		jButton1.setText("\u6587\u672c\u6587\u4ef6");
		jButton1.setMaximumSize(new java.awt.Dimension(93, 25));
		jButton1.setMinimumSize(new java.awt.Dimension(93, 25));
		jButton1.setPreferredSize(new java.awt.Dimension(93, 25));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("宋体", 0, 12));
		jLabel1.setText("\u6587\u4ef6\u5217\u8868\uff1a");

		jLabel2.setFont(new java.awt.Font("宋体", 0, 12));
		jLabel2.setText("\u68c0\u7d22\u7ed3\u679c\uff1a");

		jButton6.setFont(new java.awt.Font("宋体", 0, 12));
		jButton6.setText("\u5f00\u59cb\u68c0\u7d22");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jButton2.setFont(new java.awt.Font("宋体", 0, 12));
		jButton2.setText("\u4fdd\u5b58\u6587\u4ef6");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		textField1.setEditable(false);

		jButton3.setFont(new java.awt.Font("宋体", 0, 12));
		jButton3.setText("pdf\u6587\u4ef6");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton5.setFont(new java.awt.Font("宋体", 0, 12));
		jButton5.setText("URL");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jButton7.setFont(new java.awt.Font("宋体", 0, 12));
		jButton7.setText("\u56de\u4e3b\u9762\u677f>>");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		jTextArea1.setColumns(20);
		jTextArea1.setLineWrap(true);
		jTextArea1.setRows(5);
		jTextArea1.setWrapStyleWord(true);
		jScrollPane1.setViewportView(jTextArea1);

		jTextArea2.setColumns(20);
		jTextArea2.setLineWrap(true);
		jTextArea2.setRows(5);
		jTextArea2.setWrapStyleWord(true);
		jScrollPane2.setViewportView(jTextArea2);

		jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 24));
		jLabel3.setText("\u8bcd");

		jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 24));
		jLabel4.setText("\u6027");

		jLabel5.setFont(new java.awt.Font("微软雅黑", 0, 24));
		jLabel5.setText("\u6807");

		jLabel6.setFont(new java.awt.Font("微软雅黑", 0, 24));
		jLabel6.setText("\u6ce8");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup().addGap(201, 201, 201)
								.addComponent(jButton6)
								.addContainerGap(368, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(24,
																										24,
																										24)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																												.addComponent(
																														jScrollPane1,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														444,
																														Short.MAX_VALUE)
																												.addComponent(
																														jLabel2)
																												.addComponent(
																														jScrollPane2)))
																				.addComponent(
																						jLabel1)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(71, 71,
																		71)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(10,
																										10,
																										10)
																								.addComponent(
																										textField1,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										293,
																										javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jButton1,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(21,
																										21,
																										21)
																								.addComponent(
																										jButton3)
																								.addGap(18,
																										18,
																										18)
																								.addComponent(
																										jButton5)))))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										106, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel3)
												.addComponent(jLabel4)
												.addComponent(jLabel5)
												.addComponent(jLabel6))
								.addGap(40, 40, 40))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(420, Short.MAX_VALUE)
								.addComponent(jButton7).addGap(137, 137, 137))
				.addGroup(
						layout.createSequentialGroup().addGap(200, 200, 200)
								.addComponent(jButton2)
								.addContainerGap(369, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(
																		jButton1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		jButton5)
																.addComponent(
																		jButton3))
												.addComponent(
														jButton7,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														55,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(10, 10, 10)
								.addComponent(textField1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(31, 31, 31)
								.addComponent(jLabel1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										204,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton6,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										33,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										11, Short.MAX_VALUE)
								.addComponent(jLabel2)
								.addGap(4, 4, 4)
								.addComponent(jScrollPane2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										197,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(14, 14, 14))
				.addGroup(
						layout.createSequentialGroup()
								.addGap(39, 39, 39)
								.addComponent(jLabel3,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										27,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel5,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										23,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel6,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										23,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(517, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		MainForm mf = new MainForm();
		mf.setVisible(true);
		this.setVisible(false);
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		String accountnum1 = JOptionPane.showInputDialog(null, "请输入网址:",
				"输入网址", JOptionPane.PLAIN_MESSAGE);
		textField1.setText(accountnum1);
		//textArea2.setText(accountnum1);
		openUrl u = new openUrl();
		try {
			WebFile file = new WebFile(accountnum1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String html = u.getURLContent(accountnum1, "gb2312");
		html = u.Html2Text(html);
		jTextArea1.setText(html);
		/*String MIME    = file.getMIMEType( );
		Object content = file.getContent( );
		if ( MIME.equals( "text/html" ) && content instanceof String )
		{
		    String html = (String)content;
		    textArea2.setText(html);
		}else{
			JOptionPane.showMessageDialog(this, "该网址无法读取！");
			}*/
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		jTextArea1.setText("");
		JFileChooser jfChooser = new JFileChooser("D:\\..\\..");
		jfChooser.setDialogTitle("导入文件");
		jfChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfChooser.setMultiSelectionEnabled(false);
		jfChooser.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith("pdf") || f.isDirectory())
					return true;
				return false;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub 
				return "(*.pdf)";
			}
		});
		int result = jfChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) { // 确认打开 

			File fileIn = jfChooser.getSelectedFile();

			if (fileIn.exists()) {

				String fl = jfChooser.getSelectedFile().getPath();
				textField1.setText(fl);
				String strtxt = "";
				PdfParser pdfP = new PdfParser();
				try {
					strtxt = pdfP.pdfParser(fl);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					String s = new String(strtxt.getBytes("ISO8859_1"), "GBK");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				jTextArea1.append(strtxt); // 提示框 
			}
		} else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancel button is pushed.");
		} else if (result == JFileChooser.ERROR_OPTION) {
			System.err.println("Error when select file.");
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		String ss = jTextArea2.getText();//获得要保存的文本（可以包含回车）
		JFileChooser jfc = new javax.swing.JFileChooser();
		jfc.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith("txt") || f.isDirectory())
					return true;
				return false;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub 
				return "文本数据(*.txt)";
			}
		});
		if (JFileChooser.APPROVE_OPTION == jfc.showSaveDialog(this)) {
			File saveFile = jfc.getSelectedFile();
			try {
				if (!saveFile.exists()) {
					saveFile.createNewFile();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));
				bw.write(ss);
				bw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		PartsOfSpeechTag pos = new PartsOfSpeechTag();
		ArrayList<String> keywords = new ArrayList<String>();
		try {
			//String result="";

			String str = jTextArea1.getText();
			if (str == "") {
				JOptionPane.showMessageDialog(this, "请选择检索文件！");
			} else {
				jTextArea2.setText("");
				keywords = pos.SpeechTag(str);
				String[] strArr = keywords.toArray(new String[] {});
				for (int c = 0; c < strArr.length; c++) {
					jTextArea2.append(strArr[c]);
					jTextArea2.append("\r\n");
					//result=result+strArr[c]+"\r\n";
					//~~~~```~~~~~`~~~~`````~~~~```~~~~~```~~~~~~~~```~~~``~~~~``~~

				}
				//result=result+strArr[c]+"\r\n";
				//~~~~```~~~~~`~~~~`````~~~~```~~~~~```~~~~~~~~```~~~``~~~~``~~

				//textArea1.setText("文件"+i+"的检索结果为:\r\n"+result+"\r\n");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		jTextArea1.setText("");
		JFileChooser jfChooser = new JFileChooser("D:\\..\\..");
		jfChooser.setDialogTitle("导入文件");
		jfChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfChooser.setMultiSelectionEnabled(false);
		jfChooser.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith("txt") || f.isDirectory())
					return true;
				return false;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub 
				return "文本数据(*.txt)";
			}
		});
		int result = jfChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) { // 确认打开 

			File fileIn = jfChooser.getSelectedFile();

			if (fileIn.exists()) {

				String fl = jfChooser.getSelectedFile().getPath();
				textField1.setText(fl);
				String strtxt = "";
				int lastIndexOfDot = fl.lastIndexOf('.');
				int fileNameLength = fl.length();
				String extension = fl.substring(lastIndexOfDot + 1,
						fileNameLength);

				try {
					strtxt = readtxt(fl);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					String s = new String(strtxt.getBytes("ISO8859_1"), "GBK");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				jTextArea1.append(strtxt); // 提示框 
			}
		} else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancel button is pushed.");
		} else if (result == JFileChooser.ERROR_OPTION) {
			System.err.println("Error when select file.");
		}
	}

	private String readtxt(String file1) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file1));
		String str = "";
		String r = br.readLine();
		while (r != null) {
			str += r;
			r = br.readLine();
		}
		return str;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SpeechTagForm().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private java.awt.TextField textField1;
	// End of variables declaration//GEN-END:variables

}