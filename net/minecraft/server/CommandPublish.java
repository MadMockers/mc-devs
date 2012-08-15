package net.minecraft.server;

public class CommandPublish extends CommandAbstract
{
	public String b()
	{
		return "publish";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		String str = MinecraftServer.getServer().a(EnumGamemode.SURVIVAL, false);

		if (str != null)
			a(paramICommandListener, "commands.publish.started", new Object[] { str });
		else
			a(paramICommandListener, "commands.publish.failed", new Object[0]);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandPublish
 * JD-Core Version:		0.6.0
 */