package net.minecraft.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ServerGUI extends JComponent
{
	public static Logger a = Logger.getLogger("Minecraft");
	private static boolean b = false;
	private DedicatedServer c;

	public static void a(DedicatedServer paramDedicatedServer)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception localException) {
		}
		ServerGUI localServerGUI = new ServerGUI(paramDedicatedServer);
		b = true;
		JFrame localJFrame = new JFrame("Minecraft server");
		localJFrame.add(localServerGUI);
		localJFrame.pack();
		localJFrame.setLocationRelativeTo(null);
		localJFrame.setVisible(true);
		localJFrame.addWindowListener(new ServerWindowAdapter(paramDedicatedServer));
	}

	public ServerGUI(DedicatedServer paramDedicatedServer)
	{
		this.c = paramDedicatedServer;
		setPreferredSize(new Dimension(854, 480));

		setLayout(new BorderLayout());
		try {
			add(d(), "Center");
			add(b(), "West");
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	private JComponent b() {
		JPanel localJPanel = new JPanel(new BorderLayout());
		localJPanel.add(new GuiStatsComponent(this.c), "North");
		localJPanel.add(c(), "Center");
		localJPanel.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
		return localJPanel;
	}

	private JComponent c() {
		PlayerListBox localPlayerListBox = new PlayerListBox(this.c);
		JScrollPane localJScrollPane = new JScrollPane(localPlayerListBox, 22, 30);
		localJScrollPane.setBorder(new TitledBorder(new EtchedBorder(), "Players"));

		return localJScrollPane;
	}

	private JComponent d() {
		JPanel localJPanel = new JPanel(new BorderLayout());
		JTextArea localJTextArea = new JTextArea();
		a.addHandler(new GuiLogOutputHandler(localJTextArea));
		JScrollPane localJScrollPane = new JScrollPane(localJTextArea, 22, 30);
		localJTextArea.setEditable(false);

		JTextField localJTextField = new JTextField();
		localJTextField.addActionListener(new ServerGuiCommandListener(this, localJTextField));

		localJTextArea.addFocusListener(new ServerGuiFocusAdapter(this));

		localJPanel.add(localJScrollPane, "Center");
		localJPanel.add(localJTextField, "South");
		localJPanel.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));

		return localJPanel;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ServerGUI
 * JD-Core Version:		0.6.0
 */