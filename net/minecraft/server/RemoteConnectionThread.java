package net.minecraft.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class RemoteConnectionThread
	implements Runnable
{
	protected boolean running = false;
	protected IMinecraftServer server;
	protected Thread thread;
	protected int d = 5;
	protected List e = new ArrayList();
	protected List f = new ArrayList();

	RemoteConnectionThread(IMinecraftServer paramIMinecraftServer) {
		this.server = paramIMinecraftServer;
		if (this.server.isDebugging())
			warning("Debugging is enabled, performance maybe reduced!");
	}

	public synchronized void a()
	{
		this.thread = new Thread(this);
		this.thread.start();
		this.running = true;
	}

	public boolean c()
	{
		return this.running;
	}

	protected void debug(String paramString) {
		this.server.k(paramString);
	}

	protected void info(String paramString) {
		this.server.info(paramString);
	}

	protected void warning(String paramString) {
		this.server.warning(paramString);
	}

	protected void error(String paramString) {
		this.server.j(paramString);
	}

	protected int d() {
		return this.server.x();
	}

	protected void a(DatagramSocket paramDatagramSocket) {
		debug("registerSocket: " + paramDatagramSocket);
		this.e.add(paramDatagramSocket);
	}

	protected boolean a(DatagramSocket paramDatagramSocket, boolean paramBoolean)
	{
		debug("closeSocket: " + paramDatagramSocket);
		if (null == paramDatagramSocket) {
			return false;
		}

		int i = 0;
		if (!paramDatagramSocket.isClosed()) {
			paramDatagramSocket.close();
			i = 1;
		}

		if (paramBoolean) {
			this.e.remove(paramDatagramSocket);
		}

		return i;
	}

	protected boolean b(ServerSocket paramServerSocket) {
		return a(paramServerSocket, true);
	}

	protected boolean a(ServerSocket paramServerSocket, boolean paramBoolean) {
		debug("closeSocket: " + paramServerSocket);
		if (null == paramServerSocket) {
			return false;
		}

		int i = 0;
		try {
			if (!paramServerSocket.isClosed()) {
				paramServerSocket.close();
				i = 1;
			}
		} catch (IOException localIOException) {
			warning("IO: " + localIOException.getMessage());
		}

		if (paramBoolean) {
			this.f.remove(paramServerSocket);
		}

		return i;
	}

	protected void e() {
		a(false);
	}

	protected void a(boolean paramBoolean) {
		int i = 0;
		for (Iterator localIterator = this.e.iterator(); localIterator.hasNext(); ) { localObject = (DatagramSocket)localIterator.next();
			if (a((DatagramSocket)localObject, false))
				i++;
		}
		Object localObject;
		this.e.clear();

		for (localIterator = this.f.iterator(); localIterator.hasNext(); ) { localObject = (ServerSocket)localIterator.next();
			if (a((ServerSocket)localObject, false)) {
				i++;
			}
		}
		this.f.clear();

		if ((paramBoolean) && (0 < i))
			warning("Force closed " + i + " sockets");
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.RemoteConnectionThread
 * JD-Core Version:		0.6.0
 */