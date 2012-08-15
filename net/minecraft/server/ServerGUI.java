/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.awt.BorderLayout;
/*		 */ import java.awt.Dimension;
/*		 */ import java.util.logging.Logger;
/*		 */ import javax.swing.JComponent;
/*		 */ import javax.swing.JFrame;
/*		 */ import javax.swing.JPanel;
/*		 */ import javax.swing.JScrollPane;
/*		 */ import javax.swing.JTextArea;
/*		 */ import javax.swing.JTextField;
/*		 */ import javax.swing.UIManager;
/*		 */ import javax.swing.border.EtchedBorder;
/*		 */ import javax.swing.border.TitledBorder;
/*		 */ 
/*		 */ public class ServerGUI extends JComponent
/*		 */ {
/*	16 */	 public static Logger a = Logger.getLogger("Minecraft");
/*	17 */	 private static boolean b = false;
/*		 */	 private DedicatedServer c;
/*		 */ 
/*		 */	 public static void a(DedicatedServer paramDedicatedServer)
/*		 */	 {
/*		 */		 try
/*		 */		 {
/*	24 */			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*		 */		 }
/*		 */		 catch (Exception localException) {
/*		 */		 }
/*	28 */		 ServerGUI localServerGUI = new ServerGUI(paramDedicatedServer);
/*	29 */		 b = true;
/*	30 */		 JFrame localJFrame = new JFrame("Minecraft server");
/*	31 */		 localJFrame.add(localServerGUI);
/*	32 */		 localJFrame.pack();
/*	33 */		 localJFrame.setLocationRelativeTo(null);
/*	34 */		 localJFrame.setVisible(true);
/*	35 */		 localJFrame.addWindowListener(new ServerWindowAdapter(paramDedicatedServer));
/*		 */	 }
/*		 */ 
/*		 */	 public ServerGUI(DedicatedServer paramDedicatedServer)
/*		 */	 {
/*	52 */		 this.c = paramDedicatedServer;
/*	53 */		 setPreferredSize(new Dimension(854, 480));
/*		 */ 
/*	55 */		 setLayout(new BorderLayout());
/*		 */		 try {
/*	57 */			 add(d(), "Center");
/*	58 */			 add(b(), "West");
/*		 */		 } catch (Exception localException) {
/*	60 */			 localException.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private JComponent b() {
/*	65 */		 JPanel localJPanel = new JPanel(new BorderLayout());
/*	66 */		 localJPanel.add(new GuiStatsComponent(this.c), "North");
/*	67 */		 localJPanel.add(c(), "Center");
/*	68 */		 localJPanel.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
/*	69 */		 return localJPanel;
/*		 */	 }
/*		 */ 
/*		 */	 private JComponent c() {
/*	73 */		 PlayerListBox localPlayerListBox = new PlayerListBox(this.c);
/*	74 */		 JScrollPane localJScrollPane = new JScrollPane(localPlayerListBox, 22, 30);
/*	75 */		 localJScrollPane.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
/*		 */ 
/*	77 */		 return localJScrollPane;
/*		 */	 }
/*		 */ 
/*		 */	 private JComponent d() {
/*	81 */		 JPanel localJPanel = new JPanel(new BorderLayout());
/*	82 */		 JTextArea localJTextArea = new JTextArea();
/*	83 */		 a.addHandler(new GuiLogOutputHandler(localJTextArea));
/*	84 */		 JScrollPane localJScrollPane = new JScrollPane(localJTextArea, 22, 30);
/*	85 */		 localJTextArea.setEditable(false);
/*		 */ 
/*	87 */		 JTextField localJTextField = new JTextField();
/*	88 */		 localJTextField.addActionListener(new ServerGuiCommandListener(this, localJTextField));
/*		 */ 
/*	98 */		 localJTextArea.addFocusListener(new ServerGuiFocusAdapter(this));
/*		 */ 
/* 104 */		 localJPanel.add(localJScrollPane, "Center");
/* 105 */		 localJPanel.add(localJTextField, "South");
/* 106 */		 localJPanel.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
/*		 */ 
/* 108 */		 return localJPanel;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ServerGUI
 * JD-Core Version:		0.6.0
 */