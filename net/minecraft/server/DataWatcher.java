package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataWatcher
{
	private static final HashMap a = new HashMap();
	private final Map b;
	private boolean c;
	private ReadWriteLock d;

	public DataWatcher()
	{
		this.b = new HashMap();

		this.d = new ReentrantReadWriteLock();
	}
	public void a(int paramInt, Object paramObject) {
		Integer localInteger = (Integer)a.get(paramObject.getClass());
		if (localInteger == null) {
			throw new IllegalArgumentException("Unknown data type: " + paramObject.getClass());
		}
		if (paramInt > 31) {
			throw new IllegalArgumentException("Data value id is too big with " + paramInt + "! (Max is " + 31 + ")");
		}
		if (this.b.containsKey(Integer.valueOf(paramInt))) {
			throw new IllegalArgumentException("Duplicate id value for " + paramInt + "!");
		}

		WatchableObject localWatchableObject = new WatchableObject(localInteger.intValue(), paramInt, paramObject);
		this.d.writeLock().lock();
		this.b.put(Integer.valueOf(paramInt), localWatchableObject);
		this.d.writeLock().unlock();
	}

	public byte getByte(int paramInt) {
		return ((Byte)i(paramInt).b()).byteValue();
	}

	public short getShort(int paramInt) {
		return ((Short)i(paramInt).b()).shortValue();
	}

	public int getInt(int paramInt) {
		return ((Integer)i(paramInt).b()).intValue();
	}

	public String getString(int paramInt)
	{
		return (String)i(paramInt).b();
	}
	private WatchableObject i(int paramInt) {
		this.d.readLock().lock();
		WatchableObject localWatchableObject;
		try {
			localWatchableObject = (WatchableObject)this.b.get(Integer.valueOf(paramInt));
		} catch (Throwable localThrowable) {
			CrashReport localCrashReport = new CrashReport("getting synched entity data", localThrowable);

			localCrashReport.a("EntityData ID", Integer.valueOf(paramInt));
			throw new ReportedException(localCrashReport);
		}

		this.d.readLock().unlock();
		return localWatchableObject;
	}

	public void watch(int paramInt, Object paramObject)
	{
		WatchableObject localWatchableObject = i(paramInt);

		if (!paramObject.equals(localWatchableObject.b())) {
			localWatchableObject.a(paramObject);
			localWatchableObject.a(true);
			this.c = true;
		}
	}

	public boolean a()
	{
		return this.c;
	}

	public static void a(List paramList, DataOutputStream paramDataOutputStream) {
		if (paramList != null) {
			for (WatchableObject localWatchableObject : paramList) {
				a(paramDataOutputStream, localWatchableObject);
			}

		}

		paramDataOutputStream.writeByte(127);
	}

	public List b() {
		ArrayList localArrayList = null;

		if (this.c) {
			this.d.readLock().lock();
			for (WatchableObject localWatchableObject : this.b.values()) {
				if (localWatchableObject.d()) {
					localWatchableObject.a(false);

					if (localArrayList == null) {
						localArrayList = new ArrayList();
					}
					localArrayList.add(localWatchableObject);
				}
			}
			this.d.readLock().unlock();
		}
		this.c = false;

		return localArrayList;
	}

	public void a(DataOutputStream paramDataOutputStream) {
		this.d.readLock().lock();
		for (WatchableObject localWatchableObject : this.b.values()) {
			a(paramDataOutputStream, localWatchableObject);
		}
		this.d.readLock().unlock();

		paramDataOutputStream.writeByte(127);
	}

	private static void a(DataOutputStream paramDataOutputStream, WatchableObject paramWatchableObject)
	{
		int i = (paramWatchableObject.c() << 5 | paramWatchableObject.a() & 0x1F) & 0xFF;
		paramDataOutputStream.writeByte(i);
		Object localObject;
		switch (paramWatchableObject.c()) {
		case 0:
			paramDataOutputStream.writeByte(((Byte)paramWatchableObject.b()).byteValue());
			break;
		case 1:
			paramDataOutputStream.writeShort(((Short)paramWatchableObject.b()).shortValue());
			break;
		case 2:
			paramDataOutputStream.writeInt(((Integer)paramWatchableObject.b()).intValue());
			break;
		case 3:
			paramDataOutputStream.writeFloat(((Float)paramWatchableObject.b()).floatValue());
			break;
		case 4:
			Packet.a((String)paramWatchableObject.b(), paramDataOutputStream);
			break;
		case 5:
			localObject = (ItemStack)paramWatchableObject.b();
			paramDataOutputStream.writeShort(((ItemStack)localObject).getItem().id);
			paramDataOutputStream.writeByte(((ItemStack)localObject).count);
			paramDataOutputStream.writeShort(((ItemStack)localObject).getData());

			break;
		case 6:
			localObject = (ChunkCoordinates)paramWatchableObject.b();
			paramDataOutputStream.writeInt(((ChunkCoordinates)localObject).x);
			paramDataOutputStream.writeInt(((ChunkCoordinates)localObject).y);
			paramDataOutputStream.writeInt(((ChunkCoordinates)localObject).z);
		}
	}

	public static List a(DataInputStream paramDataInputStream)
	{
		ArrayList localArrayList = null;

		int i = paramDataInputStream.readByte();

		while (i != 127)
		{
			if (localArrayList == null) {
				localArrayList = new ArrayList();
			}

			int j = (i & 0xE0) >> 5;
			int k = i & 0x1F;

			WatchableObject localWatchableObject = null;
			switch (j) {
			case 0:
				localWatchableObject = new WatchableObject(j, k, Byte.valueOf(paramDataInputStream.readByte()));
				break;
			case 1:
				localWatchableObject = new WatchableObject(j, k, Short.valueOf(paramDataInputStream.readShort()));
				break;
			case 2:
				localWatchableObject = new WatchableObject(j, k, Integer.valueOf(paramDataInputStream.readInt()));
				break;
			case 3:
				localWatchableObject = new WatchableObject(j, k, Float.valueOf(paramDataInputStream.readFloat()));
				break;
			case 4:
				localWatchableObject = new WatchableObject(j, k, Packet.a(paramDataInputStream, 64));
				break;
			case 5:
				int m = paramDataInputStream.readShort();
				int n = paramDataInputStream.readByte();
				int i1 = paramDataInputStream.readShort();
				localWatchableObject = new WatchableObject(j, k, new ItemStack(m, n, i1));
				break;
			case 6:
				int i2 = paramDataInputStream.readInt();
				int i3 = paramDataInputStream.readInt();
				int i4 = paramDataInputStream.readInt();
				localWatchableObject = new WatchableObject(j, k, new ChunkCoordinates(i2, i3, i4));
			}

			localArrayList.add(localWatchableObject);

			i = paramDataInputStream.readByte();
		}

		return localArrayList;
	}

	static
	{
		a.put(Byte.class, Integer.valueOf(0));
		a.put(Short.class, Integer.valueOf(1));
		a.put(Integer.class, Integer.valueOf(2));
		a.put(Float.class, Integer.valueOf(3));
		a.put(String.class, Integer.valueOf(4));
		a.put(ItemStack.class, Integer.valueOf(5));
		a.put(ChunkCoordinates.class, Integer.valueOf(6));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.DataWatcher
 * JD-Core Version:		0.6.0
 */