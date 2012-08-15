package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

public class WorldLoader
	implements Convertable
{
	protected final File a;

	public WorldLoader(File paramFile)
	{
		if (!paramFile.exists()) paramFile.mkdirs();
		this.a = paramFile;
	}

	public void d()
	{
	}

	public WorldData c(String paramString)
	{
		File localFile1 = new File(this.a, paramString);
		if (!localFile1.exists()) return null;

		File localFile2 = new File(localFile1, "level.dat");
		NBTTagCompound localNBTTagCompound3;
		if (localFile2.exists()) {
			try {
				NBTTagCompound localNBTTagCompound1 = NBTCompressedStreamTools.a(new FileInputStream(localFile2));
				localNBTTagCompound3 = localNBTTagCompound1.getCompound("Data");
				return new WorldData(localNBTTagCompound3);
			} catch (Exception localException1) {
				localException1.printStackTrace();
			}
		}

		localFile2 = new File(localFile1, "level.dat_old");
		if (localFile2.exists()) {
			try {
				NBTTagCompound localNBTTagCompound2 = NBTCompressedStreamTools.a(new FileInputStream(localFile2));
				localNBTTagCompound3 = localNBTTagCompound2.getCompound("Data");
				return new WorldData(localNBTTagCompound3);
			} catch (Exception localException2) {
				localException2.printStackTrace();
			}
		}
		return null;
	}

	public void e(String paramString)
	{
		File localFile = new File(this.a, paramString);
		if (!localFile.exists()) return;

		a(localFile.listFiles());
		localFile.delete();
	}

	protected static void a(File[] paramArrayOfFile) {
		for (File localFile : paramArrayOfFile) {
			if (localFile.isDirectory()) {
				System.out.println("Deleting " + localFile);
				a(localFile.listFiles());
			}
			localFile.delete();
		}
	}

	public IDataManager a(String paramString, boolean paramBoolean) {
		return new WorldNBTStorage(this.a, paramString, paramBoolean);
	}

	public boolean isConvertable(String paramString)
	{
		return false;
	}

	public boolean convert(String paramString, IProgressUpdate paramIProgressUpdate) {
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldLoader
 * JD-Core Version:		0.6.0
 */