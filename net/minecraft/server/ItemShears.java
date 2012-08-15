package net.minecraft.server;

public class ItemShears extends Item
{
	public ItemShears(int paramInt)
	{
		super(paramInt);

		d(1);
		setMaxDurability(238);
		a(CreativeModeTab.i);
	}

	public boolean a(ItemStack paramItemStack, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityLiving paramEntityLiving)
	{
		if ((paramInt1 == Block.LEAVES.id) || (paramInt1 == Block.WEB.id) || (paramInt1 == Block.LONG_GRASS.id) || (paramInt1 == Block.VINE.id) || (paramInt1 == Block.TRIPWIRE.id)) {
			paramItemStack.damage(1, paramEntityLiving);
			return true;
		}
		return super.a(paramItemStack, paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramEntityLiving);
	}

	public boolean canDestroySpecialBlock(Block paramBlock)
	{
		return paramBlock.id == Block.WEB.id;
	}

	public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock)
	{
		if ((paramBlock.id == Block.WEB.id) || (paramBlock.id == Block.LEAVES.id)) {
			return 15.0F;
		}
		if (paramBlock.id == Block.WOOL.id) {
			return 5.0F;
		}
		return super.getDestroySpeed(paramItemStack, paramBlock);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemShears
 * JD-Core Version:		0.6.0
 */