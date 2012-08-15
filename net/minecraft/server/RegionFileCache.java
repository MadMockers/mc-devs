package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class RegionFileCache
{
	private static final Map a = new HashMap();

	public static synchronized RegionFile a(File paramFile, int paramInt1, int paramInt2)
	{
		File localFile1 = new File(paramFile, "region");
		File localFile2 = new File(localFile1, "r." + (paramInt1 >> 5) + "." + (paramInt2 >> 5) + ".mca");

		Reference localReference = (Reference)a.get(localFile2);

		if (localReference != null) {
			localRegionFile = (RegionFile)localReference.get();
			if (localRegionFile != null) {
				return localRegionFile;
			}
		}

		if (!localFile1.exists()) {
			localFile1.mkdirs();
		}

		if (a.size() >= 256) {
			a();
		}

		RegionFile localRegionFile = new RegionFile(localFile2);
		a.put(localFile2, new SoftReference(localRegionFile));
		return localRegionFile;
	}

	public static synchronized void a() {
		for (Reference localReference : a.values()) {
			try {
				RegionFile localRegionFile = (RegionFile)localReference.get();
				if (localRegionFile != null)
					localRegionFile.c();
			}
			catch (IOException localIOException) {
				localIOException.printStackTrace();
			}
		}
		a.clear();
	}

	public static DataInputStream c(File paramFile, int paramInt1, int paramInt2)
	{
		RegionFile localRegionFile = a(paramFile, paramInt1, paramInt2);
		return localRegionFile.a(paramInt1 & 0x1F, paramInt2 & 0x1F);
	}

	public static DataOutputStream d(File paramFile, int paramInt1, int paramInt2) {
		RegionFile localRegionFile = a(paramFile, paramInt1, paramInt2);
		return localRegionFile.b(paramInt1 & 0x1F, paramInt2 & 0x1F);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RegionFileCache
 * JD-Core Version:		0.6.0
 */