package net.minecraft.server;

public class MaterialMapColor
{
	public static final MaterialMapColor[] a = new MaterialMapColor[16];

	public static final MaterialMapColor b = new MaterialMapColor(0, 0);
	public static final MaterialMapColor c = new MaterialMapColor(1, 8368696);
	public static final MaterialMapColor d = new MaterialMapColor(2, 16247203);
	public static final MaterialMapColor e = new MaterialMapColor(3, 10987431);
	public static final MaterialMapColor f = new MaterialMapColor(4, 16711680);
	public static final MaterialMapColor g = new MaterialMapColor(5, 10526975);
	public static final MaterialMapColor h = new MaterialMapColor(6, 10987431);
	public static final MaterialMapColor i = new MaterialMapColor(7, 31744);
	public static final MaterialMapColor j = new MaterialMapColor(8, 16777215);
	public static final MaterialMapColor k = new MaterialMapColor(9, 10791096);
	public static final MaterialMapColor l = new MaterialMapColor(10, 12020271);
	public static final MaterialMapColor m = new MaterialMapColor(11, 7368816);
	public static final MaterialMapColor n = new MaterialMapColor(12, 4210943);
	public static final MaterialMapColor o = new MaterialMapColor(13, 6837042);
	public final int p;
	public final int q;

	private MaterialMapColor(int paramInt1, int paramInt2)
	{
		this.q = paramInt1;
		this.p = paramInt2;
		a[paramInt1] = this;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.MaterialMapColor
 * JD-Core Version:		0.6.0
 */