package net.minecraft.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class RemoteControlSession extends RemoteConnectionThread
{
	private boolean g = false;
	private Socket h;
	private byte[] i = new byte[1460];
	private String j;

	RemoteControlSession(IMinecraftServer paramIMinecraftServer, Socket paramSocket)
	{
		super(paramIMinecraftServer);
		this.h = paramSocket;
		try
		{
			this.h.setSoTimeout(0);
		} catch (Exception localException) {
			this.running = false;
		}

		this.j = paramIMinecraftServer.a("rcon.password", "");
		info("Rcon connection from: " + paramSocket.getInetAddress());
	}

	public void run()
	{
		try {
			while (this.running) {
				BufferedInputStream localBufferedInputStream = new BufferedInputStream(this.h.getInputStream());
				int k = localBufferedInputStream.read(this.i, 0, 1460);

				if (10 > k) {
					return;
				}
				int m = 0;
				int n = StatusChallengeUtils.b(this.i, 0, k);
				if (n != k - 4) {
					return;
				}
				m += 4;
				int i1 = StatusChallengeUtils.b(this.i, m, k);
				m += 4;

				int i2 = StatusChallengeUtils.b(this.i, m);
				m += 4;
				switch (i2) {
				case 3:
					String str1 = StatusChallengeUtils.a(this.i, m, k);
					m += str1.length();
					if ((0 != str1.length()) && (str1.equals(this.j))) {
						this.g = true;

						a(i1, 2, "");
					} else {
						this.g = false;
						f();
					}
					break;
				case 2:
					if (this.g) {
						String str2 = StatusChallengeUtils.a(this.i, m, k);
						try
						{
							a(i1, this.server.i(str2));
						} catch (Exception localException2) {
							a(i1, "Error executing: " + str2 + " (" + localException2.getMessage() + ")");
						}
					} else {
						f();
					}
					break;
				default:
					a(i1, String.format("Unknown request %s", new Object[] { Integer.toHexString(i2) }));
				}
			}
		} catch (SocketTimeoutException localSocketTimeoutException) {
		}
		catch (IOException localIOException) {
		}
		catch (Exception localException1) {
			System.out.println(localException1);
		} finally {
			g();
		}
	}

	private void a(int paramInt1, int paramInt2, String paramString)
	{
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(1248);
		DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
		localDataOutputStream.writeInt(Integer.reverseBytes(paramString.length() + 10));
		localDataOutputStream.writeInt(Integer.reverseBytes(paramInt1));
		localDataOutputStream.writeInt(Integer.reverseBytes(paramInt2));
		localDataOutputStream.writeBytes(paramString);
		localDataOutputStream.write(0);
		localDataOutputStream.write(0);

		this.h.getOutputStream().write(localByteArrayOutputStream.toByteArray());
	}

	private void f()
	{
		a(-1, 2, "");
	}

	private void a(int paramInt, String paramString) {
		int k = paramString.length();
		while (true)
		{
			int m = 4096 <= k ? 4096 : k;
			a(paramInt, 0, paramString.substring(0, m));
			paramString = paramString.substring(m);
			k = paramString.length();
			if (0 == k)
				break;
		}
	}

	private void g()
	{
		if (null == this.h) {
			return;
		}
		try
		{
			this.h.close();
		} catch (IOException localIOException) {
			warning("IO: " + localIOException.getMessage());
		}
		this.h = null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.RemoteControlSession
 * JD-Core Version:		0.6.0
 */