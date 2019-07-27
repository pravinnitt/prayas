package Cache;

import java.util.*;

public class LRUCache {

    /*
     * First element of this queue will always be least recently
     * used one.
     */
    static  Deque<Integer> queue ;
    static HashSet<Integer> set;
    static int size;

    public LRUCache(int n)
    {
        queue = new LinkedList<Integer>();
        set = new HashSet<>();
        size = n;
    }
    public static void main(String[] args) {
        System.out.println(LRUCache.class.getName());
        LRUCache cache = new LRUCache(3);
        cache.putInCache(1);
        cache.showCache();
        cache.putInCache(2);
        cache.showCache();
        cache.putInCache(3);
        cache.showCache();
        cache.putInCache(4);
        cache.showCache();
        cache.putInCache(1);
        cache.showCache();
    }

    private void putInCache(int param)
    {

        System.out.println("\n Entering in Cache -" +param);
        /*
         * If passed entry is not part of cache then entry need to be made
         * for this. If current size of queue is equal to max size then last
         * entry need to be removed.
         */

        if(!set.contains(param)) {
            if(queue.size()== size) {
                int oldest =queue.removeLast();
                set.remove(oldest);
            }
        }
        /*
         * If passed entry is already in the queue then it should be removed from
         * current place and add as first element of queue.
         */
        else {
            Iterator<Integer> itr = queue.iterator();
            int i =0;
            while(itr.hasNext())
            {
                if(itr.next()==param) {
                    break;
                }
                i++;
            }
            queue.remove(i);

        }
        set.add(param);
        queue.push(param);
    }

    private void showCache()
    {
        System.out.println("\nPrinting Cache -");
        Iterator<Integer> itr = queue.iterator();
        while(itr.hasNext())
        {
            System.out.print(itr.next() + " ");
        }

    }
}
