/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.BufferedReader;
/*		 */ import java.io.File;
/*		 */ import java.io.FileReader;
/*		 */ import java.io.FileWriter;
/*		 */ import java.io.PrintWriter;
/*		 */ import java.util.Set;
/*		 */ import java.util.logging.Logger;
/*		 */ 
/*		 */ public class ServerConfigurationManager extends ServerConfigurationManagerAbstract
/*		 */ {
/*		 */	 private File e;
/*		 */	 private File f;
/*		 */ 
/*		 */	 public ServerConfigurationManager(DedicatedServer paramDedicatedServer)
/*		 */	 {
/*	11 */		 super(paramDedicatedServer);
/*		 */ 
/*	13 */		 this.e = paramDedicatedServer.f("ops.txt");
/*	14 */		 this.f = paramDedicatedServer.f("white-list.txt");
/*	15 */		 this.d = paramDedicatedServer.a("view-distance", 10);
/*	16 */		 this.maxPlayers = paramDedicatedServer.a("max-players", 20);
/*	17 */		 setHasWhitelist(paramDedicatedServer.a("white-list", false));
/*		 */ 
/*	19 */		 if (!paramDedicatedServer.H()) {
/*	20 */			 getNameBans().setEnabled(true);
/*	21 */			 getIPBans().setEnabled(true);
/*		 */		 }
/*		 */ 
/*	24 */		 getNameBans().load();
/*	25 */		 getNameBans().save();
/*	26 */		 getIPBans().load();
/*	27 */		 getIPBans().save();
/*	28 */		 t();
/*	29 */		 v();
/*	30 */		 u();
/*	31 */		 w();
/*		 */	 }
/*		 */ 
/*		 */	 public void setHasWhitelist(boolean paramBoolean)
/*		 */	 {
/*	36 */		 super.setHasWhitelist(paramBoolean);
/*	37 */		 getServer().a("white-list", Boolean.valueOf(paramBoolean));
/*	38 */		 getServer().a();
/*		 */	 }
/*		 */ 
/*		 */	 public void addOp(String paramString)
/*		 */	 {
/*	43 */		 super.addOp(paramString);
/*	44 */		 u();
/*		 */	 }
/*		 */ 
/*		 */	 public void removeOp(String paramString)
/*		 */	 {
/*	49 */		 super.removeOp(paramString);
/*	50 */		 u();
/*		 */	 }
/*		 */ 
/*		 */	 public void removeWhitelist(String paramString)
/*		 */	 {
/*	55 */		 super.removeWhitelist(paramString);
/*	56 */		 w();
/*		 */	 }
/*		 */ 
/*		 */	 public void addWhitelist(String paramString)
/*		 */	 {
/*	61 */		 super.addWhitelist(paramString);
/*	62 */		 w();
/*		 */	 }
/*		 */ 
/*		 */	 public void reloadWhitelist()
/*		 */	 {
/*	67 */		 v();
/*		 */	 }
/*		 */ 
/*		 */	 private void t() {
/*		 */		 try {
/*	72 */			 getOPs().clear();
/*	73 */			 BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.e));
/*	74 */			 String str = "";
/*	75 */			 while ((str = localBufferedReader.readLine()) != null) {
/*	76 */				 getOPs().add(str.trim().toLowerCase());
/*		 */			 }
/*	78 */			 localBufferedReader.close();
/*		 */		 } catch (Exception localException) {
/*	80 */			 a.warning("Failed to load operators list: " + localException);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void u() {
/*		 */		 try {
/*	86 */			 PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.e, false));
/*	87 */			 for (String str : getOPs()) {
/*	88 */				 localPrintWriter.println(str);
/*		 */			 }
/*	90 */			 localPrintWriter.close();
/*		 */		 } catch (Exception localException) {
/*	92 */			 a.warning("Failed to save operators list: " + localException);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void v() {
/*		 */		 try {
/*	98 */			 getWhitelisted().clear();
/*	99 */			 BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.f));
/* 100 */			 String str = "";
/* 101 */			 while ((str = localBufferedReader.readLine()) != null) {
/* 102 */				 getWhitelisted().add(str.trim().toLowerCase());
/*		 */			 }
/* 104 */			 localBufferedReader.close();
/*		 */		 } catch (Exception localException) {
/* 106 */			 a.warning("Failed to load white-list: " + localException);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void w() {
/*		 */		 try {
/* 112 */			 PrintWriter localPrintWriter = new PrintWriter(new FileWriter(this.f, false));
/* 113 */			 for (String str : getWhitelisted()) {
/* 114 */				 localPrintWriter.println(str);
/*		 */			 }
/* 116 */			 localPrintWriter.close();
/*		 */		 } catch (Exception localException) {
/* 118 */			 a.warning("Failed to save white-list: " + localException);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isWhitelisted(String paramString) {
/* 123 */		 paramString = paramString.trim().toLowerCase();
/* 124 */		 return (!getHasWhitelist()) || (isOp(paramString)) || (getWhitelisted().contains(paramString));
/*		 */	 }
/*		 */ 
/*		 */	 public DedicatedServer getServer()
/*		 */	 {
/* 129 */		 return (DedicatedServer)super.getServer();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ServerConfigurationManager
 * JD-Core Version:		0.6.0
 */