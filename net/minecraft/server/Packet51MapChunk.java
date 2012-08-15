package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class Packet51MapChunk extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public byte[] buffer;
	public byte[] inflatedBuffer;
	public boolean e;
	public int size;
	private static byte[] buildBuffer = new byte[196864];

	public Packet51MapChunk() {
		this.lowPriority = true;
	}

	public Packet51MapChunk(Chunk chunk, boolean flag, int i) {
		this.lowPriority = true;
		this.a = chunk.x;
		this.b = chunk.z;
		this.e = flag;
		ChunkMap chunkmap = a(chunk, flag, i);

		this.d = chunkmap.c;
		this.c = chunkmap.b;

		this.inflatedBuffer = chunkmap.a;
	}

	public void a(DataInputStream datainputstream) throws IOException
	{
		this.a = datainputstream.readInt();
		this.b = datainputstream.readInt();
		this.e = datainputstream.readBoolean();
		this.c = datainputstream.readShort();
		this.d = datainputstream.readShort();
		this.size = datainputstream.readInt();
		if (buildBuffer.length < this.size) {
			buildBuffer = new byte[this.size];
		}

		datainputstream.readFully(buildBuffer, 0, this.size);
		int i = 0;

		for (int j = 0; j < 16; j++) {
			i += (this.c >> j & 0x1);
		}

		j = 12288 * i;
		if (this.e) {
			j += 256;
		}

		this.inflatedBuffer = new byte[j];
		Inflater inflater = new Inflater();

		inflater.setInput(buildBuffer, 0, this.size);
		try
		{
			inflater.inflate(this.inflatedBuffer);
		} catch (DataFormatException dataformatexception) {
			throw new IOException("Bad compressed data format");
		} finally {
			inflater.end();
		}
	}

	public void a(DataOutputStream dataoutputstream) throws IOException {
		dataoutputstream.writeInt(this.a);
		dataoutputstream.writeInt(this.b);
		dataoutputstream.writeBoolean(this.e);
		dataoutputstream.writeShort((short)(this.c & 0xFFFF));
		dataoutputstream.writeShort((short)(this.d & 0xFFFF));
		dataoutputstream.writeInt(this.size);
		dataoutputstream.write(this.buffer, 0, this.size);
	}

	public void handle(NetHandler nethandler) {
		nethandler.a(this);
	}

	public int a() {
		return 17 + this.size;
	}

	public static ChunkMap a(Chunk chunk, boolean flag, int i) {
		int j = 0;
		ChunkSection[] achunksection = chunk.i();
		int k = 0;
		ChunkMap chunkmap = new ChunkMap();
		byte[] abyte = buildBuffer;

		if (flag) {
			chunk.seenByPlayer = true;
		}

		for (int l = 0; l < achunksection.length; l++) {
			if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
				chunkmap.b |= 1 << l;
				if (achunksection[l].i() != null) {
					chunkmap.c |= 1 << l;
					k++;
				}
			}
		}

		for (l = 0; l < achunksection.length; l++) {
			if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
				byte[] abyte1 = achunksection[l].g();

				System.arraycopy(abyte1, 0, abyte, j, abyte1.length);
				j += abyte1.length;
			}

		}

		for (l = 0; l < achunksection.length; l++) {
			if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
				NibbleArray nibblearray = achunksection[l].j();
				System.arraycopy(nibblearray.a, 0, abyte, j, nibblearray.a.length);
				j += nibblearray.a.length;
			}
		}

		for (l = 0; l < achunksection.length; l++) {
			if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
				NibbleArray nibblearray = achunksection[l].k();
				System.arraycopy(nibblearray.a, 0, abyte, j, nibblearray.a.length);
				j += nibblearray.a.length;
			}
		}

		for (l = 0; l < achunksection.length; l++) {
			if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && ((i & 1 << l) != 0)) {
				NibbleArray nibblearray = achunksection[l].l();
				System.arraycopy(nibblearray.a, 0, abyte, j, nibblearray.a.length);
				j += nibblearray.a.length;
			}
		}

		if (k > 0) {
			for (l = 0; l < achunksection.length; l++) {
				if ((achunksection[l] != null) && ((!flag) || (!achunksection[l].a())) && (achunksection[l].i() != null) && ((i & 1 << l) != 0)) {
					NibbleArray nibblearray = achunksection[l].i();
					System.arraycopy(nibblearray.a, 0, abyte, j, nibblearray.a.length);
					j += nibblearray.a.length;
				}
			}
		}

		if (flag) {
			byte[] abyte2 = chunk.m();

			System.arraycopy(abyte2, 0, abyte, j, abyte2.length);
			j += abyte2.length;
		}

		chunkmap.a = new byte[j];
		System.arraycopy(abyte, 0, chunkmap.a, 0, j);
		return chunkmap;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet51MapChunk
 * JD-Core Version:		0.6.0
 */