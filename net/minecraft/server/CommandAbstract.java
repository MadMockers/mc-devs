package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandAbstract
	implements ICommand
{
	private static ICommandDispatcher a = null;

	public String a(ICommandListener paramICommandListener) {
		return "/" + b();
	}

	public List a() {
		return null;
	}

	public boolean b(ICommandListener paramICommandListener) {
		return paramICommandListener.b(b());
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		return null; } 
	public static int a(ICommandListener paramICommandListener, String paramString) { // Byte code:
		//	 0: aload_1
		//	 1: invokestatic 47	java/lang/Integer:parseInt	(Ljava/lang/String;)I
		//	 4: ireturn
		//	 5: astore_2
		//	 6: new 49	net/minecraft/server/ExceptionInvalidNumber
		//	 9: dup
		//	 10: ldc 51
		//	 12: iconst_1
		//	 13: anewarray 4	java/lang/Object
		//	 16: dup
		//	 17: iconst_0
		//	 18: aload_1
		//	 19: aastore
		//	 20: invokespecial 54	net/minecraft/server/ExceptionInvalidNumber:<init>	(Ljava/lang/String;[Ljava/lang/Object;)V
		//	 23: athrow
		//
		// Exception table:
		//	 from	to	target	type
		//	 0	4	5	java/lang/NumberFormatException } 
	public static int a(ICommandListener paramICommandListener, String paramString, int paramInt) { return a(paramICommandListener, paramString, paramInt, 2147483647); }

	public static int a(ICommandListener paramICommandListener, String paramString, int paramInt1, int paramInt2)
	{
		int i = a(paramICommandListener, paramString);

		if (i < paramInt1)
			throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt1) });
		if (i > paramInt2) {
			throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt2) });
		}

		return i;
	}

	public static EntityHuman c(ICommandListener paramICommandListener) {
		if ((paramICommandListener instanceof EntityHuman)) {
			return (EntityHuman)paramICommandListener;
		}
		throw new ExceptionPlayerNotFound("You must specify which player you wish to perform this action on.", new Object[0]);
	}

	public static String a(String[] paramArrayOfString, int paramInt)
	{
		StringBuilder localStringBuilder = new StringBuilder();

		for (int i = paramInt; i < paramArrayOfString.length; i++) {
			if (i > paramInt) localStringBuilder.append(" ");
			localStringBuilder.append(paramArrayOfString[i]);
		}

		return localStringBuilder.toString();
	}

	public static String a(Object[] paramArrayOfObject) {
		StringBuilder localStringBuilder = new StringBuilder();

		for (int i = 0; i < paramArrayOfObject.length; i++) {
			String str = paramArrayOfObject[i].toString();

			if (i > 0) {
				if (i == paramArrayOfObject.length - 1)
					localStringBuilder.append(" and ");
				else {
					localStringBuilder.append(", ");
				}
			}

			localStringBuilder.append(str);
		}

		return localStringBuilder.toString();
	}

	public static boolean a(String paramString1, String paramString2) {
		return paramString2.regionMatches(true, 0, paramString1, 0, paramString1.length());
	}

	public static List a(String[] paramArrayOfString1, String[] paramArrayOfString2) {
		String str1 = paramArrayOfString1[(paramArrayOfString1.length - 1)];
		ArrayList localArrayList = new ArrayList();

		for (String str2 : paramArrayOfString2) {
			if (a(str1, str2)) {
				localArrayList.add(str2);
			}
		}

		return localArrayList;
	}

	public static List a(String[] paramArrayOfString, Iterable paramIterable) {
		String str1 = paramArrayOfString[(paramArrayOfString.length - 1)];
		ArrayList localArrayList = new ArrayList();

		for (String str2 : paramIterable) {
			if (a(str1, str2)) {
				localArrayList.add(str2);
			}
		}

		return localArrayList;
	}

	public static void a(ICommandListener paramICommandListener, String paramString, Object[] paramArrayOfObject) {
		a(paramICommandListener, 0, paramString, paramArrayOfObject);
	}

	public static void a(ICommandListener paramICommandListener, int paramInt, String paramString, Object[] paramArrayOfObject) {
		if (a != null)
			a.a(paramICommandListener, paramInt, paramString, paramArrayOfObject);
	}

	public static void a(ICommandDispatcher paramICommandDispatcher)
	{
		a = paramICommandDispatcher;
	}

	public int a(ICommand paramICommand) {
		return b().compareTo(paramICommand.b());
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandAbstract
 * JD-Core Version:		0.6.0
 */