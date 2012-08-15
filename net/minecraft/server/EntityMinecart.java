package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

public class EntityMinecart extends Entity
	implements IInventory
{
	private ItemStack[] items;
	private int e;
	private boolean f;
	public int type;
	public double b;
	public double c;
	private static final int[][][] matrix = { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1, 0, 0 }, { 1, 0, 0 } }, { { -1, -1, 0 }, { 1, 0, 0 } }, { { -1, 0, 0 }, { 1, -1, 0 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { 1, 0, 0 } } };
	private int h;
	private double i;
	private double j;
	private double an;
	private double ao;
	private double ap;
	public boolean slowWhenEmpty = true;
	private double derailedX = 0.5D;
	private double derailedY = 0.5D;
	private double derailedZ = 0.5D;
	private double flyingX = 0.95D;
	private double flyingY = 0.95D;
	private double flyingZ = 0.95D;
	public double maxSpeed = 0.4D;
	public List<HumanEntity> transaction = new ArrayList();
	private int maxStack = 64;

	public ItemStack[] getContents() {
		return this.items;
	}

	public void onOpen(CraftHumanEntity who) {
		this.transaction.add(who);
	}

	public void onClose(CraftHumanEntity who) {
		this.transaction.remove(who);
	}

	public List<HumanEntity> getViewers() {
		return this.transaction;
	}

	public InventoryHolder getOwner() {
		org.bukkit.entity.Entity cart = getBukkitEntity();
		if ((cart instanceof InventoryHolder)) return (InventoryHolder)cart;
		return null;
	}

	public void setMaxStackSize(int size) {
		this.maxStack = size;
	}

	public EntityMinecart(World world)
	{
		super(world);
		this.items = new ItemStack[27];
		this.e = 0;
		this.f = false;
		this.m = true;
		a(0.98F, 0.7F);
		this.height = (this.length / 2.0F);
	}

	protected boolean e_() {
		return false;
	}

	protected void a() {
		this.datawatcher.a(16, new Byte(0));
		this.datawatcher.a(17, new Integer(0));
		this.datawatcher.a(18, new Integer(1));
		this.datawatcher.a(19, new Integer(0));
	}

	public AxisAlignedBB g(Entity entity) {
		return entity.boundingBox;
	}

	public AxisAlignedBB E() {
		return null;
	}

	public boolean M() {
		return true;
	}

	public EntityMinecart(World world, double d0, double d1, double d2, int i) {
		this(world);
		setPosition(d0, d1 + this.height, d2);
		this.motX = 0.0D;
		this.motY = 0.0D;
		this.motZ = 0.0D;
		this.lastX = d0;
		this.lastY = d1;
		this.lastZ = d2;
		this.type = i;

		this.world.getServer().getPluginManager().callEvent(new VehicleCreateEvent((Vehicle)getBukkitEntity()));
	}

	public double X() {
		return this.length * 0.0D - 0.300000011920929D;
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		if ((!this.world.isStatic) && (!this.dead))
		{
			Vehicle vehicle = (Vehicle)getBukkitEntity();
			org.bukkit.entity.Entity passenger = damagesource.getEntity() == null ? null : damagesource.getEntity().getBukkitEntity();

			VehicleDamageEvent event = new VehicleDamageEvent(vehicle, passenger, i);
			this.world.getServer().getPluginManager().callEvent(event);

			if (event.isCancelled()) {
				return true;
			}

			i = event.getDamage();

			i(-k());
			h(10);
			K();
			setDamage(getDamage() + i * 10);
			if (((damagesource.getEntity() instanceof EntityHuman)) && (((EntityHuman)damagesource.getEntity()).abilities.canInstantlyBuild)) {
				setDamage(100);
			}

			if (getDamage() > 40) {
				if (this.passenger != null) {
					this.passenger.mount(this);
				}

				VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, passenger);
				this.world.getServer().getPluginManager().callEvent(destroyEvent);

				if (destroyEvent.isCancelled()) {
					setDamage(40);
					return true;
				}

				die();
				a(Item.MINECART.id, 1, 0.0F);
				if (this.type == 1) {
					EntityMinecart entityminecart = this;

					for (int j = 0; j < entityminecart.getSize(); j++) {
						ItemStack itemstack = entityminecart.getItem(j);

						if (itemstack != null) {
							float f = this.random.nextFloat() * 0.8F + 0.1F;
							float f1 = this.random.nextFloat() * 0.8F + 0.1F;
							float f2 = this.random.nextFloat() * 0.8F + 0.1F;

							while (itemstack.count > 0) {
								int k = this.random.nextInt(21) + 10;

								if (k > itemstack.count) {
									k = itemstack.count;
								}

								itemstack.count -= k;

								EntityItem entityitem = new EntityItem(this.world, this.locX + f, this.locY + f1, this.locZ + f2, new ItemStack(itemstack.id, k, itemstack.getData(), itemstack.getEnchantments()));
								float f3 = 0.05F;

								entityitem.motX = ((float)this.random.nextGaussian() * f3);
								entityitem.motY = ((float)this.random.nextGaussian() * f3 + 0.2F);
								entityitem.motZ = ((float)this.random.nextGaussian() * f3);
								this.world.addEntity(entityitem);
							}
						}
					}

					a(Block.CHEST.id, 1, 0.0F);
				} else if (this.type == 2) {
					a(Block.FURNACE.id, 1, 0.0F);
				}
			}

			return true;
		}
		return true;
	}

	public boolean L()
	{
		return !this.dead;
	}

	public void die() {
		for (int i = 0; i < getSize(); i++) {
			ItemStack itemstack = getItem(i);

			if (itemstack != null) {
				float f = this.random.nextFloat() * 0.8F + 0.1F;
				float f1 = this.random.nextFloat() * 0.8F + 0.1F;
				float f2 = this.random.nextFloat() * 0.8F + 0.1F;

				while (itemstack.count > 0) {
					int j = this.random.nextInt(21) + 10;

					if (j > itemstack.count) {
						j = itemstack.count;
					}

					itemstack.count -= j;
					EntityItem entityitem = new EntityItem(this.world, this.locX + f, this.locY + f1, this.locZ + f2, new ItemStack(itemstack.id, j, itemstack.getData()));

					if (itemstack.hasTag()) {
						entityitem.itemStack.setTag((NBTTagCompound)itemstack.getTag().clone());
					}

					float f3 = 0.05F;

					entityitem.motX = ((float)this.random.nextGaussian() * f3);
					entityitem.motY = ((float)this.random.nextGaussian() * f3 + 0.2F);
					entityitem.motZ = ((float)this.random.nextGaussian() * f3);
					this.world.addEntity(entityitem);
				}
			}
		}

		super.die();
	}

	public void h_()
	{
		double prevX = this.locX;
		double prevY = this.locY;
		double prevZ = this.locZ;
		float prevYaw = this.yaw;
		float prevPitch = this.pitch;

		if (j() > 0) {
			h(j() - 1);
		}

		if (getDamage() > 0) {
			setDamage(getDamage() - 1);
		}

		if (this.locY < -64.0D) {
			C();
		}

		if ((h()) && (this.random.nextInt(4) == 0)) {
			this.world.a("largesmoke", this.locX, this.locY + 0.8D, this.locZ, 0.0D, 0.0D, 0.0D);
		}

		if (this.world.isStatic) {
			if (this.h > 0) {
				double d0 = this.locX + (this.i - this.locX) / this.h;
				double d1 = this.locY + (this.j - this.locY) / this.h;
				double d2 = this.locZ + (this.an - this.locZ) / this.h;
				double d3 = MathHelper.g(this.ao - this.yaw);

				this.yaw = (float)(this.yaw + d3 / this.h);
				this.pitch = (float)(this.pitch + (this.ap - this.pitch) / this.h);
				this.h -= 1;
				setPosition(d0, d1, d2);
				b(this.yaw, this.pitch);
			} else {
				setPosition(this.locX, this.locY, this.locZ);
				b(this.yaw, this.pitch);
			}
		} else {
			this.lastX = this.locX;
			this.lastY = this.locY;
			this.lastZ = this.locZ;
			this.motY -= 0.03999999910593033D;
			int i = MathHelper.floor(this.locX);
			int j = MathHelper.floor(this.locY);
			int k = MathHelper.floor(this.locZ);

			if (BlockMinecartTrack.d_(this.world, i, j - 1, k)) {
				j--;
			}

			double d4 = this.maxSpeed;
			double d5 = 0.0078125D;
			int l = this.world.getTypeId(i, j, k);

			if (BlockMinecartTrack.d(l)) {
				Vec3D vec3d = a(this.locX, this.locY, this.locZ);
				int i1 = this.world.getData(i, j, k);

				this.locY = j;
				boolean flag = false;
				boolean flag1 = false;

				if (l == Block.GOLDEN_RAIL.id) {
					flag = (i1 & 0x8) != 0;
					flag1 = !flag;
				}

				if (((BlockMinecartTrack)Block.byId[l]).n()) {
					i1 &= 7;
				}

				if ((i1 >= 2) && (i1 <= 5)) {
					this.locY = (j + 1);
				}

				if (i1 == 2) {
					this.motX -= d5;
				}

				if (i1 == 3) {
					this.motX += d5;
				}

				if (i1 == 4) {
					this.motZ += d5;
				}

				if (i1 == 5) {
					this.motZ -= d5;
				}

				int[][] aint = matrix[i1];
				double d6 = aint[1][0] - aint[0][0];
				double d7 = aint[1][2] - aint[0][2];
				double d8 = Math.sqrt(d6 * d6 + d7 * d7);
				double d9 = this.motX * d6 + this.motZ * d7;

				if (d9 < 0.0D) {
					d6 = -d6;
					d7 = -d7;
				}

				double d10 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);

				this.motX = (d10 * d6 / d8);
				this.motZ = (d10 * d7 / d8);

				if (this.passenger != null) {
					double d12 = this.passenger.motX * this.passenger.motX + this.passenger.motZ * this.passenger.motZ;
					double d11 = this.motX * this.motX + this.motZ * this.motZ;
					if ((d12 > 0.0001D) && (d11 < 0.01D)) {
						this.motX += this.passenger.motX * 0.1D;
						this.motZ += this.passenger.motZ * 0.1D;
						flag1 = false;
					}
				}

				if (flag1) {
					double d12 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
					if (d12 < 0.03D) {
						this.motX *= 0.0D;
						this.motY *= 0.0D;
						this.motZ *= 0.0D;
					} else {
						this.motX *= 0.5D;
						this.motY *= 0.0D;
						this.motZ *= 0.5D;
					}
				}

				double d12 = 0.0D;
				double d11 = i + 0.5D + aint[0][0] * 0.5D;
				double d13 = k + 0.5D + aint[0][2] * 0.5D;
				double d14 = i + 0.5D + aint[1][0] * 0.5D;
				double d15 = k + 0.5D + aint[1][2] * 0.5D;

				d6 = d14 - d11;
				d7 = d15 - d13;

				if (d6 == 0.0D) {
					this.locX = (i + 0.5D);
					d12 = this.locZ - k;
				} else if (d7 == 0.0D) {
					this.locZ = (k + 0.5D);
					d12 = this.locX - i;
				} else {
					double d16 = this.locX - d11;
					double d17 = this.locZ - d13;
					d12 = (d16 * d6 + d17 * d7) * 2.0D;
				}

				this.locX = (d11 + d6 * d12);
				this.locZ = (d13 + d7 * d12);
				setPosition(this.locX, this.locY + this.height, this.locZ);
				double d16 = this.motX;
				double d17 = this.motZ;
				if (this.passenger != null) {
					d16 *= 0.75D;
					d17 *= 0.75D;
				}

				if (d16 < -d4) {
					d16 = -d4;
				}

				if (d16 > d4) {
					d16 = d4;
				}

				if (d17 < -d4) {
					d17 = -d4;
				}

				if (d17 > d4) {
					d17 = d4;
				}

				move(d16, 0.0D, d17);
				if ((aint[0][1] != 0) && (MathHelper.floor(this.locX) - i == aint[0][0]) && (MathHelper.floor(this.locZ) - k == aint[0][2]))
					setPosition(this.locX, this.locY + aint[0][1], this.locZ);
				else if ((aint[1][1] != 0) && (MathHelper.floor(this.locX) - i == aint[1][0]) && (MathHelper.floor(this.locZ) - k == aint[1][2])) {
					setPosition(this.locX, this.locY + aint[1][1], this.locZ);
				}

				if ((this.passenger != null) || (!this.slowWhenEmpty)) {
					this.motX *= 0.996999979019165D;
					this.motY *= 0.0D;
					this.motZ *= 0.996999979019165D;
				} else {
					if (this.type == 2) {
						double d18 = this.b * this.b + this.c * this.c;

						if (d18 > 0.0001D) {
							d18 = MathHelper.sqrt(d18);
							this.b /= d18;
							this.c /= d18;
							double d19 = 0.04D;

							this.motX *= 0.800000011920929D;
							this.motY *= 0.0D;
							this.motZ *= 0.800000011920929D;
							this.motX += this.b * d19;
							this.motZ += this.c * d19;
						} else {
							this.motX *= 0.8999999761581421D;
							this.motY *= 0.0D;
							this.motZ *= 0.8999999761581421D;
						}
					}

					this.motX *= 0.9599999785423279D;
					this.motY *= 0.0D;
					this.motZ *= 0.9599999785423279D;
				}

				Vec3D vec3d1 = a(this.locX, this.locY, this.locZ);

				if ((vec3d1 != null) && (vec3d != null)) {
					double d20 = (vec3d.b - vec3d1.b) * 0.05D;

					d10 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
					if (d10 > 0.0D) {
						this.motX = (this.motX / d10 * (d10 + d20));
						this.motZ = (this.motZ / d10 * (d10 + d20));
					}

					setPosition(this.locX, vec3d1.b, this.locZ);
				}

				int j1 = MathHelper.floor(this.locX);
				int k1 = MathHelper.floor(this.locZ);

				if ((j1 != i) || (k1 != k)) {
					d10 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
					this.motX = (d10 * (j1 - i));
					this.motZ = (d10 * (k1 - k));
				}

				if (this.type == 2) {
					double d21 = this.b * this.b + this.c * this.c;
					if ((d21 > 0.0001D) && (this.motX * this.motX + this.motZ * this.motZ > 0.001D)) {
						d21 = MathHelper.sqrt(d21);
						this.b /= d21;
						this.c /= d21;
						if (this.b * this.motX + this.c * this.motZ < 0.0D) {
							this.b = 0.0D;
							this.c = 0.0D;
						} else {
							this.b = this.motX;
							this.c = this.motZ;
						}
					}
				}

				if (flag) {
					double d21 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
					if (d21 > 0.01D) {
						double d22 = 0.06D;

						this.motX += this.motX / d21 * d22;
						this.motZ += this.motZ / d21 * d22;
					} else if (i1 == 1) {
						if (this.world.s(i - 1, j, k))
							this.motX = 0.02D;
						else if (this.world.s(i + 1, j, k))
							this.motX = -0.02D;
					}
					else if (i1 == 0) {
						if (this.world.s(i, j, k - 1))
							this.motZ = 0.02D;
						else if (this.world.s(i, j, k + 1)) {
							this.motZ = -0.02D;
						}
					}
				}

				D();
			} else {
				if (this.motX < -d4) {
					this.motX = (-d4);
				}

				if (this.motX > d4) {
					this.motX = d4;
				}

				if (this.motZ < -d4) {
					this.motZ = (-d4);
				}

				if (this.motZ > d4) {
					this.motZ = d4;
				}

				if (this.onGround)
				{
					this.motX *= this.derailedX;
					this.motY *= this.derailedY;
					this.motZ *= this.derailedZ;
				}

				move(this.motX, this.motY, this.motZ);
				if (!this.onGround)
				{
					this.motX *= this.flyingX;
					this.motY *= this.flyingY;
					this.motZ *= this.flyingZ;
				}

			}

			this.pitch = 0.0F;
			double d23 = this.lastX - this.locX;
			double d24 = this.lastZ - this.locZ;

			if (d23 * d23 + d24 * d24 > 0.001D) {
				this.yaw = (float)(Math.atan2(d24, d23) * 180.0D / 3.141592653589793D);
				if (this.f) {
					this.yaw += 180.0F;
				}
			}

			double d25 = MathHelper.g(this.yaw - this.lastYaw);

			if ((d25 < -170.0D) || (d25 >= 170.0D)) {
				this.yaw += 180.0F;
				this.f = (!this.f);
			}

			b(this.yaw, this.pitch);

			org.bukkit.World bworld = this.world.getWorld();
			Location from = new Location(bworld, prevX, prevY, prevZ, prevYaw, prevPitch);
			Location to = new Location(bworld, this.locX, this.locY, this.locZ, this.yaw, this.pitch);
			Vehicle vehicle = (Vehicle)getBukkitEntity();

			this.world.getServer().getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));

			if (!from.equals(to)) {
				this.world.getServer().getPluginManager().callEvent(new VehicleMoveEvent(vehicle, from, to));
			}

			List list = this.world.getEntities(this, this.boundingBox.grow(0.2000000029802322D, 0.0D, 0.2000000029802322D));

			if ((list != null) && (!list.isEmpty())) {
				for (int l1 = 0; l1 < list.size(); l1++) {
					Entity entity = (Entity)list.get(l1);

					if ((entity != this.passenger) && (entity.M()) && ((entity instanceof EntityMinecart))) {
						entity.collide(this);
					}
				}
			}

			if ((this.passenger != null) && (this.passenger.dead)) {
				if (this.passenger.vehicle == this) {
					this.passenger.vehicle = null;
				}

				this.passenger = null;
			}

			if (this.e > 0) {
				this.e -= 1;
			}

			if (this.e <= 0) {
				this.b = (this.c = 0.0D);
			}

			d(this.e > 0);
		}
	}

	public Vec3D a(double d0, double d1, double d2) {
		int i = MathHelper.floor(d0);
		int j = MathHelper.floor(d1);
		int k = MathHelper.floor(d2);

		if (BlockMinecartTrack.d_(this.world, i, j - 1, k)) {
			j--;
		}

		int l = this.world.getTypeId(i, j, k);

		if (BlockMinecartTrack.d(l)) {
			int i1 = this.world.getData(i, j, k);

			d1 = j;
			if (((BlockMinecartTrack)Block.byId[l]).n()) {
				i1 &= 7;
			}

			if ((i1 >= 2) && (i1 <= 5)) {
				d1 = j + 1;
			}

			int[][] aint = matrix[i1];
			double d3 = 0.0D;
			double d4 = i + 0.5D + aint[0][0] * 0.5D;
			double d5 = j + 0.5D + aint[0][1] * 0.5D;
			double d6 = k + 0.5D + aint[0][2] * 0.5D;
			double d7 = i + 0.5D + aint[1][0] * 0.5D;
			double d8 = j + 0.5D + aint[1][1] * 0.5D;
			double d9 = k + 0.5D + aint[1][2] * 0.5D;
			double d10 = d7 - d4;
			double d11 = (d8 - d5) * 2.0D;
			double d12 = d9 - d6;

			if (d10 == 0.0D) {
				d0 = i + 0.5D;
				d3 = d2 - k;
			} else if (d12 == 0.0D) {
				d2 = k + 0.5D;
				d3 = d0 - i;
			} else {
				double d13 = d0 - d4;
				double d14 = d2 - d6;

				d3 = (d13 * d10 + d14 * d12) * 2.0D;
			}

			d0 = d4 + d10 * d3;
			d1 = d5 + d11 * d3;
			d2 = d6 + d12 * d3;
			if (d11 < 0.0D) {
				d1 += 1.0D;
			}

			if (d11 > 0.0D) {
				d1 += 0.5D;
			}

			return Vec3D.a().create(d0, d1, d2);
		}
		return null;
	}

	protected void b(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setInt("Type", this.type);
		if (this.type == 2) {
			nbttagcompound.setDouble("PushX", this.b);
			nbttagcompound.setDouble("PushZ", this.c);
			nbttagcompound.setShort("Fuel", (short)this.e);
		} else if (this.type == 1) {
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 0; i < this.items.length; i++) {
				if (this.items[i] != null) {
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();

					nbttagcompound1.setByte("Slot", (byte)i);
					this.items[i].save(nbttagcompound1);
					nbttaglist.add(nbttagcompound1);
				}
			}

			nbttagcompound.set("Items", nbttaglist);
		}
	}

	protected void a(NBTTagCompound nbttagcompound) {
		this.type = nbttagcompound.getInt("Type");
		if (this.type == 2) {
			this.b = nbttagcompound.getDouble("PushX");
			this.c = nbttagcompound.getDouble("PushZ");
			this.e = nbttagcompound.getShort("Fuel");
		} else if (this.type == 1) {
			NBTTagList nbttaglist = nbttagcompound.getList("Items");

			this.items = new ItemStack[getSize()];

			for (int i = 0; i < nbttaglist.size(); i++) {
				NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
				int j = nbttagcompound1.getByte("Slot") & 0xFF;

				if ((j >= 0) && (j < this.items.length))
					this.items[j] = ItemStack.a(nbttagcompound1);
			}
		}
	}

	public void collide(Entity entity)
	{
		if ((!this.world.isStatic) && 
			(entity != this.passenger))
		{
			Vehicle vehicle = (Vehicle)getBukkitEntity();
			org.bukkit.entity.Entity hitEntity = entity == null ? null : entity.getBukkitEntity();

			VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, hitEntity);
			this.world.getServer().getPluginManager().callEvent(collisionEvent);

			if (collisionEvent.isCancelled()) {
				return;
			}

			if (((entity instanceof EntityLiving)) && (!(entity instanceof EntityHuman)) && (!(entity instanceof EntityIronGolem)) && (this.type == 0) && (this.motX * this.motX + this.motZ * this.motZ > 0.01D) && (this.passenger == null) && (entity.vehicle == null)) {
				entity.mount(this);
			}

			double d0 = entity.locX - this.locX;
			double d1 = entity.locZ - this.locZ;
			double d2 = d0 * d0 + d1 * d1;

			if ((d2 >= 9.999999747378752E-005D) && (!collisionEvent.isCollisionCancelled())) {
				d2 = MathHelper.sqrt(d2);
				d0 /= d2;
				d1 /= d2;
				double d3 = 1.0D / d2;

				if (d3 > 1.0D) {
					d3 = 1.0D;
				}

				d0 *= d3;
				d1 *= d3;
				d0 *= 0.1000000014901161D;
				d1 *= 0.1000000014901161D;
				d0 *= (1.0F - this.Y);
				d1 *= (1.0F - this.Y);
				d0 *= 0.5D;
				d1 *= 0.5D;
				if ((entity instanceof EntityMinecart)) {
					double d4 = entity.locX - this.locX;
					double d5 = entity.locZ - this.locZ;
					Vec3D vec3d = Vec3D.a().create(d4, 0.0D, d5).b();
					Vec3D vec3d1 = Vec3D.a().create(MathHelper.cos(this.yaw * 3.141593F / 180.0F), 0.0D, MathHelper.sin(this.yaw * 3.141593F / 180.0F)).b();
					double d6 = Math.abs(vec3d.b(vec3d1));

					if (d6 < 0.800000011920929D) {
						return;
					}

					double d7 = entity.motX + this.motX;
					double d8 = entity.motZ + this.motZ;

					if ((((EntityMinecart)entity).type == 2) && (this.type != 2)) {
						this.motX *= 0.2000000029802322D;
						this.motZ *= 0.2000000029802322D;
						g(entity.motX - d0, 0.0D, entity.motZ - d1);
						entity.motX *= 0.949999988079071D;
						entity.motZ *= 0.949999988079071D;
					} else if ((((EntityMinecart)entity).type != 2) && (this.type == 2)) {
						entity.motX *= 0.2000000029802322D;
						entity.motZ *= 0.2000000029802322D;
						entity.g(this.motX + d0, 0.0D, this.motZ + d1);
						this.motX *= 0.949999988079071D;
						this.motZ *= 0.949999988079071D;
					} else {
						d7 /= 2.0D;
						d8 /= 2.0D;
						this.motX *= 0.2000000029802322D;
						this.motZ *= 0.2000000029802322D;
						g(d7 - d0, 0.0D, d8 - d1);
						entity.motX *= 0.2000000029802322D;
						entity.motZ *= 0.2000000029802322D;
						entity.g(d7 + d0, 0.0D, d8 + d1);
					}
				} else {
					g(-d0, 0.0D, -d1);
					entity.g(d0 / 4.0D, 0.0D, d1 / 4.0D);
				}
			}
		}
	}

	public int getSize()
	{
		return 27;
	}

	public ItemStack getItem(int i) {
		return this.items[i];
	}

	public ItemStack splitStack(int i, int j) {
		if (this.items[i] != null)
		{
			if (this.items[i].count <= j) {
				ItemStack itemstack = this.items[i];
				this.items[i] = null;
				return itemstack;
			}
			ItemStack itemstack = this.items[i].a(j);
			if (this.items[i].count == 0) {
				this.items[i] = null;
			}

			return itemstack;
		}

		return null;
	}

	public ItemStack splitWithoutUpdate(int i)
	{
		if (this.items[i] != null) {
			ItemStack itemstack = this.items[i];

			this.items[i] = null;
			return itemstack;
		}
		return null;
	}

	public void setItem(int i, ItemStack itemstack)
	{
		this.items[i] = itemstack;
		if ((itemstack != null) && (itemstack.count > getMaxStackSize()))
			itemstack.count = getMaxStackSize();
	}

	public String getName()
	{
		return "container.minecart";
	}

	public int getMaxStackSize() {
		return this.maxStack;
	}
	public void update() {
	}

	public boolean c(EntityHuman entityhuman) {
		if (this.type == 0) {
			if ((this.passenger != null) && ((this.passenger instanceof EntityHuman)) && (this.passenger != entityhuman)) {
				return true;
			}

			if (!this.world.isStatic)
				entityhuman.mount(this);
		}
		else if (this.type == 1) {
			if (!this.world.isStatic)
				entityhuman.openContainer(this);
		}
		else if (this.type == 2) {
			ItemStack itemstack = entityhuman.inventory.getItemInHand();

			if ((itemstack != null) && (itemstack.id == Item.COAL.id)) {
				if (--itemstack.count == 0) {
					entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
				}

				this.e += 3600;
			}

			this.b = (this.locX - entityhuman.locX);
			this.c = (this.locZ - entityhuman.locZ);
		}

		return true;
	}

	public boolean a(EntityHuman entityhuman) {
		return !this.dead;
	}

	protected boolean h() {
		return (this.datawatcher.getByte(16) & 0x1) != 0;
	}

	protected void d(boolean flag) {
		if (flag)
			this.datawatcher.watch(16, Byte.valueOf((byte)(this.datawatcher.getByte(16) | 0x1)));
		else
			this.datawatcher.watch(16, Byte.valueOf((byte)(this.datawatcher.getByte(16) & 0xFFFFFFFE))); 
	}

	public void startOpen() {
	}

	public void f() {
	}

	public void setDamage(int i) {
		this.datawatcher.watch(19, Integer.valueOf(i));
	}

	public int getDamage() {
		return this.datawatcher.getInt(19);
	}

	public void h(int i) {
		this.datawatcher.watch(17, Integer.valueOf(i));
	}

	public int j() {
		return this.datawatcher.getInt(17);
	}

	public void i(int i) {
		this.datawatcher.watch(18, Integer.valueOf(i));
	}

	public int k() {
		return this.datawatcher.getInt(18);
	}

	public Vector getFlyingVelocityMod()
	{
		return new Vector(this.flyingX, this.flyingY, this.flyingZ);
	}

	public void setFlyingVelocityMod(Vector flying) {
		this.flyingX = flying.getX();
		this.flyingY = flying.getY();
		this.flyingZ = flying.getZ();
	}

	public Vector getDerailedVelocityMod() {
		return new Vector(this.derailedX, this.derailedY, this.derailedZ);
	}

	public void setDerailedVelocityMod(Vector derailed) {
		this.derailedX = derailed.getX();
		this.derailedY = derailed.getY();
		this.derailedZ = derailed.getZ();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityMinecart
 * JD-Core Version:		0.6.0
 */