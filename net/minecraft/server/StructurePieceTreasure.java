package net.minecraft.server;

import java.util.Random;

public class StructurePieceTreasure extends WeightedRandomChoice
{
	private int b;
	private int c;
	private int d;
	private int e;

	public StructurePieceTreasure(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		super(paramInt5);
		this.b = paramInt1;
		this.c = paramInt2;
		this.d = paramInt3;
		this.e = paramInt4;
	}

	public static void a(Random paramRandom, StructurePieceTreasure[] paramArrayOfStructurePieceTreasure, TileEntityChest paramTileEntityChest, int paramInt)
	{
		for (int i = 0; i < paramInt; i++) {
			StructurePieceTreasure localStructurePieceTreasure = (StructurePieceTreasure)WeightedRandom.a(paramRandom, paramArrayOfStructurePieceTreasure);

			int j = localStructurePieceTreasure.d + paramRandom.nextInt(localStructurePieceTreasure.e - localStructurePieceTreasure.d + 1);
			if (Item.byId[localStructurePieceTreasure.b].getMaxStackSize() >= j) {
				paramTileEntityChest.setItem(paramRandom.nextInt(paramTileEntityChest.getSize()), new ItemStack(localStructurePieceTreasure.b, j, localStructurePieceTreasure.c));
			}
			else
				for (int k = 0; k < j; k++)
					paramTileEntityChest.setItem(paramRandom.nextInt(paramTileEntityChest.getSize()), new ItemStack(localStructurePieceTreasure.b, 1, localStructurePieceTreasure.c));
		}
	}

	public static void a(Random paramRandom, StructurePieceTreasure[] paramArrayOfStructurePieceTreasure, TileEntityDispenser paramTileEntityDispenser, int paramInt)
	{
		for (int i = 0; i < paramInt; i++) {
			StructurePieceTreasure localStructurePieceTreasure = (StructurePieceTreasure)WeightedRandom.a(paramRandom, paramArrayOfStructurePieceTreasure);

			int j = localStructurePieceTreasure.d + paramRandom.nextInt(localStructurePieceTreasure.e - localStructurePieceTreasure.d + 1);
			if (Item.byId[localStructurePieceTreasure.b].getMaxStackSize() >= j) {
				paramTileEntityDispenser.setItem(paramRandom.nextInt(paramTileEntityDispenser.getSize()), new ItemStack(localStructurePieceTreasure.b, j, localStructurePieceTreasure.c));
			}
			else
				for (int k = 0; k < j; k++)
					paramTileEntityDispenser.setItem(paramRandom.nextInt(paramTileEntityDispenser.getSize()), new ItemStack(localStructurePieceTreasure.b, 1, localStructurePieceTreasure.c));
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.StructurePieceTreasure
 * JD-Core Version:		0.6.0
 */