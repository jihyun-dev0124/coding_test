package hackerRank.queueUsingTwoStacks;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLruCache20260125 {
    interface Cache{
        String get(String key);
        void put(String key, String value);
    }

    static class LRUCache implements Cache{
        private final int capacity;
        private final ConcurrentHashMap<String, Node> index = new ConcurrentHashMap<>();
        private final ReentrantLock lock = new ReentrantLock();
        private final Node head = new Node(null, null); // dummy
        private final Node tail = new Node(null, null); // dummy

        private static class Node {
            final String key;
            volatile String value;
            Node prev, next;
            Node(String key, String value) { this.key = key; this.value = value; }
        }

        public LRUCache(int capacity) {
            this.capacity = Math.max(0, capacity);
            head.next = tail; tail.prev = head;
        }

        @Override
        public String get(String key) {
            Node node = index.get(key);
            if (node == null) return null;

            lock.lock();
            try {
                moveToTail(node);
                return node.value;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void put(String key, String value) {
            if (capacity == 0) return;

            lock.lock();
            try {
                Node existing = index.get(key);
                if (existing != null) {
                    existing.value = value;
                    moveToTail(existing);
                    return;
                }

                Node node = new Node(key, value);
                index.put(key, node);
                addToTail(node);

                if (index.size() > capacity) {
                    Node lru = removeHeadNext(); // 가장 오래된 노드
                    if (lru != null) index.remove(lru.key);
                }
            } finally {
                lock.unlock();
            }
        }

        private void moveToTail(Node node) {
            unlink(node);
            addToTail(node);
        }

        private void addToTail(Node node) {
            Node prev = tail.prev;
            prev.next = node;
            node.prev = prev;
            node.next = tail;
            tail.prev = node;
        }

        private void unlink(Node node) {
            Node p = node.prev, n = node.next;
            if (p != null) p.next = n;
            if (n != null) n.prev = p;
            node.prev = node.next = null;
        }

        private Node removeHeadNext() {
            Node first = head.next;
            if (first == tail) return null;
            unlink(first);
            return first;
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
