package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

class MojangStatisticsTask extends TimerTask
{
	MojangStatisticsTask(MojangStatisticsGenerator paramMojangStatisticsGenerator)
	{
	}

	public void run()
	{
		if (!MojangStatisticsGenerator.a(this.a).getSnooperEnabled()) return;
		HashMap localHashMap;
		synchronized (MojangStatisticsGenerator.b(this.a)) {
			localHashMap = new HashMap(MojangStatisticsGenerator.c(this.a));
		}

		localHashMap.put("snooper_count", Integer.valueOf(MojangStatisticsGenerator.d(this.a)));
		HttpUtilities.a(MojangStatisticsGenerator.e(this.a), localHashMap, true);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MojangStatisticsTask
 * JD-Core Version:		0.6.0
 */