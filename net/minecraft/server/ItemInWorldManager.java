package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;

public class ItemInWorldManager
{
	public World world;
	public EntityPlayer player;
	private EnumGamemode gamemode;
	private boolean d;
	private int lastDigTick;
	private int f;
	private int g;
	private int h;
	private int currentTick;
	private boolean j;
	private int k;
	private int l;
	private int m;
	private int n;
	private int o;

	public ItemInWorldManager(World world)
	{
		this.gamemode = EnumGamemode.NONE;
		this.o = -1;
		this.world = world;
	}

	public ItemInWorldManager(WorldServer world)
	{
		this(world);
	}

	public void setGameMode(EnumGamemode enumgamemode)
	{
		this.gamemode = enumgamemode;
		enumgamemode.a(this.player.abilities);
		this.player.updateAbilities();
	}

	public EnumGamemode getGameMode() {
		return this.gamemode;
	}

	public boolean isCreative() {
		return this.gamemode.d();
	}

	public void b(EnumGamemode enumgamemode) {
		if (this.gamemode == EnumGamemode.NONE) {
			this.gamemode = enumgamemode;
		}

		setGameMode(this.gamemode);
	}

	public void a() {
		this.currentTick = (int)(System.currentTimeMillis() / 50L);

		if (this.j) {
			int i = this.currentTick - this.n;
			int k = this.world.getTypeId(this.k, this.l, this.m);

			if (k == 0) {
				this.j = false;
			} else {
				Block block = Block.byId[k];

				float f = block.getDamage(this.player, this.player.world, this.k, this.l, this.m) * (i + 1);
				int j = (int)(f * 10.0F);
				if (j != this.o) {
					this.world.f(this.player.id, this.k, this.l, this.m, j);
					this.o = j;
				}

				if (f >= 1.0F) {
					this.j = false;
					breakBlock(this.k, this.l, this.m);
				}
			}
		} else if (this.d) {
			int i = this.world.getTypeId(this.f, this.g, this.h);
			Block block1 = Block.byId[i];

			if (block1 == null) {
				this.world.f(this.player.id, this.f, this.g, this.h, -1);
				this.o = -1;
				this.d = false;
			} else {
				int l = this.currentTick - this.lastDigTick;

				float f = block1.getDamage(this.player, this.player.world, this.f, this.g, this.h) * (l + 1);
				int j = (int)(f * 10.0F);
				if (j != this.o) {
					this.world.f(this.player.id, this.f, this.g, this.h, j);
					this.o = j;
				}
			}
		}
	}

	public void dig(int i, int j, int k, int l)
	{
		PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_BLOCK, i, j, k, l, this.player.inventory.getItemInHand());

		if (!this.gamemode.isAdventure())
		{
			if (event.isCancelled())
			{
				this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
				return;
			}

			if (isCreative()) {
				if (!this.world.douseFire((EntityHuman)null, i, j, k, l))
					breakBlock(i, j, k);
			}
			else {
				this.world.douseFire(this.player, i, j, k, l);
				this.lastDigTick = this.currentTick;
				float f = 1.0F;
				int i1 = this.world.getTypeId(i, j, k);

				if (i1 <= 0) {
					return;
				}

				if (event.useInteractedBlock() == Event.Result.DENY)
				{
					if (i1 == Block.WOODEN_DOOR.id)
					{
						boolean bottom = (this.world.getData(i, j, k) & 0x8) == 0;
						this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
						this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, this.world));
					} else if (i1 == Block.TRAP_DOOR.id) {
						this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
					}
				} else {
					Block.byId[i1].attack(this.world, i, j, k, this.player);

					this.world.douseFire((EntityHuman)null, i, j, k, l);
				}

				float toolDamage = Block.byId[i1].getDamage(this.player, this.world, i, j, k);
				if (event.useItemInHand() == Event.Result.DENY)
				{
					if (toolDamage > 1.0F) {
						this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
					}
					return;
				}
				BlockDamageEvent blockEvent = CraftEventFactory.callBlockDamageEvent(this.player, i, j, k, this.player.inventory.getItemInHand(), toolDamage >= 1.0F);

				if (blockEvent.isCancelled())
				{
					this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
					return;
				}

				if (blockEvent.getInstaBreak()) {
					toolDamage = 2.0F;
				}

