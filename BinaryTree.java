    public class BinaryTree{


            public int cost;
            private BTNode root;
           
        public BinaryTree(){

            root = null;

        }


        public String toString(){

           return toString(this.root);
        }

        private String toString(BTNode root){

            if(root == null){return "null";}
           
            return root.key + " " + toString(root.left) +   toString(root.right);
          

        }

        public BTNode getRoot(){

            return root;
        }

}