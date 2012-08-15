package net.minecraft.server;

public class LocaleI18n
{
	private static LocaleLanguage a = LocaleLanguage.a();

	public static String get(String paramString) {
		return a.b(paramString);
	}

	public static String get(String paramString, Object[] paramArrayOfObject) {
		return a.a(paramString, paramArrayOfObject);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.LocaleI18n
 * JD-Core Version:		0.6.0
 */