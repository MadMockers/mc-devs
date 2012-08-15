package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

public class InventoryEnderChest extends InventorySubcontainer
{
	private TileEntityEnderChest a;
	public List<HumanEntity> transaction = new ArrayList();
	public Player player;
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
		return this.player;
	}

	public void setMaxStackSize(int size) {
		this.maxStack = size;
	}

	public int getMaxStackSize() {
		return this.maxStack;
	}

	public InventoryEnderChest()
	{
		super("container.enderchest", 27);
	}

	public void a(TileEntityEnderChest tileentityenderchest) {
		this.a = tileentityenderchest;
	}

	public void a(NBTTagList nbttaglist)
	{
		for (int i = 0; i < getSize(); i++) {
			setItem(i, (ItemStack)null);
		}

		for (i = 0; i < nbttaglist.size(); i++) {
			NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.get(i);
			int j = nbttagcompound.getByte("Slot") & 0xFF;

			if ((j >= 0) && (j < getSize()))
				setItem(j, ItemStack.a(nbttagcompound));
		}
	}

	public NBTTagList g()
	{
		NBTTagList nbttaglist = new NBTTagList("EnderItems");

		for (int i = 0; i < getSize(); i++) {
			ItemStack itemstack = getItem(i);

			if (itemstack != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();

				nbttagcompound.setByte("Slot", (byte)i);
				itemstack.save(nbttagcompound);
				nbttaglist.add(nbttagcompound);
			}
		}

		return nbttaglist;
	}

	public boolean a(EntityHuman entityhuman) {
		return (this.a != null) && (!this.a.a(entityhuman)) ? false : super.a(entityhuman);
	}

	public void startOpen() {
		if (this.a != null) {
			this.a.a();
		}

		super.startOpen();
	}

	public void f() {
		if (this.a != null) {
			this.a.b();
		}

		super.f();
		this.a = null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.InventoryEnderChest
 * JD-Core Version:		0.6.0
 */