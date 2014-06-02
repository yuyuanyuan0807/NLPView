import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFrame;

/**
 *
 * @author  __USER__
 */

public class Example extends javax.swing.JFrame {

	/** Creates new form Example */
	public Example() {
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jButton5 = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jButton6 = new javax.swing.JButton();
		list1 = new java.awt.List();
		jLabel2 = new javax.swing.JLabel();
		textArea1 = new java.awt.TextArea();
		jButton2 = new javax.swing.JButton();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu3 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem5 = new javax.swing.JMenuItem();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem6 = new javax.swing.JMenuItem();
		jMenuItem7 = new javax.swing.JMenuItem();
		jMenuItem8 = new javax.swing.JMenuItem();
		jMenuItem9 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem10 = new javax.swing.JMenuItem();
		jMenuItem11 = new javax.swing.JMenuItem();
		jMenuItem12 = new javax.swing.JMenuItem();
		jMenuItem13 = new javax.swing.JMenuItem();
		jMenuBar2 = new javax.swing.JMenuBar();
		jMenu4 = new javax.swing.JMenu();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem4 = new javax.swing.JMenuItem();
		jMenuItem14 = new javax.swing.JMenuItem();
		jMenu5 = new javax.swing.JMenu();
		jMenuItem15 = new javax.swing.JMenuItem();
		jMenuItem16 = new javax.swing.JMenuItem();
		jMenuItem17 = new javax.swing.JMenuItem();
		jMenuItem18 = new javax.swing.JMenuItem();
		jMenu6 = new javax.swing.JMenu();
		jMenuItem19 = new javax.swing.JMenuItem();
		jMenuItem20 = new javax.swing.JMenuItem();
		jMenuItem21 = new javax.swing.JMenuItem();
		jMenuItem22 = new javax.swing.JMenuItem();
		checkbox1 = new java.awt.Checkbox();
		jMenuBar2 = new javax.swing.JMenuBar();
		jMenu4 = new javax.swing.JMenu();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenuItem4 = new javax.swing.JMenuItem();
		jMenuItem14 = new javax.swing.JMenuItem();
		jMenu5 = new javax.swing.JMenu();
		jMenuItem15 = new javax.swing.JMenuItem();
		jMenuItem16 = new javax.swing.JMenuItem();
		jMenuItem17 = new javax.swing.JMenuItem();
		jMenuItem18 = new javax.swing.JMenuItem();
		jMenu6 = new javax.swing.JMenu();
		jMenuItem19 = new javax.swing.JMenuItem();
		jMenuItem20 = new javax.swing.JMenuItem();
		jMenuItem21 = new javax.swing.JMenuItem();
		jMenuItem22 = new javax.swing.JMenuItem();

		jButton5.setText("\u5f00\u59cb\u68c0\u7d22");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setFont(new java.awt.Font("宋体", 0, 12));
		jButton1.setText("\u5bfc\u5165\u6587\u4ef6");
		jButton1.setMaximumSize(new java.awt.Dimension(93, 25));
		jButton1.setMinimumSize(new java.awt.Dimension(93, 25));
		jButton1.setPreferredSize(new java.awt.Dimension(93, 25));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton3.setFont(new java.awt.Font("宋体", 0, 12));
		jButton3.setText("\u5220\u9664\u9009\u4e2d\u9879");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.setFont(new java.awt.Font("宋体", 0, 12));
		jButton4.setText("\u6e05\u7a7a\u5217\u8868");
		jButton4.setMaximumSize(new java.awt.Dimension(93, 25));
		jButton4.setMinimumSize(new java.awt.Dimension(93, 25));
		jButton4.setPreferredSize(new java.awt.Dimension(93, 25));
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("宋体", 0, 12));
		jLabel1.setText("\u6587\u4ef6\u5217\u8868\uff1a");

		jButton6.setFont(new java.awt.Font("宋体", 0, 12));
		jButton6.setText("\u5f00\u59cb\u68c0\u7d22");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jLabel2.setFont(new java.awt.Font("宋体", 0, 12));
		jLabel2.setText("\u68c0\u7d22\u7ed3\u679c\uff1a");

		jButton2.setFont(new java.awt.Font("宋体", 0, 12));
		jButton2.setText("\u4fdd\u5b58\u6587\u4ef6");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jMenu3.setText("Menu");

		jMenuItem1.setText("\u8bed\u53e5\u68c0\u7d22");
		jMenu3.add(jMenuItem1);

		jMenuItem2.setText("\u6587\u4ef6\u68c0\u7d22");
		jMenu3.add(jMenuItem2);

