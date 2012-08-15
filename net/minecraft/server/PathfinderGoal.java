package net.minecraft.server;

public abstract class PathfinderGoal
{
	private int a = 0;

	public abstract boolean a();

	public boolean b() { return a(); }

	public boolean g()
	{
		return true;
	}

	public void e() {
	}

	public void c() {
	}

	public void d() {
	}

	public void a(int paramInt) {
		this.a = paramInt;
	}

	public int h() {
		return this.a;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoal
 * JD-Core Version:		0.6.0
 */