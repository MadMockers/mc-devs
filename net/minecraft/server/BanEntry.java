package net.minecraft.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class BanEntry
{
	public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
	public static Logger b = Logger.getLogger("Minecraft");
	private final String c;
	private Date d = new Date();
	private String e = "(Unknown)";
	private Date f = null;
	private String g = "Banned by an operator.";

	public BanEntry(String paramString) {
		this.c = paramString;
	}

	public String getName() {
		return this.c;
	}

	public Date getCreated() {
		return this.d;
	}

	public void setCreated(Date paramDate) {
		this.d = (paramDate != null ? paramDate : new Date());
	}

	public String getSource() {
		return this.e;
	}

	public void setSource(String paramString) {
		this.e = paramString;
	}

	public Date getExpires() {
		return this.f;
	}

	public void setExpires(Date paramDate) {
		this.f = paramDate;
	}

	public boolean hasExpired() {
		if (this.f == null) return false;
		return this.f.before(new Date());
	}

	public String getReason() {
		return this.g;
	}

	public void setReason(String paramString) {
		this.g = paramString;
	}

	public String g() {
		StringBuilder localStringBuilder = new StringBuilder();

		localStringBuilder.append(getName());
		localStringBuilder.append("|");

		localStringBuilder.append(a.format(getCreated()));
		localStringBuilder.append("|");

		localStringBuilder.append(getSource());
		localStringBuilder.append("|");

		localStringBuilder.append(getExpires() == null ? "Forever" : a.format(getExpires()));
		localStringBuilder.append("|");

		localStringBuilder.append(getReason());

		return localStringBuilder.toString();
	}

	public static BanEntry c(String paramString) {
		if (paramString.trim().length() < 2) return null;

		String[] arrayOfString = paramString.trim().split(Pattern.quote("|"), 5);
		BanEntry localBanEntry = new BanEntry(arrayOfString[0].trim());
		int i = 0;

		i++; if (arrayOfString.length <= i) return localBanEntry; try
		{
			localBanEntry.setCreated(a.parse(arrayOfString[i].trim()));
		} catch (ParseException localParseException1) {
			b.log(Level.WARNING, "Could not read creation date format for ban entry '" + localBanEntry.getName() + "' (was: '" + arrayOfString[i] + "')", localParseException1);
		}

		i++; if (arrayOfString.length <= i) return localBanEntry;
		localBanEntry.setSource(arrayOfString[i].trim());

		i++; if (arrayOfString.length <= i) return localBanEntry; try
		{
			String str = arrayOfString[i].trim();
			if ((!str.equalsIgnoreCase("Forever")) && (str.length() > 0))
				localBanEntry.setExpires(a.parse(str));
		}
		catch (ParseException localParseException2) {
			b.log(Level.WARNING, "Could not read expiry date format for ban entry '" + localBanEntry.getName() + "' (was: '" + arrayOfString[i] + "')", localParseException2);
		}

		i++; if (arrayOfString.length <= i) return localBanEntry;
		localBanEntry.setReason(arrayOfString[i].trim());

		return localBanEntry;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BanEntry
 * JD-Core Version:		0.6.0
 */