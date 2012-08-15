package net.minecraft.server;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MethodProfiler
{
	private final List b;
	private final List c;
	public boolean a;
	private String d;
	private final Map e;

	public MethodProfiler()
	{
		this.b = new ArrayList();
		this.c = new ArrayList();
		this.a = false;
		this.d = "";
		this.e = new HashMap();
	}

	public void a()
	{
		this.e.clear();
		this.d = "";
		this.b.clear();
	}

	public void a(String paramString) {
		if (!this.a) return;
		if (this.d.length() > 0) this.d += ".";
		this.d += paramString;
		this.b.add(this.d);

		this.c.add(Long.valueOf(System.nanoTime()));
	}

	public void b() {
		if (!this.a) return;

		long l1 = System.nanoTime();
		long l2 = ((Long)this.c.remove(this.c.size() - 1)).longValue();
		this.b.remove(this.b.size() - 1);
		long l3 = l1 - l2;

		if (this.e.containsKey(this.d))
			this.e.put(this.d, Long.valueOf(((Long)this.e.get(this.d)).longValue() + l3));
		else {
			this.e.put(this.d, Long.valueOf(l3));
		}

		if (l3 > 100000000L) {
			System.out.println("Something's taking too long! '" + this.d + "' took aprox " + l3 / 1000000.0D + " ms");
		}

		this.d = (!this.b.isEmpty() ? (String)this.b.get(this.b.size() - 1) : "");
	}

	public List b(String paramString) {
		if (!this.a) return null;

		String str1 = paramString;
		long l1 = this.e.containsKey("root") ? ((Long)this.e.get("root")).longValue() : 0L;
		long l2 = this.e.containsKey(paramString) ? ((Long)this.e.get(paramString)).longValue() : -1L;

		ArrayList localArrayList = new ArrayList();

		if (paramString.length() > 0) paramString = paramString + ".";
		long l3 = 0L;

		for (Iterator localIterator = this.e.keySet().iterator(); localIterator.hasNext(); ) { localObject = (String)localIterator.next();
			if ((((String)localObject).length() > paramString.length()) && (((String)localObject).startsWith(paramString)) && (((String)localObject).indexOf(".", paramString.length() + 1) < 0)) {
				l3 += ((Long)this.e.get(localObject)).longValue();
			}
		}

		float f = (float)l3;
		if (l3 < l2) l3 = l2;
		if (l1 < l3) l1 = l3;

		for (Object localObject = this.e.keySet().iterator(); ((Iterator)localObject).hasNext(); ) { str2 = (String)((Iterator)localObject).next();
			if ((str2.length() > paramString.length()) && (str2.startsWith(paramString)) && (str2.indexOf(".", paramString.length() + 1) < 0)) {
				long l4 = ((Long)this.e.get(str2)).longValue();
				double d1 = l4 * 100.0D / l3;
				double d2 = l4 * 100.0D / l1;
				String str3 = str2.substring(paramString.length());
				localArrayList.add(new ProfilerInfo(str3, d1, d2));
			}
		}
		String str2;
		for (localObject = this.e.keySet().iterator(); ((Iterator)localObject).hasNext(); ) { str2 = (String)((Iterator)localObject).next();
			this.e.put(str2, Long.valueOf(((Long)this.e.get(str2)).longValue() * 999L / 1000L));
		}

		if ((float)l3 > f) {
			localArrayList.add(new ProfilerInfo("unspecified", ((float)l3 - f) * 100.0D / l3, ((float)l3 - f) * 100.0D / l1));
		}
		Collections.sort(localArrayList);
		localArrayList.add(0, new ProfilerInfo(str1, 100.0D, l3 * 100.0D / l1));
		return (List)localArrayList;
	}

	public void c(String paramString) {
		b();
		a(paramString);
	}

	public String c() {
		return this.b.size() == 0 ? "[UNKNOWN]" : (String)this.b.get(this.b.size() - 1);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.MethodProfiler
 * JD-Core Version:		0.6.0
 */