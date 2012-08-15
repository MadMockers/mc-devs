/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ class NetworkMonitorThread extends Thread
/*		 */ {
/*		 */	 NetworkMonitorThread(NetworkManager paramNetworkManager)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void run()
/*		 */	 {
/*		 */		 try
/*		 */		 {
/* 358 */			 Thread.sleep(2000L);
/* 359 */			 if (NetworkManager.a(this.a)) {
/* 360 */				 NetworkManager.h(this.a).interrupt();
/* 361 */				 this.a.a("disconnect.closed", new Object[0]);
/*		 */			 }
/*		 */		 } catch (Exception localException) {
/* 364 */			 localException.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NetworkMonitorThread
 * JD-Core Version:		0.6.0
 */