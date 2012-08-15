package net.minecraft.server;

public class CommandGamemodeDefault extends CommandGamemode
{
	public String b()
	{
		return "defaultgamemode";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.defaultgamemode.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if (paramArrayOfString.length > 0) {
			EnumGamemode localEnumGamemode = b(paramICommandListener, paramArrayOfString[0]);
			a(localEnumGamemode);

			String str = LocaleI18n.get("gameMode." + localEnumGamemode.b());
			a(paramICommandListener, "commands.defaultgamemode.success", new Object[] { str });

			return;
		}

		throw new ExceptionUsage("commands.defaultgamemode.usage", new Object[0]);
	}

	protected void a(EnumGamemode paramEnumGamemode) {
		MinecraftServer.getServer().a(paramEnumGamemode);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandGamemodeDefault
 * JD-Core Version:		0.6.0
 */