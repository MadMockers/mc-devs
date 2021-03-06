package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

public class BlockDispenser extends BlockContainer
{
	private Random a = new Random();

	protected BlockDispenser(int i) {
		super(i, Material.STONE);
		this.textureId = 45;
		a(CreativeModeTab.d);
	}

	public int p_() {
		return 4;
	}

	public int getDropType(int i, Random random, int j) {
		return Block.DISPENSER.id;
	}

	public void onPlace(World world, int i, int j, int k) {
		super.onPlace(world, i, j, k);
		l(world, i, j, k);
	}

	private void l(World world, int i, int j, int k) {
		if (!world.isStatic) {
			int l = world.getTypeId(i, j, k - 1);
			int i1 = world.getTypeId(i, j, k + 1);
			int j1 = world.getTypeId(i - 1, j, k);
			int k1 = world.getTypeId(i + 1, j, k);
			byte b0 = 3;

			if ((Block.n[l] != 0) && (Block.n[i1] == 0)) {
				b0 = 3;
			}

			if ((Block.n[i1] != 0) && (Block.n[l] == 0)) {
				b0 = 2;
			}

			if ((Block.n[j1] != 0) && (Block.n[k1] == 0)) {
				b0 = 5;
			}

			if ((Block.n[k1] != 0) && (Block.n[j1] == 0)) {
				b0 = 4;
			}

			world.setData(i, j, k, b0);
		}
	}

	public int a(int i) {
		return i == 3 ? this.textureId + 1 : i == 0 ? this.textureId + 17 : i == 1 ? this.textureId + 17 : this.textureId;
	}

