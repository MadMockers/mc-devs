/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public final class ThreadShutdown extends Thread
/*		 */ {
/*		 */	 public ThreadShutdown(DedicatedServer paramDedicatedServer)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void run()
/*		 */	 {
/* 527 */		 this.a.stop();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ThreadShutdown
 * JD-Core Version:		0.6.0
 */