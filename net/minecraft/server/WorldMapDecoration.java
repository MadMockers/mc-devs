package net.minecraft.server;

public class WorldMapDecoration
{
	public byte type;
	public byte locX;
	public byte locY;
	public byte rotation;

	public WorldMapDecoration(WorldMap paramWorldMap, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
	{
		this.type = paramByte1;
		this.locX = paramByte2;
		this.locY = paramByte3;
		this.rotation = paramByte4;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldMapDecoration
 * JD-Core Version:		0.6.0
 */