package net.minecraft.server;

import java.util.List;

public class CommandGive extends CommandAbstract
{
	public String b()
	{
		return "give";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.give.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if (paramArrayOfString.length >= 2) {
			EntityHuman localEntityHuman = a(paramArrayOfString[0]);

			int i = a(paramICommandListener, paramArrayOfString[1], 1);
			int j = 1;
			int k = 0;

			if (Item.byId[i] == null) {
				throw new ExceptionInvalidNumber("commands.give.notFound", new Object[] { Integer.valueOf(i) });
			}

			if (paramArrayOfString.length >= 3) {
				j = a(paramICommandListener, paramArrayOfString[2], 1, 64);
			}

			if (paramArrayOfString.length >= 4) {
				k = a(paramICommandListener, paramArrayOfString[3]);
			}

			ItemStack localItemStack = new ItemStack(i, j, k);

			localEntityHuman.drop(localItemStack);

			a(paramICommandListener, "commands.give.success", new Object[] { Item.byId[i].i(localItemStack), Integer.valueOf(i), Integer.valueOf(j), localEntityHuman.getLocalizedName() });
			return;
		}

		throw new ExceptionUsage("commands.give.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1) {
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
 * Qualified Name:		 net.minecraft.server.CommandGive
 * JD-Core Version:		0.6.0
 */