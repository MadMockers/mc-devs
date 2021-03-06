package net.minecraft.server;

import java.util.List;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryDoubleChest;
import org.bukkit.craftbukkit.inventory.CraftInventoryPlayer;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;

public class ContainerChest extends Container
{
	public IInventory container;
	private int f;
	private CraftInventoryView bukkitEntity = null;
	private PlayerInventory player;

	public CraftInventoryView getBukkitView()
	{
		if (this.bukkitEntity != null)
			return this.bukkitEntity;
		CraftInventory inventory;
		CraftInventory inventory;
		if ((this.container instanceof PlayerInventory)) {
			inventory = new CraftInventoryPlayer((PlayerInventory)this.container);
		}
		else
		{
			CraftInventory inventory;
			if ((this.container instanceof InventoryLargeChest))
				inventory = new CraftInventoryDoubleChest((InventoryLargeChest)this.container);
			else {
				inventory = new CraftInventory(this.container);
			}
		}
		this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
		return this.bukkitEntity;
	}

	public ContainerChest(IInventory iinventory, IInventory iinventory1)
	{
		this.container = iinventory1;
		this.f = (iinventory1.getSize() / 9);
		iinventory1.startOpen();
		int i = (this.f - 4) * 18;

		this.player = ((PlayerInventory)iinventory);

		for (int j = 0; j < this.f; j++) {
			for (int k = 0; k < 9; k++) {
				a(new Slot(iinventory1, k + j * 9, 8 + k * 18, 18 + j * 18));
			}
		}

		for (j = 0; j < 3; j++) {
			for (int k = 0; k < 9; k++) {
				a(new Slot(iinventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
			}
		}

		for (j = 0; j < 9; j++)
			a(new Slot(iinventory, j, 8 + j * 18, 161 + i));
	}

	public boolean c(EntityHuman entityhuman)
	{
		if (!this.checkReachable) return true;
		return this.container.a(entityhuman);
	}

	public ItemStack b(int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.b.get(i);

		if ((slot != null) && (slot.d())) {
			ItemStack itemstack1 = slot.getItem();

			itemstack = itemstack1.cloneItemStack();
			if (i < this.f * 9) {
				if (!a(itemstack1, this.f * 9, this.b.size(), true))
					return null;
			}
			else if (!a(itemstack1, 0, this.f * 9, false)) {
				return null;
			}

			if (itemstack1.count == 0)
				slot.set((ItemStack)null);
			else {
				slot.e();
			}
		}

		return itemstack;
	}

	public void a(EntityHuman entityhuman) {
		super.a(entityhuman);
		this.container.f();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ContainerChest
 * JD-Core Version:		0.6.0
 */