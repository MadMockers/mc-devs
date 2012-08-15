package net.minecraft.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StripColor
{
	private static final Pattern a = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

	public static String a(String paramString)
	{
		return a.matcher(paramString).replaceAll("");
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.StripColor
 * JD-Core Version:		0.6.0
 */