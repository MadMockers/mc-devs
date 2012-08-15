package net.minecraft.server;

import java.util.Random;

public class BlockChest extends BlockContainer
{
	private Random a = new Random();

	protected BlockChest(int paramInt) {
		super(paramInt, Material.WOOD);
		this.textureId = 26;
		a(CreativeModeTab.c);
	}

	public boolean d()
	{
		return false;
	}

	public boolean c()
	{
		return false;
	}

	public int b()
	{
		return 22;
	}

	public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		super.onPlace(paramWorld, paramInt1, paramInt2, paramInt3);
		b_(paramWorld, paramInt1, paramInt2, paramInt3);

		int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1);
		int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1);
		int k = paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3);
		int m = paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3);
		if (i == this.id) b_(paramWorld, paramInt1, paramInt2, paramInt3 - 1);
		if (j == this.id) b_(paramWorld, paramInt1, paramInt2, paramInt3 + 1);
		if (k == this.id) b_(paramWorld, paramInt1 - 1, paramInt2, paramInt3);
		if (m == this.id) b_(paramWorld, paramInt1 + 1, paramInt2, paramInt3);
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
	{
		int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1);
		int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1);
		int k = paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3);
		int m = paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3);

		int n = 0;
		int i1 = MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;

		if (i1 == 0) n = 2;
		if (i1 == 1) n = 5;
		if (i1 == 2) n = 3;
		if (i1 == 3) n = 4;

		if ((i != this.id) && (j != this.id) && (k != this.id) && (m != this.id)) {
			paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
		} else {
			if (((i == this.id) || (j == this.id)) && ((n == 4) || (n == 5))) {
				if (i == this.id) paramWorld.setData(paramInt1, paramInt2, paramInt3 - 1, n); else
					paramWorld.setData(paramInt1, paramInt2, paramInt3 + 1, n);
				paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
			}
			if (((k == this.id) || (m == this.id)) && ((n == 2) || (n == 3))) {
				if (k == this.id) paramWorld.setData(paramInt1 - 1, paramInt2, paramInt3, n); else
					paramWorld.setData(paramInt1 + 1, paramInt2, paramInt3, n);
				paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
			}
		}
	}

	public void b_(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
		if (paramWorld.isStatic) {
			return;
		}

		int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1);
		int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1);
		int k = paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3);
		int m = paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3);

		int n = 4;
		int i1;
		int i2;
		int i3;
		if ((i == this.id) || (j == this.id)) {
			i1 = paramWorld.getTypeId(paramInt1 - 1, paramInt2, i == this.id ? paramInt3 - 1 : paramInt3 + 1);
			i2 = paramWorld.getTypeId(paramInt1 + 1, paramInt2, i == this.id ? paramInt3 - 1 : paramInt3 + 1);

			n = 5;

			i3 = -1;
			if (i == this.id) i3 = paramWorld.getData(paramInt1, paramInt2, paramInt3 - 1); else
				i3 = paramWorld.getData(paramInt1, paramInt2, paramInt3 + 1);
			if (i3 == 4) n = 4;

			if (((Block.n[k] != 0) || (Block.n[i1] != 0)) && (Block.n[m] == 0) && (Block.n[i2] == 0)) n = 5;
			if (((Block.n[m] != 0) || (Block.n[i2] != 0)) && (Block.n[k] == 0) && (Block.n[i1] == 0)) n = 4; 
		}
		else if ((k == this.id) || (m == this.id)) {
			i1 = paramWorld.getTypeId(k == this.id ? paramInt1 - 1 : paramInt1 + 1, paramInt2, paramInt3 - 1);
			i2 = paramWorld.getTypeId(k == this.id ? paramInt1 - 1 : paramInt1 + 1, paramInt2, paramInt3 + 1);

			n = 3;
			i3 = -1;
			if (k == this.id) i3 = paramWorld.getData(paramInt1 - 1, paramInt2, paramInt3); else
				i3 = paramWorld.getData(paramInt1 + 1, paramInt2, paramInt3);
			if (i3 == 2) n = 2;

			if (((Block.n[i] != 0) || (Block.n[i1] != 0)) && (Block.n[j] == 0) && (Block.n[i2] == 0)) n = 3;
			if (((Block.n[j] != 0) || (Block.n[i2] != 0)) && (Block.n[i] == 0) && (Block.n[i1] == 0)) n = 2; 
		}
		else {
			n = 3;
			if ((Block.n[i] != 0) && (Block.n[j] == 0)) n = 3;
			if ((Block.n[j] != 0) && (Block.n[i] == 0)) n = 2;
			if ((Block.n[k] != 0) && (Block.n[m] == 0)) n = 5;
			if ((Block.n[m] != 0) && (Block.n[k] == 0)) n = 4;
		}

		paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
	}

	public int a(int paramInt)
	{
		return 4;
	}

	public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		int i = 0;

		if (paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == this.id) i++;
		if (paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == this.id) i++;
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == this.id) i++;
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == this.id) i++;

		if (i > 1) return false;

		if (l(paramWorld, paramInt1 - 1, paramInt2, paramInt3)) return false;
		if (l(paramWorld, paramInt1 + 1, paramInt2, paramInt3)) return false;
		if (l(paramWorld, paramInt1, paramInt2, paramInt3 - 1)) return false;
		return !l(paramWorld, paramInt1, paramInt2, paramInt3 + 1);
	}

	private boolean l(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) != this.id) return false;
		if (paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == this.id) return true;
		if (paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == this.id) return true;
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == this.id) return true;
		return paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == this.id;
	}

	public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		super.doPhysics(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
		TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
		if (localTileEntityChest != null) localTileEntityChest.h();
	}

	public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
		if (localTileEntityChest != null) {
			for (int i = 0; i < localTileEntityChest.getSize(); i++) {
				ItemStack localItemStack = localTileEntityChest.getItem(i);
				if (localItemStack != null) {
					float f1 = this.a.nextFloat() * 0.8F + 0.1F;
					float f2 = this.a.nextFloat() * 0.8F + 0.1F;
					float f3 = this.a.nextFloat() * 0.8F + 0.1F;

					while (localItemStack.count > 0) {
						int j = this.a.nextInt(21) + 10;
						if (j > localItemStack.count) j = localItemStack.count;
						localItemStack.count -= j;

						EntityItem localEntityItem = new EntityItem(paramWorld, paramInt1 + f1, paramInt2 + f2, paramInt3 + f3, new ItemStack(localItemStack.id, j, localItemStack.getData()));
						float f4 = 0.05F;
						localEntityItem.motX = ((float)this.a.nextGaussian() * f4);
						localEntityItem.motY = ((float)this.a.nextGaussian() * f4 + 0.2F);
						localEntityItem.motZ = ((float)this.a.nextGaussian() * f4);
						if (localItemStack.hasTag()) {
							localEntityItem.itemStack.setTag((NBTTagCompound)localItemStack.getTag().clone());
						}
						paramWorld.addEntity(localEntityItem);
					}
				}
			}
		}
		super.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
	}

	public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		Object localObject = (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
		if (localObject == null) return true;

		if (paramWorld.s(paramInt1, paramInt2 + 1, paramInt3)) return true;
		if (n(paramWorld, paramInt1, paramInt2, paramInt3)) return true;

		if ((paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == this.id) && ((paramWorld.s(paramInt1 - 1, paramInt2 + 1, paramInt3)) || (n(paramWorld, paramInt1 - 1, paramInt2, paramInt3)))) return true;
		if ((paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == this.id) && ((paramWorld.s(paramInt1 + 1, paramInt2 + 1, paramInt3)) || (n(paramWorld, paramInt1 + 1, paramInt2, paramInt3)))) return true;
		if ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == this.id) && ((paramWorld.s(paramInt1, paramInt2 + 1, paramInt3 - 1)) || (n(paramWorld, paramInt1, paramInt2, paramInt3 - 1)))) return true;
		if ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == this.id) && ((paramWorld.s(paramInt1, paramInt2 + 1, paramInt3 + 1)) || (n(paramWorld, paramInt1, paramInt2, paramInt3 + 1)))) return true;

		if (paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == this.id) localObject = new InventoryLargeChest("container.chestDouble", (TileEntityChest)paramWorld.getTileEntity(paramInt1 - 1, paramInt2, paramInt3), (IInventory)localObject);
		if (paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == this.id) localObject = new InventoryLargeChest("container.chestDouble", (IInventory)localObject, (TileEntityChest)paramWorld.getTileEntity(paramInt1 + 1, paramInt2, paramInt3));
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == this.id) localObject = new InventoryLargeChest("container.chestDouble", (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3 - 1), (IInventory)localObject);
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == this.id) localObject = new InventoryLargeChest("container.chestDouble", (IInventory)localObject, (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3 + 1));

		if (paramWorld.isStatic) {
			return true;
		}

		paramEntityHuman.openContainer((IInventory)localObject);

		return true;
	}

	public TileEntity a(World paramWorld)
	{
		return new TileEntityChest();
	}

	private static boolean n(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
		for (EntityOcelot localEntityOcelot1 : paramWorld.a(EntityOcelot.class, AxisAlignedBB.a().a(paramInt1, paramInt2 + 1, paramInt3, paramInt1 + 1, paramInt2 + 2, paramInt3 + 1))) {
			EntityOcelot localEntityOcelot2 = (EntityOcelot)localEntityOcelot1;
			if (localEntityOcelot2.isSitting()) return true;
		}
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockChest
 * JD-Core Version:		0.6.0
 */