	public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
		if (world.isStatic) {
			return true;
		}
		TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);

		if (tileentitydispenser != null) {
			entityhuman.openDispenser(tileentitydispenser);
		}

		return true;
	}

	public void dispense(World world, int i, int j, int k, Random random)
	{
		int l = world.getData(i, j, k);
		byte b0 = 0;
		byte b1 = 0;

		if (l == 3)
			b1 = 1;
		else if (l == 2)
			b1 = -1;
		else if (l == 5)
			b0 = 1;
		else {
			b0 = -1;
		}

		TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);

		if (tileentitydispenser != null) {
			int i1 = tileentitydispenser.i();

			if (i1 < 0) {
				world.triggerEffect(1001, i, j, k, 0);
			} else {
				double d0 = i + b0 * 0.6D + 0.5D;
				double d1 = j + 0.5D;
				double d2 = k + b1 * 0.6D + 0.5D;
				ItemStack itemstack = tileentitydispenser.getItem(i1);

				itemstack = itemstack.cloneItemStack();
				itemstack.count = 1;

				double d3 = random.nextDouble() * 0.1D + 0.2D;
				double motX = b0 * d3;
				double motY = 0.2000000029802322D;
				double motZ = b1 * d3;
				motX += random.nextGaussian() * 0.007499999832361937D * 6.0D;
				motY += random.nextGaussian() * 0.007499999832361937D * 6.0D;
				motZ += random.nextGaussian() * 0.007499999832361937D * 6.0D;

				org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
				org.bukkit.inventory.ItemStack bukkitItem = new CraftItemStack(itemstack).clone();

				BlockDispenseEvent event = new BlockDispenseEvent(block, bukkitItem, new Vector(motX, motY, motZ));
				world.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) {
					return;
				}

				itemstack = CraftItemStack.createNMSItemStack(event.getItem());

				int j1 = a(tileentitydispenser, world, itemstack, random, i, j, k, b0, b1, d0, d1, d2);

				if (j1 == 1)
				{
					if (event.getItem().equals(bukkitItem))
					{
						tileentitydispenser.splitStack(i1, 1);
					}
				}
				else if (j1 == 0)
				{
					motX = event.getVelocity().getX();
					motY = event.getVelocity().getY();
					motZ = event.getVelocity().getZ();

					if (event.getItem().equals(bukkitItem))
					{
						tileentitydispenser.splitStack(i1, 1);
					}

					EntityItem entityitem = new EntityItem(world, d0, d1 - 0.3D, d2, itemstack);
					entityitem.motX = event.getVelocity().getX();
					entityitem.motY = event.getVelocity().getY();
					entityitem.motZ = event.getVelocity().getZ();
					world.addEntity(entityitem);

					world.triggerEffect(1000, i, j, k, 0);
				}
				else if (j1 == 2) {
					ItemStack old = tileentitydispenser.getItem(i1);
					if ((old.id == Item.BUCKET.id) && (old.count > 1)) {
						old.count -= 1;
						if (tileentitydispenser.a(itemstack) < 0)
							a(world, itemstack, random, 6, l, i1, d0, d1, d2);
					}
					else {
						tileentitydispenser.setItem(i1, itemstack);
					}

				}

				world.triggerEffect(2000, i, j, k, b0 + 1 + (b1 + 1) * 3);
			}
		}
	}

	public void doPhysics(World world, int i, int j, int k, int l) {
		if ((l > 0) && (Block.byId[l].isPowerSource())) {
			boolean flag = (world.isBlockIndirectlyPowered(i, j, k)) || (world.isBlockIndirectlyPowered(i, j + 1, k));

			if (flag)
				world.a(i, j, k, this.id, p_());
		}
	}

	public void b(World world, int i, int j, int k, Random random)
	{
		if ((!world.isStatic) && ((world.isBlockIndirectlyPowered(i, j, k)) || (world.isBlockIndirectlyPowered(i, j + 1, k))))
			dispense(world, i, j, k, random);
	}

	public TileEntity a(World world)
	{
		return new TileEntityDispenser();
	}

	public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
		int l = MathHelper.floor(entityliving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;

		if (l == 0) {
			world.setData(i, j, k, 2);
		}

		if (l == 1) {
			world.setData(i, j, k, 5);
		}

		if (l == 2) {
			world.setData(i, j, k, 3);
		}

		if (l == 3)
			world.setData(i, j, k, 4);
	}

	public void remove(World world, int i, int j, int k, int l, int i1)
	{
		TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);

		if (tileentitydispenser != null) {
			for (int j1 = 0; j1 < tileentitydispenser.getSize(); j1++) {
				ItemStack itemstack = tileentitydispenser.getItem(j1);

				if (itemstack != null) {
					float f = this.a.nextFloat() * 0.8F + 0.1F;
					float f1 = this.a.nextFloat() * 0.8F + 0.1F;
					float f2 = this.a.nextFloat() * 0.8F + 0.1F;

					while (itemstack.count > 0) {
						int k1 = this.a.nextInt(21) + 10;

						if (k1 > itemstack.count) {
							k1 = itemstack.count;
						}

						itemstack.count -= k1;
						EntityItem entityitem = new EntityItem(world, i + f, j + f1, k + f2, new ItemStack(itemstack.id, k1, itemstack.getData()));

						if (itemstack.hasTag()) {
							entityitem.itemStack.setTag((NBTTagCompound)itemstack.getTag().clone());
						}

						float f3 = 0.05F;

						entityitem.motX = ((float)this.a.nextGaussian() * f3);
						entityitem.motY = ((float)this.a.nextGaussian() * f3 + 0.2F);
						entityitem.motZ = ((float)this.a.nextGaussian() * f3);
						world.addEntity(entityitem);
					}
				}
			}
		}

		super.remove(world, i, j, k, l, i1);
	}

	private static void a(World world, ItemStack itemstack, Random random, int i, int j, int k, double d0, double d1, double d2) {
		EntityItem entityitem = new EntityItem(world, d0, d1 - 0.3D, d2, itemstack);
		double d3 = random.nextDouble() * 0.1D + 0.2D;

		entityitem.motX = (j * d3);
		entityitem.motY = 0.2000000029802322D;
		entityitem.motZ = (k * d3);
		entityitem.motX += random.nextGaussian() * 0.007499999832361937D * i;
		entityitem.motY += random.nextGaussian() * 0.007499999832361937D * i;
		entityitem.motZ += random.nextGaussian() * 0.007499999832361937D * i;
		world.addEntity(entityitem);
	}

	private static int a(TileEntityDispenser tileentitydispenser, World world, ItemStack itemstack, Random random, int i, int j, int k, int l, int i1, double d0, double d1, double d2) {
		float f = 1.1F;
		byte b0 = 6;

		if (itemstack.id == Item.ARROW.id) {
			EntityArrow entityarrow = new EntityArrow(world, d0, d1, d2);

			entityarrow.shoot(l, 0.1000000014901161D, i1, f, b0);
			entityarrow.fromPlayer = 1;
			world.addEntity(entityarrow);
			world.triggerEffect(1002, i, j, k, 0);
			return 1;
		}if (itemstack.id == Item.EGG.id) {
			EntityEgg entityegg = new EntityEgg(world, d0, d1, d2);

			entityegg.c(l, 0.1000000014901161D, i1, f, b0);
			world.addEntity(entityegg);
			world.triggerEffect(1002, i, j, k, 0);
			return 1;
		}if (itemstack.id == Item.SNOW_BALL.id) {
			EntitySnowball entitysnowball = new EntitySnowball(world, d0, d1, d2);

			entitysnowball.c(l, 0.1000000014901161D, i1, f, b0);
			world.addEntity(entitysnowball);
			world.triggerEffect(1002, i, j, k, 0);
			return 1;
		}if ((itemstack.id == Item.POTION.id) && (ItemPotion.g(itemstack.getData()))) {
			EntityPotion entitypotion = new EntityPotion(world, d0, d1, d2, itemstack.getData());

			entitypotion.c(l, 0.1000000014901161D, i1, f * 1.25F, b0 * 0.5F);
			world.addEntity(entitypotion);
			world.triggerEffect(1002, i, j, k, 0);
			return 1;
		}if (itemstack.id == Item.EXP_BOTTLE.id) {
			EntityThrownExpBottle entitythrownexpbottle = new EntityThrownExpBottle(world, d0, d1, d2);

			entitythrownexpbottle.c(l, 0.1000000014901161D, i1, f * 1.25F, b0 * 0.5F);
			world.addEntity(entitythrownexpbottle);
			world.triggerEffect(1002, i, j, k, 0);
			return 1;
		}if (itemstack.id == Item.MONSTER_EGG.id) {
			ItemMonsterEgg.a(world, itemstack.getData(), d0 + l * 0.3D, d1 - 0.3D, d2 + i1 * 0.3D);
			world.triggerEffect(1002, i, j, k, 0);
			return 1;
		}if (itemstack.id == Item.FIREBALL.id) {
			EntitySmallFireball entitysmallfireball = new EntitySmallFireball(world, d0 + l * 0.3D, d1, d2 + i1 * 0.3D, l + random.nextGaussian() * 0.05D, random.nextGaussian() * 0.05D, i1 + random.nextGaussian() * 0.05D);

			world.addEntity(entitysmallfireball);
			world.triggerEffect(1009, i, j, k, 0);
			return 1;
		}if ((itemstack.id != Item.LAVA_BUCKET.id) && (itemstack.id != Item.WATER_BUCKET.id)) {
			if (itemstack.id == Item.BUCKET.id) {
				int j1 = i + l;
				int k1 = k + i1;
				Material material = world.getMaterial(j1, j, k1);
				int l1 = world.getData(j1, j, k1);

				if ((material == Material.WATER) && (l1 == 0)) {
					world.setTypeId(j1, j, k1, 0);
					if (--itemstack.count == 0) {
						itemstack.id = Item.WATER_BUCKET.id;
						itemstack.count = 1;
					} else if (tileentitydispenser.a(new ItemStack(Item.WATER_BUCKET)) < 0) {
						a(world, new ItemStack(Item.WATER_BUCKET), random, 6, l, i1, d0, d1, d2);
					}

					return 2;
				}if ((material == Material.LAVA) && (l1 == 0)) {
					world.setTypeId(j1, j, k1, 0);
					if (--itemstack.count == 0) {
						itemstack.id = Item.LAVA_BUCKET.id;
						itemstack.count = 1;
					} else if (tileentitydispenser.a(new ItemStack(Item.LAVA_BUCKET)) < 0) {
						a(world, new ItemStack(Item.LAVA_BUCKET), random, 6, l, i1, d0, d1, d2);
					}

					return 2;
				}
				return 0;
			}
			if ((itemstack.getItem() instanceof ItemMinecart)) {
				d0 = i + l * 1.8F + Math.abs(i1) * 0.5F;
				d2 = k + i1 * 1.8F + Math.abs(l) * 0.5F;
				if (BlockMinecartTrack.d_(world, i + l, j, k + i1)) {
					d1 = j + 0.5F;
				} else {
					if ((!world.isEmpty(i + l, j, k + i1)) || (!BlockMinecartTrack.d_(world, i + l, j - 1, k + i1))) {
						return 0;
					}

					d1 = j - 0.5F;
				}

				EntityMinecart entityminecart = new EntityMinecart(world, d0, d1, d2, ((ItemMinecart)itemstack.getItem()).a);

				world.addEntity(entityminecart);
				world.triggerEffect(1000, i, j, k, 0);
				return 1;
			}if (itemstack.id == Item.BOAT.id) {
				d0 = i + l * 1.8F + Math.abs(i1) * 0.5F;
				d2 = k + i1 * 1.8F + Math.abs(l) * 0.5F;
				if (world.getMaterial(i + l, j, k + i1) == Material.WATER) {
					d1 = j + 1.0F;
				} else {
					if ((!world.isEmpty(i + l, j, k + i1)) || (world.getMaterial(i + l, j - 1, k + i1) != Material.WATER)) {
						return 0;
					}

					d1 = j;
				}

				EntityBoat entityboat = new EntityBoat(world, d0, d1, d2);

				world.addEntity(entityboat);
				world.triggerEffect(1000, i, j, k, 0);
				return 1;
			}
			return 0;
		}

		ItemBucket itembucket = (ItemBucket)itemstack.getItem();

		if (itembucket.a(world, i, j, k, i + l, j, k + i1)) {
			itemstack.id = Item.BUCKET.id;
			itemstack.count = 1;
			return 2;
		}
		return 0;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockDispenser
 * JD-Core Version:		0.6.0
 */