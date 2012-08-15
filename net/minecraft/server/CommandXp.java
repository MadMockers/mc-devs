package net.minecraft.server;

import java.util.List;

public class CommandXp extends CommandAbstract
{
	public String b()
	{
		return "xp";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.xp.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if (paramArrayOfString.length > 0)
		{
			int i = a(paramICommandListener, paramArrayOfString[0], 0, 5000);
			EntityHuman localEntityHuman;
			if (paramArrayOfString.length > 1)
				localEntityHuman = a(paramArrayOfString[1]);
			else {
				localEntityHuman = c(paramICommandListener);
			}

			localEntityHuman.giveExp(i);
			a(paramICommandListener, "commands.xp.success", new Object[] { Integer.valueOf(i), localEntityHuman.getLocalizedName() });
			return;
		}

		throw new ExceptionUsage("commands.xp.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 2) {
			return a(paramArrayOfString, c());
		}

		return null;
	}

	protected EntityHuman a(String paramString) {
		EntityPlayer localEntityPlayer = MinecraftServer.getServer().getServerConfigurationManager().f(paramString);

		if (localEntityPlayer == null) {
			throw new ExceptionPlayerNotFound();
		}
		return localEntityPlayer;
	}

	protected String[] c()
	{
		return MinecraftServer.getServer().getPlayers();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandXp
 * JD-Core Version:		0.6.0
 */