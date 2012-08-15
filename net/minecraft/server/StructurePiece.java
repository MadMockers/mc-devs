package net.minecraft.server;

import java.util.List;
import java.util.Random;

public abstract class StructurePiece
{
	protected StructureBoundingBox e;
	protected int f;
	protected int g;

	protected StructurePiece(int paramInt)
	{
		this.g = paramInt;
		this.f = -1;
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom) {
	}

	public abstract boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox);

	public StructureBoundingBox b() {
		return this.e;
	}

	public int c() {
		return this.g;
	}

	public static StructurePiece a(List paramList, StructureBoundingBox paramStructureBoundingBox)
	{
		for (StructurePiece localStructurePiece : paramList) {
			if ((localStructurePiece.b() != null) && (localStructurePiece.b().a(paramStructureBoundingBox))) {
				return localStructurePiece;
			}
		}
		return null;
	}

	public ChunkPosition a() {
		return new ChunkPosition(this.e.e(), this.e.f(), this.e.g());
	}

	protected boolean a(World paramWorld, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = Math.max(this.e.a - 1, paramStructureBoundingBox.a);
		int j = Math.max(this.e.b - 1, paramStructureBoundingBox.b);
		int k = Math.max(this.e.c - 1, paramStructureBoundingBox.c);
		int m = Math.min(this.e.d + 1, paramStructureBoundingBox.d);
		int n = Math.min(this.e.e + 1, paramStructureBoundingBox.e);
		int i1 = Math.min(this.e.f + 1, paramStructureBoundingBox.f);
		int i3;
		int i4;
		for (int i2 = i; i2 <= m; i2++) {
			for (i3 = k; i3 <= i1; i3++) {
				i4 = paramWorld.getTypeId(i2, j, i3);
				if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
					return true;
				}
				i4 = paramWorld.getTypeId(i2, n, i3);
				if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
					return true;
				}
			}
		}

		for (i2 = i; i2 <= m; i2++) {
			for (i3 = j; i3 <= n; i3++) {
				i4 = paramWorld.getTypeId(i2, i3, k);
				if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
					return true;
				}
				i4 = paramWorld.getTypeId(i2, i3, i1);
				if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
					return true;
				}
			}
		}

		for (i2 = k; i2 <= i1; i2++) {
			for (i3 = j; i3 <= n; i3++) {
				i4 = paramWorld.getTypeId(i, i3, i2);
				if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
					return true;
				}
				i4 = paramWorld.getTypeId(m, i3, i2);
				if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
					return true;
				}
			}
		}
		return false;
	}

	protected int a(int paramInt1, int paramInt2) {
		switch (this.f) {
		case 0:
		case 2:
			return this.e.a + paramInt1;
		case 1:
			return this.e.d - paramInt2;
		case 3:
			return this.e.a + paramInt2;
		}
		return paramInt1;
	}

	protected int a(int paramInt)
	{
		if (this.f == -1) {
			return paramInt;
		}
		return paramInt + this.e.b;
	}

	protected int b(int paramInt1, int paramInt2) {
		switch (this.f) {
		case 2:
			return this.e.f - paramInt2;
		case 0:
			return this.e.c + paramInt2;
		case 1:
		case 3:
			return this.e.c + paramInt1;
		}
		return paramInt2;
	}

	protected int c(int paramInt1, int paramInt2)
	{
		if (paramInt1 == Block.RAILS.id) {
			if ((this.f == 1) || (this.f == 3)) {
				if (paramInt2 == 1) {
					return 0;
				}
				return 1;
			}
		}
		else if ((paramInt1 == Block.WOODEN_DOOR.id) || (paramInt1 == Block.IRON_DOOR_BLOCK.id)) {
			if (this.f == 0) {
				if (paramInt2 == 0) {
					return 2;
				}
				if (paramInt2 == 2)
					return 0;
			} else {
				if (this.f == 1)
				{
					return paramInt2 + 1 & 0x3;
				}if (this.f == 3)
				{
					return paramInt2 + 3 & 0x3;
				}
			}
		} else if ((paramInt1 == Block.COBBLESTONE_STAIRS.id) || (paramInt1 == Block.WOOD_STAIRS.id) || (paramInt1 == Block.NETHER_BRICK_STAIRS.id) || (paramInt1 == Block.STONE_STAIRS.id) || (paramInt1 == Block.SANDSTONE_STAIRS.id)) {
			if (this.f == 0) {
				if (paramInt2 == 2) {
					return 3;
				}
				if (paramInt2 == 3)
					return 2;
			}
			else if (this.f == 1) {
				if (paramInt2 == 0) {
					return 2;
				}
				if (paramInt2 == 1) {
					return 3;
				}
				if (paramInt2 == 2) {
					return 0;
				}
				if (paramInt2 == 3)
					return 1;
			}
			else if (this.f == 3) {
				if (paramInt2 == 0) {
					return 2;
				}
				if (paramInt2 == 1) {
					return 3;
				}
				if (paramInt2 == 2) {
					return 1;
				}
				if (paramInt2 == 3)
					return 0;
			}
		}
		else if (paramInt1 == Block.LADDER.id) {
			if (this.f == 0) {
				if (paramInt2 == 2) {
					return 3;
				}
				if (paramInt2 == 3)
					return 2;
			}
			else if (this.f == 1) {
				if (paramInt2 == 2) {
					return 4;
				}
				if (paramInt2 == 3) {
					return 5;
				}
				if (paramInt2 == 4) {
					return 2;
				}
				if (paramInt2 == 5)
					return 3;
			}
			else if (this.f == 3) {
				if (paramInt2 == 2) {
					return 5;
				}
				if (paramInt2 == 3) {
					return 4;
				}
				if (paramInt2 == 4) {
					return 2;
				}
				if (paramInt2 == 5) {
					return 3;
				}
			}
		}
		else if (paramInt1 == Block.STONE_BUTTON.id) {
			if (this.f == 0) {
				if (paramInt2 == 3) {
					return 4;
				}
				if (paramInt2 == 4)
					return 3;
			}
			else if (this.f == 1) {
				if (paramInt2 == 3) {
					return 1;
				}
				if (paramInt2 == 4) {
					return 2;
				}
				if (paramInt2 == 2) {
					return 3;
				}
				if (paramInt2 == 1)
					return 4;
			}
			else if (this.f == 3) {
				if (paramInt2 == 3) {
					return 2;
				}
				if (paramInt2 == 4) {
					return 1;
				}
				if (paramInt2 == 2) {
					return 3;
				}
				if (paramInt2 == 1)
					return 4;
			}
		}
		else if ((paramInt1 == Block.TRIPWIRE_SOURCE.id) || ((Block.byId[paramInt1] != null) && ((Block.byId[paramInt1] instanceof BlockDirectional)))) {
			if (this.f == 0) {
				if ((paramInt2 == 0) || (paramInt2 == 2))
					return Direction.e[paramInt2];
			}
			else if (this.f == 1) {
				if (paramInt2 == 2) {
					return 1;
				}
				if (paramInt2 == 0) {
					return 3;
				}
				if (paramInt2 == 1) {
					return 2;
				}
				if (paramInt2 == 3)
					return 0;
			}
			else if (this.f == 3) {
				if (paramInt2 == 2) {
					return 3;
				}
				if (paramInt2 == 0) {
					return 1;
				}
				if (paramInt2 == 1) {
					return 2;
				}
				if (paramInt2 == 3)
					return 0;
			}
		}
		else if ((paramInt1 == Block.PISTON.id) || (paramInt1 == Block.PISTON_STICKY.id) || (paramInt1 == Block.LEVER.id) || (paramInt1 == Block.DISPENSER.id)) {
			if (this.f == 0) {
				if ((paramInt2 == 2) || (paramInt2 == 3))
					return Facing.OPPOSITE_FACING[paramInt2];
			}
			else if (this.f == 1) {
				if (paramInt2 == 2) {
					return 4;
				}
				if (paramInt2 == 3) {
					return 5;
				}
				if (paramInt2 == 4) {
					return 2;
				}
				if (paramInt2 == 5)
					return 3;
			}
			else if (this.f == 3) {
				if (paramInt2 == 2) {
					return 5;
				}
				if (paramInt2 == 3) {
					return 4;
				}
				if (paramInt2 == 4) {
					return 2;
				}
				if (paramInt2 == 5) {
					return 3;
				}
			}
		}
		return paramInt2;
	}

	protected void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = a(paramInt3, paramInt5);
		int j = a(paramInt4);
		int k = b(paramInt3, paramInt5);

		if (!paramStructureBoundingBox.b(i, j, k)) {
			return;
		}

		paramWorld.setRawTypeIdAndData(i, j, k, paramInt1, paramInt2);
	}

	protected int a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = a(paramInt1, paramInt3);
		int j = a(paramInt2);
		int k = b(paramInt1, paramInt3);

		if (!paramStructureBoundingBox.b(i, j, k)) {
			return 0;
		}

		return paramWorld.getTypeId(i, j, k);
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
		for (int i = paramInt2; i <= paramInt5; i++)
			for (int j = paramInt1; j <= paramInt4; j++)
				for (int k = paramInt3; k <= paramInt6; k++)
					a(paramWorld, 0, 0, j, i, k, paramStructureBoundingBox);
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
	{
		for (int i = paramInt2; i <= paramInt5; i++)
			for (int j = paramInt1; j <= paramInt4; j++)
				for (int k = paramInt3; k <= paramInt6; k++)
				{
					if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0)) {
						continue;
					}
					if ((i == paramInt2) || (i == paramInt5) || (j == paramInt1) || (j == paramInt4) || (k == paramInt3) || (k == paramInt6))
						a(paramWorld, paramInt7, 0, j, i, k, paramStructureBoundingBox);
					else
						a(paramWorld, paramInt8, 0, j, i, k, paramStructureBoundingBox);
				}
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, boolean paramBoolean)
	{
		for (int i = paramInt2; i <= paramInt5; i++)
			for (int j = paramInt1; j <= paramInt4; j++)
				for (int k = paramInt3; k <= paramInt6; k++)
				{
					if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0)) {
						continue;
					}
					if ((i == paramInt2) || (i == paramInt5) || (j == paramInt1) || (j == paramInt4) || (k == paramInt3) || (k == paramInt6))
						a(paramWorld, paramInt7, paramInt8, j, i, k, paramStructureBoundingBox);
					else
						a(paramWorld, paramInt9, paramInt10, j, i, k, paramStructureBoundingBox);
				}
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, Random paramRandom, StructurePieceBlockSelector paramStructurePieceBlockSelector)
	{
		for (int i = paramInt2; i <= paramInt5; i++)
			for (int j = paramInt1; j <= paramInt4; j++)
				for (int k = paramInt3; k <= paramInt6; k++)
				{
					if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0)) {
						continue;
					}
					paramStructurePieceBlockSelector.a(paramRandom, j, i, k, (i == paramInt2) || (i == paramInt5) || (j == paramInt1) || (j == paramInt4) || (k == paramInt3) || (k == paramInt6));
					a(paramWorld, paramStructurePieceBlockSelector.a(), paramStructurePieceBlockSelector.b(), j, i, k, paramStructureBoundingBox);
				}
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
	{
		for (int i = paramInt2; i <= paramInt5; i++)
			for (int j = paramInt1; j <= paramInt4; j++)
				for (int k = paramInt3; k <= paramInt6; k++)
				{
					if (paramRandom.nextFloat() > paramFloat) {
						continue;
					}
					if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0)) {
						continue;
					}
					if ((i == paramInt2) || (i == paramInt5) || (j == paramInt1) || (j == paramInt4) || (k == paramInt3) || (k == paramInt6))
						a(paramWorld, paramInt7, 0, j, i, k, paramStructureBoundingBox);
					else
						a(paramWorld, paramInt8, 0, j, i, k, paramStructureBoundingBox);
				}
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		if (paramRandom.nextFloat() < paramFloat)
			a(paramWorld, paramInt4, paramInt5, paramInt1, paramInt2, paramInt3, paramStructureBoundingBox);
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean)
	{
		float f1 = paramInt4 - paramInt1 + 1;
		float f2 = paramInt5 - paramInt2 + 1;
		float f3 = paramInt6 - paramInt3 + 1;
		float f4 = paramInt1 + f1 / 2.0F;
		float f5 = paramInt3 + f3 / 2.0F;

		for (int i = paramInt2; i <= paramInt5; i++) {
			float f6 = (i - paramInt2) / f2;

			for (int j = paramInt1; j <= paramInt4; j++) {
				float f7 = (j - f4) / (f1 * 0.5F);

				for (int k = paramInt3; k <= paramInt6; k++) {
					float f8 = (k - f5) / (f3 * 0.5F);

					if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0))
					{
						continue;
					}
					float f9 = f7 * f7 + f6 * f6 + f8 * f8;

					if (f9 <= 1.05F)
						a(paramWorld, paramInt7, 0, j, i, k, paramStructureBoundingBox);
				}
			}
		}
	}

	protected void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = a(paramInt1, paramInt3);
		int j = a(paramInt2);
		int k = b(paramInt1, paramInt3);

		if (!paramStructureBoundingBox.b(i, j, k)) {
			return;
		}

		while ((!paramWorld.isEmpty(i, j, k)) && (j < 255)) {
			paramWorld.setRawTypeIdAndData(i, j, k, 0, 0);
			j++;
		}
	}

	protected void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = a(paramInt3, paramInt5);
		int j = a(paramInt4);
		int k = b(paramInt3, paramInt5);

		if (!paramStructureBoundingBox.b(i, j, k)) {
			return;
		}

		while (((paramWorld.isEmpty(i, j, k)) || (paramWorld.getMaterial(i, j, k).isLiquid())) && (j > 1)) {
			paramWorld.setRawTypeIdAndData(i, j, k, paramInt1, paramInt2);
			j--;
		}
	}

	protected boolean a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, StructurePieceTreasure[] paramArrayOfStructurePieceTreasure, int paramInt4)
	{
		int i = a(paramInt1, paramInt3);
		int j = a(paramInt2);
		int k = b(paramInt1, paramInt3);

		if ((paramStructureBoundingBox.b(i, j, k)) && 
			(paramWorld.getTypeId(i, j, k) != Block.CHEST.id)) {
			paramWorld.setTypeId(i, j, k, Block.CHEST.id);
			TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(i, j, k);
			if (localTileEntityChest != null) StructurePieceTreasure.a(paramRandom, paramArrayOfStructurePieceTreasure, localTileEntityChest, paramInt4);
			return true;
		}

		return false;
	}

	protected boolean a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, StructurePieceTreasure[] paramArrayOfStructurePieceTreasure, int paramInt5)
	{
		int i = a(paramInt1, paramInt3);
		int j = a(paramInt2);
		int k = b(paramInt1, paramInt3);

		if ((paramStructureBoundingBox.b(i, j, k)) && 
			(paramWorld.getTypeId(i, j, k) != Block.DISPENSER.id)) {
			paramWorld.setTypeIdAndData(i, j, k, Block.DISPENSER.id, c(Block.DISPENSER.id, paramInt4));
			TileEntityDispenser localTileEntityDispenser = (TileEntityDispenser)paramWorld.getTileEntity(i, j, k);
			if (localTileEntityDispenser != null) StructurePieceTreasure.a(paramRandom, paramArrayOfStructurePieceTreasure, localTileEntityDispenser, paramInt5);
			return true;
		}

		return false;
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int i = a(paramInt1, paramInt3);
		int j = a(paramInt2);
		int k = b(paramInt1, paramInt3);

		if (paramStructureBoundingBox.b(i, j, k))
			ItemDoor.place(paramWorld, i, j, k, paramInt4, Block.WOODEN_DOOR);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.StructurePiece
 * JD-Core Version:		0.6.0
 */