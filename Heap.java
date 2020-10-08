import java.util.*;

public class Heap {
    /** 
     * IMP POINT :
     * ONCE A NODE HAS BEEN ADDED TO PQ, EVEN IF ITS VALUE IS CHANGED IN THE CORREPONDING,
     * IT WON'T AFFECT THE POSITION IN THE PQ
     * THAT'S WHY NETWORK DELAY QUES WAS FAILING
     * 
     * */
    // int[] visited = new int[]{200, 1, 30};
    // PriorityQueue<Integer> pq = new PriorityQueue<>((x,y)->visited[x] - visited[y]);
    // pq.add(0);
    // pq.add(1);
    // pq.add(2);
    // System.out.println(pq);
    // visited[1] = 1000;
    // System.out.println(pq);

    int[] heapArray;
    int lastIndex;
    private int lastIndexDelete = -1;
    private int largestSingle;

    Heap(int capacity) {
        heapArray = new int[capacity];
        this.lastIndex = capacity - 1;
    }

    void addToheap(int i) {// insert new key at end
        this.heapArray[lastIndex] = i;
        // System.out.println("1");
        heapify(lastIndex);
        printHeap();
        System.out.println("-------------");
    }

    void heapify(int childIndex) {
        // for(int)
        if (this.heapArray[lastIndex] > this.heapArray[((lastIndex + 1) / 2) - 1]) {
            System.out.println("size " + lastIndex + " " + this.heapArray[lastIndex]);
            System.out
                    .println("size/2 " + (((lastIndex + 1) / 2) - 1) + " " + this.heapArray[((lastIndex + 1) / 2) - 1]);
            int temp = this.heapArray[lastIndex];
            this.heapArray[lastIndex] = this.heapArray[((lastIndex + 1) / 2) - 1];
            this.heapArray[((lastIndex + 1) / 2) - 1] = temp;
        }

        if (childIndex / 2 == 0) {
            return;
        } else
            heapify(childIndex / 2);
    }

    void printHeap() {
        for (int i = 0; i < heapArray.length; i++) {
            System.out.println(this.heapArray[i]);
        }
    }

