/*	 */ package net.minecraft.server;
/*	 */ 
/*	 */ public class ExceptionInvalidNumber extends CommandException
/*	 */ {
/*	 */	 public ExceptionInvalidNumber()
/*	 */	 {
/* 5 */		 this("commands.generic.num.invalid", new Object[0]);
/*	 */	 }
/*	 */ 
/*	 */	 public ExceptionInvalidNumber(String paramString, Object[] paramArrayOfObject) {
/* 9 */		 super(paramString, paramArrayOfObject);
/*	 */	 }
/*	 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ExceptionInvalidNumber
 * JD-Core Version:		0.6.0
 */