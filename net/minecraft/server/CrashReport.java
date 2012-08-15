/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.File;
/*		 */ import java.io.FileWriter;
/*		 */ import java.io.IOException;
/*		 */ import java.io.PrintWriter;
/*		 */ import java.io.StringWriter;
/*		 */ import java.text.SimpleDateFormat;
/*		 */ import java.util.Date;
/*		 */ import java.util.LinkedHashMap;
/*		 */ import java.util.Map;
/*		 */ import java.util.Map.Entry;
/*		 */ import java.util.concurrent.Callable;
/*		 */ import java.util.logging.Level;
/*		 */ import java.util.logging.Logger;
/*		 */ 
/*		 */ public class CrashReport
/*		 */ {
/*		 */	 private final String a;
/*		 */	 private final Throwable b;
/*	18 */	 private final Map c = new LinkedHashMap();
/*	19 */	 private File d = null;
/*		 */ 
/*		 */	 public CrashReport(String paramString, Throwable paramThrowable) {
/*	22 */		 this.a = paramString;
/*	23 */		 this.b = paramThrowable;
/*		 */ 
/*	25 */		 g();
/*		 */	 }
/*		 */ 
/*		 */	 private void g() {
/*	29 */		 a("Minecraft Version", new CrashReportVersion(this));
/*		 */ 
/*	35 */		 a("Operating System", new CrashReportOperatingSystem(this));
/*		 */ 
/*	41 */		 a("Java Version", new CrashReportJavaVersion(this));
/*		 */ 
/*	47 */		 a("Java VM Version", new CrashReportJavaVMVersion(this));
/*		 */ 
/*	53 */		 a("Memory", new CrashReportMemory(this));
/*		 */ 
/*	67 */		 a("JVM Flags", new CrashReportJVMFlags(this));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString, Callable paramCallable)
/*		 */	 {
/*		 */		 try
/*		 */		 {
/*	91 */			 a(paramString, paramCallable.call());
/*		 */		 } catch (Throwable localThrowable) {
/*	93 */			 a(paramString, localThrowable);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString, Object paramObject) {
/*	98 */		 this.c.put(paramString, paramObject == null ? "null" : paramObject.toString());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString, Throwable paramThrowable) {
/* 102 */		 a(paramString, "~ERROR~ " + paramThrowable.getClass().getSimpleName() + ": " + paramThrowable.getMessage());
/*		 */	 }
/*		 */ 
/*		 */	 public String a() {
/* 106 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public Throwable b() {
/* 110 */		 return this.b;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StringBuilder paramStringBuilder)
/*		 */	 {
/* 122 */		 int i = 1;
/*		 */ 
/* 124 */		 for (Map.Entry localEntry : this.c.entrySet()) {
/* 125 */			 if (i == 0) paramStringBuilder.append("\n");
/*		 */ 
/* 127 */			 paramStringBuilder.append("- ");
/* 128 */			 paramStringBuilder.append((String)localEntry.getKey());
/* 129 */			 paramStringBuilder.append(": ");
/* 130 */			 paramStringBuilder.append((String)localEntry.getValue());
/*		 */ 
/* 132 */			 i = 0;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public String d() {
/* 137 */		 StringWriter localStringWriter = null;
/* 138 */		 PrintWriter localPrintWriter = null;
/* 139 */		 String str = this.b.toString();
/*		 */		 try
/*		 */		 {
/* 142 */			 localStringWriter = new StringWriter();
/* 143 */			 localPrintWriter = new PrintWriter(localStringWriter);
/* 144 */			 this.b.printStackTrace(localPrintWriter);
/* 145 */			 str = localStringWriter.toString();
/*		 */		 } finally {
/*		 */			 try {
/* 148 */				 if (localStringWriter != null) localStringWriter.close();
/* 149 */				 if (localPrintWriter != null) localPrintWriter.close(); 
/*		 */			 }
/*		 */			 catch (IOException localIOException2) {
/*		 */			 }
/*		 */		 }
/* 153 */		 return str;
/*		 */	 }
/*		 */ 
/*		 */	 public String e() {
/* 157 */		 StringBuilder localStringBuilder = new StringBuilder();
/*		 */ 
/* 159 */		 localStringBuilder.append("---- Minecraft Crash Report ----\n");
/* 160 */		 localStringBuilder.append("// ");
/* 161 */		 localStringBuilder.append(h());
/* 162 */		 localStringBuilder.append("\n\n");
/*		 */ 
/* 164 */		 localStringBuilder.append("Time: ");
/* 165 */		 localStringBuilder.append(new SimpleDateFormat().format(new Date()));
/* 166 */		 localStringBuilder.append("\n");
/*		 */ 
/* 168 */		 localStringBuilder.append("Description: ");
/* 169 */		 localStringBuilder.append(this.a);
/* 170 */		 localStringBuilder.append("\n\n");
/*		 */ 
/* 172 */		 localStringBuilder.append(d());
/* 173 */		 localStringBuilder.append("\n");
/*		 */ 
/* 175 */		 localStringBuilder.append("Relevant Details:");
/* 176 */		 localStringBuilder.append("\n");
/* 177 */		 a(localStringBuilder);
/*		 */ 
/* 179 */		 return localStringBuilder.toString();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(File paramFile)
/*		 */	 {
/* 187 */		 if (this.d != null) return false;
/* 188 */		 if (paramFile.getParentFile() != null) paramFile.getParentFile().mkdirs();
/*		 */		 try
/*		 */		 {
/* 191 */			 FileWriter localFileWriter = new FileWriter(paramFile);
/* 192 */			 localFileWriter.write(e());
/* 193 */			 localFileWriter.close();
/*		 */ 
/* 195 */			 this.d = paramFile;
/* 196 */			 return true;
/*		 */		 } catch (Throwable localThrowable) {
/* 198 */			 Logger.getLogger("Minecraft").log(Level.SEVERE, "Could not save crash report to " + paramFile, localThrowable);
/* 199 */		 }return false;
/*		 */	 }
/*		 */ 
/*		 */	 private static String h()
/*		 */	 {
/* 205 */		 String[] arrayOfString = { "Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(" };
/*		 */		 try
/*		 */		 {
/* 237 */			 return arrayOfString[(int)(java.lang.System.nanoTime() % arrayOfString.length)]; } catch (Throwable localThrowable) {
/*		 */		 }
/* 239 */		 return "Witty comment unavailable :(";
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReport
 * JD-Core Version:		0.6.0
 */