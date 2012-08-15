package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockCauldron extends Block
{
	public BlockCauldron(int paramInt)
	{
		super(paramInt, Material.ORE);
		this.textureId = 154;
	}

	public int a(int paramInt1, int paramInt2)
	{
		if (paramInt1 == 1) {
			return 138;
		}
		if (paramInt1 == 0) {
			return 155;
		}
		return 154;
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
	{
		a(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
		super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		float f = 0.125F;
		a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);

		f();
	}

	public void f()
	{
		a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public boolean d()
	{
		return false;
	}

	public int b()
	{
		return 24;
	}

	public boolean c()
	{
		return false;
	}

	public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		if (paramWorld.isStatic) {
			return true;
		}

		ItemStack localItemStack1 = paramEntityHuman.inventory.getItemInHand();
		if (localItemStack1 == null) {
			return true;
		}

		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);

		if (localItemStack1.id == Item.WATER_BUCKET.id) {
			if (i < 3) {
				if (!paramEntityHuman.abilities.canInstantlyBuild) {
					paramEntityHuman.inventory.setItem(paramEntityHuman.inventory.itemInHandIndex, new ItemStack(Item.BUCKET));
				}

				paramWorld.setData(paramInt1, paramInt2, paramInt3, 3);
			}
			return true;
		}if ((localItemStack1.id == Item.GLASS_BOTTLE.id) && 
			(i > 0)) {
			ItemStack localItemStack2 = new ItemStack(Item.POTION, 1, 0);
			if (!paramEntityHuman.inventory.pickup(localItemStack2))
				paramWorld.addEntity(new EntityItem(paramWorld, paramInt1 + 0.5D, paramInt2 + 1.5D, paramInt3 + 0.5D, localItemStack2));
			else if ((paramEntityHuman instanceof EntityPlayer)) {
				((EntityPlayer)paramEntityHuman).updateInventory(paramEntityHuman.defaultContainer);
			}

			localItemStack1.count -= 1;
			if (localItemStack1.count <= 0) {
				paramEntityHuman.inventory.setItem(paramEntityHuman.inventory.itemInHandIndex, null);
			}
			paramWorld.setData(paramInt1, paramInt2, paramInt3, i - 1);
		}

		return true;
	}

	public void f(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (paramWorld.random.nextInt(20) != 1) return;

		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);

		if (i < 3)
			paramWorld.setData(paramInt1, paramInt2, paramInt3, i + 1);
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Item.CAULDRON.id;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockCauldron
 * JD-Core Version:		0.6.0
 */