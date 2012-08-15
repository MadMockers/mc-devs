package net.minecraft.server;

import java.util.Random;

public abstract class BlockFluids extends Block
{
	protected BlockFluids(int paramInt, Material paramMaterial)
	{
		super(paramInt, (paramMaterial == Material.LAVA ? 14 : 12) * 16 + 13, paramMaterial);
		float f1 = 0.0F;
		float f2 = 0.0F;

		a(0.0F + f2, 0.0F + f1, 0.0F + f2, 1.0F + f2, 1.0F + f1, 1.0F + f2);
		b(true);
	}

	public boolean c(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		return this.material != Material.LAVA;
	}

	public static float d(int paramInt)
	{
		if (paramInt >= 8) paramInt = 0;
		return (paramInt + 1) / 9.0F;
	}

	public int a(int paramInt)
	{
		if ((paramInt == 0) || (paramInt == 1)) {
			return this.textureId;
		}
		return this.textureId + 1;
	}

	protected int f_(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (paramWorld.getMaterial(paramInt1, paramInt2, paramInt3) == this.material) return paramWorld.getData(paramInt1, paramInt2, paramInt3);
		return -1;
	}

	protected int d(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
		if (paramIBlockAccess.getMaterial(paramInt1, paramInt2, paramInt3) != this.material) return -1;
		int i = paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3);
		if (i >= 8) i = 0;
		return i;
	}

	public boolean c()
	{
		return false;
	}

	public boolean d()
	{
		return false;
	}

	public boolean a(int paramInt, boolean paramBoolean)
	{
		return (paramBoolean) && (paramInt == 0);
	}

	public boolean d(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		Material localMaterial = paramIBlockAccess.getMaterial(paramInt1, paramInt2, paramInt3);
		if (localMaterial == this.material) return false;
		if (paramInt4 == 1) return true;
		if (localMaterial == Material.ICE) return false;
		return super.d(paramIBlockAccess, paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		return null;
	}

	public int b()
	{
		return 4;
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return 0;
	}

	public int a(Random paramRandom)
	{
		return 0;
	}

	private Vec3D i(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
		Vec3D localVec3D = Vec3D.a().create(0.0D, 0.0D, 0.0D);
		int i = d(paramIBlockAccess, paramInt1, paramInt2, paramInt3);
		for (int j = 0; j < 4; j++)
		{
			int k = paramInt1;
			int m = paramInt2;
			int n = paramInt3;

			if (j == 0) k--;
			if (j == 1) n--;
			if (j == 2) k++;
			if (j == 3) n++;

			int i1 = d(paramIBlockAccess, k, m, n);
			int i2;
			if (i1 < 0) {
				if (!paramIBlockAccess.getMaterial(k, m, n).isSolid()) {
					i1 = d(paramIBlockAccess, k, m - 1, n);
					if (i1 >= 0) {
						i2 = i1 - (i - 8);
						localVec3D = localVec3D.add((k - paramInt1) * i2, (m - paramInt2) * i2, (n - paramInt3) * i2);
					}
				}
			}
			else if (i1 >= 0) {
				i2 = i1 - i;
				localVec3D = localVec3D.add((k - paramInt1) * i2, (m - paramInt2) * i2, (n - paramInt3) * i2);
			}

		}

		if (paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3) >= 8) {
			j = 0;
			if ((j != 0) || (d(paramIBlockAccess, paramInt1, paramInt2, paramInt3 - 1, 2))) j = 1;
			if ((j != 0) || (d(paramIBlockAccess, paramInt1, paramInt2, paramInt3 + 1, 3))) j = 1;
			if ((j != 0) || (d(paramIBlockAccess, paramInt1 - 1, paramInt2, paramInt3, 4))) j = 1;
			if ((j != 0) || (d(paramIBlockAccess, paramInt1 + 1, paramInt2, paramInt3, 5))) j = 1;
			if ((j != 0) || (d(paramIBlockAccess, paramInt1, paramInt2 + 1, paramInt3 - 1, 2))) j = 1;
			if ((j != 0) || (d(paramIBlockAccess, paramInt1, paramInt2 + 1, paramInt3 + 1, 3))) j = 1;
			if ((j != 0) || (d(paramIBlockAccess, paramInt1 - 1, paramInt2 + 1, paramInt3, 4))) j = 1;
			if ((j != 0) || (d(paramIBlockAccess, paramInt1 + 1, paramInt2 + 1, paramInt3, 5))) j = 1;
			if (j != 0) localVec3D = localVec3D.b().add(0.0D, -6.0D, 0.0D);
		}
		localVec3D = localVec3D.b();
		return localVec3D;
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Entity paramEntity, Vec3D paramVec3D)
	{
		Vec3D localVec3D = i(paramWorld, paramInt1, paramInt2, paramInt3);
		paramVec3D.a += localVec3D.a;
		paramVec3D.b += localVec3D.b;
		paramVec3D.c += localVec3D.c;
	}

	public int p_()
	{
		if (this.material == Material.WATER) return 5;
		if (this.material == Material.LAVA) return 30;
		return 0;
	}

	public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		l(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		l(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	private void l(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) != this.id) return;
		if (this.material == Material.LAVA) {
			int i = 0;
			if ((i != 0) || (paramWorld.getMaterial(paramInt1, paramInt2, paramInt3 - 1) == Material.WATER)) i = 1;
			if ((i != 0) || (paramWorld.getMaterial(paramInt1, paramInt2, paramInt3 + 1) == Material.WATER)) i = 1;
			if ((i != 0) || (paramWorld.getMaterial(paramInt1 - 1, paramInt2, paramInt3) == Material.WATER)) i = 1;
			if ((i != 0) || (paramWorld.getMaterial(paramInt1 + 1, paramInt2, paramInt3) == Material.WATER)) i = 1;
			if ((i != 0) || (paramWorld.getMaterial(paramInt1, paramInt2 + 1, paramInt3) == Material.WATER)) i = 1;
			if (i != 0) {
				int j = paramWorld.getData(paramInt1, paramInt2, paramInt3);
				if (j == 0)
					paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, Block.OBSIDIAN.id);
				else if (j <= 4) {
					paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, Block.COBBLESTONE.id);
				}
				fizz(paramWorld, paramInt1, paramInt2, paramInt3);
			}
		}
	}

	protected void fizz(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
		paramWorld.makeSound(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (paramWorld.random.nextFloat() - paramWorld.random.nextFloat()) * 0.8F);
		for (int i = 0; i < 8; i++)
			paramWorld.a("largesmoke", paramInt1 + Math.random(), paramInt2 + 1.2D, paramInt3 + Math.random(), 0.0D, 0.0D, 0.0D);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockFluids
 * JD-Core Version:		0.6.0
 */