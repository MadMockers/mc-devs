package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PlayerManager
{
	private final WorldServer world;
	private final List managedPlayers = new ArrayList();
	private final LongHashMap c = new LongHashMap();
	private final Queue d = new ConcurrentLinkedQueue();
	private final int e;
	private final int[][] f = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private boolean wasNotEmpty;

	public PlayerManager(WorldServer worldserver, int i)
	{
		if (i > 15)
			throw new IllegalArgumentException("Too big view radius!");
		if (i < 3) {
			throw new IllegalArgumentException("Too small view radius!");
		}
		this.e = i;
		this.world = worldserver;
	}

	public WorldServer a()
	{
		return this.world;
	}

	public void flush() {
		Iterator iterator = this.d.iterator();

		while (iterator.hasNext()) {
			PlayerInstance playerinstance = (PlayerInstance)iterator.next();

			playerinstance.a();

			iterator.remove();
		}

		if (this.managedPlayers.isEmpty()) {
			if (!this.wasNotEmpty) return;
			WorldProvider worldprovider = this.world.worldProvider;

			if (!worldprovider.e()) {
				this.world.chunkProviderServer.a();
			}

			this.wasNotEmpty = false;
		} else {
			this.wasNotEmpty = true;
		}
	}

	private PlayerInstance a(int i, int j, boolean flag)
	{
		long k = i + 2147483647L | j + 2147483647L << 32;
		PlayerInstance playerinstance = (PlayerInstance)this.c.getEntry(k);

		if ((playerinstance == null) && (flag)) {
			playerinstance = new PlayerInstance(this, i, j);
			this.c.put(k, playerinstance);
		}

		return playerinstance;
	}

	public void flagDirty(int i, int j, int k) {
		int l = i >> 4;
		int i1 = k >> 4;
		PlayerInstance playerinstance = a(l, i1, false);

		if (playerinstance != null)
			playerinstance.a(i & 0xF, j, k & 0xF);
	}

	public void addPlayer(EntityPlayer entityplayer)
	{
		int i = (int)entityplayer.locX >> 4;
		int j = (int)entityplayer.locZ >> 4;

		entityplayer.d = entityplayer.locX;
		entityplayer.e = entityplayer.locZ;

		for (int k = i - this.e; k <= i + this.e; k++) {
			for (int l = j - this.e; l <= j + this.e; l++) {
				a(k, l, true).a(entityplayer);
			}
		}

		this.managedPlayers.add(entityplayer);
		b(entityplayer);
	}

	public void b(EntityPlayer entityplayer) {
		ArrayList arraylist = new ArrayList(entityplayer.chunkCoordIntPairQueue);
		int i = 0;
		int j = this.e;
		int k = (int)entityplayer.locX >> 4;
		int l = (int)entityplayer.locZ >> 4;
		int i1 = 0;
		int j1 = 0;
		ChunkCoordIntPair chunkcoordintpair = PlayerInstance.a(a(k, l, true));

		entityplayer.chunkCoordIntPairQueue.clear();
		if (arraylist.contains(chunkcoordintpair)) {
			entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
		}

		for (int k1 = 1; k1 <= j * 2; k1++) {
			for (int l1 = 0; l1 < 2; l1++) {
				int[] aint = this.f[(i++ % 4)];

				for (int i2 = 0; i2 < k1; i2++) {
					i1 += aint[0];
					j1 += aint[1];
					chunkcoordintpair = PlayerInstance.a(a(k + i1, l + j1, true));
					if (arraylist.contains(chunkcoordintpair)) {
						entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
					}
				}
			}
		}

		i %= 4;

		for (k1 = 0; k1 < j * 2; k1++) {
			i1 += this.f[i][0];
			j1 += this.f[i][1];
			chunkcoordintpair = PlayerInstance.a(a(k + i1, l + j1, true));
			if (arraylist.contains(chunkcoordintpair))
				entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
		}
	}

	public void removePlayer(EntityPlayer entityplayer)
	{
		int i = (int)entityplayer.d >> 4;
		int j = (int)entityplayer.e >> 4;

		for (int k = i - this.e; k <= i + this.e; k++) {
			for (int l = j - this.e; l <= j + this.e; l++) {
				PlayerInstance playerinstance = a(k, l, false);

				if (playerinstance != null) {
					playerinstance.b(entityplayer);
				}
			}
		}

		this.managedPlayers.remove(entityplayer);
	}

	private boolean a(int i, int j, int k, int l, int i1) {
		int j1 = i - k;
		int k1 = j - l;

		return (k1 >= -i1) && (k1 <= i1);
	}

	public void movePlayer(EntityPlayer entityplayer) {
		int i = (int)entityplayer.locX >> 4;
		int j = (int)entityplayer.locZ >> 4;
		double d0 = entityplayer.d - entityplayer.locX;
		double d1 = entityplayer.e - entityplayer.locZ;
		double d2 = d0 * d0 + d1 * d1;

		if (d2 >= 64.0D) {
			int k = (int)entityplayer.d >> 4;
			int l = (int)entityplayer.e >> 4;
			int i1 = this.e;
			int j1 = i - k;
			int k1 = j - l;

			if ((j1 != 0) || (k1 != 0)) {
				for (int l1 = i - i1; l1 <= i + i1; l1++) {
					for (int i2 = j - i1; i2 <= j + i1; i2++) {
						if (!a(l1, i2, k, l, i1)) {
							a(l1, i2, true).a(entityplayer);
						}

						if (!a(l1 - j1, i2 - k1, i, j, i1)) {
							PlayerInstance playerinstance = a(l1 - j1, i2 - k1, false);

							if (playerinstance != null) {
								playerinstance.b(entityplayer);
							}
						}
					}
				}

				b(entityplayer);
				entityplayer.d = entityplayer.locX;
				entityplayer.e = entityplayer.locZ;

				if ((i1 > 1) || (i1 < -1) || (j1 > 1) || (j1 < -1)) {
					int x = i;
					int z = j;
					List chunksToSend = entityplayer.chunkCoordIntPairQueue;

					Collections.sort(chunksToSend, new Comparator(x, z) {
						public int compare(ChunkCoordIntPair a, ChunkCoordIntPair b) {
							return Math.max(Math.abs(a.x - this.val$x), Math.abs(a.z - this.val$z)) - Math.max(Math.abs(b.x - this.val$x), Math.abs(b.z - this.val$z));
						}
					});
				}
			}
		}
	}

	public boolean a(EntityPlayer entityplayer, int i, int j) {
		PlayerInstance playerinstance = a(i, j, false);

		return playerinstance == null ? false : PlayerInstance.b(playerinstance).contains(entityplayer);
	}

	public static int getFurthestViewableBlock(int i) {
		return i * 16 - 16;
	}

	static WorldServer a(PlayerManager playermanager) {
		return playermanager.world;
	}

	static LongHashMap b(PlayerManager playermanager) {
		return playermanager.c;
	}

	static Queue c(PlayerManager playermanager) {
		return playermanager.d;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PlayerManager
 * JD-Core Version:		0.6.0
 */