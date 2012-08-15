package net.minecraft.server;

public enum EnumCreatureType
{
	private final Class d;
	private final int e;
	private final Material f;
	private final boolean g;

	private EnumCreatureType(Class paramMaterial, int paramBoolean, Material arg5, boolean arg6)
	{
		this.d = paramMaterial;
		this.e = paramBoolean;
		Object localObject;
		this.f = localObject;
		boolean bool;
		this.g = bool;
	}

	public Class a() {
		return this.d;
	}

	public int b() {
		return this.e;
	}

	public Material c() {
		return this.f;
	}

	public boolean d() {
		return this.g;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnumCreatureType
 * JD-Core Version:		0.6.0
 */