package net.minecraft.server;

public class MovingObjectPosition
{
	public EnumMovingObjectType type;
	public int b;
	public int c;
	public int d;
	public int face;
	public Vec3D pos;
	public Entity entity;

	public MovingObjectPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Vec3D paramVec3D)
	{
		this.type = EnumMovingObjectType.TILE;
		this.b = paramInt1;
		this.c = paramInt2;
		this.d = paramInt3;
		this.face = paramInt4;
		this.pos = Vec3D.a().create(paramVec3D.a, paramVec3D.b, paramVec3D.c);
	}

	public MovingObjectPosition(Entity paramEntity) {
		this.type = EnumMovingObjectType.ENTITY;
		this.entity = paramEntity;
		this.pos = Vec3D.a().create(paramEntity.locX, paramEntity.locY, paramEntity.locZ);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.MovingObjectPosition
 * JD-Core Version:		0.6.0
 */