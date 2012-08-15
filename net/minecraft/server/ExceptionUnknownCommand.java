/*	 */ package net.minecraft.server;
/*	 */ 
/*	 */ public class ExceptionUnknownCommand extends CommandException
/*	 */ {
/*	 */	 public ExceptionUnknownCommand()
/*	 */	 {
/* 5 */		 this("commands.generic.notFound", new Object[0]);
/*	 */	 }
/*	 */ 
/*	 */	 public ExceptionUnknownCommand(String paramString, Object[] paramArrayOfObject) {
/* 9 */		 super(paramString, paramArrayOfObject);
/*	 */	 }
/*	 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ExceptionUnknownCommand
 * JD-Core Version:		0.6.0
 */