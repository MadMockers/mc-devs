package net.minecraft.server;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class InsensitiveStringMap
	implements Map
{
	private final Map a = new LinkedHashMap();

	public int size() {
		return this.a.size();
	}

	public boolean isEmpty() {
		return this.a.isEmpty();
	}

	public boolean containsKey(Object paramObject) {
		return this.a.containsKey(paramObject.toString().toLowerCase());
	}

	public boolean containsValue(Object paramObject) {
		return this.a.containsKey(paramObject);
	}

	public Object get(Object paramObject) {
		return this.a.get(paramObject.toString().toLowerCase());
	}

	public Object put(String paramString, Object paramObject) {
		return this.a.put(paramString.toLowerCase(), paramObject);
	}

	public Object remove(Object paramObject) {
		return this.a.remove(paramObject.toString().toLowerCase());
	}

	public void putAll(Map paramMap) {
		for (Map.Entry localEntry : paramMap.entrySet())
			put((String)localEntry.getKey(), localEntry.getValue());
	}

	public void clear()
	{
		this.a.clear();
	}

	public Set keySet() {
		return this.a.keySet();
	}

	public Collection values() {
		return this.a.values();
	}

	public Set entrySet() {
		return this.a.entrySet();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.InsensitiveStringMap
 * JD-Core Version:		0.6.0
 */