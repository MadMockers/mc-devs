package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CommandHandler
	implements ICommandHandler
{
	private final Map a = new HashMap();
	private final Set b = new HashSet();

	public void a(ICommandListener paramICommandListener, String paramString) {
		if (paramString.startsWith("/")) paramString = paramString.substring(1);

		String[] arrayOfString = paramString.split(" ");
		String str = arrayOfString[0];

		arrayOfString = a(arrayOfString);

		ICommand localICommand = (ICommand)this.a.get(str);
		try
		{
			if (localICommand == null) {
				throw new ExceptionUnknownCommand();
			}
			if (localICommand.b(paramICommandListener))
				localICommand.b(paramICommandListener, arrayOfString);
			else
				paramICommandListener.sendMessage("§cYou do not have permission to use this command.");
		}
		catch (ExceptionUsage localExceptionUsage)
		{
			paramICommandListener.sendMessage("§c" + paramICommandListener.a("commands.generic.usage", new Object[] { paramICommandListener.a(localExceptionUsage.getMessage(), localExceptionUsage.a()) }));
		} catch (CommandException localCommandException) {
			paramICommandListener.sendMessage("§c" + paramICommandListener.a(localCommandException.getMessage(), localCommandException.a()));
		} catch (Throwable localThrowable) {
			paramICommandListener.sendMessage("§c" + paramICommandListener.a("commands.generic.exception", new Object[0]));
			localThrowable.printStackTrace();
		}
	}

	public ICommand a(ICommand paramICommand) {
		List localList = paramICommand.a();

		this.a.put(paramICommand.b(), paramICommand);
		this.b.add(paramICommand);

		if (localList != null) {
			for (String str : localList) {
				ICommand localICommand = (ICommand)this.a.get(str);

				if ((localICommand == null) || (!localICommand.b().equals(str))) {
					this.a.put(str, paramICommand);
				}
			}
		}

		return paramICommand;
	}

	private static String[] a(String[] paramArrayOfString) {
		String[] arrayOfString = new String[paramArrayOfString.length - 1];

		for (int i = 1; i < paramArrayOfString.length; i++) {
			arrayOfString[(i - 1)] = paramArrayOfString[i];
		}

		return arrayOfString;
	}

	public List b(ICommandListener paramICommandListener, String paramString) {
		String[] arrayOfString = paramString.split(" ", -1);
		String str = arrayOfString[0];
		Object localObject;
		if (arrayOfString.length == 1)
		{
			localObject = new ArrayList();

			for (Map.Entry localEntry : this.a.entrySet()) {
				if ((CommandAbstract.a(str, (String)localEntry.getKey())) && (((ICommand)localEntry.getValue()).b(paramICommandListener))) {
					((List)localObject).add(localEntry.getKey());
				}
			}

			return localObject;
		}if (arrayOfString.length > 1)
		{
			localObject = (ICommand)this.a.get(str);

			if (localObject != null) {
				return ((ICommand)localObject).a(paramICommandListener, a(arrayOfString));
			}
		}

		return (List)null;
	}

	public List a(ICommandListener paramICommandListener) {
		ArrayList localArrayList = new ArrayList();

		for (ICommand localICommand : this.b) {
			if (localICommand.b(paramICommandListener)) {
				localArrayList.add(localICommand);
			}
		}

		return localArrayList;
	}

	public Map a() {
		return this.a;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandHandler
 * JD-Core Version:		0.6.0
 */