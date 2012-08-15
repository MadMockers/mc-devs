package net.minecraft.server;

public class CommandSaveOff extends CommandAbstract
{
	public String b()
	{
		return "save-off";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		MinecraftServer localMinecraftServer = MinecraftServer.getServer();

		for (int i = 0; i < localMinecraftServer.worldServer.length; i++) {
			if (localMinecraftServer.worldServer[i] != null) {
				WorldServer localWorldServer = localMinecraftServer.worldServer[i];
				localWorldServer.savingDisabled = true;
			}
		}

		a(paramICommandListener, "commands.save.disabled", new Object[0]);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandSaveOff
 * JD-Core Version:		0.6.0
 */