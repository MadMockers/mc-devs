package net.minecraft.server;

public class ItemStep extends ItemBlock
{
	private final boolean a;
	private final BlockStepAbstract b;
	private final BlockStepAbstract c;

	public ItemStep(int i, BlockStepAbstract blockstepabstract, BlockStepAbstract blockstepabstract1, boolean flag)
	{
		super(i);
		this.b = blockstepabstract;
		this.c = blockstepabstract1;
		this.a = flag;
		setMaxDurability(0);
		a(true);
	}

	public int filterData(int i) {
		return i;
	}

	public String c(ItemStack itemstack) {
		return this.b.d(itemstack.getData());
	}

	public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		if (this.a)
			return super.interactWith(itemstack, entityhuman, world, i, j, k, l, f, f1, f2);
		if (itemstack.count == 0)
			return false;
		if (!entityhuman.e(i, j, k)) {
			return false;
		}
		int i1 = world.getTypeId(i, j, k);
		int j1 = world.getData(i, j, k);
		int k1 = j1 & 0x7;
		boolean flag = (j1 & 0x8) != 0;

		if (((l == 1) && (!flag)) || ((l == 0) && (flag) && (i1 == this.b.id) && (k1 == itemstack.getData()))) {
			return super.interactWith(itemstack, entityhuman, world, i, j, k, -1, f, f1, f2);
		}
		return a(itemstack, entityhuman, world, i, j, k, l) ? true : super.interactWith(itemstack, entityhuman, world, i, j, k, l, f, f1, f2);
	}

	private boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l)
	{
		if (l == 0) {
			j--;
		}

		if (l == 1) {
			j++;
		}

		if (l == 2) {
			k--;
		}

		if (l == 3) {
			k++;
		}

		if (l == 4) {
			i--;
		}

		if (l == 5) {
			i++;
		}

		int i1 = world.getTypeId(i, j, k);
		int j1 = world.getData(i, j, k);
		int k1 = j1 & 0x7;

		if ((i1 == this.b.id) && (k1 == itemstack.getData())) {
			if ((world.b(this.c.e(world, i, j, k))) && (world.setTypeIdAndData(i, j, k, this.c.id, k1))) {
				world.makeSound(i + 0.5F, j + 0.5F, k + 0.5F, this.c.stepSound.getName(), (this.c.stepSound.getVolume1() + 1.0F) / 2.0F, this.c.stepSound.getVolume2() * 0.8F);
				itemstack.count -= 1;
			}

			return true;
		}
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemStep
 * JD-Core Version:		0.6.0
 */