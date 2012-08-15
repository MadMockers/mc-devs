package net.minecraft.server;

import java.util.List;

public class CommandKick extends CommandAbstract
{
	public String b()
	{
		return "kick";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.kick.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].length() > 1)) {
			EntityPlayer localEntityPlayer = MinecraftServer.getServer().getServerConfigurationManager().f(paramArrayOfString[0]);
			String str = "Kicked by an operator.";
			int i = 0;

			if (localEntityPlayer == null) {
				throw new ExceptionPlayerNotFound();
			}

			if (paramArrayOfString.length >= 2) {
				str = a(paramArrayOfString, 1);
				i = 1;
			}

			localEntityPlayer.netServerHandler.disconnect(str);

			if (i != 0)
				a(paramICommandListener, "commands.kick.success.reason", new Object[] { localEntityPlayer.getLocalizedName(), str });
			else {
				a(paramICommandListener, "commands.kick.success", new Object[] { localEntityPlayer.getLocalizedName() });
			}

			return;
		}

		throw new ExceptionUsage("commands.kick.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length >= 1) {
			return a(paramArrayOfString, MinecraftServer.getServer().getPlayers());
		}

		return null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandKick
 * JD-Core Version:		0.6.0
 */