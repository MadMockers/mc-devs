/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.IOException;
/*		 */ import java.util.List;
/*		 */ import java.util.zip.DataFormatException;
/*		 */ import java.util.zip.Inflater;
/*		 */ 
/*		 */ public class Packet56MapChunkBulk extends Packet
/*		 */ {
/*		 */	 private int[] c;
/*		 */	 private int[] d;
/*		 */	 public int[] a;
/*		 */	 public int[] b;
/*		 */	 public byte[] buffer;
/*		 */	 private byte[][] inflatedBuffers;
/*		 */	 public int size;
/*	21 */	 public byte[] buildBuffer = new byte[0];
/*		 */ 
/*		 */	 public Packet56MapChunkBulk() {
/*		 */	 }
/*		 */ 
/*		 */	 public Packet56MapChunkBulk(List list) {
/*	27 */		 int i = list.size();
/*		 */ 
/*	29 */		 this.c = new int[i];
/*	30 */		 this.d = new int[i];
/*	31 */		 this.a = new int[i];
/*	32 */		 this.b = new int[i];
/*	33 */		 this.inflatedBuffers = new byte[i][];
/*	34 */		 int j = 0;
/*		 */ 
/*	36 */		 for (int k = 0; k < i; k++) {
/*	37 */			 Chunk chunk = (Chunk)list.get(k);
/*	38 */			 ChunkMap chunkmap = Packet51MapChunk.a(chunk, true, 65535);
/*		 */ 
/*	40 */			 if (this.buildBuffer.length < j + chunkmap.a.length) {
/*	41 */				 byte[] abyte = new byte[j + chunkmap.a.length];
/*		 */ 
/*	43 */				 System.arraycopy(this.buildBuffer, 0, abyte, 0, this.buildBuffer.length);
/*	44 */				 this.buildBuffer = abyte;
/*		 */			 }
/*		 */ 
/*	47 */			 System.arraycopy(chunkmap.a, 0, this.buildBuffer, j, chunkmap.a.length);
/*	48 */			 j += chunkmap.a.length;
/*	49 */			 this.c[k] = chunk.x;
/*	50 */			 this.d[k] = chunk.z;
/*	51 */			 this.a[k] = chunkmap.b;
/*	52 */			 this.b[k] = chunkmap.c;
/*	53 */			 this.inflatedBuffers[k] = chunkmap.a;
/*		 */		 }
/*		 */ 
/*	68 */		 this.lowPriority = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataInputStream datainputstream) throws IOException
/*		 */	 {
/*	73 */		 short short1 = datainputstream.readShort();
/*		 */ 
/*	75 */		 this.size = datainputstream.readInt();
/*	76 */		 this.c = new int[short1];
/*	77 */		 this.d = new int[short1];
/*	78 */		 this.a = new int[short1];
/*	79 */		 this.b = new int[short1];
/*	80 */		 this.inflatedBuffers = new byte[short1][];
/*	81 */		 if (this.buildBuffer.length < this.size) {
/*	82 */			 this.buildBuffer = new byte[this.size];
/*		 */		 }
/*		 */ 
/*	85 */		 datainputstream.readFully(this.buildBuffer, 0, this.size);
/*	86 */		 byte[] abyte = new byte[196864 * short1];
/*	87 */		 Inflater inflater = new Inflater();
/*		 */ 
/*	89 */		 inflater.setInput(this.buildBuffer, 0, this.size);
/*		 */		 try
/*		 */		 {
/*	92 */			 inflater.inflate(abyte);
/*		 */		 } catch (DataFormatException dataformatexception) {
/*	94 */			 throw new IOException("Bad compressed data format");
/*		 */		 } finally {
/*	96 */			 inflater.end();
/*		 */		 }
/*		 */ 
/*	99 */		 int i = 0;
/*		 */ 
/* 101 */		 for (int j = 0; j < short1; j++) {
/* 102 */			 this.c[j] = datainputstream.readInt();
/* 103 */			 this.d[j] = datainputstream.readInt();
/* 104 */			 this.a[j] = datainputstream.readShort();
/* 105 */			 this.b[j] = datainputstream.readShort();
/* 106 */			 int k = 0;
/*		 */ 
/* 110 */			 for (int l = 0; l < 16; l++) {
/* 111 */				 k += (this.a[j] >> l & 0x1);
/*		 */			 }
/*		 */ 
/* 114 */			 l = 10240 * k + 256;
/* 115 */			 this.inflatedBuffers[j] = new byte[l];
/* 116 */			 System.arraycopy(abyte, i, this.inflatedBuffers[j], 0, l);
/* 117 */			 i += l;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream dataoutputstream) throws IOException {
/* 122 */		 dataoutputstream.writeShort(this.c.length);
/* 123 */		 dataoutputstream.writeInt(this.size);
/* 124 */		 dataoutputstream.write(this.buffer, 0, this.size);
/*		 */ 
/* 126 */		 for (int i = 0; i < this.c.length; i++) {
/* 127 */			 dataoutputstream.writeInt(this.c[i]);
/* 128 */			 dataoutputstream.writeInt(this.d[i]);
/* 129 */			 dataoutputstream.writeShort((short)(this.a[i] & 0xFFFF));
/* 130 */			 dataoutputstream.writeShort((short)(this.b[i] & 0xFFFF));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void handle(NetHandler nethandler) {
/* 135 */		 nethandler.a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public int a() {
/* 139 */		 return 6 + this.size + 12 * d();
/*		 */	 }
/*		 */ 
/*		 */	 public int d() {
/* 143 */		 return this.c.length;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a_() {
/* 147 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet56MapChunkBulk
 * JD-Core Version:		0.6.0
 */