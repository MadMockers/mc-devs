/*	 */ package net.minecraft.server;
/*	 */ 
/*	 */ public class ExceptionPlayerNotFound extends CommandException
/*	 */ {
/*	 */	 public ExceptionPlayerNotFound()
/*	 */	 {
/* 5 */		 this("commands.generic.player.notFound", new Object[0]);
/*	 */	 }
/*	 */ 
/*	 */	 public ExceptionPlayerNotFound(String paramString, Object[] paramArrayOfObject) {
/* 9 */		 super(paramString, paramArrayOfObject);
/*	 */	 }
/*	 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ExceptionPlayerNotFound
 * JD-Core Version:		0.6.0
 */