package net.minecraft.server;

public class CommandToggleDownfall extends CommandAbstract
{
	public String b()
	{
		return "toggledownfall";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		c();
		a(paramICommandListener, "commands.downfall.success", new Object[0]);
	}

	protected void c() {
		MinecraftServer.getServer().worldServer[0].w();
		MinecraftServer.getServer().worldServer[0].getWorldData().setThundering(true);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandToggleDownfall
 * JD-Core Version:		0.6.0
 */