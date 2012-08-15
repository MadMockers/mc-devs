package net.minecraft.server;

import java.util.Random;

public class BlockLockedChest extends Block
{
	protected BlockLockedChest(int paramInt)
	{
		super(paramInt, Material.WOOD);
		this.textureId = 26;
	}

	public int a(int paramInt)
	{
		if (paramInt == 1) return this.textureId - 1;
		if (paramInt == 0) return this.textureId - 1;
		if (paramInt == 3) return this.textureId + 1;
		return this.textureId;
	}

	public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		return true;
	}

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
	{
		paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockLockedChest
 * JD-Core Version:		0.6.0
 */