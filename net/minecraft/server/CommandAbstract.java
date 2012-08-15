/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ public abstract class CommandAbstract
/*		 */	 implements ICommand
/*		 */ {
/*	 9 */	 private static ICommandDispatcher a = null;
/*		 */ 
/*		 */	 public String a(ICommandListener paramICommandListener) {
/*	12 */		 return "/" + b();
/*		 */	 }
/*		 */ 
/*		 */	 public List a() {
/*	16 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(ICommandListener paramICommandListener) {
/*	20 */		 return paramICommandListener.b(b());
/*		 */	 }
/*		 */ 
/*		 */	 public List a(ICommandListener paramICommandListener, String[] paramArrayOfString) {
/*	24 */		 return null; } 
/*		 */	 public static int a(ICommandListener paramICommandListener, String paramString) { // Byte code:
/*		 */		 //	 0: aload_1
/*		 */		 //	 1: invokestatic 47	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*		 */		 //	 4: ireturn
/*		 */		 //	 5: astore_2
/*		 */		 //	 6: new 49	net/minecraft/server/ExceptionInvalidNumber
/*		 */		 //	 9: dup
/*		 */		 //	 10: ldc 51
/*		 */		 //	 12: iconst_1
/*		 */		 //	 13: anewarray 4	java/lang/Object
/*		 */		 //	 16: dup
/*		 */		 //	 17: iconst_0
/*		 */		 //	 18: aload_1
/*		 */		 //	 19: aastore
/*		 */		 //	 20: invokespecial 54	net/minecraft/server/ExceptionInvalidNumber:<init>	(Ljava/lang/String;[Ljava/lang/Object;)V
/*		 */		 //	 23: athrow
/*		 */		 //
/*		 */		 // Exception table:
/*		 */		 //	 from	to	target	type
/*		 */		 //	 0	4	5	java/lang/NumberFormatException } 
/*	36 */	 public static int a(ICommandListener paramICommandListener, String paramString, int paramInt) { return a(paramICommandListener, paramString, paramInt, 2147483647); }
/*		 */ 
/*		 */	 public static int a(ICommandListener paramICommandListener, String paramString, int paramInt1, int paramInt2)
/*		 */	 {
/*	40 */		 int i = a(paramICommandListener, paramString);
/*		 */ 
/*	42 */		 if (i < paramInt1)
/*	43 */			 throw new ExceptionInvalidNumber("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt1) });
/*	44 */		 if (i > paramInt2) {
/*	45 */			 throw new ExceptionInvalidNumber("commands.generic.num.tooBig", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt2) });
/*		 */		 }
/*		 */ 
/*	48 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 public static EntityHuman c(ICommandListener paramICommandListener) {
/*	52 */		 if ((paramICommandListener instanceof EntityHuman)) {
/*	53 */			 return (EntityHuman)paramICommandListener;
/*		 */		 }
/*	55 */		 throw new ExceptionPlayerNotFound("You must specify which player you wish to perform this action on.", new Object[0]);
/*		 */	 }
/*		 */ 
/*		 */	 public static String a(String[] paramArrayOfString, int paramInt)
/*		 */	 {
/*	60 */		 StringBuilder localStringBuilder = new StringBuilder();
/*		 */ 
/*	62 */		 for (int i = paramInt; i < paramArrayOfString.length; i++) {
/*	63 */			 if (i > paramInt) localStringBuilder.append(" ");
/*	64 */			 localStringBuilder.append(paramArrayOfString[i]);
/*		 */		 }
/*		 */ 
/*	67 */		 return localStringBuilder.toString();
/*		 */	 }
/*		 */ 
/*		 */	 public static String a(Object[] paramArrayOfObject) {
/*	71 */		 StringBuilder localStringBuilder = new StringBuilder();
/*		 */ 
/*	73 */		 for (int i = 0; i < paramArrayOfObject.length; i++) {
/*	74 */			 String str = paramArrayOfObject[i].toString();
/*		 */ 
/*	76 */			 if (i > 0) {
/*	77 */				 if (i == paramArrayOfObject.length - 1)
/*	78 */					 localStringBuilder.append(" and ");
/*		 */				 else {
/*	80 */					 localStringBuilder.append(", ");
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	84 */			 localStringBuilder.append(str);
/*		 */		 }
/*		 */ 
/*	87 */		 return localStringBuilder.toString();
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean a(String paramString1, String paramString2) {
/*	91 */		 return paramString2.regionMatches(true, 0, paramString1, 0, paramString1.length());
/*		 */	 }
/*		 */ 
/*		 */	 public static List a(String[] paramArrayOfString1, String[] paramArrayOfString2) {
/*	95 */		 String str1 = paramArrayOfString1[(paramArrayOfString1.length - 1)];
/*	96 */		 ArrayList localArrayList = new ArrayList();
/*		 */ 
/*	98 */		 for (String str2 : paramArrayOfString2) {
/*	99 */			 if (a(str1, str2)) {
/* 100 */				 localArrayList.add(str2);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 104 */		 return localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 public static List a(String[] paramArrayOfString, Iterable paramIterable) {
/* 108 */		 String str1 = paramArrayOfString[(paramArrayOfString.length - 1)];
/* 109 */		 ArrayList localArrayList = new ArrayList();
/*		 */ 
/* 111 */		 for (String str2 : paramIterable) {
/* 112 */			 if (a(str1, str2)) {
/* 113 */				 localArrayList.add(str2);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 117 */		 return localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(ICommandListener paramICommandListener, String paramString, Object[] paramArrayOfObject) {
/* 121 */		 a(paramICommandListener, 0, paramString, paramArrayOfObject);
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(ICommandListener paramICommandListener, int paramInt, String paramString, Object[] paramArrayOfObject) {
/* 125 */		 if (a != null)
/* 126 */			 a.a(paramICommandListener, paramInt, paramString, paramArrayOfObject);
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(ICommandDispatcher paramICommandDispatcher)
/*		 */	 {
/* 131 */		 a = paramICommandDispatcher;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(ICommand paramICommand) {
/* 135 */		 return b().compareTo(paramICommand.b());
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandAbstract
 * JD-Core Version:		0.6.0
 */