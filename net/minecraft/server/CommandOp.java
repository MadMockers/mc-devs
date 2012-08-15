package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class CommandOp extends CommandAbstract
{
	public String b()
	{
		return "op";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.op.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length == 1) && (paramArrayOfString[0].length() > 0)) {
			MinecraftServer.getServer().getServerConfigurationManager().addOp(paramArrayOfString[0]);

			a(paramICommandListener, "commands.op.success", new Object[] { paramArrayOfString[0] });
			return;
		}

		throw new ExceptionUsage("commands.op.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1) {
			String str1 = paramArrayOfString[(paramArrayOfString.length - 1)];
			ArrayList localArrayList = new ArrayList();

			for (String str2 : MinecraftServer.getServer().getPlayers()) {
				if ((!MinecraftServer.getServer().getServerConfigurationManager().isOp(str2)) && (a(str1, str2))) {
					localArrayList.add(str2);
				}
			}

			return localArrayList;
		}

		return null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandOp
 * JD-Core Version:		0.6.0
 */