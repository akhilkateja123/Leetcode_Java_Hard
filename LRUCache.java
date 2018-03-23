package aws.amazon.practice;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	Map<Integer, Integer[]> cache;
	int capacity;
	int head = -1;

	public LRUCache(int capacity) {
		cache = new HashMap<Integer, Integer[]>(capacity);
		this.capacity = capacity;
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			cache.get(key)[1] = cache.get(key)[1] + 1;
			if (cache.get(key)[1] < cache.get(head)[1])
				head = key;
			return cache.get(key)[0];
		}
		return -1;
	}

	public void put(int key, int value) {
		if (!cache.containsKey(key)) {
			if (cache.keySet().size() == this.capacity) {
				cache.remove(head);
			}
			cache.put(key, new Integer[] { value, 1 });
		} else {
           cache.get(key)[0]=value;
           cache.get(key)[1] = cache.get(key)[1] + 1;
           
		}
	}

}
