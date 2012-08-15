package net.minecraft.server;

public class CommandList extends CommandAbstract
{
	public String b()
	{
		return "list";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		paramICommandListener.sendMessage(paramICommandListener.a("commands.players.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().x()), Integer.valueOf(MinecraftServer.getServer().y()) }));
		paramICommandListener.sendMessage(MinecraftServer.getServer().getServerConfigurationManager().c());
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandList
 * JD-Core Version:		0.6.0
 */