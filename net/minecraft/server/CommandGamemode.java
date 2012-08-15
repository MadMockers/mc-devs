package net.minecraft.server;

import java.util.List;

public class CommandGamemode extends CommandAbstract
{
	public String b()
	{
		return "gamemode";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.gamemode.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if (paramArrayOfString.length > 0) {
			EnumGamemode localEnumGamemode = b(paramICommandListener, paramArrayOfString[0]);
			EntityHuman localEntityHuman = paramArrayOfString.length >= 2 ? a(paramArrayOfString[1]) : c(paramICommandListener);

			localEntityHuman.a(localEnumGamemode);

			String str = LocaleI18n.get("gameMode." + localEnumGamemode.b());

			if (localEntityHuman != paramICommandListener)
				a(paramICommandListener, 1, "commands.gamemode.success.other", new Object[] { localEntityHuman.getLocalizedName(), str });
			else {
				a(paramICommandListener, 1, "commands.gamemode.success.self", new Object[] { str });
			}

			return;
		}

		throw new ExceptionUsage("commands.gamemode.usage", new Object[0]);
	}

	protected EnumGamemode b(ICommandListener paramICommandListener, String paramString) {
		if ((paramString.equalsIgnoreCase(EnumGamemode.SURVIVAL.b())) || (paramString.equalsIgnoreCase("s")))
			return EnumGamemode.SURVIVAL;
		if ((paramString.equalsIgnoreCase(EnumGamemode.CREATIVE.b())) || (paramString.equalsIgnoreCase("c")))
			return EnumGamemode.CREATIVE;
		if ((paramString.equalsIgnoreCase(EnumGamemode.ADVENTURE.b())) || (paramString.equalsIgnoreCase("a"))) {
			return EnumGamemode.ADVENTURE;
		}
		return WorldSettings.a(a(paramICommandListener, paramString, 0, EnumGamemode.values().length - 2));
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1)
			return a(paramArrayOfString, new String[] { "survival", "creative", "adventure" });
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
 * Qualified Name:		 net.minecraft.server.CommandGamemode
 * JD-Core Version:		0.6.0
 */