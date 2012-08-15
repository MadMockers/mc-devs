/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.BufferedReader;
/*		 */ import java.io.File;
/*		 */ import java.io.FileNotFoundException;
/*		 */ import java.io.FileReader;
/*		 */ import java.io.FileWriter;
/*		 */ import java.io.IOException;
/*		 */ import java.io.PrintWriter;
/*		 */ import java.text.SimpleDateFormat;
/*		 */ import java.util.Collection;
/*		 */ import java.util.Date;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.Map;
/*		 */ import java.util.logging.Level;
/*		 */ import java.util.logging.Logger;
/*		 */ 
/*		 */ public class BanList
/*		 */ {
/*	15 */	 private final InsensitiveStringMap a = new InsensitiveStringMap();
/*		 */	 private final File b;
/*	17 */	 private boolean c = true;
/*		 */ 
/*		 */	 public BanList(File paramFile) {
/*	20 */		 this.b = paramFile;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isEnabled()
/*		 */	 {
/*	28 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public void setEnabled(boolean paramBoolean) {
/*	32 */		 this.c = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public Map getEntries() {
/*	36 */		 removeExpired();
/*	37 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isBanned(String paramString) {
/*	41 */		 if (!isEnabled()) return false;
/*		 */ 
/*	43 */		 removeExpired();
/*	44 */		 return this.a.containsKey(paramString);
/*		 */	 }
/*		 */ 
/*		 */	 public void add(BanEntry paramBanEntry) {
/*	48 */		 this.a.put(paramBanEntry.getName(), paramBanEntry);
/*	49 */		 save();
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(String paramString) {
/*	53 */		 this.a.remove(paramString);
/*	54 */		 save();
/*		 */	 }
/*		 */ 
/*		 */	 public void removeExpired()
/*		 */	 {
/*	62 */		 Iterator localIterator = this.a.values().iterator();
/*		 */ 
/*	64 */		 while (localIterator.hasNext()) {
/*	65 */			 BanEntry localBanEntry = (BanEntry)localIterator.next();
/*		 */ 
/*	67 */			 if (localBanEntry.hasExpired())
/*	68 */				 localIterator.remove();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void load()
/*		 */	 {
/*	74 */		 if (!this.b.isFile()) return; BufferedReader localBufferedReader;
/*		 */		 try
/*		 */		 {
/*	78 */			 localBufferedReader = new BufferedReader(new FileReader(this.b));
/*		 */		 } catch (FileNotFoundException localFileNotFoundException) {
/*	80 */			 throw new Error();
/*		 */		 }
/*		 */		 try
/*		 */		 {
/*		 */			 String str;
/*	86 */			 while ((str = localBufferedReader.readLine()) != null)
/*	87 */				 if (!str.startsWith("#")) {
/*	88 */					 BanEntry localBanEntry = BanEntry.c(str);
/*		 */ 
/*	90 */					 if (localBanEntry != null)
/*	91 */						 this.a.put(localBanEntry.getName(), localBanEntry);
/*		 */				 }
/*		 */		 }
/*		 */		 catch (IOException localIOException)
/*		 */		 {
/*	96 */			 Logger.getLogger("Minecraft").log(Level.SEVERE, "Could not load ban list", localIOException);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void save() {
/* 101 */		 save(true);
/*		 */	 }
/*		 */ 
/*		 */	 public void save(boolean paramBoolean) {
/* 105 */		 removeExpired();
/*		 */		 try
/*		 */		 {
/* 108 */			 PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.b, false));
/*		 */ 
/* 110 */			 if (paramBoolean) {
/* 111 */				 localPrintWriter.println("# Updated " + new SimpleDateFormat().format(new Date()) + " by Minecraft " + "1.3.1");
/* 112 */				 localPrintWriter.println("# victim name | ban date | banned by | banned until | reason");
/* 113 */				 localPrintWriter.println();
/*		 */			 }
/*		 */ 
/* 116 */			 for (BanEntry localBanEntry : this.a.values()) {
/* 117 */				 localPrintWriter.println(localBanEntry.g());
/*		 */			 }
/*		 */ 
/* 120 */			 localPrintWriter.close();
/*		 */		 } catch (IOException localIOException) {
/* 122 */			 Logger.getLogger("Minecraft").log(Level.SEVERE, "Could not save ban list", localIOException);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BanList
 * JD-Core Version:		0.6.0
 */