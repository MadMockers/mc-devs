package net.minecraft.server;

public class CommandStop extends CommandAbstract
{
	public String b()
	{
		return "stop";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		a(paramICommandListener, "commands.stop.start", new Object[0]);

		MinecraftServer.getServer().safeShutdown();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandStop
 * JD-Core Version:		0.6.0
 */