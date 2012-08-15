package net.minecraft.server;

import java.util.List;

public class CommandDeop extends CommandAbstract
{
	public String b()
	{
		return "deop";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.deop.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length == 1) && (paramArrayOfString[0].length() > 0)) {
			MinecraftServer.getServer().getServerConfigurationManager().removeOp(paramArrayOfString[0]);

			a(paramICommandListener, "commands.deop.success", new Object[] { paramArrayOfString[0] });
			return;
		}

		throw new ExceptionUsage("commands.deop.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1) {
			return a(paramArrayOfString, MinecraftServer.getServer().getServerConfigurationManager().getOPs());
		}

		return null;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandDeop
 * JD-Core Version:		0.6.0
 */