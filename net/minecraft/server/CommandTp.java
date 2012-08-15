package net.minecraft.server;

import java.util.List;

public class CommandTp extends CommandAbstract
{
	public String b()
	{
		return "tp";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.tp.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if (paramArrayOfString.length >= 1) {
			MinecraftServer localMinecraftServer = MinecraftServer.getServer();
			EntityPlayer localEntityPlayer1;
			if ((paramArrayOfString.length == 2) || (paramArrayOfString.length == 4)) {
				localEntityPlayer1 = localMinecraftServer.getServerConfigurationManager().f(paramArrayOfString[0]);
				if (localEntityPlayer1 == null) throw new ExceptionPlayerNotFound(); 
			}
			else {
				localEntityPlayer1 = (EntityPlayer)c(paramICommandListener);
			}

			if ((paramArrayOfString.length == 3) || (paramArrayOfString.length == 4)) {
				if (localEntityPlayer1.world != null) {
					int i = paramArrayOfString.length - 3;
					int j = 30000000;
					int k = a(paramICommandListener, paramArrayOfString[(i++)], -j, j);
					int m = a(paramICommandListener, paramArrayOfString[(i++)], 0, 256);
					int n = a(paramICommandListener, paramArrayOfString[(i++)], -j, j);

					localEntityPlayer1.enderTeleportTo(k + 0.5F, m, n + 0.5F);
					a(paramICommandListener, "commands.tp.coordinates", new Object[] { localEntityPlayer1.getLocalizedName(), Integer.valueOf(k), Integer.valueOf(m), Integer.valueOf(n) });
				}
			} else if ((paramArrayOfString.length == 1) || (paramArrayOfString.length == 2)) {
				EntityPlayer localEntityPlayer2 = localMinecraftServer.getServerConfigurationManager().f(paramArrayOfString[(paramArrayOfString.length - 1)]);
				if (localEntityPlayer2 == null) throw new ExceptionPlayerNotFound();

				localEntityPlayer1.netServerHandler.a(localEntityPlayer2.locX, localEntityPlayer2.locY, localEntityPlayer2.locZ, localEntityPlayer2.yaw, localEntityPlayer2.pitch);
				a(paramICommandListener, "commands.tp.success", new Object[] { localEntityPlayer1.getLocalizedName(), localEntityPlayer2.getLocalizedName() });
			}

			return;
		}

		throw new ExceptionUsage("commands.tp.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if ((paramArrayOfString.length == 1) || (paramArrayOfString.length == 2)) {
			return a(paramArrayOfString, MinecraftServer.getServer().getPlayers());
		}

		return null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandTp
 * JD-Core Version:		0.6.0
 */