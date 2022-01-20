package search;

public class BinarySortTree{


    public Node root = null;

    private static boolean search(int key, Node node, Node father, Node[] ret){
        if(node == null){
            ret[0] = null;
            ret[1] = father;
            return false;
        }
        else if(node.key == key){
            ret[0] = node;
            ret[1] = father;
            return true;
        }
        else if(key < node.key){
            return search(key, node.lchild, node, ret);
        }
        else{
            return search(key, node.rchild, node, ret);
        }
    }


    public Node search(int key){
        Node[] nodes = new Node[2];
        boolean ret = search(key, root, null, nodes);
        if(ret){
            return nodes[0];
        }
        else{
            return null;
        }
    }


    public boolean insert(int key, String data){
        if(root == null){
            root = new Node();
            root.key = key;
            root.data = data;
            root.lchild = null;
            root.rchild = null;
            return true;
        }
        else{
            Node[] nodes = new Node[2];
            boolean ret = search(key, root, null, nodes);
            if(ret){
                return false;
            }
            Node node = new Node();
            node.key = key;
            node.data = data;
            node.lchild = null;
            node.rchild = null;
            if(node.key < nodes[1].key){
                nodes[1].lchild = node;
            }
            else{
                nodes[1].rchild = node;
            }
            return true;
        }
    }

    private static String pre_order(Node node){
        if(node == null){
            return "";
        }
        else{
            String str =Integer.toString(node.key)+" ";// "(" + node.key + ","  + node.data + ") ";
            str += pre_order(node.lchild);
            str += pre_order(node.rchild);
            return str;
        }
    }

     private static String in_order(Node node){
        if(node == null){
            return "";
        }
        else{
            String str = in_order(node.lchild);
            str += Integer.toString(node.key)+" ";//"(" + node.key + "," + node.data + ") ";
            str += in_order(node.rchild);
            return str;
        }
    }


    public String pre_order(){
        return pre_order(root);
    }

    public String in_order(){
        return in_order(root);
    }


    public boolean delete(int key){
        Node[] nodes = new Node[2];
        boolean ret = search(key, root, null, nodes);
        if(ret == false){
            return false;
        }
        else{
            if(nodes[0].lchild != null && nodes[1].rchild == null){
                Node q = nodes[0];
                Node p = nodes[0].lchild;
                while(p.rchild != null){
                    q = p;
                    p = p.rchild;
                }
                nodes[0].key = p.key;
                nodes[0].data = p.data;
                nodes[0] = p;
                nodes[1] = q;
            }
            if(nodes[0].lchild == null){
                if(nodes[1] == null){
                    root = nodes[0].rchild;
                }
                else{
                    if(nodes[1].lchild == nodes[0]){
                        nodes[1].lchild = nodes[0].rchild;
                    }
                    else{
                        nodes[1].rchild = nodes[0].rchild;
                    }
                }
            }
            else if(nodes[0].rchild == null){
                if(nodes[1] == null){
                    root = nodes[0].lchild;
                }
                else{
                    if(nodes[1].lchild == nodes[0]){
                        nodes[1].lchild = nodes[0].lchild;
                    }
                    else{
                        nodes[1].rchild = nodes[0].lchild;
                    }
                }
            }
            return true;
        }
    }
    
}