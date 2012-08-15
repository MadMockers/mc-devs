package net.minecraft.server;

public class CommandSeed extends CommandAbstract
{
	public String b()
	{
		return "seed";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		EntityHuman localEntityHuman = c(paramICommandListener);
		paramICommandListener.sendMessage("Seed: " + localEntityHuman.world.getSeed());
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandSeed
 * JD-Core Version:		0.6.0
 */