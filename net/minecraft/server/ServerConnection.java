package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ServerConnection
{
	public static Logger a = Logger.getLogger("Minecraft");
	private final MinecraftServer c;
	private final List d = Collections.synchronizedList(new ArrayList());
	public volatile boolean b = false;

	public ServerConnection(MinecraftServer paramMinecraftServer) {
		this.c = paramMinecraftServer;
		this.b = true;
	}

	public void a(NetServerHandler paramNetServerHandler) {
		this.d.add(paramNetServerHandler);
	}

	public void a() {
		this.b = false;
	}

	public void b() {
		for (int i = 0; i < this.d.size(); i++) {
			NetServerHandler localNetServerHandler = (NetServerHandler)this.d.get(i);
			try {
				localNetServerHandler.d();
			} catch (Exception localException) {
				a.log(Level.WARNING, "Failed to handle packet: " + localException, localException);
				localNetServerHandler.disconnect("Internal server error");
			}
			if (localNetServerHandler.disconnected) {
				this.d.remove(i--);
			}
			localNetServerHandler.networkManager.a();
		}
	}

	public MinecraftServer d() {
		return this.c;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ServerConnection
 * JD-Core Version:		0.6.0
 */