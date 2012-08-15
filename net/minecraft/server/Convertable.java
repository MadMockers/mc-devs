package net.minecraft.server;

public abstract interface Convertable
{
	public abstract IDataManager a(String paramString, boolean paramBoolean);

	public abstract void d();

	public abstract void e(String paramString);

	public abstract boolean isConvertable(String paramString);

	public abstract boolean convert(String paramString, IProgressUpdate paramIProgressUpdate);
}

/* 
 * Qualified Name:		 net.minecraft.server.Convertable
 * JD-Core Version:		0.6.0
 */