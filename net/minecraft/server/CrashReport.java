package net.minecraft.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrashReport
{
	private final String a;
	private final Throwable b;
	private final Map c = new LinkedHashMap();
	private File d = null;

	public CrashReport(String paramString, Throwable paramThrowable) {
		this.a = paramString;
		this.b = paramThrowable;

		g();
	}

	private void g() {
		a("Minecraft Version", new CrashReportVersion(this));

		a("Operating System", new CrashReportOperatingSystem(this));

		a("Java Version", new CrashReportJavaVersion(this));

		a("Java VM Version", new CrashReportJavaVMVersion(this));

		a("Memory", new CrashReportMemory(this));

		a("JVM Flags", new CrashReportJVMFlags(this));
	}

	public void a(String paramString, Callable paramCallable)
	{
		try
		{
			a(paramString, paramCallable.call());
		} catch (Throwable localThrowable) {
			a(paramString, localThrowable);
		}
	}

	public void a(String paramString, Object paramObject) {
		this.c.put(paramString, paramObject == null ? "null" : paramObject.toString());
	}

	public void a(String paramString, Throwable paramThrowable) {
		a(paramString, "~ERROR~ " + paramThrowable.getClass().getSimpleName() + ": " + paramThrowable.getMessage());
	}

	public String a() {
		return this.a;
	}

	public Throwable b() {
		return this.b;
	}

	public void a(StringBuilder paramStringBuilder)
	{
		int i = 1;

		for (Map.Entry localEntry : this.c.entrySet()) {
			if (i == 0) paramStringBuilder.append("\n");

			paramStringBuilder.append("- ");
			paramStringBuilder.append((String)localEntry.getKey());
			paramStringBuilder.append(": ");
			paramStringBuilder.append((String)localEntry.getValue());

			i = 0;
		}
	}

	public String d() {
		StringWriter localStringWriter = null;
		PrintWriter localPrintWriter = null;
		String str = this.b.toString();
		try
		{
			localStringWriter = new StringWriter();
			localPrintWriter = new PrintWriter(localStringWriter);
			this.b.printStackTrace(localPrintWriter);
			str = localStringWriter.toString();
		} finally {
			try {
				if (localStringWriter != null) localStringWriter.close();
				if (localPrintWriter != null) localPrintWriter.close(); 
			}
			catch (IOException localIOException2) {
			}
		}
		return str;
	}

	public String e() {
		StringBuilder localStringBuilder = new StringBuilder();

		localStringBuilder.append("---- Minecraft Crash Report ----\n");
		localStringBuilder.append("// ");
		localStringBuilder.append(h());
		localStringBuilder.append("\n\n");

		localStringBuilder.append("Time: ");
		localStringBuilder.append(new SimpleDateFormat().format(new Date()));
		localStringBuilder.append("\n");

		localStringBuilder.append("Description: ");
		localStringBuilder.append(this.a);
		localStringBuilder.append("\n\n");

		localStringBuilder.append(d());
		localStringBuilder.append("\n");

		localStringBuilder.append("Relevant Details:");
		localStringBuilder.append("\n");
		a(localStringBuilder);

		return localStringBuilder.toString();
	}

	public boolean a(File paramFile)
	{
		if (this.d != null) return false;
		if (paramFile.getParentFile() != null) paramFile.getParentFile().mkdirs();
		try
		{
			FileWriter localFileWriter = new FileWriter(paramFile);
			localFileWriter.write(e());
			localFileWriter.close();

			this.d = paramFile;
			return true;
		} catch (Throwable localThrowable) {
			Logger.getLogger("Minecraft").log(Level.SEVERE, "Could not save crash report to " + paramFile, localThrowable);
		}return false;
	}

	private static String h()
	{
		String[] arrayOfString = { "Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(" };
		try
		{
			return arrayOfString[(int)(java.lang.System.nanoTime() % arrayOfString.length)]; } catch (Throwable localThrowable) {
		}
		return "Witty comment unavailable :(";
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CrashReport
 * JD-Core Version:		0.6.0
 */