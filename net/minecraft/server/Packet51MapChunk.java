/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.IOException;
/*		 */ import java.util.zip.DataFormatException;
/*		 */ import java.util.zip.Inflater;
/*		 */ 
/*		 */ public class Packet51MapChunk extends Packet
/*		 */ {
/*		 */	 public int a;
/*		 */	 public int b;
/*		 */	 public int c;
/*		 */	 public int d;
/*		 */	 public byte[] buffer;
/*		 */	 public byte[] inflatedBuffer;
/*		 */	 public boolean e;
/*		 */	 public int size;
/*	22 */	 private static byte[] buildBuffer = new byte[196864];
/*		 */ 
/*		 */	 public Packet51MapChunk() {
/*	25 */		 this.lowPriority = true;
/*		 */	 }
/*		 */ 
/*		 */	 public Packet51MapChunk(Chunk chunk, boolean flag, int i) {
/*	29 */		 this.lowPriority = true;
/*	30 */		 this.a = chunk.x;
/*	31 */		 this.b = chunk.z;
/*	32 */		 this.e = flag;
/*	33 */		 ChunkMap chunkmap = a(chunk, flag, i);
/*		 */ 
/*	36 */		 this.d = chunkmap.c;
/*	37 */		 this.c = chunkmap.b;
/*		 */ 
/*	50 */		 this.inflatedBuffer = chunkmap.a;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataInputStream datainputstream) throws IOException
/*		 */	 {
/*	55 */		 this.a = datainputstream.readInt();
/*	56 */		 this.b = datainputstream.readInt();
/*	57 */		 this.e = datainputstream.readBoolean();
/*	58 */		 this.c = datainputstream.readShort();
/*	59 */		 this.d = datainputstream.readShort();
/*	60 */		 this.size = datainputstream.readInt();
/*	61 */		 if (buildBuffer.length < this.size) {
/*	62 */			 buildBuffer = new byte[this.size];
/*		 */		 }
/*		 */ 
/*	65 */		 datainputstream.readFully(buildBuffer, 0, this.size);
/*	66 */		 int i = 0;
/*		 */ 
/*	70 */		 for (int j = 0; j < 16; j++) {
/*	71 */			 i += (this.c >> j & 0x1);
/*		 */		 }
/*		 */ 
/*	74 */		 j = 12288 * i;
/*	75 */		 if (this.e) {
/*	76 */			 j += 256;
/*		 */		 }
/*		 */ 
/*	79 */		 this.inflatedBuffer = new byte[j];
/*	80 */		 Inflater inflater = new Inflater();
/*		 */ 
/*	82 */		 inflater.setInput(buildBuffer, 0, this.size);
/*		 */		 try
/*		 */		 {
/*	85 */			 inflater.inflate(this.inflatedBuffer);
/*		 */		 } catch (DataFormatException dataformatexception) {
/*	87 */			 throw new IOException("Bad compressed data format");
/*		 */		 } finally {
/*	89 */			 inflater.end();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream dataoutputstream) throws IOException {
/*	94 */		 dataoutputstream.writeInt(this.a);
/*	95 */		 dataoutputstream.writeInt(this.b);
/*	96 */		 dataoutputstream.writeBoolean(this.e);
/*	97 */		 dataoutputstream.writeShort((short)(this.c & 0xFFFF));
/*	98 */		 dataoutputstream.writeShort((short)(this.d & 0xFFFF));
/*	99 */		 dataoutputstream.writeInt(this.size);
/* 100 */		 dataoutputstream.write(this.buffer, 0, this.size);
/*		 */	 }
/*		 */ 
/*		 */	 public void handle(NetHandler nethandler) {
/* 104 */		 nethandler.a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public int a() {
/* 108 */		 return 17 + this.size;
/*		 */	 }
/*		 */ 
/*		 */	 public static ChunkMap a(Chunk chunk, boolean flag, int i) {
/* 112 */		 int j = 0;
/* 113 */		 ChunkSection[] achunksection = chunk.i();
/* 114 */		 int k = 0;
/* 115 */		 ChunkMap chunkmap = new ChunkMap();
/* 116 */		 byte[] abyte = buildBuffer;
/*		 */ 
/* 118 */		 if (flag) {
/* 119 */			 chunk.seenByPlayer = true;
/*		 */		 }
/*		 */ 
/* 124 */		 for (int l = 0; l < achunksection.length; l++) {
/* 125 */			 if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
/* 126 */				 chunkmap.b |= 1 << l;
/* 127 */				 if (achunksection[l].i() != null) {
/* 128 */					 chunkmap.c |= 1 << l;
/* 129 */					 k++;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 134 */		 for (l = 0; l < achunksection.length; l++) {
/* 135 */			 if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
/* 136 */				 byte[] abyte1 = achunksection[l].g();
/*		 */ 
/* 138 */				 System.arraycopy(abyte1, 0, abyte, j, abyte1.length);
/* 139 */				 j += abyte1.length;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 145 */		 for (l = 0; l < achunksection.length; l++) {
/* 146 */			 if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
/* 147 */				 NibbleArray nibblearray = achunksection[l].j();
/* 148 */				 System.arraycopy(nibblearray.a, 0, abyte, j, nibblearray.a.length);
/* 149 */				 j += nibblearray.a.length;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 153 */		 for (l = 0; l < achunksection.length; l++) {
/* 154 */			 if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
/* 155 */				 NibbleArray nibblearray = achunksection[l].k();
/* 156 */				 System.arraycopy(nibblearray.a, 0, abyte, j, nibblearray.a.length);
/* 157 */				 j += nibblearray.a.length;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 161 */		 for (l = 0; l < achunksection.length; l++) {
/* 162 */			 if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
/* 163 */				 NibbleArray nibblearray = achunksection[l].l();
/* 164 */				 System.arraycopy(nibblearray.a, 0, abyte, j, nibblearray.a.length);
/* 165 */				 j += nibblearray.a.length;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 169 */		 if (k > 0) {
/* 170 */			 for (l = 0; l < achunksection.length; l++) {
/* 171 */				 if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && (achunksection[l].i() != null) && ((i & 1 << l) != 0)) {
/* 172 */					 NibbleArray nibblearray = achunksection[l].i();
/* 173 */					 System.arraycopy(nibblearray.a, 0, abyte, j, nibblearray.a.length);
/* 174 */					 j += nibblearray.a.length;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 179 */		 if (flag) {
/* 180 */			 byte[] abyte2 = chunk.m();
/*		 */ 
/* 182 */			 System.arraycopy(abyte2, 0, abyte, j, abyte2.length);
/* 183 */			 j += abyte2.length;
/*		 */		 }
/*		 */ 
/* 186 */		 chunkmap.a = new byte[j];
/* 187 */		 System.arraycopy(abyte, 0, chunkmap.a, 0, j);
/* 188 */		 return chunkmap;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet51MapChunk
 * JD-Core Version:		0.6.0
 */