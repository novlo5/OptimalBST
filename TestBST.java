public class TestBST{
    public static void main(String[] args){

               BSTOptimizer optimizer = new BSTOptimizer();
               optimizer.MEMOIZE = true;
                optimizer.addKey("A", 4);
                optimizer.addKey("B", 2);
                optimizer.addKey("C", 1);
                optimizer.addKey("D", 3);
                optimizer.addKey("E", 5);
                optimizer.addKey("F", 2);
                optimizer.addKey("G", 1);
                optimizer.optimize();
               
                System.out.println();
               System.out.println(optimizer.tree.cost);
               System.out.println(optimizer.CALLS);
             
                
    }
}