    void printMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == arr.length - 1 && j == arr[0].length - 1) {
                    System.out.println(arr[i] + ";");
                    System.out.println();
                } else
                    System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(arr[i] + ";");
                System.out.println();
            } else
                System.out.print(arr[i] + ", ");
        }
    }

    void maxHeapify(int nodeIndex) {
        // if(heapArray[nodeIndex] != null){
        if (nodeIndex < heapArray.length) {
            int leftChild = -1;
            int rightChild = -1;
            // for(int i = (heapArray.length)/2-1; i >0; i--){
            // System.out.println("heapArray.length/2-1 "+ (heapArray.length/2-1));
            System.out.println("heapArray[nodeIndex] " + heapArray[nodeIndex]);
            if (nodeIndex * 2 + 1 < heapArray.length) {
                leftChild = nodeIndex * 2 + 1;
            }
            if (nodeIndex * 2 + 1 < heapArray.length) {
                rightChild = nodeIndex * 2 + 2;
            }

            int largest;
            if (leftChild > 0 && rightChild > 0) {
                System.out.println("leftchild " + leftChild + "  " + heapArray[leftChild]);
                System.out.println("rightchild " + rightChild + "  " + heapArray[rightChild]);

                if (heapArray[nodeIndex] < heapArray[leftChild]) {
                    largest = leftChild;
                } else
                    largest = nodeIndex;
                if (heapArray[largest] < heapArray[rightChild]) {
                    largest = rightChild;
                }
                System.out.println("heapArray[largest] " + heapArray[largest]);
                if (largest != nodeIndex) {
                    System.out.println("largest " + largest);
                    System.out.println("nodeIndex " + nodeIndex);
                    int temp = heapArray[nodeIndex];
                    heapArray[nodeIndex] = heapArray[largest];
                    heapArray[largest] = temp;

                    System.out.println("swapped " + heapArray[nodeIndex] + "   " + heapArray[largest]);

                    maxHeapify(largest);

                } else {
                    System.out.println("no swap");
                }
            }

            // }
        } else
            return;

    }

    /**
     * one big mistake while using recursion was that the last indexes were also
     * used to calculate children which then went out of bounds. As the left and
     * right children were initialised to 0, the indexes were getting wrongly set.
     * So use -1 as initial value
     * 
     * also the variables need to be initialized outside of current scope.
     */

    void deleteFromHeap() {
        for (int i = 0; i < heapArray.length; i++) {
            if (heapArray[i] == 0) {

                System.out.println("the empty index is " + i);
                System.out.println("the last value is " + heapArray[i]);
                lastIndexDelete = i;

            }
        }

        if (lastIndexDelete == -1) {
            lastIndexDelete = heapArray.length - 1;
        }

        System.out.println("lastIndexDelete " + lastIndexDelete + " value is " + heapArray[lastIndexDelete]);
        heapArray[0] = heapArray[lastIndexDelete];
        heapArray[lastIndexDelete] = 0;

        printHeap();
        heapifySinglePath(0);
    }

    void heapifySinglePath(int index) {
        /** Always the heapify FLOATS DOWN */
        System.out.println("index " + index);
        largestSingle = index;
        if (index < heapArray.length) {

            if (((2 * index) + 1) < heapArray.length) {
                System.out.println("2*index+1 " + (2 * index) + 1);
                if (heapArray[largestSingle] < heapArray[(2 * index) + 1]) {
                    largestSingle = (2 * index) + 1;
                }
            }

            if (((2 * index) + 2) < heapArray.length) {
                System.out.println("2*index+2 " + (2 * index) + 2);
                if (heapArray[(2 * index) + 2] > heapArray[largestSingle]) {
                    largestSingle = 2 * index + 2;
                }
            }

            if (largestSingle != index) {
                int temp = heapArray[index];
                heapArray[index] = heapArray[largestSingle];
                heapArray[largestSingle] = temp;
                System.out.println("swapped " + heapArray[index] + "  " + heapArray[largestSingle]);
                heapifySinglePath(largestSingle);
            }

        }
    }

    /*
     * * provides O(log(n)) time for the enqueuing and dequeuing methods ({@code
     * offer}, {@code poll}, {@code remove()} and {@code add}); linear time for the
     * {@code remove(Object)} and {@code contains(Object)} methods; and constant
     * time for the retrieval methods ({@code peek} ->first el, {@code element}, and
     * {@code size}).
     * 
     * removeEq, removeAt and contains capacity grow automatically listiterator,
     * hasNext size()
     */
    // k-sorted array
    /**
     * 1 add comparator
     * 
     */

    // SKYLINE
    // FINDING MEDIAN
    // https://www.techiedelight.com/convert-min-heap-to-max-heap-linear-time/

    /** one optimization can be to keep heap size limited to k */
    // https://leetcode.com/problems/k-closest-points-to-origin/
    class Holder{
        int index; int dist;
        Holder(int i, int d){
            this.index = i;
            this.dist =d;
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        
        PriorityQueue<Holder> heap = 
            new PriorityQueue<Holder>((x, y)-> x.dist - y.dist);
        
        for(int i=0; i<n; i++){
            int a = points[i][0]; int b = points[i][1];
            int c = a*a + b*b;
            heap.add(new Holder(i, c));
        }
        
        int[][] res = new int[K][2];
        
        for(int i =0; i<K; i++){
            Holder curr = heap.remove();
            res[i][0] = points[curr.index][0];
            res[i][1] = points[curr.index][1];
        }
        
        return res;
    }

    /**
     * one optimization can be to keep heap size limited to k
     * 
     * USE A CUSTOM CLASS MOSTLY IS REQUIRED
     * 
     * one idea was to map freq with an arrayList of strings but how to fetch
     * previous count? will have to traverse over all freqs to find where string is
     * residing, remove it and then add to new freq(old freq+1)
     * 
     * BEST TO GO FOR SIMPLE HASHMAP AND pQueue
     * 
     * wny go for pQueue? hashmap stores in a sequential order so for fetching all k
     * we have to traverse the whole heap So pQueue.
     * 
     * for fetching all the els with freq say 4 we have to remove as many times,
     * keep a track of last freq and update counter when freq value changes
     * 
     * IMP : 
     * 1 STR1.COMPARETO(STR2)
     * 2 how to implement a pQueue with 2 comparators 
     * 3 heap.size() check before removing
     * 
     */
    // https://leetcode.com/problems/top-k-frequent-words/

    class KFreq {
        int freq;
        String str;

        KFreq(int f, String s) {
            this.freq = f;
            this.str = s;
        }
    }

    public ArrayList<String> topKFrequent(String[] words, int k) {
        int n = words.length;
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        System.out.println('a' - 'b');
        for (int i = 0; i < n; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        PriorityQueue<KFreq> heap = new PriorityQueue<KFreq>((x, y) -> {
            if (x.freq == y.freq)
                return x.str.compareTo(y.str);
            // y.str.charAt(0) - x.str.charAt(0);
            return y.freq - x.freq;
        });

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            String s = e.getKey();
            int freq = e.getValue();
            heap.add(new KFreq(freq, s));
        }
        int counter = 0;

        while (heap.size() != 0 && counter<k) {
            System.out.println((heap.remove().str));
            counter++;
        }

        return res;
    }

    // IF ALL K DIFF FREQ WORDS HAD TO BE PRINTED

    /**
     * ["the", "day", "is", "bun" , bun ", ""sun", "the", "the", "the", "sun",
     * "is", "is"], k = 3 (TOP 3 FREQUENT) 
     * the - 4, day - 1, is - 3, bun - 2, sun -2 WE HAVE TO
     * PRINT the-is-bun-sun
     * bun and sun have same freq so both need to be printed
     * 
     * MAINTAIN A COUNTER AND PREVFREQ != CURRFREQ, INCREMENT COUNTER     * 
     * INITIALIZE PREVFREQ BY POPPING ONCE KFREQ CURR
     * THEN RUN WHILE LOOP
     */
    public ArrayList<String> topKFrequentDiff(String[] words, int k) {
        int n = words.length;
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        // System.out.println('a' - 'b'); //prints-1
        for (int i = 0; i < n; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        PriorityQueue<KFreq> heap = new PriorityQueue<KFreq>((x, y) -> {
            if (x.freq == y.freq) return x.str.compareTo(y.str);
            return y.freq - x.freq;
        });

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            String s = e.getKey();
            int freq = e.getValue();
            heap.add(new KFreq(freq, s));
        }

        int counter = 0; int prevFreq = 0;
        KFreq curr = null;
        if(heap.size()!=0){
            curr = heap.remove();
            prevFreq = curr.freq;
            res.add(curr.str);
        }

        while (heap.size() != 0 && counter<k) {
            curr = heap.remove();
            res.add(curr.str);
            if(curr.freq != prevFreq) counter++;
        }

        System.out.println(res);
        return res;
    }


    /** 
     * 1 create a custom class to hold index, row and val
     * 2 row and index are needed so that when we pop an element,
     *  we can find the next el in the corresponding row easily
     *  new KListNode(curr.index+1, nums.get(curr.row).get(curr.index+1), curr.row);
     * 
     *  NEXT EL'S VAL OF CURR ROW -> nums.get(curr.row).get(curr.index+1)
     * 
     * 3 use a minheap, with comparator
     * 4 add first els of each row
     * 5 use 4 vars max, start, end, range
     * 
     * 6 while q == nums.size makes the loop run till we have atleast 1 el
     * from each row
     * 7 flow -> pop an el, compare with max to get range,
     *  add another from same row
     * 8 if we already have lesser diff, move on
     *  else udpate start, end and range
     * 
     * 9 if current el is not the last in its row
     *  curr.index+1<nums.get(curr.row).size()
     *  add one, else continue
     *   
     * {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}};
     * suppose we have 20, 22 and 24 in heap
     * once 20 is popped we update range, start, end
     * but its the last and IF WE DON'T HAVE ELS OF ALL K ROWS
     * we can't compare, while loop breaks and we return;
     * 
     */
    // https://www.geeksforgeeks.org/find-smallest-range-containing-elements-from-k-lists/
    // https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
    class KListNode{
        int index, val, row;
        KListNode(int i, int v, int r){
            this.index = i;
            this.val = v;
            this.row = r;
        }
    }

    public int[] smallestRange(ArrayList<ArrayList<Integer>> nums) {
        PriorityQueue<KListNode> q = new PriorityQueue<>((x,y)->x.val- y.val);
        int max = Integer.MIN_VALUE;

        for(int i =0; i<nums.size(); i++){
            q.add(new KListNode(0, nums.get(i).get(0) ,i));
            max = Math.max(max, nums.get(i).get(0));
        }
        int start = -1;
        int end = -1;
        int range = Integer.MAX_VALUE;

        while(q.size() == nums.size()){
            
            KListNode curr =q.remove();
            if(max-curr.val < range){
                start = curr.val;
                end = max;
                range = max - curr.val;
            }
            
            //el removed so add el of the same row
            if(curr.index+1<nums.get(curr.row).size()){
                KListNode currNew = new KListNode(curr.index+1, 
                            nums.get(curr.row).get(curr.index+1), curr.row);
                q.add(currNew);
                max= Math.max(max, currNew.val);
            }
        }

        // int[] x = {start,end};
        // print1DMatrix(x);
        return new int[]{start, end};
    }

    // https://leetcode.com/problems/sort-the-matrix-diagonally/
    
    // https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
    // https://www.geeksforgeeks.org/nearly-sorted-algorithm/
    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss
    // /301357/Java-0ms-(added-Python-and-C%2B%2B)%3A-Easy-to-understand-solutions-using-Heap-and-Binary-Search

    public static void main(String[] args) {
        Heap newHeap = new Heap(17);

        // newHeap.addToheap(3);
        // newHeap.addToheap(5);
        // newHeap.addToheap(7);
        // newHeap.addToheap(9);
        newHeap.heapArray[0] = 2;
        newHeap.heapArray[1] = 4;
        newHeap.heapArray[2] = 3;
        newHeap.heapArray[3] = 6;
        newHeap.heapArray[4] = 79;
        newHeap.heapArray[5] = 7;
        newHeap.heapArray[6] = 17;
        newHeap.heapArray[7] = 57;
        newHeap.heapArray[8] = 73;
        newHeap.heapArray[9] = 97;
        newHeap.heapArray[10] = 47;
        newHeap.heapArray[11] = 27;
        newHeap.heapArray[12] = 75;
        newHeap.heapArray[13] = 72;
        newHeap.heapArray[14] = 74;
        newHeap.heapArray[15] = 77;
        newHeap.heapArray[16] = 53;

        // newHeap.printHeap();

        for (int i = (newHeap.heapArray.length) / 2 - 1; i >= 0; i--) {
            // newHeap.maxHeapify(i);
        }
        // newHeap.maxHeapify(7);

        // newHeap.printHeap();

        // newHeap.deleteFromHeap();

        // newHeap.printHeap();
        String[] topk = { "a", "ba", "aaa" };
        int k = 1;
        // newHeap.topKFrequent(topk, k);

        // newHeap.topKFrequentDiff(topk, k);
        int[][] nums = {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}};
        ArrayList<ArrayList<Integer>> numsKList = new ArrayList<ArrayList<Integer>>();
        for(int i =0; i<nums.length; i++){
            ArrayList<Integer> newRow = new ArrayList<>();
            for(int j : nums[i]){
                newRow.add(j);
            }
            numsKList.add(newRow);
        }
        // System.out.println(numsKList);
        // newHeap.smallestRange(numsKList);

        
    }
}