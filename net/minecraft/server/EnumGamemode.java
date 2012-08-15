package net.minecraft.server;

public enum EnumGamemode
{
	int e;
	String f;

	private EnumGamemode(int arg3, String arg4)
	{
		int i;
		this.e = i;
		Object localObject;
		this.f = localObject;
	}

	public int a() {
		return this.e;
	}

	public String b() {
		return this.f;
	}

	public void a(PlayerAbilities paramPlayerAbilities) {
		if (this == CREATIVE) {
			paramPlayerAbilities.canFly = true;
			paramPlayerAbilities.canInstantlyBuild = true;
			paramPlayerAbilities.isInvulnerable = true;
		} else {
			paramPlayerAbilities.canFly = false;
			paramPlayerAbilities.canInstantlyBuild = false;
			paramPlayerAbilities.isInvulnerable = false;
			paramPlayerAbilities.isFlying = false;
		}
		paramPlayerAbilities.mayBuild = (!isAdventure());
	}

	public boolean isAdventure() {
		return this == ADVENTURE;
	}

	public boolean d() {
		return this == CREATIVE;
	}

	public static EnumGamemode a(int paramInt)
	{
		for (EnumGamemode localEnumGamemode : values()) {
			if (localEnumGamemode.e == paramInt) {
				return localEnumGamemode;
			}
		}
		return SURVIVAL;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnumGamemode
 * JD-Core Version:		0.6.0
 */