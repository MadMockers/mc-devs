package net.minecraft.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandHelp extends CommandAbstract
{
	public String b()
	{
		return "help";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.help.usage", new Object[0]);
	}

	public List a()
	{
		return Arrays.asList(new String[] { "?" }); } 
	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) { List localList = d(paramICommandListener);
		int i = 7;
		int j = localList.size() / i;
		int k = 0;
		ICommand localICommand;
		try { k = paramArrayOfString.length == 0 ? 0 : a(paramICommandListener, paramArrayOfString[0], 1, j + 1) - 1;
		} catch (ExceptionInvalidNumber localExceptionInvalidNumber)
		{
			Map localMap = c();
			localICommand = (ICommand)localMap.get(paramArrayOfString[0]);

			if (localICommand != null)
			{
				throw new ExceptionUsage(localICommand.a(paramICommandListener), new Object[0]);
			}
			throw new ExceptionUnknownCommand();
		}

		int m = Math.min((k + 1) * i, localList.size());

		paramICommandListener.sendMessage("§2" + paramICommandListener.a("commands.help.header", new Object[] { Integer.valueOf(k + 1), Integer.valueOf(j + 1) }));

		for (int n = k * i; n < m; n++) {
			localICommand = (ICommand)localList.get(n);

			paramICommandListener.sendMessage(localICommand.a(paramICommandListener));
		}

		if (k == 0)
			paramICommandListener.sendMessage("§a" + paramICommandListener.a("commands.help.footer", new Object[0]));
	}

	protected List d(ICommandListener paramICommandListener)
	{
		List localList = MinecraftServer.getServer().getCommandHandler().a(paramICommandListener);
		Collections.sort(localList);
		return localList;
	}

	protected Map c() {
		return MinecraftServer.getServer().getCommandHandler().a();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandHelp
 * JD-Core Version:		0.6.0
 */