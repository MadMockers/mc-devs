package net.minecraft.server;

public class ItemRecord extends Item
{
	public final String a;

	protected ItemRecord(int paramInt, String paramString)
	{
		super(paramInt);
		this.a = paramString;
		this.maxStackSize = 1;
		a(CreativeModeTab.f);
	}

	public boolean interactWith(ItemStack paramItemStack, EntityHuman paramEntityHuman, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		if ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) == Block.JUKEBOX.id) && (paramWorld.getData(paramInt1, paramInt2, paramInt3) == 0)) {
			if (paramWorld.isStatic) return true;

			((BlockJukeBox)Block.JUKEBOX).e(paramWorld, paramInt1, paramInt2, paramInt3, this.id);
			paramWorld.a(null, 1005, paramInt1, paramInt2, paramInt3, this.id);
			paramItemStack.count -= 1;
			return true;
		}
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemRecord
 * JD-Core Version:		0.6.0
 */