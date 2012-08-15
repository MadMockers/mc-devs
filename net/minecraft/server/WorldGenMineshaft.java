package net.minecraft.server;

import java.util.Random;

public class WorldGenMineshaft extends StructureGenerator
{
	protected boolean a(int paramInt1, int paramInt2)
	{
		return (this.b.nextInt(100) == 0) && (this.b.nextInt(80) < Math.max(Math.abs(paramInt1), Math.abs(paramInt2)));
	}

	protected StructureStart b(int paramInt1, int paramInt2)
	{
		return new WorldGenMineshaftStart(this.c, this.b, paramInt1, paramInt2);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenMineshaft
 * JD-Core Version:		0.6.0
 */