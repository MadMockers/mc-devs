/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.BufferedInputStream;
/*		 */ import java.io.ByteArrayOutputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.IOException;
/*		 */ import java.io.OutputStream;
/*		 */ import java.io.PrintStream;
/*		 */ import java.net.Socket;
/*		 */ import java.net.SocketTimeoutException;
/*		 */ 
/*		 */ public class RemoteControlSession extends RemoteConnectionThread
/*		 */ {
/*	23 */	 private boolean g = false;
/*		 */	 private Socket h;
/*	25 */	 private byte[] i = new byte[1460];
/*		 */	 private String j;
/*		 */ 
/*		 */	 RemoteControlSession(IMinecraftServer paramIMinecraftServer, Socket paramSocket)
/*		 */	 {
/*	29 */		 super(paramIMinecraftServer);
/*	30 */		 this.h = paramSocket;
/*		 */		 try
/*		 */		 {
/*	33 */			 this.h.setSoTimeout(0);
/*		 */		 } catch (Exception localException) {
/*	35 */			 this.running = false;
/*		 */		 }
/*		 */ 
/*	38 */		 this.j = paramIMinecraftServer.a("rcon.password", "");
/*	39 */		 info("Rcon connection from: " + paramSocket.getInetAddress());
/*		 */	 }
/*		 */ 
/*		 */	 public void run()
/*		 */	 {
/*		 */		 try {
/*	45 */			 while (this.running) {
/*	46 */				 BufferedInputStream localBufferedInputStream = new BufferedInputStream(this.h.getInputStream());
/*	47 */				 int k = localBufferedInputStream.read(this.i, 0, 1460);
/*		 */ 
/*	49 */				 if (10 > k) {
/*		 */					 return;
/*		 */				 }
/*	53 */				 int m = 0;
/*	54 */				 int n = StatusChallengeUtils.b(this.i, 0, k);
/*	55 */				 if (n != k - 4) {
/*		 */					 return;
/*		 */				 }
/*	59 */				 m += 4;
/*	60 */				 int i1 = StatusChallengeUtils.b(this.i, m, k);
/*	61 */				 m += 4;
/*		 */ 
/*	63 */				 int i2 = StatusChallengeUtils.b(this.i, m);
/*	64 */				 m += 4;
/*	65 */				 switch (i2) {
/*		 */				 case 3:
/*	67 */					 String str1 = StatusChallengeUtils.a(this.i, m, k);
/*	68 */					 m += str1.length();
/*	69 */					 if ((0 != str1.length()) && (str1.equals(this.j))) {
/*	70 */						 this.g = true;
/*		 */ 
/*	72 */						 a(i1, 2, "");
/*		 */					 } else {
/*	74 */						 this.g = false;
/*	75 */						 f();
/*		 */					 }
/*	77 */					 break;
/*		 */				 case 2:
/*	79 */					 if (this.g) {
/*	80 */						 String str2 = StatusChallengeUtils.a(this.i, m, k);
/*		 */						 try
/*		 */						 {
/*	83 */							 a(i1, this.server.i(str2));
/*		 */						 } catch (Exception localException2) {
/*	85 */							 a(i1, "Error executing: " + str2 + " (" + localException2.getMessage() + ")");
/*		 */						 }
/*		 */					 } else {
/*	88 */						 f();
/*		 */					 }
/*	90 */					 break;
/*		 */				 default:
/*	92 */					 a(i1, String.format("Unknown request %s", new Object[] { Integer.toHexString(i2) }));
/*		 */				 }
/*		 */			 }
/*		 */		 } catch (SocketTimeoutException localSocketTimeoutException) {
/*		 */		 }
/*		 */		 catch (IOException localIOException) {
/*		 */		 }
/*		 */		 catch (Exception localException1) {
/* 100 */			 System.out.println(localException1);
/*		 */		 } finally {
/* 102 */			 g();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int paramInt1, int paramInt2, String paramString)
/*		 */	 {
/* 109 */		 ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(1248);
/* 110 */		 DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
/* 111 */		 localDataOutputStream.writeInt(Integer.reverseBytes(paramString.length() + 10));
/* 112 */		 localDataOutputStream.writeInt(Integer.reverseBytes(paramInt1));
/* 113 */		 localDataOutputStream.writeInt(Integer.reverseBytes(paramInt2));
/* 114 */		 localDataOutputStream.writeBytes(paramString);
/* 115 */		 localDataOutputStream.write(0);
/* 116 */		 localDataOutputStream.write(0);
/*		 */ 
/* 118 */		 this.h.getOutputStream().write(localByteArrayOutputStream.toByteArray());
/*		 */	 }
/*		 */ 
/*		 */	 private void f()
/*		 */	 {
/* 123 */		 a(-1, 2, "");
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int paramInt, String paramString) {
/* 127 */		 int k = paramString.length();
/*		 */		 while (true)
/*		 */		 {
/* 131 */			 int m = 4096 <= k ? 4096 : k;
/* 132 */			 a(paramInt, 0, paramString.substring(0, m));
/* 133 */			 paramString = paramString.substring(m);
/* 134 */			 k = paramString.length();
/* 135 */			 if (0 == k)
/*		 */				 break;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void g()
/*		 */	 {
/* 148 */		 if (null == this.h) {
/* 149 */			 return;
/*		 */		 }
/*		 */		 try
/*		 */		 {
/* 153 */			 this.h.close();
/*		 */		 } catch (IOException localIOException) {
/* 155 */			 warning("IO: " + localIOException.getMessage());
/*		 */		 }
/* 157 */		 this.h = null;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RemoteControlSession
 * JD-Core Version:		0.6.0
 */