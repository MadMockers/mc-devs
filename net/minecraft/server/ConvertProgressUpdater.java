package net.minecraft.server;

import java.util.logging.Logger;

public class ConvertProgressUpdater
	implements IProgressUpdate
{
	private long b = System.currentTimeMillis();

	public ConvertProgressUpdater(MinecraftServer paramMinecraftServer) {
	}

	public void a(String paramString) {
	}

	public void a(int paramInt) {
		if (System.currentTimeMillis() - this.b >= 1000L) {
			this.b = System.currentTimeMillis();
			MinecraftServer.log.info("Converting... " + paramInt + "%");
		}
	}

	public void c(String paramString)
	{
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ConvertProgressUpdater
 * JD-Core Version:		0.6.0
 */