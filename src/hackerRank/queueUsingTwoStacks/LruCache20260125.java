package hackerRank.queueUsingTwoStacks;

import java.util.LinkedHashMap;

public class LruCache20260125 {
    interface Cache{
        String get(String key);
        void put(String key, String value);
    }

    static class LRUCache implements Cache{
        private int capacity;
        private LinkedHashMap<String,String> buffer;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            /*this.buffer = new LinkedHashMap<>(capacity, 0.75f, true){
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                    return size() > capacity;
                }
            };*/
            this.buffer = new LinkedHashMap<>(capacity, 0.75f, true);

        }

        @Override
        public String get(String key) {
            return buffer.get(key);
        }

        @Override
        public void put(String key, String value) {
            buffer.put(key, value);
        }
    }

    public static void main(String[] args) {
        //1. capacity 초과한 캐시를 넣었을 경우 가장 오래된 캐시 삭제
        Cache cache = new LRUCache(2);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("cache = " + cache.get("key1")); //null
        System.out.println("cache = " + cache.get("key2")); //"value2"
        System.out.println("cache = " + cache.get("key3")); //"value3"

        //2. 캐시 값을 갱신하면 최신 상태가 됨.
        cache.put("key2", "value2-1");
        cache.put("key4", "value4");
        System.out.println("cache = " + cache.get("key2")); //value2-1
        System.out.println("cache = " + cache.get("key3")); //null





    }

}
