/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.File;
/*		 */ import java.io.FileWriter;
/*		 */ import java.text.SimpleDateFormat;
/*		 */ import java.util.Date;
/*		 */ import java.util.List;
/*		 */ import java.util.logging.Level;
/*		 */ import java.util.logging.Logger;
/*		 */ 
/*		 */ public class CommandDebug extends CommandAbstract
/*		 */ {
/*	20 */	 private long a = 0L;
/*	21 */	 private int b = 0;
/*		 */ 
/*		 */	 public String b() {
/*	24 */		 return "debug";
/*		 */	 }
/*		 */ 
/*		 */	 public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
/*	28 */		 if (paramArrayOfString.length == 1) {
/*	29 */			 if (paramArrayOfString[0].equals("start")) {
/*	30 */				 a(paramICommandListener, "commands.debug.start", new Object[0]);
/*		 */ 
/*	32 */				 MinecraftServer.getServer().ag();
/*	33 */				 this.a = System.currentTimeMillis();
/*	34 */				 this.b = MinecraftServer.getServer().af();
/*	35 */				 return;
/*	36 */			 }if (paramArrayOfString[0].equals("stop")) {
/*	37 */				 if (!MinecraftServer.getServer().methodProfiler.a) {
/*	38 */					 throw new CommandException("commands.debug.notStarted", new Object[0]);
/*		 */				 }
/*		 */ 
/*	41 */				 long l1 = System.currentTimeMillis();
/*	42 */				 int i = MinecraftServer.getServer().af();
/*		 */ 
/*	44 */				 long l2 = l1 - this.a;
/*	45 */				 int j = i - this.b;
/*		 */ 
/*	47 */				 a(l2, j);
/*		 */ 
/*	49 */				 MinecraftServer.getServer().methodProfiler.a = false;
/*	50 */				 a(paramICommandListener, "commands.debug.stop", new Object[] { Float.valueOf((float)l2 / 1000.0F), Integer.valueOf(j) });
/*	51 */				 return;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	55 */		 throw new ExceptionUsage("commands.debug.usage", new Object[0]);
/*		 */	 }
/*		 */ 
/*		 */	 private void a(long paramLong, int paramInt) {
/*	59 */		 File localFile = new File(MinecraftServer.getServer().f("debug"), "profile-results-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + ".txt");
/*		 */ 
/*	61 */		 localFile.getParentFile().mkdirs();
/*		 */		 try
/*		 */		 {
/*	64 */			 FileWriter localFileWriter = new FileWriter(localFile);
/*	65 */			 localFileWriter.write(b(paramLong, paramInt));
/*	66 */			 localFileWriter.close();
/*		 */		 } catch (Throwable localThrowable) {
/*	68 */			 Logger.getLogger("Minecraft").log(Level.SEVERE, "Could not save profiler results to " + localFile, localThrowable);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private String b(long paramLong, int paramInt) {
/*	73 */		 StringBuilder localStringBuilder = new StringBuilder();
/*		 */ 
/*	75 */		 localStringBuilder.append("---- Minecraft Profiler Results ----\n");
/*	76 */		 localStringBuilder.append("// ");
/*	77 */		 localStringBuilder.append(c());
/*	78 */		 localStringBuilder.append("\n\n");
/*		 */ 
/*	80 */		 localStringBuilder.append("Time span: ").append(paramLong).append(" ms\n");
/*	81 */		 localStringBuilder.append("Tick span: ").append(paramInt).append(" ticks\n");
/*	82 */		 localStringBuilder.append("// This is approximately ").append(String.format("%.2f", new Object[] { Float.valueOf(paramInt / ((float)paramLong / 1000.0F)) })).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
/*		 */ 
/*	84 */		 localStringBuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
/*		 */ 
/*	86 */		 a(0, "root", localStringBuilder);
/*		 */ 
/*	88 */		 localStringBuilder.append("--- END PROFILE DUMP ---\n\n");
/*		 */ 
/*	90 */		 return localStringBuilder.toString();
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int paramInt, String paramString, StringBuilder paramStringBuilder) {
/*	94 */		 List localList = MinecraftServer.getServer().methodProfiler.b(paramString);
/*	95 */		 if ((localList == null) || (localList.size() < 3)) return;
/*		 */ 
/*	97 */		 for (int i = 1; i < localList.size(); i++) {
/*	98 */			 ProfilerInfo localProfilerInfo = (ProfilerInfo)localList.get(i);
/*		 */ 
/* 100 */			 paramStringBuilder.append(String.format("[%02d] ", new Object[] { Integer.valueOf(paramInt) }));
/* 101 */			 for (int j = 0; j < paramInt; j++) paramStringBuilder.append(" ");
/* 102 */			 paramStringBuilder.append(localProfilerInfo.c);
/* 103 */			 paramStringBuilder.append(" - ");
/* 104 */			 paramStringBuilder.append(String.format("%.2f", new Object[] { Double.valueOf(localProfilerInfo.a) }));
/* 105 */			 paramStringBuilder.append("%/");
/* 106 */			 paramStringBuilder.append(String.format("%.2f", new Object[] { Double.valueOf(localProfilerInfo.b) }));
/* 107 */			 paramStringBuilder.append("%\n");
/*		 */ 
/* 109 */			 if (localProfilerInfo.c.equals("unspecified")) continue;
/*		 */			 try {
/* 111 */				 a(paramInt + 1, paramString + "." + localProfilerInfo.c, paramStringBuilder);
/*		 */			 } catch (Exception localException) {
/* 113 */				 paramStringBuilder.append("[[ EXCEPTION " + localException + " ]]");
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private static String c()
/*		 */	 {
/* 121 */		 String[] arrayOfString = { "Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server." };
/*		 */		 try
/*		 */		 {
/* 139 */			 return arrayOfString[(int)(System.nanoTime() % arrayOfString.length)]; } catch (Throwable localThrowable) {
/*		 */		 }
/* 141 */		 return "Witty comment unavailable :(";
/*		 */	 }
/*		 */ 
/*		 */	 public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
/*		 */	 {
/* 147 */		 if (paramArrayOfString.length == 1) return a(paramArrayOfString, new String[] { "start", "stop" });
/*		 */ 
/* 149 */		 return null;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandDebug
 * JD-Core Version:		0.6.0
 */