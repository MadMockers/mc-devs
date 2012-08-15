package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;

public class ContainerBrewingStand extends Container
{
	private TileEntityBrewingStand brewingStand;
	private final Slot f;
	private int g = 0;

	private CraftInventoryView bukkitEntity = null;
	private PlayerInventory player;

	public ContainerBrewingStand(PlayerInventory playerinventory, TileEntityBrewingStand tileentitybrewingstand)
	{
		this.player = playerinventory;
		this.brewingStand = tileentitybrewingstand;
		a(new SlotPotionBottle(playerinventory.player, tileentitybrewingstand, 0, 56, 46));
		a(new SlotPotionBottle(playerinventory.player, tileentitybrewingstand, 1, 79, 53));
		a(new SlotPotionBottle(playerinventory.player, tileentitybrewingstand, 2, 102, 46));
		this.f = a(new SlotBrewing(this, tileentitybrewingstand, 3, 79, 17));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; i++)
			a(new Slot(playerinventory, i, 8 + i * 18, 142));
	}

	public void addSlotListener(ICrafting icrafting)
	{
		super.addSlotListener(icrafting);
		icrafting.setContainerData(this, 0, this.brewingStand.t_());
	}

	public void b() {
		super.b();
		Iterator iterator = this.listeners.iterator();

		while (iterator.hasNext()) {
			ICrafting icrafting = (ICrafting)iterator.next();

			if (this.g != this.brewingStand.t_()) {
				icrafting.setContainerData(this, 0, this.brewingStand.t_());
			}
		}

		this.g = this.brewingStand.t_();
	}

	public boolean c(EntityHuman entityhuman) {
		if (!this.checkReachable) return true;
		return this.brewingStand.a(entityhuman);
	}

	public ItemStack b(int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.b.get(i);

		if ((slot != null) && (slot.d())) {
			ItemStack itemstack1 = slot.getItem();

			itemstack = itemstack1.cloneItemStack();
			if (((i < 0) || (i > 2)) && (i != 3)) {
				if ((!this.f.d()) && (this.f.isAllowed(itemstack1))) {
					if (!a(itemstack1, 3, 4, false))
						return null;
				}
				else if (SlotPotionBottle.a_(itemstack)) {
					if (!a(itemstack1, 0, 3, false))
						return null;
				}
				else if ((i >= 4) && (i < 31)) {
					if (!a(itemstack1, 31, 40, false))
						return null;
				}
				else if ((i >= 31) && (i < 40)) {
					if (!a(itemstack1, 4, 31, false))
						return null;
				}
				else if (!a(itemstack1, 4, 40, false))
					return null;
			}
			else {
				if (!a(itemstack1, 4, 40, true)) {
					return null;
				}

				slot.a(itemstack1, itemstack);
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

		CraftInventory inventory = new CraftInventory(this.brewingStand);
		this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
		return this.bukkitEntity;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ContainerBrewingStand
 * JD-Core Version:		0.6.0
 */