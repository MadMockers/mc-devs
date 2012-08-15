package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BanList
{
	private final InsensitiveStringMap a = new InsensitiveStringMap();
	private final File b;
	private boolean c = true;

	public BanList(File paramFile) {
		this.b = paramFile;
	}

	public boolean isEnabled()
	{
		return this.c;
	}

	public void setEnabled(boolean paramBoolean) {
		this.c = paramBoolean;
	}

	public Map getEntries() {
		removeExpired();
		return this.a;
	}

	public boolean isBanned(String paramString) {
		if (!isEnabled()) return false;

		removeExpired();
		return this.a.containsKey(paramString);
	}

	public void add(BanEntry paramBanEntry) {
		this.a.put(paramBanEntry.getName(), paramBanEntry);
		save();
	}

	public void remove(String paramString) {
		this.a.remove(paramString);
		save();
	}

	public void removeExpired()
	{
		Iterator localIterator = this.a.values().iterator();

		while (localIterator.hasNext()) {
			BanEntry localBanEntry = (BanEntry)localIterator.next();

			if (localBanEntry.hasExpired())
				localIterator.remove();
		}
	}

	public void load()
	{
		if (!this.b.isFile()) return; BufferedReader localBufferedReader;
		try
		{
			localBufferedReader = new BufferedReader(new FileReader(this.b));
		} catch (FileNotFoundException localFileNotFoundException) {
			throw new Error();
		}
		try
		{
			String str;
			while ((str = localBufferedReader.readLine()) != null)
				if (!str.startsWith("#")) {
					BanEntry localBanEntry = BanEntry.c(str);

					if (localBanEntry != null)
						this.a.put(localBanEntry.getName(), localBanEntry);
				}
		}
		catch (IOException localIOException)
		{
			Logger.getLogger("Minecraft").log(Level.SEVERE, "Could not load ban list", localIOException);
		}
	}

	public void save() {
		save(true);
	}

	public void save(boolean paramBoolean) {
		removeExpired();
		try
		{
			PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.b, false));

			if (paramBoolean) {
				localPrintWriter.println("# Updated " + new SimpleDateFormat().format(new Date()) + " by Minecraft " + "1.3.1");
				localPrintWriter.println("# victim name | ban date | banned by | banned until | reason");
				localPrintWriter.println();
			}

			for (BanEntry localBanEntry : this.a.values()) {
				localPrintWriter.println(localBanEntry.g());
			}

			localPrintWriter.close();
		} catch (IOException localIOException) {
			Logger.getLogger("Minecraft").log(Level.SEVERE, "Could not save ban list", localIOException);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BanList
 * JD-Core Version:		0.6.0
 */