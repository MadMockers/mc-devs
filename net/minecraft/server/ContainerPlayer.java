package net.minecraft.server;

import java.util.List;
import org.bukkit.craftbukkit.inventory.CraftInventoryCrafting;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;

public class ContainerPlayer extends Container
{
	public InventoryCrafting craftInventory;
	public IInventory resultInventory;
	public boolean g;
	private CraftInventoryView bukkitEntity = null;
	private PlayerInventory player;

	public ContainerPlayer(PlayerInventory playerinventory)
	{
		this(playerinventory, true);
	}

	public ContainerPlayer(PlayerInventory playerinventory, boolean flag) {
		this.resultInventory = new InventoryCraftResult();
		this.craftInventory = new InventoryCrafting(this, 2, 2, playerinventory.player);
		this.craftInventory.resultInventory = this.resultInventory;
		this.player = playerinventory;
		this.g = false;
		this.g = flag;
		a(new SlotResult(playerinventory.player, this.craftInventory, this.resultInventory, 0, 144, 36));

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				a(new Slot(this.craftInventory, j + i * 2, 88 + j * 18, 26 + i * 18));
			}
		}

		for (i = 0; i < 4; i++) {
			a(new SlotArmor(this, playerinventory, playerinventory.getSize() - 1 - i, 8, 8 + i * 18, i));
		}

		for (i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				a(new Slot(playerinventory, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; i++)
			a(new Slot(playerinventory, i, 8 + i * 18, 142));
	}

	public void a(IInventory iinventory)
	{
		CraftingManager.getInstance().lastCraftView = getBukkitView();
		ItemStack craftResult = CraftingManager.getInstance().craft(this.craftInventory);
		this.resultInventory.setItem(0, craftResult);
		if (this.listeners.size() < 1) {
			return;
		}

		EntityPlayer player = (EntityPlayer)this.listeners.get(0);
		player.netServerHandler.sendPacket(new Packet103SetSlot(player.activeContainer.windowId, 0, craftResult));
	}

	public void a(EntityHuman entityhuman)
	{
		super.a(entityhuman);

		for (int i = 0; i < 4; i++) {
			ItemStack itemstack = this.craftInventory.splitWithoutUpdate(i);

			if (itemstack != null) {
				entityhuman.drop(itemstack);
			}
		}

		this.resultInventory.setItem(0, (ItemStack)null);
	}

	public boolean c(EntityHuman entityhuman) {
		return true;
	}

	public ItemStack b(int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.b.get(i);

		if ((slot != null) && (slot.d())) {
			ItemStack itemstack1 = slot.getItem();

			itemstack = itemstack1.cloneItemStack();
			if (i == 0) {
				if (!a(itemstack1, 9, 45, true)) {
					return null;
				}

				slot.a(itemstack1, itemstack);
			} else if ((i >= 1) && (i < 5)) {
				if (!a(itemstack1, 9, 45, false))
					return null;
			}
			else if ((i >= 5) && (i < 9)) {
				if (!a(itemstack1, 9, 45, false))
					return null;
			}
			else if (((itemstack.getItem() instanceof ItemArmor)) && (!((Slot)this.b.get(5 + ((ItemArmor)itemstack.getItem()).a)).d())) {
				int j = 5 + ((ItemArmor)itemstack.getItem()).a;

				if (!a(itemstack1, j, j + 1, false))
					return null;
			}
			else if ((i >= 9) && (i < 36)) {
				if (!a(itemstack1, 36, 45, false))
					return null;
			}
			else if ((i >= 36) && (i < 45)) {
				if (!a(itemstack1, 9, 36, false))
					return null;
			}
			else if (!a(itemstack1, 9, 45, false)) {
				return null;
			}

			if (itemstack1.count == 0)
				slot.set((ItemStack)null);
			else {
				slot.e();
			}

			if (itemstack1.count == itemstack.count) {
				return null;
			}

			slot.b(itemstack1);
		}

		return itemstack;
	}

	public CraftInventoryView getBukkitView()
	{
		if (this.bukkitEntity != null) {
			return this.bukkitEntity;
		}

		CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftInventory, this.resultInventory);
		this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
		return this.bukkitEntity;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ContainerPlayer
 * JD-Core Version:		0.6.0
 */