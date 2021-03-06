package net.minecraft.server;

import java.util.Collection;
import java.util.Random;

public class WeightedRandom
{
	public static int a(Collection paramCollection)
	{
		int i = 0;
		for (WeightedRandomChoice localWeightedRandomChoice : paramCollection) {
			i += localWeightedRandomChoice.a;
		}
		return i;
	}

	public static WeightedRandomChoice a(Random paramRandom, Collection paramCollection, int paramInt)
	{
		if (paramInt <= 0) {
			throw new IllegalArgumentException();
		}

		int i = paramRandom.nextInt(paramInt);
		for (WeightedRandomChoice localWeightedRandomChoice : paramCollection) {
			i -= localWeightedRandomChoice.a;
			if (i < 0) {
				return localWeightedRandomChoice;
			}
		}
		return null;
	}

	public static WeightedRandomChoice a(Random paramRandom, Collection paramCollection) {
		return a(paramRandom, paramCollection, a(paramCollection));
	}

	public static int a(WeightedRandomChoice[] paramArrayOfWeightedRandomChoice) {
		int i = 0;
		for (WeightedRandomChoice localWeightedRandomChoice : paramArrayOfWeightedRandomChoice) {
			i += localWeightedRandomChoice.a;
		}
		return i;
	}

	public static WeightedRandomChoice a(Random paramRandom, WeightedRandomChoice[] paramArrayOfWeightedRandomChoice, int paramInt)
	{
		if (paramInt <= 0) {
			throw new IllegalArgumentException();
		}

		int i = paramRandom.nextInt(paramInt);
		for (WeightedRandomChoice localWeightedRandomChoice : paramArrayOfWeightedRandomChoice) {
			i -= localWeightedRandomChoice.a;
			if (i < 0) {
				return localWeightedRandomChoice;
			}
		}
		return null;
	}

	public static WeightedRandomChoice a(Random paramRandom, WeightedRandomChoice[] paramArrayOfWeightedRandomChoice) {
		return a(paramRandom, paramArrayOfWeightedRandomChoice, a(paramArrayOfWeightedRandomChoice));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WeightedRandom
 * JD-Core Version:		0.6.0
 */