package net.minecraft.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.IllegalFormatException;
import java.util.Properties;
import java.util.TreeMap;

public class LocaleLanguage
{
	private static LocaleLanguage a = new LocaleLanguage("en_US");
	private Properties b;
	private TreeMap c;
	private String d;
	private boolean e;

	public LocaleLanguage(String paramString)
	{
		this.b = new Properties();
		e();
		a(paramString);
	}

	public static LocaleLanguage a() {
		return a;
	}

	private void e()
	{
		TreeMap localTreeMap = new TreeMap();
		try
		{
			BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(LocaleLanguage.class.getResourceAsStream("/lang/languages.txt"), "UTF-8"));

			String str = localBufferedReader.readLine();
			while (str != null) {
				String[] arrayOfString = str.split("=");
				if ((arrayOfString != null) && (arrayOfString.length == 2)) {
					localTreeMap.put(arrayOfString[0], arrayOfString[1]);
				}

				str = localBufferedReader.readLine();
			}
		}
		catch (IOException localIOException) {
			localIOException.printStackTrace();
			return;
		}

		this.c = localTreeMap;
		this.c.put("en_US", "English (US)");
	}

	public TreeMap b() {
		return this.c;
	}

	private void a(Properties paramProperties, String paramString) {
		BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(LocaleLanguage.class.getResourceAsStream("/lang/" + paramString + ".lang"), "UTF-8"));

		String str = localBufferedReader.readLine();
		while (str != null) {
			str = str.trim();
			if (!str.startsWith("#")) {
				String[] arrayOfString = str.split("=");
				if ((arrayOfString != null) && (arrayOfString.length == 2)) {
					paramProperties.setProperty(arrayOfString[0], arrayOfString[1]);
				}
			}

			str = localBufferedReader.readLine();
		}
	}

	public void a(String paramString)
	{
		if (paramString.equals(this.d)) {
			return;
		}

		Properties localProperties = new Properties();
		try
		{
			a(localProperties, "en_US");
		}
		catch (IOException localIOException1) {
		}
		this.e = false;
		if (!"en_US".equals(paramString)) {
			try {
				a(localProperties, paramString);

				Enumeration localEnumeration = localProperties.propertyNames();
				while ((localEnumeration.hasMoreElements()) && (!this.e)) {
					Object localObject1 = localEnumeration.nextElement();
					Object localObject2 = localProperties.get(localObject1);
					if (localObject2 != null) {
						String str = localObject2.toString();

						for (int i = 0; i < str.length(); i++)
							if (str.charAt(i) >= 'Ā') {
								this.e = true;
								break;
							}
					}
				}
			}
			catch (IOException localIOException2)
			{
				localIOException2.printStackTrace();
				return;
			}
		}

		this.d = paramString;
		this.b = localProperties;
	}

	public String b(String paramString)
	{
		return this.b.getProperty(paramString, paramString);
	}

	public String a(String paramString, Object[] paramArrayOfObject) {
		String str = this.b.getProperty(paramString, paramString);
		try {
			return String.format(str, paramArrayOfObject); } catch (IllegalFormatException localIllegalFormatException) {
		}
		return "Format error: " + str;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.LocaleLanguage
 * JD-Core Version:		0.6.0
 */