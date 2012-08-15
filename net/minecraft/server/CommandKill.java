package net.minecraft.server;

public class CommandKill extends CommandAbstract
{
	public String b()
	{
		return "kill";
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		EntityHuman localEntityHuman = c(paramICommandListener);

		localEntityHuman.damageEntity(DamageSource.OUT_OF_WORLD, 1000);

		paramICommandListener.sendMessage("Ouch. That look like it hurt.");
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandKill
 * JD-Core Version:		0.6.0
 */