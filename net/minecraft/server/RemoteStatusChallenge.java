/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.net.DatagramPacket;
/*		 */ import java.util.Date;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ class RemoteStatusChallenge
/*		 */ {
/*		 */	 private long time;
/*		 */	 private int token;
/*		 */	 private byte[] identity;
/*		 */	 private byte[] response;
/*		 */	 private String f;
/*		 */ 
/*		 */	 public RemoteStatusChallenge(RemoteStatusListener paramRemoteStatusListener, DatagramPacket paramDatagramPacket)
/*		 */	 {
/* 342 */		 this.time = new Date().getTime();
/* 343 */		 byte[] arrayOfByte = paramDatagramPacket.getData();
/* 344 */		 this.identity = new byte[4];
/* 345 */		 this.identity[0] = arrayOfByte[3];
/* 346 */		 this.identity[1] = arrayOfByte[4];
/* 347 */		 this.identity[2] = arrayOfByte[5];
/* 348 */		 this.identity[3] = arrayOfByte[6];
/* 349 */		 this.f = new String(this.identity);
/* 350 */		 this.token = new Random().nextInt(16777216);
/* 351 */		 this.response = String.format("", new Object[] { this.f, Integer.valueOf(this.token) }).getBytes();
/*		 */	 }
/*		 */ 
/*		 */	 public Boolean isExpired(long paramLong) {
/* 355 */		 return Boolean.valueOf(this.time < paramLong);
/*		 */	 }
/*		 */ 
/*		 */	 public int getToken() {
/* 359 */		 return this.token;
/*		 */	 }
/*		 */ 
/*		 */	 public byte[] getChallengeResponse() {
/* 363 */		 return this.response;
/*		 */	 }
/*		 */ 
/*		 */	 public byte[] getIdentityToken() {
/* 367 */		 return this.identity;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RemoteStatusChallenge
 * JD-Core Version:		0.6.0
 */