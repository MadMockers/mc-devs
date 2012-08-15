package net.minecraft.server;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandBanIp extends CommandAbstract
{
	public static final Pattern a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	public String b()
	{
		return "ban-ip";
	}

	public boolean b(ICommandListener paramICommandListener)
	{
		return (MinecraftServer.getServer().getServerConfigurationManager().getIPBans().isEnabled()) && (super.b(paramICommandListener));
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.banip.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length >= 1) && (paramArrayOfString[0].length() > 1)) {
			Matcher localMatcher = a.matcher(paramArrayOfString[0]);
			String str = null;

			if (paramArrayOfString.length >= 2) {
				str = a(paramArrayOfString, 1);
			}

			if (localMatcher.matches()) {
				a(paramICommandListener, paramArrayOfString[0], str);
			} else {
				EntityPlayer localEntityPlayer = MinecraftServer.getServer().getServerConfigurationManager().f(paramArrayOfString[0]);

				if (localEntityPlayer == null) {
					throw new ExceptionPlayerNotFound("commands.banip.invalid", new Object[0]);
				}

				a(paramICommandListener, localEntityPlayer.r(), str);
			}

			return;
		}

		throw new ExceptionUsage("commands.banip.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1) {
			return a(paramArrayOfString, MinecraftServer.getServer().getPlayers());
		}

		return null;
	}

	protected void a(ICommandListener paramICommandListener, String paramString1, String paramString2) {
		BanEntry localBanEntry = new BanEntry(paramString1);

		localBanEntry.setSource(paramICommandListener.getName());
		if (paramString2 != null) localBanEntry.setReason(paramString2);

		MinecraftServer.getServer().getServerConfigurationManager().getIPBans().add(localBanEntry);

		List localList = MinecraftServer.getServer().getServerConfigurationManager().j(paramString1);
		String[] arrayOfString = new String[localList.size()];
		int i = 0;

		for (EntityPlayer localEntityPlayer : localList) {
			localEntityPlayer.netServerHandler.disconnect("You have been IP banned.");
			arrayOfString[(i++)] = localEntityPlayer.getLocalizedName();
		}

		if (localList.isEmpty())
			a(paramICommandListener, "commands.banip.success", new Object[] { paramString1 });
		else
			a(paramICommandListener, "commands.banip.success.players", new Object[] { paramString1, a(arrayOfString) });
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandBanIp
 * JD-Core Version:		0.6.0
 */