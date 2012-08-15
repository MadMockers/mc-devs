package net.minecraft.server;

public class CommandSaveOn extends CommandAbstract
{
	public String b()
	{
		return "save-on";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		MinecraftServer localMinecraftServer = MinecraftServer.getServer();

		for (int i = 0; i < localMinecraftServer.worldServer.length; i++) {
			if (localMinecraftServer.worldServer[i] != null) {
				WorldServer localWorldServer = localMinecraftServer.worldServer[i];
				localWorldServer.savingDisabled = false;
			}
		}

		a(paramICommandListener, "commands.save.enabled", new Object[0]);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandSaveOn
 * JD-Core Version:		0.6.0
 */