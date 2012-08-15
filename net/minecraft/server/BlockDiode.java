package net.minecraft.server;

import java.util.Random;

public class BlockDiode extends BlockDirectional
{
	public static final double[] a = { -0.0625D, 0.0625D, 0.1875D, 0.3125D };

	private static final int[] b = { 1, 2, 3, 4 };
	private final boolean c;

	protected BlockDiode(int paramInt, boolean paramBoolean)
	{
		super(paramInt, 6, Material.ORIENTABLE);
		this.c = paramBoolean;
		a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	public boolean c()
	{
		return false;
	}

	public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (!paramWorld.t(paramInt1, paramInt2 - 1, paramInt3)) {
			return false;
		}
		return super.canPlace(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public boolean d(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (!paramWorld.t(paramInt1, paramInt2 - 1, paramInt3)) {
			return false;
		}
		return super.d(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		boolean bool = e(paramWorld, paramInt1, paramInt2, paramInt3, i);
		if ((this.c) && (!bool)) {
			paramWorld.setTypeIdAndData(paramInt1, paramInt2, paramInt3, Block.DIODE_OFF.id, i);
		} else if (!this.c)
		{
			paramWorld.setTypeIdAndData(paramInt1, paramInt2, paramInt3, Block.DIODE_ON.id, i);
			if (!bool) {
				int j = (i & 0xC) >> 2;
				paramWorld.a(paramInt1, paramInt2, paramInt3, Block.DIODE_ON.id, b[j] * 2);
			}
		}
	}

	public int a(int paramInt1, int paramInt2)
	{
		if (paramInt1 == 0) {
			if (this.c) {
				return 99;
			}
			return 115;
		}
		if (paramInt1 == 1) {
			if (this.c) {
				return 147;
			}
			return 131;
		}

		return 5;
	}

	public int b()
	{
		return 15;
	}

	public int a(int paramInt)
	{
		return a(paramInt, 0);
	}

	public boolean c(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		return a(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public boolean a(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if (!this.c) {
			return false;
		}

		int i = d(paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3));

		if ((i == 0) && (paramInt4 == 3)) return true;
		if ((i == 1) && (paramInt4 == 4)) return true;
		if ((i == 2) && (paramInt4 == 2)) return true;
		return (i == 3) && (paramInt4 == 5);
	}

	public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if (!d(paramWorld, paramInt1, paramInt2, paramInt3)) {
			c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
			paramWorld.applyPhysics(paramInt1 + 1, paramInt2, paramInt3, this.id);
			paramWorld.applyPhysics(paramInt1 - 1, paramInt2, paramInt3, this.id);
			paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 + 1, this.id);
			paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 - 1, this.id);
			paramWorld.applyPhysics(paramInt1, paramInt2 - 1, paramInt3, this.id);
			paramWorld.applyPhysics(paramInt1, paramInt2 + 1, paramInt3, this.id);
			return;
		}

		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);

		boolean bool = e(paramWorld, paramInt1, paramInt2, paramInt3, i);
		int j = (i & 0xC) >> 2;
		if (((this.c) && (!bool)) || ((!this.c) && (bool)))
			paramWorld.a(paramInt1, paramInt2, paramInt3, this.id, b[j] * 2);
	}

	private boolean e(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int i = d(paramInt4);
		switch (i) {
		case 0:
			return (paramWorld.isBlockFaceIndirectlyPowered(paramInt1, paramInt2, paramInt3 + 1, 3)) || ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == Block.REDSTONE_WIRE.id) && (paramWorld.getData(paramInt1, paramInt2, paramInt3 + 1) > 0));
		case 2:
			return (paramWorld.isBlockFaceIndirectlyPowered(paramInt1, paramInt2, paramInt3 - 1, 2)) || ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == Block.REDSTONE_WIRE.id) && (paramWorld.getData(paramInt1, paramInt2, paramInt3 - 1) > 0));
		case 3:
			return (paramWorld.isBlockFaceIndirectlyPowered(paramInt1 + 1, paramInt2, paramInt3, 5)) || ((paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == Block.REDSTONE_WIRE.id) && (paramWorld.getData(paramInt1 + 1, paramInt2, paramInt3) > 0));
		case 1:
			return (paramWorld.isBlockFaceIndirectlyPowered(paramInt1 - 1, paramInt2, paramInt3, 4)) || ((paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == Block.REDSTONE_WIRE.id) && (paramWorld.getData(paramInt1 - 1, paramInt2, paramInt3) > 0));
		}
		return false;
	}

	public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		int j = (i & 0xC) >> 2;
		j = j + 1 << 2 & 0xC;

		paramWorld.setData(paramInt1, paramInt2, paramInt3, j | i & 0x3);
		return true;
	}

	public boolean isPowerSource()
	{
		return true;
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
	{
		int i = ((MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3) + 2) % 4;
		paramWorld.setData(paramInt1, paramInt2, paramInt3, i);

		boolean bool = e(paramWorld, paramInt1, paramInt2, paramInt3, i);
		if (bool)
			paramWorld.a(paramInt1, paramInt2, paramInt3, this.id, 1);
	}

	public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		paramWorld.applyPhysics(paramInt1 + 1, paramInt2, paramInt3, this.id);
		paramWorld.applyPhysics(paramInt1 - 1, paramInt2, paramInt3, this.id);
		paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 + 1, this.id);
		paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 - 1, this.id);
		paramWorld.applyPhysics(paramInt1, paramInt2 - 1, paramInt3, this.id);
		paramWorld.applyPhysics(paramInt1, paramInt2 + 1, paramInt3, this.id);
	}

	public void postBreak(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if (this.c) {
			paramWorld.applyPhysics(paramInt1 + 1, paramInt2, paramInt3, this.id);
			paramWorld.applyPhysics(paramInt1 - 1, paramInt2, paramInt3, this.id);
			paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 + 1, this.id);
			paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 - 1, this.id);
			paramWorld.applyPhysics(paramInt1, paramInt2 - 1, paramInt3, this.id);
			paramWorld.applyPhysics(paramInt1, paramInt2 + 1, paramInt3, this.id);
		}
		super.postBreak(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public boolean d()
	{
		return false;
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Item.DIODE.id;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockDiode
 * JD-Core Version:		0.6.0
 */