package net.minecraft.server;

final class WorldGenStrongholdPiece2 extends WorldGenStrongholdPieceWeight
{
	WorldGenStrongholdPiece2(Class paramClass, int paramInt1, int paramInt2)
	{
		super(paramClass, paramInt1, paramInt2);
	}
	public boolean a(int paramInt) {
		return (super.a(paramInt)) && (paramInt > 5);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdPiece2
 * JD-Core Version:		0.6.0
 */