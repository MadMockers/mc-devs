/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class ThreadServerApplication extends Thread
/*		 */ {
/*		 */	 public ThreadServerApplication(MinecraftServer paramMinecraftServer, String paramString)
/*		 */	 {
/* 536 */		 super(paramString);
/*		 */	 }
/*		 */	 public void run() {
/* 539 */		 this.a.run();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ThreadServerApplication
 * JD-Core Version:		0.6.0
 */