				if (toolDamage >= 1.0F)
				{
					breakBlock(i, j, k);
				} else {
					this.d = true;
					this.f = i;
					this.g = j;
					this.h = k;
					int j1 = (int)(f * 10.0F);

					this.world.f(this.player.id, i, j, k, j1);
					this.o = j1;
				}
			}
		}
	}

	public void a(int i, int j, int k) {
		if ((i == this.f) && (j == this.g) && (k == this.h)) {
			this.currentTick = (int)(System.currentTimeMillis() / 50L);
			int l = this.currentTick - this.lastDigTick;
			int i1 = this.world.getTypeId(i, j, k);

			if (i1 != 0) {
				Block block = Block.byId[i1];
				float f = block.getDamage(this.player, this.player.world, i, j, k) * (l + 1);

				if (f >= 0.7F) {
					this.d = false;
					this.world.f(this.player.id, i, j, k, -1);
					breakBlock(i, j, k);
				} else if (!this.j) {
					this.d = false;
					this.j = true;
					this.k = i;
					this.l = j;
					this.m = k;
					this.n = this.lastDigTick;
				}
			}
		}
		else {
			this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
		}
	}

	public void c(int i, int j, int k)
	{
		this.d = false;
		this.world.f(this.player.id, this.f, this.g, this.h, -1);
	}

	private boolean d(int i, int j, int k) {
		Block block = Block.byId[this.world.getTypeId(i, j, k)];
		int l = this.world.getData(i, j, k);

		if (block != null) {
			block.a(this.world, i, j, k, l, this.player);
		}

		boolean flag = this.world.setTypeId(i, j, k, 0);

		if ((block != null) && (flag)) {
			block.postBreak(this.world, i, j, k, l);
		}

		return flag;
	}

	public boolean breakBlock(int i, int j, int k)
	{
		BlockBreakEvent event = null;

		if ((this.player instanceof EntityPlayer)) {
			org.bukkit.block.Block block = this.world.getWorld().getBlockAt(i, j, k);

			if (this.world.getTileEntity(i, j, k) == null) {
				Packet53BlockChange packet = new Packet53BlockChange(i, j, k, this.world);

				packet.material = 0;
				packet.data = 0;
				this.player.netServerHandler.sendPacket(packet);
			}

			event = new BlockBreakEvent(block, this.player.getBukkitEntity());

			event.setCancelled(this.gamemode.isAdventure());

			Block nmsBlock = Block.byId[block.getTypeId()];

			if ((nmsBlock != null) && (!event.isCancelled()) && (!isCreative()) && (this.player.b(nmsBlock)))
			{
				if ((!nmsBlock.q_()) || (!EnchantmentManager.hasSilkTouchEnchantment(this.player.inventory))) {
					int data = block.getData();
					int bonusLevel = EnchantmentManager.getBonusBlockLootEnchantmentLevel(this.player.inventory);

					event.setExpToDrop(nmsBlock.getExpDrop(this.world, data, bonusLevel));
				}
			}

			this.world.getServer().getPluginManager().callEvent(event);

			if (event.isCancelled())
			{
				this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
				return false;
			}

		}

		int l = this.world.getTypeId(i, j, k);
		if (Block.byId[l] == null) return false;
		int i1 = this.world.getData(i, j, k);

		this.world.a(this.player, 2001, i, j, k, l + (this.world.getData(i, j, k) << 12));
		boolean flag = d(i, j, k);

		if (isCreative()) {
			this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
		} else {
			ItemStack itemstack = this.player.bC();
			boolean flag1 = this.player.b(Block.byId[l]);

			if (itemstack != null) {
				itemstack.a(this.world, l, i, j, k, this.player);
				if (itemstack.count == 0) {
					this.player.bD();
				}
			}

			if ((flag) && (flag1)) {
				Block.byId[l].a(this.world, this.player, i, j, k, i1);
			}

		}

		if ((flag) && (event != null)) {
			Block.byId[l].g(this.world, i, j, k, event.getExpToDrop());
		}

		return flag;
	}

	public boolean useItem(EntityHuman entityhuman, World world, ItemStack itemstack)
	{
		int i = itemstack.count;
		int j = itemstack.getData();
		ItemStack itemstack1 = itemstack.a(world, entityhuman);

		if ((itemstack1 == itemstack) && ((itemstack1 == null) || (itemstack1.count == i)) && ((itemstack1 == null) || (itemstack1.m() <= 0))) {
			return false;
		}
		entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = itemstack1;
		if (isCreative()) {
			itemstack1.count = i;
			itemstack1.setData(j);
		}

		if (itemstack1.count == 0) {
			entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = null;
		}

		return true;
	}

	public boolean interact(EntityHuman entityhuman, World world, ItemStack itemstack, int i, int j, int k, int l, float f, float f1, float f2)
	{
		int i1 = world.getTypeId(i, j, k);

		boolean result = false;
		if (i1 > 0) {
			PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, i, j, k, l, itemstack);
			if (event.useInteractedBlock() == Event.Result.DENY)
			{
				if (i1 == Block.WOODEN_DOOR.id) {
					boolean bottom = (world.getData(i, j, k) & 0x8) == 0;
					((EntityPlayer)entityhuman).netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, world));
				}
				result = event.useItemInHand() != Event.Result.ALLOW;
			} else {
				result = Block.byId[i1].interact(world, i, j, k, entityhuman, l, f, f1, f2);
			}

			if ((itemstack != null) && (!result)) {
				int j1 = itemstack.getData();
				int k1 = itemstack.count;

				result = itemstack.placeItem(entityhuman, world, i, j, k, l, f, f1, f2);

				if (isCreative()) {
					itemstack.setData(j1);
					itemstack.count = k1;
				}

			}

			if ((itemstack != null) && (((!result) && (event.useItemInHand() != Event.Result.DENY)) || (event.useItemInHand() == Event.Result.ALLOW))) {
				useItem(entityhuman, world, itemstack);
			}
		}
		return result;
	}

	public void a(WorldServer worldserver)
	{
		this.world = worldserver;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemInWorldManager
 * JD-Core Version:		0.6.0
 */