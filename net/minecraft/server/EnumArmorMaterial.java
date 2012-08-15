package net.minecraft.server;

public enum EnumArmorMaterial
{
	private int f;
	private int[] g;
	private int h;

	private EnumArmorMaterial(int paramInt2, int[] arg4, int arg5)
	{
		this.f = paramInt2;
		Object localObject;
		this.g = localObject;
		int j;
		this.h = j;
	}

	public int a(int paramInt) {
		return ItemArmor.c()[paramInt] * this.f;
	}

	public int b(int paramInt) {
		return this.g[paramInt];
	}

	public int a() {
		return this.h;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnumArmorMaterial
 * JD-Core Version:		0.6.0
 */