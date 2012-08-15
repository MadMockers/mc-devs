package net.minecraft.server;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;

public class MojangStatisticsGenerator
{
	private Map a = new HashMap();
	private final String b = UUID.randomUUID().toString();
	private final URL c;
	private final IMojangStatistics d;
	private final Timer e = new Timer("Snooper Timer", true);
	private final Object f = new Object();
	private boolean g = false;
	private int h = 0;

	public MojangStatisticsGenerator(String paramString, IMojangStatistics paramIMojangStatistics) {
		try {
			this.c = new URL("http://snoop.minecraft.net/" + paramString + "?version=" + 1);
		} catch (MalformedURLException localMalformedURLException) {
			throw new IllegalArgumentException();
		}

		this.d = paramIMojangStatistics;
	}

	public void a() {
		if (this.g) return;
		this.g = true;

		f();

		this.e.schedule(new MojangStatisticsTask(this), 0L, 900000L);
	}

	private void f()
	{
		g();

		a("snooper_token", this.b);
		a("os_name", System.getProperty("os.name"));
		a("os_version", System.getProperty("os.version"));
		a("os_architecture", System.getProperty("os.arch"));
		a("java_version", System.getProperty("java.version"));
		a("version", "1.3.1");

		this.d.b(this);
	}

	private void g() {
		RuntimeMXBean localRuntimeMXBean = ManagementFactory.getRuntimeMXBean();
		List localList = localRuntimeMXBean.getInputArguments();
		int i = 0;

		for (String str : localList) {
			if (str.startsWith("-X")) {
				a("jvm_arg[" + i++ + "]", str);
			}
		}

		a("jvm_args", Integer.valueOf(i));
	}

	public void b() {
		a("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
		a("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
		a("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
		a("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));

		this.d.a(this);
	}

	public void a(String paramString, Object paramObject) {
		synchronized (this.f) {
			this.a.put(paramString, paramObject);
		}
	}

	public boolean d()
	{
		return this.g;
	}

	public void e() {
		this.e.cancel();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.MojangStatisticsGenerator
 * JD-Core Version:		0.6.0
 */