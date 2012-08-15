package net.minecraft.server;

public class BlockFence extends Block
{
	public BlockFence(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.WOOD);
		a(CreativeModeTab.c);
	}

	public BlockFence(int paramInt1, int paramInt2, Material paramMaterial) {
		super(paramInt1, paramInt2, paramMaterial);
		a(CreativeModeTab.c);
	}

	public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		boolean bool1 = d(paramWorld, paramInt1, paramInt2, paramInt3 - 1);
		boolean bool2 = d(paramWorld, paramInt1, paramInt2, paramInt3 + 1);
		boolean bool3 = d(paramWorld, paramInt1 - 1, paramInt2, paramInt3);
		boolean bool4 = d(paramWorld, paramInt1 + 1, paramInt2, paramInt3);

		float f1 = 0.375F;
		float f2 = 0.625F;
		float f3 = 0.375F;
		float f4 = 0.625F;

		if (bool1) {
			f3 = 0.0F;
		}
		if (bool2) {
			f4 = 1.0F;
		}
		if (bool3) {
			f1 = 0.0F;
		}
		if (bool4) {
			f2 = 1.0F;
		}

		return AxisAlignedBB.a().a(paramInt1 + f1, paramInt2, paramInt3 + f3, paramInt1 + f2, paramInt2 + 1.5F, paramInt3 + f4);
	}

	public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		boolean bool1 = d(paramIBlockAccess, paramInt1, paramInt2, paramInt3 - 1);
		boolean bool2 = d(paramIBlockAccess, paramInt1, paramInt2, paramInt3 + 1);
		boolean bool3 = d(paramIBlockAccess, paramInt1 - 1, paramInt2, paramInt3);
		boolean bool4 = d(paramIBlockAccess, paramInt1 + 1, paramInt2, paramInt3);

		float f1 = 0.375F;
		float f2 = 0.625F;
		float f3 = 0.375F;
		float f4 = 0.625F;

		if (bool1) {
			f3 = 0.0F;
		}
		if (bool2) {
			f4 = 1.0F;
		}
		if (bool3) {
			f1 = 0.0F;
		}
		if (bool4) {
			f2 = 1.0F;
		}

		a(f1, 0.0F, f3, f2, 1.0F, f4);
	}

	public boolean d()
	{
		return false;
	}

	public boolean c()
	{
		return false;
	}

	public boolean c(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		return false;
	}

	public int b()
	{
		return 11;
	}

	public boolean d(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
		int i = paramIBlockAccess.getTypeId(paramInt1, paramInt2, paramInt3);
		if ((i == this.id) || (i == Block.FENCE_GATE.id)) {
			return true;
		}
		Block localBlock = Block.byId[i];
		if ((localBlock != null) && 
			(localBlock.material.k()) && (localBlock.c())) {
			return localBlock.material != Material.PUMPKIN;
		}

		return false;
	}

	public static boolean c(int paramInt) {
		return (paramInt == Block.FENCE.id) || (paramInt == Block.NETHER_FENCE.id);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockFence
 * JD-Core Version:		0.6.0
 */