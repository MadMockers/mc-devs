package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PotionBrewer
{
	public static final String a;
	public static final String b;
	public static final String c;
	public static final String d;
	public static final String e;
	public static final String f;
	public static final String g;
	public static final String h;
	public static final String i;
	public static final String j;
	public static final String k;
	private static final HashMap effectDurations = new HashMap();
	private static final HashMap effectAmplifiers = new HashMap();
	private static final HashMap n;
	private static final String[] appearances;

	public static boolean a(int paramInt1, int paramInt2)
	{
		return (paramInt1 & 1 << paramInt2) != 0;
	}

	private static int c(int paramInt1, int paramInt2) {
		return a(paramInt1, paramInt2) ? 1 : 0;
	}

	private static int d(int paramInt1, int paramInt2) {
		return a(paramInt1, paramInt2) ? 0 : 1;
	}

	public static int a(Collection paramCollection)
	{
		int m = 3694022;

		if ((paramCollection == null) || (paramCollection.isEmpty())) {
			return m;
		}

		float f1 = 0.0F;
		float f2 = 0.0F;
		float f3 = 0.0F;
		float f4 = 0.0F;

		for (MobEffect localMobEffect : paramCollection) {
			int i1 = MobEffectList.byId[localMobEffect.getEffectId()].j();

			for (int i2 = 0; i2 <= localMobEffect.getAmplifier(); i2++) {
				f1 += (i1 >> 16 & 0xFF) / 255.0F;
				f2 += (i1 >> 8 & 0xFF) / 255.0F;
				f3 += (i1 >> 0 & 0xFF) / 255.0F;
				f4 += 1.0F;
			}
		}

		f1 = f1 / f4 * 255.0F;
		f2 = f2 / f4 * 255.0F;
		f3 = f3 / f4 * 255.0F;

		return (int)f1 << 16 | (int)f2 << 8 | (int)f3;
	}

	private static int a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int m = 0;
		if (paramBoolean1)
			m = d(paramInt4, paramInt2);
		else if (paramInt1 != -1) {
			if ((paramInt1 == 0) && (h(paramInt4) == paramInt2))
				m = 1;
			else if ((paramInt1 == 1) && (h(paramInt4) > paramInt2))
				m = 1;
			else if ((paramInt1 == 2) && (h(paramInt4) < paramInt2))
				m = 1;
		}
		else {
			m = c(paramInt4, paramInt2);
		}
		if (paramBoolean2) {
			m *= paramInt3;
		}
		if (paramBoolean3) {
			m *= -1;
		}
		return m;
	}

	private static int h(int paramInt) {
		int m = 0;
		for (; paramInt > 0; m++) {
			paramInt &= paramInt - 1;
		}
		return m;
	}

	private static int a(String paramString, int paramInt1, int paramInt2, int paramInt3)
	{
		if ((paramInt1 >= paramString.length()) || (paramInt2 < 0) || (paramInt1 >= paramInt2)) {
			return 0;
		}

		int m = paramString.indexOf('|', paramInt1);
		if ((m >= 0) && (m < paramInt2)) {
			i1 = a(paramString, paramInt1, m - 1, paramInt3);
			if (i1 > 0) {
				return i1;
			}

			i2 = a(paramString, m + 1, paramInt2, paramInt3);
			if (i2 > 0) {
				return i2;
			}
			return 0;
		}

		int i1 = paramString.indexOf('&', paramInt1);
		if ((i1 >= 0) && (i1 < paramInt2)) {
			i2 = a(paramString, paramInt1, i1 - 1, paramInt3);
			if (i2 <= 0) {
				return 0;
			}

			i3 = a(paramString, i1 + 1, paramInt2, paramInt3);
			if (i3 <= 0) {
				return 0;
			}

			if (i2 > i3) {
				return i2;
			}
			return i3;
		}

		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		boolean bool2 = false;
		boolean bool3 = false;
		int i5 = -1;
		int i6 = 0;
		int i7 = 0;
		int i8 = 0;
		boolean bool1;
		for (int i9 = paramInt1; i9 < paramInt2; i9++)
		{
			int i10 = paramString.charAt(i9);
			if ((i10 >= 48) && (i10 <= 57)) {
				if (i2 != 0) {
					i7 = i10 - 48;
					i3 = 1;
				} else {
					i6 *= 10;
					i6 += i10 - 48;
					i4 = 1;
				}
			} else if (i10 == 42) {
				i2 = 1;
			} else if (i10 == 33) {
				if (i4 != 0) {
					i8 += a(bool2, i3, bool3, i5, i6, i7, paramInt3);
					i4 = bool1 = i2 = bool3 = bool2 = 0;
					i6 = i7 = 0;
					i5 = -1;
				}

				bool2 = true;
			} else if (i10 == 45) {
				if (i4 != 0) {
					i8 += a(bool2, bool1, bool3, i5, i6, i7, paramInt3);
					i4 = bool1 = i2 = bool3 = bool2 = 0;
					i6 = i7 = 0;
					i5 = -1;
				}

				bool3 = true;
			} else if ((i10 == 61) || (i10 == 60) || (i10 == 62)) {
				if (i4 != 0) {
					i8 += a(bool2, bool1, bool3, i5, i6, i7, paramInt3);
					i4 = bool1 = i2 = bool3 = bool2 = 0;
					i6 = i7 = 0;
					i5 = -1;
				}

				if (i10 == 61)
					i5 = 0;
				else if (i10 == 60)
					i5 = 2;
				else if (i10 == 62)
					i5 = 1;
			} else {
				if ((i10 != 43) || 
					(i4 == 0)) continue;
				i8 += a(bool2, bool1, bool3, i5, i6, i7, paramInt3);
				i4 = bool1 = i2 = bool3 = bool2 = 0;
				i6 = i7 = 0;
				i5 = -1;
			}
		}

		if (i4 != 0) {
			i8 += a(bool2, bool1, bool3, i5, i6, i7, paramInt3);
		}

		return i8;
	}

	public static List getEffects(int paramInt, boolean paramBoolean)
	{
		ArrayList localArrayList = null;

		for (MobEffectList localMobEffectList : MobEffectList.byId) {
			if ((localMobEffectList == null) || ((localMobEffectList.i()) && (!paramBoolean))) {
				continue;
			}
			String str1 = (String)effectDurations.get(Integer.valueOf(localMobEffectList.getId()));
			if (str1 == null)
			{
				continue;
			}
			int i2 = a(str1, 0, str1.length(), paramInt);
			if (i2 > 0) {
				int i3 = 0;
				String str2 = (String)effectAmplifiers.get(Integer.valueOf(localMobEffectList.getId()));
				if (str2 != null) {
					i3 = a(str2, 0, str2.length(), paramInt);
					if (i3 < 0) {
						i3 = 0;
					}
				}

				if (localMobEffectList.isInstant()) {
					i2 = 1;
				}
				else {
					i2 = 1200 * (i2 * 3 + (i2 - 1) * 2);
					i2 >>= i3;
					i2 = (int)Math.round(i2 * localMobEffectList.getDurationModifier());

					if ((paramInt & 0x4000) != 0) {
						i2 = (int)Math.round(i2 * 0.75D + 0.5D);
					}
				}

				if (localArrayList == null) {
					localArrayList = new ArrayList();
				}
				localArrayList.add(new MobEffect(localMobEffectList.getId(), i2, i3));
			}
		}

		return localArrayList;
	}

	private static int a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
	{
		if (paramBoolean3) {
			if (!a(paramInt1, paramInt2))
				return 0;
		}
		else if (paramBoolean1)
			paramInt1 &= (1 << paramInt2 ^ 0xFFFFFFFF);
		else if (paramBoolean2) {
			if ((paramInt1 & 1 << paramInt2) == 0)
				paramInt1 |= 1 << paramInt2;
			else
				paramInt1 &= (1 << paramInt2 ^ 0xFFFFFFFF);
		}
		else {
			paramInt1 |= 1 << paramInt2;
		}
		return paramInt1;
	}

	public static int a(int paramInt, String paramString)
	{
		int m = 0;
		int i1 = paramString.length();

		int i2 = 0;
		boolean bool1 = false;
		boolean bool2 = false;
		boolean bool3 = false;
		int i3 = 0;
		for (int i4 = m; i4 < i1; i4++)
		{
			int i5 = paramString.charAt(i4);
			if ((i5 >= 48) && (i5 <= 57)) {
				i3 *= 10;
				i3 += i5 - 48;
				i2 = 1;
			} else if (i5 == 33) {
				if (i2 != 0) {
					paramInt = a(paramInt, i3, bool2, bool1, bool3);
					i2 = bool2 = bool1 = bool3 = 0;
					i3 = 0;
				}

				bool1 = true;
			} else if (i5 == 45) {
				if (i2 != 0) {
					paramInt = a(paramInt, i3, bool2, bool1, bool3);
					i2 = bool2 = bool1 = bool3 = 0;
					i3 = 0;
				}

				bool2 = true;
			} else if (i5 == 43) {
				if (i2 != 0) {
					paramInt = a(paramInt, i3, bool2, bool1, bool3);
					i2 = bool2 = bool1 = bool3 = 0;
					i3 = 0;
				}
			} else if (i5 == 38) {
				if (i2 != 0) {
					paramInt = a(paramInt, i3, bool2, bool1, bool3);
					i2 = bool2 = bool1 = bool3 = 0;
					i3 = 0;
				}
				bool3 = true;
			}
		}
		if (i2 != 0) {
			paramInt = a(paramInt, i3, bool2, bool1, bool3);
		}

		return paramInt & 0x7FFF;
	}

	static
	{
		a = null;

		c = "+0-1-2-3&4-4+13";
		effectDurations.put(Integer.valueOf(MobEffectList.REGENERATION.getId()), "0 & !1 & !2 & !3 & 0+6");

		b = "-0+1-2-3&4-4+13";
		effectDurations.put(Integer.valueOf(MobEffectList.FASTER_MOVEMENT.getId()), "!0 & 1 & !2 & !3 & 1+6");

		h = "+0+1-2-3&4-4+13";
		effectDurations.put(Integer.valueOf(MobEffectList.FIRE_RESISTANCE.getId()), "0 & 1 & !2 & !3 & 0+6");

		f = "+0-1+2-3&4-4+13";
		effectDurations.put(Integer.valueOf(MobEffectList.HEAL.getId()), "0 & !1 & 2 & !3");

		d = "-0-1+2-3&4-4+13";
		effectDurations.put(Integer.valueOf(MobEffectList.POISON.getId()), "!0 & !1 & 2 & !3 & 2+6");

		e = "-0+3-4+13";
		effectDurations.put(Integer.valueOf(MobEffectList.WEAKNESS.getId()), "!0 & !1 & !2 & 3 & 3+6");
		effectDurations.put(Integer.valueOf(MobEffectList.HARM.getId()), "!0 & !1 & 2 & 3");
		effectDurations.put(Integer.valueOf(MobEffectList.SLOWER_MOVEMENT.getId()), "!0 & 1 & !2 & 3 & 3+6");

		g = "+0-1-2+3&4-4+13";
		effectDurations.put(Integer.valueOf(MobEffectList.INCREASE_DAMAGE.getId()), "0 & !1 & !2 & 3 & 3+6");

		j = "+5-6-7";
		effectAmplifiers.put(Integer.valueOf(MobEffectList.FASTER_MOVEMENT.getId()), "5");
		effectAmplifiers.put(Integer.valueOf(MobEffectList.FASTER_DIG.getId()), "5");
		effectAmplifiers.put(Integer.valueOf(MobEffectList.INCREASE_DAMAGE.getId()), "5");
		effectAmplifiers.put(Integer.valueOf(MobEffectList.REGENERATION.getId()), "5");
		effectAmplifiers.put(Integer.valueOf(MobEffectList.HARM.getId()), "5");
		effectAmplifiers.put(Integer.valueOf(MobEffectList.HEAL.getId()), "5");
		effectAmplifiers.put(Integer.valueOf(MobEffectList.RESISTANCE.getId()), "5");
		effectAmplifiers.put(Integer.valueOf(MobEffectList.POISON.getId()), "5");

		i = "-5+6-7";

		k = "+14&13-13";

		n = new HashMap();

		appearances = new String[] { "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky" };
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PotionBrewer
 * JD-Core Version:		0.6.0
 */