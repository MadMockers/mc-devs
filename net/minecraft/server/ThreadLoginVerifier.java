package net.minecraft.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyPair;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent.Result;
import org.bukkit.plugin.PluginManager;

class ThreadLoginVerifier extends Thread
{
	final NetLoginHandler netLoginHandler;
	CraftServer server;

	ThreadLoginVerifier(NetLoginHandler netloginhandler, CraftServer server)
	{
		this.server = server;

		this.netLoginHandler = netloginhandler;
	}

	public void run() {
		try {
			String s = new BigInteger(MinecraftEncryption.a(NetLoginHandler.a(this.netLoginHandler), NetLoginHandler.b(this.netLoginHandler).E().getPublic(), NetLoginHandler.c(this.netLoginHandler))).toString(16);
			URL url = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(NetLoginHandler.d(this.netLoginHandler), "UTF-8") + "&serverId=" + URLEncoder.encode(s, "UTF-8"));
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
			String s1 = bufferedreader.readLine();

			bufferedreader.close();
			if (!"YES".equals(s1)) {
				this.netLoginHandler.disconnect("Failed to verify username!");
				return;
			}

			if (this.netLoginHandler.getSocket() == null) {
				return;
			}

			AsyncPlayerPreLoginEvent asyncEvent = new AsyncPlayerPreLoginEvent(NetLoginHandler.d(this.netLoginHandler), this.netLoginHandler.getSocket().getInetAddress());
			this.server.getPluginManager().callEvent(asyncEvent);

			PlayerPreLoginEvent event = new PlayerPreLoginEvent(NetLoginHandler.d(this.netLoginHandler), this.netLoginHandler.getSocket().getInetAddress());
			if (asyncEvent.getResult() != PlayerPreLoginEvent.Result.ALLOWED) {
				event.disallow(asyncEvent.getResult(), asyncEvent.getKickMessage());
			}
			this.server.getPluginManager().callEvent(event);

			if (event.getResult() != PlayerPreLoginEvent.Result.ALLOWED) {
				this.netLoginHandler.disconnect(event.getKickMessage());
				return;
			}

			NetLoginHandler.a(this.netLoginHandler, true);
		} catch (Exception exception) {
			this.netLoginHandler.disconnect("Failed to verify username! [internal error " + exception + "]");
			exception.printStackTrace();
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ThreadLoginVerifier
 * JD-Core Version:		0.6.0
 */