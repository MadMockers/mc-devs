package net.minecraft.server;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.util.EntryBase;
import org.bukkit.craftbukkit.util.LongBaseHashtable;
import org.bukkit.craftbukkit.util.LongHash;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public final class SpawnerCreature
{
	protected static final Class[] a = { EntitySpider.class, EntityZombie.class, EntitySkeleton.class };

	protected static ChunkPosition getRandomPosition(World world, int i, int j) {
		Chunk chunk = world.getChunkAt(i, j);
		int k = i * 16 + world.random.nextInt(16);
		int l = j * 16 + world.random.nextInt(16);
		int i1 = world.random.nextInt(chunk == null ? world.L() : chunk.h() + 16 - 1);

		return new ChunkPosition(k, i1, l);
	}

	public static final int spawnEntities(WorldServer worldserver, boolean flag, boolean flag1) {
		if ((!flag) && (!flag1)) {
			return 0;
		}

		LongBaseHashtable chunkCoords = new LongBaseHashtable();

		for (int i = 0; i < worldserver.players.size(); i++) {
			EntityHuman entityhuman = (EntityHuman)worldserver.players.get(i);
			int k = MathHelper.floor(entityhuman.locX / 16.0D);

			int j = MathHelper.floor(entityhuman.locZ / 16.0D);
			byte b0 = 8;

			for (int l = -b0; l <= b0; l++) {
				for (int i1 = -b0; i1 <= b0; i1++) {
					boolean flag2 = (l == -b0) || (l == b0) || (i1 == -b0) || (i1 == b0);

					long chunkCoord = LongHash.toLong(l + k, i1 + j);

					if (!flag2)
						chunkCoords.put(new ChunkEntry(l + k, i1 + j, false));
					else if (!chunkCoords.containsKey(chunkCoord)) {
						chunkCoords.put(new ChunkEntry(l + k, i1 + j, true));
					}
				}
			}

		}

		i = 0;
		ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
		ArrayList b = chunkCoords.entries();
		EnumCreatureType[] aenumcreaturetype = EnumCreatureType.values();

		int j = aenumcreaturetype.length;
		EnumCreatureType enumcreaturetype;
		label903: label912: for (int j1 = 0; j1 < j; j1++) {
			enumcreaturetype = aenumcreaturetype[j1];

			int limit = 0;
			switch (1.$SwitchMap$net$minecraft$server$EnumCreatureType[enumcreaturetype.ordinal()]) {
			case 1:
				limit = worldserver.getWorld().getMonsterSpawnLimit();
				break;
			case 2:
				limit = worldserver.getWorld().getAnimalSpawnLimit();
				break;
			case 3:
				limit = worldserver.getWorld().getWaterAnimalSpawnLimit();
			}

			if (limit == 0) {
				return 0;
			}

			if (((enumcreaturetype.d()) && (!flag1)) || ((!enumcreaturetype.d()) && (!flag)) || (worldserver.a(enumcreaturetype.a()) > limit * b.size() / 256))
			{
				continue;
			}
			for (EntryBase base : b) {
				ChunkEntry entry = (ChunkEntry)base;
				if (!entry.spawn) {
					ChunkPosition chunkposition = getRandomPosition(worldserver, entry.getX(), entry.getZ());

					int k1 = chunkposition.x;
					int l1 = chunkposition.y;
					int i2 = chunkposition.z;

					if ((!worldserver.s(k1, l1, i2)) && (worldserver.getMaterial(k1, l1, i2) == enumcreaturetype.c())) {
						int j2 = 0;
						int k2 = 0;
						while (true) {
							if (k2 >= 3) break label912; int l2 = k1;
							int i3 = l1;
							int j3 = i2;
							byte b1 = 6;
							BiomeMeta biomemeta = null;
							int k3 = 0;
							while (true)
							{
								if (k3 >= 4) break label903;
								l2 += worldserver.random.nextInt(b1) - worldserver.random.nextInt(b1);
								i3 += worldserver.random.nextInt(1) - worldserver.random.nextInt(1);
								j3 += worldserver.random.nextInt(b1) - worldserver.random.nextInt(b1);
								if (a(enumcreaturetype, worldserver, l2, i3, j3)) {
									float f = l2 + 0.5F;
									float f1 = i3;
									float f2 = j3 + 0.5F;

									if (worldserver.findNearbyPlayer(f, f1, f2, 24.0D) == null) {
										float f3 = f - chunkcoordinates.x;
										float f4 = f1 - chunkcoordinates.y;
										float f5 = f2 - chunkcoordinates.z;
										float f6 = f3 * f3 + f4 * f4 + f5 * f5;

										if (f6 >= 576.0F) {
											if (biomemeta == null) {
												biomemeta = worldserver.a(enumcreaturetype, l2, i3, j3);
												if (biomemeta == null)
													break label903;
											}
											EntityLiving entityliving;
											try
											{
												entityliving = (EntityLiving)biomemeta.b.getConstructor(new Class[] { World.class }).newInstance(new Object[] { worldserver });
											} catch (Exception exception) {
												exception.printStackTrace();
												return i;
											}

											entityliving.setPositionRotation(f, f1, f2, worldserver.random.nextFloat() * 360.0F, 0.0F);
											if (entityliving.canSpawn()) {
												j2++;

												worldserver.addEntity(entityliving, CreatureSpawnEvent.SpawnReason.NATURAL);
												a(entityliving, worldserver, f, f1, f2);
												if (j2 >= entityliving.bl())
												{
													break;
												}
											}
											i += j2;
										}
									}
								}

								k3++;
							}

							k2++;
						}
					}

				}

			}

		}

		return i;
	}

	public static boolean a(EnumCreatureType enumcreaturetype, World world, int i, int j, int k)
	{
		if (enumcreaturetype.c() == Material.WATER)
			return (world.getMaterial(i, j, k).isLiquid()) && (!world.s(i, j + 1, k));
		if (!world.t(i, j - 1, k)) {
			return false;
		}
		int l = world.getTypeId(i, j - 1, k);

		return (l != Block.BEDROCK.id) && (!world.s(i, j, k)) && (!world.getMaterial(i, j, k).isLiquid()) && (!world.s(i, j + 1, k));
	}

	private static void a(EntityLiving entityliving, World world, float f, float f1, float f2)
	{
		if (entityliving.dead) return;
		if (((entityliving instanceof EntitySpider)) && (world.random.nextInt(100) == 0)) {
			EntitySkeleton entityskeleton = new EntitySkeleton(world);

			entityskeleton.setPositionRotation(f, f1, f2, entityliving.yaw, 0.0F);

			world.addEntity(entityskeleton, CreatureSpawnEvent.SpawnReason.JOCKEY);
			entityskeleton.mount(entityliving);
		} else if ((entityliving instanceof EntitySheep)) {
			((EntitySheep)entityliving).setColor(EntitySheep.a(world.random));
		} else if (((entityliving instanceof EntityOcelot)) && (world.random.nextInt(7) == 0)) {
			for (int i = 0; i < 2; i++) {
				EntityOcelot entityocelot = new EntityOcelot(world);

				entityocelot.setPositionRotation(f, f1, f2, entityliving.yaw, 0.0F);
				entityocelot.setAge(-24000);
				world.addEntity(entityocelot, CreatureSpawnEvent.SpawnReason.NATURAL);
			}
		}
	}

	public static void a(World world, BiomeBase biomebase, int i, int j, int k, int l, Random random) {
		List list = biomebase.getMobs(EnumCreatureType.CREATURE);

		if (!list.isEmpty())
			while (random.nextFloat() < biomebase.f()) {
				BiomeMeta biomemeta = (BiomeMeta)WeightedRandom.a(world.random, list);
				int i1 = biomemeta.c + random.nextInt(1 + biomemeta.d - biomemeta.c);
				int j1 = i + random.nextInt(k);
				int k1 = j + random.nextInt(l);
				int l1 = j1;
				int i2 = k1;

				for (int j2 = 0; j2 < i1; j2++) {
					boolean flag = false;

					for (int k2 = 0; (!flag) && (k2 < 4); k2++) {
						int l2 = world.h(j1, k1);

						if (a(EnumCreatureType.CREATURE, world, j1, l2, k1)) { float f = j1 + 0.5F;
							float f1 = l2;
							float f2 = k1 + 0.5F;
							EntityLiving entityliving;
							try { entityliving = (EntityLiving)biomemeta.b.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
							} catch (Exception exception) {
								exception.printStackTrace();
								continue;
							}

							entityliving.setPositionRotation(f, f1, f2, random.nextFloat() * 360.0F, 0.0F);

							world.addEntity(entityliving, CreatureSpawnEvent.SpawnReason.CHUNK_GEN);
							a(entityliving, world, f, f1, f2);
							flag = true;
						}

						j1 += random.nextInt(5) - random.nextInt(5);

						for (k1 += random.nextInt(5) - random.nextInt(5); (j1 < i) || (j1 >= i + k) || (k1 < j) || (k1 >= j + k); k1 = i2 + random.nextInt(5) - random.nextInt(5))
							j1 = l1 + random.nextInt(5) - random.nextInt(5);
					}
				}
			}
	}

	private static class ChunkEntry extends EntryBase
	{
		public boolean spawn;

		public ChunkEntry(int x, int z, boolean spawn)
		{
			super();
			this.spawn = spawn;
		}

		int getX() {
			return LongHash.msw(this.key);
		}

		int getZ() {
			return LongHash.lsw(this.key);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.SpawnerCreature
 * JD-Core Version:		0.6.0
 */