		jMenuItem5.setText("Item");
		jMenu3.add(jMenuItem5);

		jMenuBar1.add(jMenu3);

		jMenu1.setText("File");

		jMenuItem6.setText("Item");
		jMenu1.add(jMenuItem6);

		jMenuItem7.setText("Item");
		jMenu1.add(jMenuItem7);

		jMenuItem8.setText("Item");
		jMenu1.add(jMenuItem8);

		jMenuItem9.setText("Item");
		jMenu1.add(jMenuItem9);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("Edit");

		jMenuItem10.setText("Item");
		jMenu2.add(jMenuItem10);

		jMenuItem11.setText("Item");
		jMenu2.add(jMenuItem11);

		jMenuItem12.setText("Item");
		jMenu2.add(jMenuItem12);

		jMenuItem13.setText("Item");
		jMenu2.add(jMenuItem13);

		jMenuBar1.add(jMenu2);

		jMenu4.setText("Menu");

		jMenuItem3.setText("\u8bed\u53e5\u68c0\u7d22");
		jMenu4.add(jMenuItem3);

		jMenuItem4.setText("\u6587\u4ef6\u68c0\u7d22");
		jMenu4.add(jMenuItem4);

		jMenuItem14.setText("Item");
		jMenu4.add(jMenuItem14);

		jMenuBar2.add(jMenu4);

		jMenu5.setText("File");

		jMenuItem15.setText("Item");
		jMenu5.add(jMenuItem15);

		jMenuItem16.setText("Item");
		jMenu5.add(jMenuItem16);

		jMenuItem17.setText("Item");
		jMenu5.add(jMenuItem17);

		jMenuItem18.setText("Item");
		jMenu5.add(jMenuItem18);

		jMenuBar2.add(jMenu5);

		jMenu6.setText("Edit");

		jMenuItem19.setText("Item");
		jMenu6.add(jMenuItem19);

		jMenuItem20.setText("Item");
		jMenu6.add(jMenuItem20);

		jMenuItem21.setText("Item");
		jMenu6.add(jMenuItem21);

		jMenuItem22.setText("Item");
		jMenu6.add(jMenuItem22);

		jMenuBar2.add(jMenu6);

		setJMenuBar(jMenuBar2);

		checkbox1.setLabel("\u591a\u9009");

