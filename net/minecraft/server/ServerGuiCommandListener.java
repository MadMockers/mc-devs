package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

class ServerGuiCommandListener
	implements ActionListener
{
	ServerGuiCommandListener(ServerGUI paramServerGUI, JTextField paramJTextField)
	{
	}

	public void actionPerformed(ActionEvent paramActionEvent)
	{
		String str = this.a.getText().trim();
		if (str.length() > 0) {
			ServerGUI.a(this.b).issueCommand(str, MinecraftServer.getServer());
		}
		this.a.setText("");
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ServerGuiCommandListener
 * JD-Core Version:		0.6.0
 */