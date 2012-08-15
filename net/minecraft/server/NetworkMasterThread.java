/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ class NetworkMasterThread extends Thread
/*		 */ {
/*		 */	 NetworkMasterThread(NetworkManager paramNetworkManager)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void run()
/*		 */	 {
/*		 */		 try
/*		 */		 {
/* 288 */			 Thread.sleep(5000L);
/* 289 */			 if (NetworkManager.g(this.a).isAlive())
/*		 */				 try {
/* 291 */					 NetworkManager.g(this.a).stop();
/*		 */				 }
/*		 */				 catch (Throwable localThrowable1) {
/*		 */				 }
/* 295 */			 if (NetworkManager.h(this.a).isAlive())
/*		 */				 try {
/* 297 */					 NetworkManager.h(this.a).stop();
/*		 */				 } catch (Throwable localThrowable2) {
/*		 */				 }
/*		 */		 }
/*		 */		 catch (InterruptedException localInterruptedException) {
/* 302 */			 localInterruptedException.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NetworkMasterThread
 * JD-Core Version:		0.6.0
 */