package net.minecraft.server;

public class NoteBlockData
{
	private int a;
	private int b;
	private int c;
	private int d;
	private int e;
	private int f;

	public NoteBlockData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
	{
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramInt3;
		this.e = paramInt5;
		this.f = paramInt6;
		this.d = paramInt4;
	}

	public int a() {
		return this.a;
	}

	public int b() {
		return this.b;
	}

	public int c() {
		return this.c;
	}

	public int d() {
		return this.e;
	}

	public int e() {
		return this.f;
	}

	public int f() {
		return this.d;
	}

	public boolean equals(Object paramObject)
	{
		if ((paramObject instanceof NoteBlockData)) {
			NoteBlockData localNoteBlockData = (NoteBlockData)paramObject;
			return (this.a == localNoteBlockData.a) && (this.b == localNoteBlockData.b) && (this.c == localNoteBlockData.c) && (this.e == localNoteBlockData.e) && (this.f == localNoteBlockData.f) && (this.d == localNoteBlockData.d);
		}
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NoteBlockData
 * JD-Core Version:		0.6.0
 */