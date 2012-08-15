package net.minecraft.server;

import java.util.List;

public class CommandTime extends CommandAbstract
{
	public String b()
	{
		return "time";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.time.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if (paramArrayOfString.length > 1)
		{
			int i;
			if (paramArrayOfString[0].equals("set"))
			{
				if (paramArrayOfString[1].equals("day"))
					i = 0;
				else if (paramArrayOfString[1].equals("night"))
					i = 12500;
				else {
					i = a(paramICommandListener, paramArrayOfString[1], 0);
				}

				a(paramICommandListener, i);
				a(paramICommandListener, "commands.time.set", new Object[] { Integer.valueOf(i) });
				return;
			}if (paramArrayOfString[0].equals("add")) {
				i = a(paramICommandListener, paramArrayOfString[1], 0);
				b(paramICommandListener, i);

				a(paramICommandListener, "commands.time.added", new Object[] { Integer.valueOf(i) });
				return;
			}
		}

		throw new ExceptionUsage("commands.time.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1)
			return a(paramArrayOfString, new String[] { "set", "add" });
		if ((paramArrayOfString.length == 2) && (paramArrayOfString[0].equals("set"))) {
			return a(paramArrayOfString, new String[] { "day", "night" });
		}

		return null;
	}

	protected void a(ICommandListener paramICommandListener, int paramInt) {
		for (int i = 0; i < MinecraftServer.getServer().worldServer.length; i++)
			MinecraftServer.getServer().worldServer[i].setTimeAndFixTicklists(paramInt);
	}

	protected void b(ICommandListener paramICommandListener, int paramInt)
	{
		for (int i = 0; i < MinecraftServer.getServer().worldServer.length; i++) {
			WorldServer localWorldServer = MinecraftServer.getServer().worldServer[i];
			localWorldServer.setTimeAndFixTicklists(localWorldServer.getTime() + paramInt);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandTime
 * JD-Core Version:		0.6.0
 */