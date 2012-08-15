package net.minecraft.server;

public abstract interface PlayerFileData
{
	public abstract void save(EntityHuman paramEntityHuman);

	public abstract void load(EntityHuman paramEntityHuman);

	public abstract String[] getSeenPlayers();
}

/* 
 * Qualified Name:		 net.minecraft.server.PlayerFileData
 * JD-Core Version:		0.6.0
 */