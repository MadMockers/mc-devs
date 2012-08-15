package net.minecraft.server;

public class EntityFallingBlock extends Entity
{
	public int id;
	public int data;
	public int c = 0;
	public boolean dropItem = true;

	public EntityFallingBlock(World paramWorld) {
		super(paramWorld);
	}

	public EntityFallingBlock(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
		this(paramWorld, paramDouble1, paramDouble2, paramDouble3, paramInt, 0);
	}

	public EntityFallingBlock(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt1, int paramInt2) {
		super(paramWorld);
		this.id = paramInt1;
		this.data = paramInt2;
		this.m = true;
		a(0.98F, 0.98F);
		this.height = (this.length / 2.0F);
		setPosition(paramDouble1, paramDouble2, paramDouble3);

		this.motX = 0.0D;
		this.motY = 0.0D;
		this.motZ = 0.0D;

		this.lastX = paramDouble1;
		this.lastY = paramDouble2;
		this.lastZ = paramDouble3;
	}

	protected boolean e_()
	{
		return false;
	}

	protected void a()
	{
	}

	public boolean L()
	{
		return !this.dead;
	}

	public void h_()
	{
		if (this.id == 0) {
			die();
			return;
		}

		this.lastX = this.locX;
		this.lastY = this.locY;
		this.lastZ = this.locZ;
		this.c += 1;

		this.motY -= 0.03999999910593033D;
		move(this.motX, this.motY, this.motZ);
		this.motX *= 0.9800000190734863D;
		this.motY *= 0.9800000190734863D;
		this.motZ *= 0.9800000190734863D;

		if (!this.world.isStatic) {
			int i = MathHelper.floor(this.locX);
			int j = MathHelper.floor(this.locY);
			int k = MathHelper.floor(this.locZ);

			if (this.c == 1) {
				if ((this.c == 1) && (this.world.getTypeId(i, j, k) == this.id))
					this.world.setTypeId(i, j, k, 0);
				else {
					die();
				}
			}

			if (this.onGround) {
				this.motX *= 0.699999988079071D;
				this.motZ *= 0.699999988079071D;
				this.motY *= -0.5D;

				if (this.world.getTypeId(i, j, k) != Block.PISTON_MOVING.id) {
					die();
					if (((!this.world.mayPlace(this.id, i, j, k, true, 1, null)) || (BlockSand.canFall(this.world, i, j - 1, k)) || (!this.world.setTypeIdAndData(i, j, k, this.id, this.data))) && 
						(!this.world.isStatic) && 
						(this.dropItem)) a(new ItemStack(this.id, 1, this.data), 0.0F);
				}
			}
			else if (((this.c > 100) && (!this.world.isStatic) && ((j < 1) || (j > 256))) || (this.c > 600)) {
				if (this.dropItem) b(this.id, 1);
				die();
			}
		}
	}

	protected void b(NBTTagCompound paramNBTTagCompound)
	{
		paramNBTTagCompound.setByte("Tile", (byte)this.id);
		paramNBTTagCompound.setByte("Data", (byte)this.data);
		paramNBTTagCompound.setByte("Time", (byte)this.c);
		paramNBTTagCompound.setBoolean("DropItem", this.dropItem);
	}

	protected void a(NBTTagCompound paramNBTTagCompound)
	{
		this.id = (paramNBTTagCompound.getByte("Tile") & 0xFF);
		this.data = (paramNBTTagCompound.getByte("Data") & 0xFF);
		this.c = (paramNBTTagCompound.getByte("Time") & 0xFF);

		if (paramNBTTagCompound.hasKey("DropItem")) {
			this.dropItem = paramNBTTagCompound.getBoolean("DropItem");
		}

		if (this.id == 0)
			this.id = Block.SAND.id;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityFallingBlock
 * JD-Core Version:		0.6.0
 */