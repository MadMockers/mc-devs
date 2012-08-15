package net.minecraft.server;

import java.util.Random;

public class MathHelper
{
	private static float[] a = new float[65536];

	public static final float sin(float paramFloat)
	{
		return a[((int)(paramFloat * 10430.378F) & 0xFFFF)];
	}

	public static final float cos(float paramFloat) {
		return a[((int)(paramFloat * 10430.378F + 16384.0F) & 0xFFFF)];
	}

	public static final float c(float paramFloat) {
		return (float)Math.sqrt(paramFloat);
	}

	public static final float sqrt(double paramDouble) {
		return (float)Math.sqrt(paramDouble);
	}

	public static int d(float paramFloat) {
		int i = (int)paramFloat;
		return paramFloat < i ? i - 1 : i;
	}

	public static int floor(double paramDouble)
	{
		int i = (int)paramDouble;
		return paramDouble < i ? i - 1 : i;
	}

	public static long d(double paramDouble) {
		long l = ()paramDouble;
		return paramDouble < l ? l - 1L : l;
	}

	public static float abs(float paramFloat)
	{
		return paramFloat >= 0.0F ? paramFloat : -paramFloat;
	}

	public static int a(int paramInt) {
		return paramInt >= 0 ? paramInt : -paramInt;
	}

	public static int f(float paramFloat) {
		int i = (int)paramFloat;
		return paramFloat > i ? i + 1 : i;
	}

	public static int f(double paramDouble) {
		int i = (int)paramDouble;
		return paramDouble > i ? i + 1 : i;
	}

	public static int a(int paramInt1, int paramInt2, int paramInt3) {
		if (paramInt1 < paramInt2) {
			return paramInt2;
		}
		if (paramInt1 > paramInt3) {
			return paramInt3;
		}
		return paramInt1;
	}

	public static double a(double paramDouble1, double paramDouble2)
	{
		if (paramDouble1 < 0.0D) paramDouble1 = -paramDouble1;
		if (paramDouble2 < 0.0D) paramDouble2 = -paramDouble2;
		return paramDouble1 > paramDouble2 ? paramDouble1 : paramDouble2;
	}

	public static int a(Random paramRandom, int paramInt1, int paramInt2)
	{
		if (paramInt1 >= paramInt2) {
			return paramInt1;
		}
		return paramRandom.nextInt(paramInt2 - paramInt1 + 1) + paramInt1;
	}

	public static double a(long[] paramArrayOfLong) {
		long l1 = 0L;

		for (long l2 : paramArrayOfLong) {
			l1 += l2;
		}

		return l1 / paramArrayOfLong.length;
	}

	public static float g(float paramFloat) {
		paramFloat %= 360.0F;
		if (paramFloat >= 180.0F) paramFloat -= 360.0F;
		if (paramFloat < -180.0F) paramFloat += 360.0F;
		return paramFloat;
	}

	public static double g(double paramDouble) {
		paramDouble %= 360.0D;
		if (paramDouble >= 180.0D) paramDouble -= 360.0D;
		if (paramDouble < -180.0D) paramDouble += 360.0D;
		return paramDouble;
	}

	static
	{
		for (int i = 0; i < 65536; i++)
			a[i] = (float)Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.MathHelper
 * JD-Core Version:		0.6.0
 */