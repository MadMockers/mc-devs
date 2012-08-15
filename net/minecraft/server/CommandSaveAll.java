package net.minecraft.server;

public class CommandSaveAll extends CommandAbstract
{
	public String b()
	{
		return "save-all";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		MinecraftServer localMinecraftServer = MinecraftServer.getServer();

		paramICommandListener.sendMessage(paramICommandListener.a("commands.save.start", new Object[0]));

		if (localMinecraftServer.getServerConfigurationManager() != null) {
			localMinecraftServer.getServerConfigurationManager().savePlayers();
		}
		try
		{
			for (int i = 0; i < localMinecraftServer.worldServer.length; i++)
				if (localMinecraftServer.worldServer[i] != null) {
					WorldServer localWorldServer = localMinecraftServer.worldServer[i];
					boolean bool = localWorldServer.savingDisabled;
					localWorldServer.savingDisabled = false;
					localWorldServer.save(true, null);
					localWorldServer.savingDisabled = bool;
				}
		}
		catch (ExceptionWorldConflict localExceptionWorldConflict) {
			a(paramICommandListener, "commands.save.failed", new Object[] { localExceptionWorldConflict.getMessage() });
			return;
		}

		a(paramICommandListener, "commands.save.success", new Object[0]);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandSaveAll
 * JD-Core Version:		0.6.0
 */