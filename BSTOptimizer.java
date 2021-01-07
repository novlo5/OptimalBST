import java.util.ArrayList;
import java.util.Arrays;

public class BSTOptimizer {

    private final int SIZE = 100 ;
    private final int MAX_COST = 2147483647; // max int
    private int length;
    private Node[] keys;
    private int[][] MEMO;
    public int[][] optimal;
    public BinaryTree tree;
    public boolean MEMOIZE = false;
    public int CALLS = 0;

        public BSTOptimizer(){

            keys = new Node[SIZE];
            tree = new BinaryTree();
              
        }


        public void addKey(String key, int frequency){

            for(int i = 0; i < this.keys.length; i++){
                if(this.keys[i] == null){
                    Node newEntry = new Node(key, frequency);
                    this.keys[i]= newEntry;
                    this.length++;
                    return;
                }
            }
        }

        public BinaryTree optimize(){

            optimal = new int[this.length][this.length];
            for(int[] each: optimal){
                Arrays.fill(each, -1);
            }
            this.selectionSort();

                if(!MEMOIZE){
                    this.tree.cost = costOfOptimalBST(0, this.length - 1);
                    return this.tree;
                }

                else{
                    MEMO = new int[this.length][this.length];

                    for(int[] each: MEMO){
                        Arrays.fill(each, -1);
                    }
                    this.tree.cost =  memoCost(0, this.length - 1);
                    return this.tree;
                }

        }
        
        private int costOfOptimalBST(int left, int right){
            this.CALLS++;
            //counting number of recursive calls
            if(right < left){return 0;}

            if(right == left){return this.keys[right].frequency;}
                int minCost = MAX_COST;

                for(int root = left; root <= right; root++){
                    
                    int cost =  sum(left,right) + costOfOptimalBST(left, root - 1) + costOfOptimalBST(root + 1, right);
                   
                    if (minCost > cost){ 
                        minCost = cost;
                        this.optimal[left][right] = root; // this was for bst construction but I couldnt figure it out
                    }
                }
                
                return minCost;

        }


        private int memoCost(int left, int right){
            
            this.CALLS++;
            if(right < left){return 0;}
            if(right == left){return this.keys[right].frequency;}
            if(this.MEMO[left][right] !=  -1){
            return MEMO[left][right];
        }
                int minCost = MAX_COST;

                for(int root = left; root <= right; root++){

                    int cost =  sum(left,right) + memoCost(left, root - 1) + memoCost(root + 1, right);
                    if (minCost > cost){ 
                        minCost = cost;
                        this.optimal[left][right] = root;
                    }
                }
               
                this.MEMO[left][right] = minCost;
                return minCost;

        }

       /* private BinaryTree constructTree(int left, int right){

            if(right < left){return null;}
            if(this.optimal[left][right] != -1){ }





        } */
        
        private void selectionSort(){

            for(int i=0; i < this.length; i++){

                String key = this.keys[i].key;

                for(int j = i + 1; j < this.length; j++){

                    if(key.compareTo(this.keys[j].key) > 0){

                        Node temp = this.keys[i];
                        this.keys[i] = this.keys[j];
                        this.keys[j] = temp;
                    }
                }
            }

        }
        private int sum(int left, int right){

            int sum = 0;

            for(int i = left; i <= right; i++){

                    sum += this.keys[i].frequency;
            }

            return sum;

        }
}
