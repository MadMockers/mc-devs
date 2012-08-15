package net.minecraft.server;

import java.util.Random;

public abstract class StructurePieceBlockSelector
{
	protected int a;
	protected int b;

	public abstract void a(Random paramRandom, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);

	public int a()
	{
		return this.a;
	}

	public int b() {
		return this.b;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.StructurePieceBlockSelector
 * JD-Core Version:		0.6.0
 */