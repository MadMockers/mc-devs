package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import joptsimple.OptionSet;

public class PropertyManager
{
	public static Logger a = Logger.getLogger("Minecraft");
	public Properties properties = new Properties();
	private File c;
	private OptionSet options = null;

	public PropertyManager(File file1)
	{
		this.c = file1;
		if (file1.exists()) {
			FileInputStream fileinputstream = null;
			try
			{
				fileinputstream = new FileInputStream(file1);
				this.properties.load(fileinputstream);
			} catch (Exception ioexception) {
				a.log(Level.WARNING, "Failed to load " + file1, exception);
				a();
			} finally {
				if (fileinputstream != null)
					try {
						fileinputstream.close();
					}
					catch (IOException ioexception) {
					}
			}
		}
		else {
			a.log(Level.WARNING, file1 + " does not exist");
			a();
		}
	}

	public PropertyManager(OptionSet options)
	{
		this((File)options.valueOf("config"));

		this.options = options;
	}

	private <T> T getOverride(String name, T value) {
		if ((this.options != null) && (this.options.has(name))) {
			return this.options.valueOf(name);
		}

		return value;
	}

	public void a()
	{
		a.log(Level.INFO, "Generating new properties file");
		savePropertiesFile();
	}

	public void savePropertiesFile() {
		FileOutputStream fileoutputstream = null;
		try
		{
			fileoutputstream = new FileOutputStream(this.c);
			this.properties.store(fileoutputstream, "Minecraft server properties");
		} catch (Exception ioexception) {
			a.log(Level.WARNING, "Failed to save " + this.c, exception);
			a();
		} finally {
			if (fileoutputstream != null)
				try {
					fileoutputstream.close();
				}
				catch (IOException ioexception)
				{
				}
		}
	}

	public File c() {
		return this.c;
	}

	public String getString(String s, String s1) {
		if (!this.properties.containsKey(s)) {
			s1 = (String)getOverride(s, s1);
			this.properties.setProperty(s, s1);
			savePropertiesFile();
		}

		return (String)getOverride(s, this.properties.getProperty(s, s1));
	}

	public int getInt(String s, int i) {
		try {
			return ((Integer)getOverride(s, Integer.valueOf(Integer.parseInt(getString(s, "" + i))))).intValue();
		} catch (Exception exception) {
			i = ((Integer)getOverride(s, Integer.valueOf(i))).intValue();
			this.properties.setProperty(s, "" + i);
		}return i;
	}

	public boolean getBoolean(String s, boolean flag)
	{
		try {
			return ((Boolean)getOverride(s, Boolean.valueOf(Boolean.parseBoolean(getString(s, "" + flag))))).booleanValue();
		} catch (Exception exception) {
			flag = ((Boolean)getOverride(s, Boolean.valueOf(flag))).booleanValue();
			this.properties.setProperty(s, "" + flag);
		}return flag;
	}

	public void a(String s, Object object)
	{
		this.properties.setProperty(s, "" + object);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PropertyManager
 * JD-Core Version:		0.6.0
 */