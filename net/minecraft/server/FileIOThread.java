package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileIOThread
	implements Runnable
{
	public static final FileIOThread a = new FileIOThread();

	private List b = Collections.synchronizedList(new ArrayList());

	private volatile long c = 0L;
	private volatile long d = 0L;
	private volatile boolean e = false;

	private FileIOThread() {
		Thread localThread = new Thread(this, "File IO Thread");
		localThread.setPriority(1);
		localThread.start();
	}

	public void run() {
		while (true)
			b();
	}

	private void b()
	{
		for (int i = 0; i < this.b.size(); i++) {
			IAsyncChunkSaver localIAsyncChunkSaver = (IAsyncChunkSaver)this.b.get(i);
			boolean bool = localIAsyncChunkSaver.c();
			if (!bool) {
				this.b.remove(i--);
				this.d += 1L;
			}
			try
			{
				Thread.sleep(this.e ? 0L : 10L);
			} catch (InterruptedException localInterruptedException2) {
				localInterruptedException2.printStackTrace();
			}
		}
		if (this.b.isEmpty())
			try {
				Thread.sleep(25L);
			} catch (InterruptedException localInterruptedException1) {
				localInterruptedException1.printStackTrace();
			}
	}

	public void a(IAsyncChunkSaver paramIAsyncChunkSaver)
	{
		if (this.b.contains(paramIAsyncChunkSaver)) return;
		this.c += 1L;
		this.b.add(paramIAsyncChunkSaver);
	}

	public void a() {
		this.e = true;
		while (this.c != this.d) {
			Thread.sleep(10L);
		}
		this.e = false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.FileIOThread
 * JD-Core Version:		0.6.0
 */