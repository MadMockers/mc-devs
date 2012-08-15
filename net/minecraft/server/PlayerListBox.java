package net.minecraft.server;

import java.util.List;
import java.util.Vector;
import javax.swing.JList;

public class PlayerListBox extends JList
	implements IUpdatePlayerListBox
{
	private MinecraftServer a;
	private int b = 0;

	public PlayerListBox(MinecraftServer paramMinecraftServer) {
		this.a = paramMinecraftServer;
		paramMinecraftServer.a(this);
	}

	public void a() {
		if (this.b++ % 20 == 0) {
			Vector localVector = new Vector();
			for (int i = 0; i < this.a.getServerConfigurationManager().players.size(); i++) {
				localVector.add(((EntityPlayer)this.a.getServerConfigurationManager().players.get(i)).name);
			}
			setListData(localVector);
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PlayerListBox
 * JD-Core Version:		0.6.0
 */