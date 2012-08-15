/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.BufferedReader;
/*		 */ import java.io.IOException;
/*		 */ import java.io.InputStreamReader;
/*		 */ import java.util.Enumeration;
/*		 */ import java.util.IllegalFormatException;
/*		 */ import java.util.Properties;
/*		 */ import java.util.TreeMap;
/*		 */ 
/*		 */ public class LocaleLanguage
/*		 */ {
/*	 8 */	 private static LocaleLanguage a = new LocaleLanguage("en_US");
/*		 */	 private Properties b;
/*		 */	 private TreeMap c;
/*		 */	 private String d;
/*		 */	 private boolean e;
/*		 */ 
/*		 */	 public LocaleLanguage(String paramString)
/*		 */	 {
/*	16 */		 this.b = new Properties();
/*	17 */		 e();
/*	18 */		 a(paramString);
/*		 */	 }
/*		 */ 
/*		 */	 public static LocaleLanguage a() {
/*	22 */		 return a;
/*		 */	 }
/*		 */ 
/*		 */	 private void e()
/*		 */	 {
/*	27 */		 TreeMap localTreeMap = new TreeMap();
/*		 */		 try
/*		 */		 {
/*	30 */			 BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(LocaleLanguage.class.getResourceAsStream("/lang/languages.txt"), "UTF-8"));
/*		 */ 
/*	32 */			 String str = localBufferedReader.readLine();
/*	33 */			 while (str != null) {
/*	34 */				 String[] arrayOfString = str.split("=");
/*	35 */				 if ((arrayOfString != null) && (arrayOfString.length == 2)) {
/*	36 */					 localTreeMap.put(arrayOfString[0], arrayOfString[1]);
/*		 */				 }
/*		 */ 
/*	39 */				 str = localBufferedReader.readLine();
/*		 */			 }
/*		 */		 }
/*		 */		 catch (IOException localIOException) {
/*	43 */			 localIOException.printStackTrace();
/*	44 */			 return;
/*		 */		 }
/*		 */ 
/*	47 */		 this.c = localTreeMap;
/*	48 */		 this.c.put("en_US", "English (US)");
/*		 */	 }
/*		 */ 
/*		 */	 public TreeMap b() {
/*	52 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(Properties paramProperties, String paramString) {
/*	56 */		 BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(LocaleLanguage.class.getResourceAsStream("/lang/" + paramString + ".lang"), "UTF-8"));
/*		 */ 
/*	60 */		 String str = localBufferedReader.readLine();
/*	61 */		 while (str != null) {
/*	62 */			 str = str.trim();
/*	63 */			 if (!str.startsWith("#")) {
/*	64 */				 String[] arrayOfString = str.split("=");
/*	65 */				 if ((arrayOfString != null) && (arrayOfString.length == 2)) {
/*	66 */					 paramProperties.setProperty(arrayOfString[0], arrayOfString[1]);
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	70 */			 str = localBufferedReader.readLine();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString)
/*		 */	 {
/*	77 */		 if (paramString.equals(this.d)) {
/*	78 */			 return;
/*		 */		 }
/*		 */ 
/*	81 */		 Properties localProperties = new Properties();
/*		 */		 try
/*		 */		 {
/*	84 */			 a(localProperties, "en_US");
/*		 */		 }
/*		 */		 catch (IOException localIOException1) {
/*		 */		 }
/*	88 */		 this.e = false;
/*	89 */		 if (!"en_US".equals(paramString)) {
/*		 */			 try {
/*	91 */				 a(localProperties, paramString);
/*		 */ 
/*	94 */				 Enumeration localEnumeration = localProperties.propertyNames();
/*	95 */				 while ((localEnumeration.hasMoreElements()) && (!this.e)) {
/*	96 */					 Object localObject1 = localEnumeration.nextElement();
/*	97 */					 Object localObject2 = localProperties.get(localObject1);
/*	98 */					 if (localObject2 != null) {
/*	99 */						 String str = localObject2.toString();
/*		 */ 
/* 101 */						 for (int i = 0; i < str.length(); i++)
/* 102 */							 if (str.charAt(i) >= 'Ā') {
/* 103 */								 this.e = true;
/* 104 */								 break;
/*		 */							 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */			 catch (IOException localIOException2)
/*		 */			 {
/* 111 */				 localIOException2.printStackTrace();
/* 112 */				 return;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 116 */		 this.d = paramString;
/* 117 */		 this.b = localProperties;
/*		 */	 }
/*		 */ 
/*		 */	 public String b(String paramString)
/*		 */	 {
/* 129 */		 return this.b.getProperty(paramString, paramString);
/*		 */	 }
/*		 */ 
/*		 */	 public String a(String paramString, Object[] paramArrayOfObject) {
/* 133 */		 String str = this.b.getProperty(paramString, paramString);
/*		 */		 try {
/* 135 */			 return String.format(str, paramArrayOfObject); } catch (IllegalFormatException localIllegalFormatException) {
/*		 */		 }
/* 137 */		 return "Format error: " + str;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.LocaleLanguage
 * JD-Core Version:		0.6.0
 */