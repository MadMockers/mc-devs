/*	 */ package net.minecraft.server;
/*	 */ 
/*	 */ public class ExceptionInvalidSyntax extends CommandException
/*	 */ {
/*	 */	 public ExceptionInvalidSyntax()
/*	 */	 {
/* 5 */		 this("commands.generic.snytax", new Object[0]);
/*	 */	 }
/*	 */ 
/*	 */	 public ExceptionInvalidSyntax(String paramString, Object[] paramArrayOfObject) {
/* 9 */		 super(paramString, paramArrayOfObject);
/*	 */	 }
/*	 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ExceptionInvalidSyntax
 * JD-Core Version:		0.6.0
 */