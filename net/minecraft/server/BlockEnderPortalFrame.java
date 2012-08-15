package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockEnderPortalFrame extends Block
{
	public BlockEnderPortalFrame(int paramInt)
	{
		super(paramInt, 159, Material.SHATTERABLE);
	}

	public int a(int paramInt1, int paramInt2)
	{
		if (paramInt1 == 1) {
			return this.textureId - 1;
		}
		if (paramInt1 == 0) {
			return this.textureId + 16;
		}
		return this.textureId;
	}

	public boolean d()
	{
		return false;
	}

	public int b()
	{
		return 26;
	}

	public void f()
	{
		a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
	{
		a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
		super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);

		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		if (e(i)) {
			a(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		}
		f();
	}

	public static boolean e(int paramInt) {
		return (paramInt & 0x4) != 0;
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return 0;
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
	{
		int i = ((MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3) + 2) % 4;
		paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockEnderPortalFrame
 * JD-Core Version:		0.6.0
 */