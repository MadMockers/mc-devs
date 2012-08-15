/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.logging.Logger;
/*		 */ 
/*		 */ public class ConvertProgressUpdater
/*		 */	 implements IProgressUpdate
/*		 */ {
/* 108 */	 private long b = System.currentTimeMillis();
/*		 */ 
/*		 */	 public ConvertProgressUpdater(MinecraftServer paramMinecraftServer) {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString) {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt) {
/* 117 */		 if (System.currentTimeMillis() - this.b >= 1000L) {
/* 118 */			 this.b = System.currentTimeMillis();
/* 119 */			 MinecraftServer.log.info("Converting... " + paramInt + "%");
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void c(String paramString)
/*		 */	 {
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ConvertProgressUpdater
 * JD-Core Version:		0.6.0
 */