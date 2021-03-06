package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;

public class InventoryLargeChest
	implements IInventory
{
	private String a;
	public IInventory left;
	public IInventory right;
	public List<HumanEntity> transaction = new ArrayList();

	public ItemStack[] getContents() {
		ItemStack[] result = new ItemStack[getSize()];
		for (int i = 0; i < result.length; i++) {
			result[i] = getItem(i);
		}
		return result;
	}

	public void onOpen(CraftHumanEntity who) {
		this.left.onOpen(who);
		this.right.onOpen(who);
		this.transaction.add(who);
	}

	public void onClose(CraftHumanEntity who) {
		this.left.onClose(who);
		this.right.onClose(who);
		this.transaction.remove(who);
	}

	public List<HumanEntity> getViewers() {
		return this.transaction;
	}

	public InventoryHolder getOwner() {
		return null;
	}

	public void setMaxStackSize(int size) {
		this.left.setMaxStackSize(size);
		this.right.setMaxStackSize(size);
	}

	public InventoryLargeChest(String s, IInventory iinventory, IInventory iinventory1)
	{
		this.a = s;
		if (iinventory == null) {
			iinventory = iinventory1;
		}

		if (iinventory1 == null) {
			iinventory1 = iinventory;
		}

		this.left = iinventory;
		this.right = iinventory1;
	}

	public int getSize() {
		return this.left.getSize() + this.right.getSize();
	}

	public String getName() {
		return this.a;
	}

	public ItemStack getItem(int i) {
		return i >= this.left.getSize() ? this.right.getItem(i - this.left.getSize()) : this.left.getItem(i);
	}

	public ItemStack splitStack(int i, int j) {
		return i >= this.left.getSize() ? this.right.splitStack(i - this.left.getSize(), j) : this.left.splitStack(i, j);
	}

	public ItemStack splitWithoutUpdate(int i) {
		return i >= this.left.getSize() ? this.right.splitWithoutUpdate(i - this.left.getSize()) : this.left.splitWithoutUpdate(i);
	}

	public void setItem(int i, ItemStack itemstack) {
		if (i >= this.left.getSize())
			this.right.setItem(i - this.left.getSize(), itemstack);
		else
			this.left.setItem(i, itemstack);
	}

	public int getMaxStackSize()
	{
		return Math.min(this.left.getMaxStackSize(), this.right.getMaxStackSize());
	}

	public void update() {
		this.left.update();
		this.right.update();
	}

	public boolean a(EntityHuman entityhuman) {
		return (this.left.a(entityhuman)) && (this.right.a(entityhuman));
	}

	public void startOpen() {
		this.left.startOpen();
		this.right.startOpen();
	}

	public void f() {
		this.left.f();
		this.right.f();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.InventoryLargeChest
 * JD-Core Version:		0.6.0
 */