/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.text.ParseException;
/*		 */ import java.text.SimpleDateFormat;
/*		 */ import java.util.Date;
/*		 */ import java.util.logging.Level;
/*		 */ import java.util.logging.Logger;
/*		 */ import java.util.regex.Pattern;
/*		 */ 
/*		 */ public class BanEntry
/*		 */ {
/*	13 */	 public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*	14 */	 public static Logger b = Logger.getLogger("Minecraft");
/*		 */	 private final String c;
/*	18 */	 private Date d = new Date();
/*	19 */	 private String e = "(Unknown)";
/*	20 */	 private Date f = null;
/*	21 */	 private String g = "Banned by an operator.";
/*		 */ 
/*		 */	 public BanEntry(String paramString) {
/*	24 */		 this.c = paramString;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/*	28 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public Date getCreated() {
/*	32 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public void setCreated(Date paramDate) {
/*	36 */		 this.d = (paramDate != null ? paramDate : new Date());
/*		 */	 }
/*		 */ 
/*		 */	 public String getSource() {
/*	40 */		 return this.e;
/*		 */	 }
/*		 */ 
/*		 */	 public void setSource(String paramString) {
/*	44 */		 this.e = paramString;
/*		 */	 }
/*		 */ 
/*		 */	 public Date getExpires() {
/*	48 */		 return this.f;
/*		 */	 }
/*		 */ 
/*		 */	 public void setExpires(Date paramDate) {
/*	52 */		 this.f = paramDate;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean hasExpired() {
/*	56 */		 if (this.f == null) return false;
/*	57 */		 return this.f.before(new Date());
/*		 */	 }
/*		 */ 
/*		 */	 public String getReason() {
/*	61 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public void setReason(String paramString) {
/*	65 */		 this.g = paramString;
/*		 */	 }
/*		 */ 
/*		 */	 public String g() {
/*	69 */		 StringBuilder localStringBuilder = new StringBuilder();
/*		 */ 
/*	71 */		 localStringBuilder.append(getName());
/*	72 */		 localStringBuilder.append("|");
/*		 */ 
/*	74 */		 localStringBuilder.append(a.format(getCreated()));
/*	75 */		 localStringBuilder.append("|");
/*		 */ 
/*	77 */		 localStringBuilder.append(getSource());
/*	78 */		 localStringBuilder.append("|");
/*		 */ 
/*	80 */		 localStringBuilder.append(getExpires() == null ? "Forever" : a.format(getExpires()));
/*	81 */		 localStringBuilder.append("|");
/*		 */ 
/*	83 */		 localStringBuilder.append(getReason());
/*		 */ 
/*	85 */		 return localStringBuilder.toString();
/*		 */	 }
/*		 */ 
/*		 */	 public static BanEntry c(String paramString) {
/*	89 */		 if (paramString.trim().length() < 2) return null;
/*		 */ 
/*	91 */		 String[] arrayOfString = paramString.trim().split(Pattern.quote("|"), 5);
/*	92 */		 BanEntry localBanEntry = new BanEntry(arrayOfString[0].trim());
/*	93 */		 int i = 0;
/*		 */ 
/*	95 */		 i++; if (arrayOfString.length <= i) return localBanEntry; try
/*		 */		 {
/*	97 */			 localBanEntry.setCreated(a.parse(arrayOfString[i].trim()));
/*		 */		 } catch (ParseException localParseException1) {
/*	99 */			 b.log(Level.WARNING, "Could not read creation date format for ban entry '" + localBanEntry.getName() + "' (was: '" + arrayOfString[i] + "')", localParseException1);
/*		 */		 }
/*		 */ 
/* 102 */		 i++; if (arrayOfString.length <= i) return localBanEntry;
/* 103 */		 localBanEntry.setSource(arrayOfString[i].trim());
/*		 */ 
/* 105 */		 i++; if (arrayOfString.length <= i) return localBanEntry; try
/*		 */		 {
/* 107 */			 String str = arrayOfString[i].trim();
/* 108 */			 if ((!str.equalsIgnoreCase("Forever")) && (str.length() > 0))
/* 109 */				 localBanEntry.setExpires(a.parse(str));
/*		 */		 }
/*		 */		 catch (ParseException localParseException2) {
/* 112 */			 b.log(Level.WARNING, "Could not read expiry date format for ban entry '" + localBanEntry.getName() + "' (was: '" + arrayOfString[i] + "')", localParseException2);
/*		 */		 }
/*		 */ 
/* 115 */		 i++; if (arrayOfString.length <= i) return localBanEntry;
/* 116 */		 localBanEntry.setReason(arrayOfString[i].trim());
/*		 */ 
/* 118 */		 return localBanEntry;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BanEntry
 * JD-Core Version:		0.6.0
 */