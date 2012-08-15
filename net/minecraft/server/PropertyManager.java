/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.File;
/*		 */ import java.io.FileInputStream;
/*		 */ import java.io.FileOutputStream;
/*		 */ import java.io.IOException;
/*		 */ import java.util.Properties;
/*		 */ import java.util.logging.Level;
/*		 */ import java.util.logging.Logger;
/*		 */ import joptsimple.OptionSet;
/*		 */ 
/*		 */ public class PropertyManager
/*		 */ {
/*	15 */	 public static Logger a = Logger.getLogger("Minecraft");
/*	16 */	 public Properties properties = new Properties();
/*		 */	 private File c;
/*	46 */	 private OptionSet options = null;
/*		 */ 
/*		 */	 public PropertyManager(File file1)
/*		 */	 {
/*	20 */		 this.c = file1;
/*	21 */		 if (file1.exists()) {
/*	22 */			 FileInputStream fileinputstream = null;
/*		 */			 try
/*		 */			 {
/*	25 */				 fileinputstream = new FileInputStream(file1);
/*	26 */				 this.properties.load(fileinputstream);
/*		 */			 } catch (Exception ioexception) {
/*	28 */				 a.log(Level.WARNING, "Failed to load " + file1, exception);
/*	29 */				 a();
/*		 */			 } finally {
/*	31 */				 if (fileinputstream != null)
/*		 */					 try {
/*	33 */						 fileinputstream.close();
/*		 */					 }
/*		 */					 catch (IOException ioexception) {
/*		 */					 }
/*		 */			 }
/*		 */		 }
/*		 */		 else {
/*	40 */			 a.log(Level.WARNING, file1 + " does not exist");
/*	41 */			 a();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public PropertyManager(OptionSet options)
/*		 */	 {
/*	49 */		 this((File)options.valueOf("config"));
/*		 */ 
/*	51 */		 this.options = options;
/*		 */	 }
/*		 */ 
/*		 */	 private <T> T getOverride(String name, T value) {
/*	55 */		 if ((this.options != null) && (this.options.has(name))) {
/*	56 */			 return this.options.valueOf(name);
/*		 */		 }
/*		 */ 
/*	59 */		 return value;
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/*	64 */		 a.log(Level.INFO, "Generating new properties file");
/*	65 */		 savePropertiesFile();
/*		 */	 }
/*		 */ 
/*		 */	 public void savePropertiesFile() {
/*	69 */		 FileOutputStream fileoutputstream = null;
/*		 */		 try
/*		 */		 {
/*	72 */			 fileoutputstream = new FileOutputStream(this.c);
/*	73 */			 this.properties.store(fileoutputstream, "Minecraft server properties");
/*		 */		 } catch (Exception ioexception) {
/*	75 */			 a.log(Level.WARNING, "Failed to save " + this.c, exception);
/*	76 */			 a();
/*		 */		 } finally {
/*	78 */			 if (fileoutputstream != null)
/*		 */				 try {
/*	80 */					 fileoutputstream.close();
/*		 */				 }
/*		 */				 catch (IOException ioexception)
/*		 */				 {
/*		 */				 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public File c() {
/*	89 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public String getString(String s, String s1) {
/*	93 */		 if (!this.properties.containsKey(s)) {
/*	94 */			 s1 = (String)getOverride(s, s1);
/*	95 */			 this.properties.setProperty(s, s1);
/*	96 */			 savePropertiesFile();
/*		 */		 }
/*		 */ 
/*	99 */		 return (String)getOverride(s, this.properties.getProperty(s, s1));
/*		 */	 }
/*		 */ 
/*		 */	 public int getInt(String s, int i) {
/*		 */		 try {
/* 104 */			 return ((Integer)getOverride(s, Integer.valueOf(Integer.parseInt(getString(s, "" + i))))).intValue();
/*		 */		 } catch (Exception exception) {
/* 106 */			 i = ((Integer)getOverride(s, Integer.valueOf(i))).intValue();
/* 107 */			 this.properties.setProperty(s, "" + i);
/* 108 */		 }return i;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean getBoolean(String s, boolean flag)
/*		 */	 {
/*		 */		 try {
/* 114 */			 return ((Boolean)getOverride(s, Boolean.valueOf(Boolean.parseBoolean(getString(s, "" + flag))))).booleanValue();
/*		 */		 } catch (Exception exception) {
/* 116 */			 flag = ((Boolean)getOverride(s, Boolean.valueOf(flag))).booleanValue();
/* 117 */			 this.properties.setProperty(s, "" + flag);
/* 118 */		 }return flag;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String s, Object object)
/*		 */	 {
/* 123 */		 this.properties.setProperty(s, "" + object);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PropertyManager
 * JD-Core Version:		0.6.0
 */