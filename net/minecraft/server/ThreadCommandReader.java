package net.minecraft.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.console.ConsoleReader;
import org.bukkit.craftbukkit.Main;

class ThreadCommandReader extends Thread
{
	final DedicatedServer server;

	ThreadCommandReader(DedicatedServer dedicatedserver)
	{
		this.server = dedicatedserver;
	}

	public void run()
	{
		if (!Main.useConsole) {
			return;
		}

		ConsoleReader bufferedreader = this.server.reader;
		try
		{
			while ((!this.server.isStopped()) && (this.server.isRunning()))
			{
				String s;
				String s;
				if (Main.useJline)
					s = bufferedreader.readLine(">", null);
				else {
					s = bufferedreader.readLine();
				}
				if (s != null) {
					this.server.issueCommand(s, this.server);
				}
			}
		}
		catch (IOException ioexception)
		{
			MinecraftServer.log.log(Level.SEVERE, null, ioexception);
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ThreadCommandReader
 * JD-Core Version:		0.6.0
 */