		checkbox1.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				checkbox1ItemStateChanged(evt);
			}
		});

		jMenu4.setText("Menu");

		jMenuItem3.setText("\u8bed\u53e5\u68c0\u7d22");
		jMenu4.add(jMenuItem3);

		jMenuItem4.setText("\u6587\u4ef6\u68c0\u7d22");
		jMenu4.add(jMenuItem4);

		jMenuItem14.setText("Item");
		jMenu4.add(jMenuItem14);

		jMenuBar2.add(jMenu4);

		jMenu5.setText("File");

		jMenuItem15.setText("Item");
		jMenu5.add(jMenuItem15);

		jMenuItem16.setText("Item");
		jMenu5.add(jMenuItem16);

		jMenuItem17.setText("Item");
		jMenu5.add(jMenuItem17);

		jMenuItem18.setText("Item");
		jMenu5.add(jMenuItem18);

		jMenuBar2.add(jMenu5);

		jMenu6.setText("Edit");

		jMenuItem19.setText("Item");
		jMenu6.add(jMenuItem19);

		jMenuItem20.setText("Item");
		jMenu6.add(jMenuItem20);

		jMenuItem21.setText("Item");
		jMenu6.add(jMenuItem21);

		jMenuItem22.setText("Item");
		jMenu6.add(jMenuItem22);

		jMenuBar2.add(jMenu6);

		setJMenuBar(jMenuBar2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
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
																												.addGap(23,
																														23,
																														23)
																												.addComponent(
																														jButton1,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGap(18,
																														18,
																														18)
																												.addComponent(
																														jButton3)
																												.addGap(18,
																														18,
																														18)
																												.addComponent(
																														jButton4,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														97,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																														47,
																														Short.MAX_VALUE))
																								.addGroup(
																										layout.createSequentialGroup()
																												.addComponent(
																														jLabel1)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																														256,
																														Short.MAX_VALUE)
																												.addComponent(
																														checkbox1,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGap(23,
																														23,
																														23))))
																.addGroup(
																		layout.createSequentialGroup()
																				.addGap(32,
																						32,
																						32)
																				.addComponent(
																						list1,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						369,
																						Short.MAX_VALUE)))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jButton6)
																.addGap(44, 44,
																		44)))
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(137,
																		137,
																		137)
																.addComponent(
																		jButton2))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(22, 22,
																		22)
																.addComponent(
																		textArea1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		225,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(39, 39,
																		39)
																.addComponent(
																		jLabel2)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jButton1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														44, Short.MAX_VALUE)
												.addComponent(
														jButton3,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														44, Short.MAX_VALUE)
												.addComponent(
														jButton4,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														44, Short.MAX_VALUE))
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(17, 17,
																		17)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						jLabel2)))
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		checkbox1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		textArea1,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		224,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		31,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(68, 68,
																		68))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		list1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		221,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton6,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		33,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap()))));

		getAccessibleContext().setAccessibleName("button1");

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void checkbox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		if (checkbox1.getState()) {
			list1.setMultipleMode(true);
		} else {
			list1.setMultipleMode(false);
		}

	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		String ss = textArea1.getText();//获得要保存的文本（可以包含回车）
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

		WordExtraction ex = new WordExtraction();

		ArrayList<String> keywords = new ArrayList<String>();
		try {
			//String result="";

			String[] str = list1.getSelectedItems();
			if (str.length == 0) {
				JOptionPane.showMessageDialog(this, "请选择检索文件！");
			} else {
				textArea1.setText("");
				for (int i = 0; i < str.length; i++) {
					String file1 = str[i];
					String str1 = str[i];
					String name[] = str1.split("\\\\");
					if (name.length > 1) {
						str1 = name[name.length - 1];
					}

					textArea1.appendText(str1 + "的检索结果为:\r\n");
					String strtxt = readtxt(file1);
					keywords = ex.GetKeyword(strtxt, 20);
					String[] strArr = keywords.toArray(new String[] {});
					for (int c = 0; c < strArr.length; c++) {
						textArea1.appendText(strArr[c]);
						textArea1.appendText("\r\n");
						//result=result+strArr[c]+"\r\n";
						//~~~~```~~~~~`~~~~`````~~~~```~~~~~```~~~~~~~~```~~~``~~~~``~~

					}

					//textArea1.setText("文件"+i+"的检索结果为:\r\n"+result+"\r\n");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		int i = 0;
		i = list1.getItemCount();
		if (i == 0) {
			JOptionPane.showMessageDialog(this, "Cann't delete!");

		} else {

			list1.removeAll();
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {

		int i = -1;
		i = list1.getSelectedIndex();
		if (i == -1) {
			JOptionPane.showMessageDialog(this, "Cann't delete!");

		} else {
			list1.remove(i);
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
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

				list1.add(fl, 0); // 提示框 
			} else {
			}
		} else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancel button is pushed.");
		} else if (result == JFileChooser.ERROR_OPTION) {
			System.err.println("Error when select file.");
		}

	}

	private void list1Listener(ItemEvent evt) {

		String selString;
		String sel;
		int selNum;
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			selNum = list1.getSelectedIndex();
			//sel = list1.getItem(selNum);

			/*jLabel2.setText(selString);
			switch(selNum) 
			{ 
			case 0: 
			 jLabel2.setForeground(Color.red); 
			 break; 
			case 1: 
			jLabel2.setForeground(Color.green); 
			 break; 
			} */
		}
	}

	private void ActionPerformed(java.awt.event.ActionEvent evt) {

	}

	/**
	 * @param args the command line arguments
	 */

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

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Example().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private java.awt.Checkbox checkbox1;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenu jMenu5;
	private javax.swing.JMenu jMenu6;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuBar jMenuBar2;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem10;
	private javax.swing.JMenuItem jMenuItem11;
	private javax.swing.JMenuItem jMenuItem12;
	private javax.swing.JMenuItem jMenuItem13;
	private javax.swing.JMenuItem jMenuItem14;
	private javax.swing.JMenuItem jMenuItem15;
	private javax.swing.JMenuItem jMenuItem16;
	private javax.swing.JMenuItem jMenuItem17;
	private javax.swing.JMenuItem jMenuItem18;
	private javax.swing.JMenuItem jMenuItem19;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem20;
	private javax.swing.JMenuItem jMenuItem21;
	private javax.swing.JMenuItem jMenuItem22;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItem4;
	private javax.swing.JMenuItem jMenuItem5;
	private javax.swing.JMenuItem jMenuItem6;
	private javax.swing.JMenuItem jMenuItem7;
	private javax.swing.JMenuItem jMenuItem8;
	private javax.swing.JMenuItem jMenuItem9;
	private java.awt.List list1;
	private java.awt.TextArea textArea1;
	// End of variables declaration//GEN-END:variables

}
