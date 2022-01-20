import java.util.*;
public class GeneralizedTable {

    public static final int TAG_ITEM = 0; // ԭ�ӽڵ�
    public static final int TAG_TABLE = 1; // ��ڵ�
    private char mStartSymb = '(';
    private char mEndSymb = ')';
    private Node mGenTable;

    public GeneralizedTable() {
        mGenTable = new Node(null, null, TAG_TABLE, null);
    }
    // ʹ�ù���� src ����һ���µĹ����
    public GeneralizedTable(GeneralizedTable src) {
        if (src != null) {
            mGenTable = src.mGenTable;
        }
    }
    /**
     * @param genTable
     */
    public GeneralizedTable(String genTable) {
        if (genTable == null) {
            throw new NullPointerException(
                    "genTable is null in constructor GeneralizedTable!...");
        }
        initTable(genTable);
    }

    private void initTable(String genTable) {
        String ts = genTable.replaceAll("\\s", "");
        int len = ts.length();
        Stack<Character> symbStack = new Stack<Character>();
        Stack<Node> nodeStck = new Stack<Node>();
        initSymbolicCharactor(ts);
        mGenTable = new Node(null, null, TAG_TABLE, null);
        Node itemNode, tableNode = mGenTable, tmpNode;
        for (int i = 0; i < len; i++) {
            if (ts.charAt(i) == mStartSymb) {
                tmpNode = new Node(null, null, TAG_TABLE, null);
                // tableNode = tableNode.mPt;
                symbStack.push(ts.charAt(i));
                if (symbStack.size() > 1) {
                    nodeStck.push(tableNode);
                    tableNode.mPh = tmpNode;
                    tableNode = tableNode.mPh;
                } else {
                    tableNode.mPt = tmpNode;
                    tableNode = tableNode.mPt;
                }
            } else if (ts.charAt(i) == mEndSymb) {
                if (symbStack.isEmpty()) {
                    throw new IllegalArgumentException(
                            "IllegalArgumentException in constructor GeneralizedTable!...");
                }
                if (symbStack.size() > 1) {
                    tableNode = nodeStck.pop();
                }
                symbStack.pop();
            } else if (ts.charAt(i) == ',') {
                tableNode.mPt = new Node(null, null, TAG_TABLE, null);
                tableNode = tableNode.mPt;
            } else {
                itemNode = new Node(null, null, TAG_ITEM, ts.charAt(i));
                tableNode.mPh = itemNode;
            }
        }

        if (!symbStack.isEmpty()) {
            throw new IllegalArgumentException(
                    "IllegalArgumentException in constructor GeneralizedTable!...");
        }
    }

    private void initSymbolicCharactor(String ts) {
        mStartSymb = ts.charAt(0);
        switch (mStartSymb) {
            case '(':
                mEndSymb = ')';
                break;
            case '{':
                mEndSymb = '}';
                break;
            case '[':
                mEndSymb = ']';
                break;
            default:
                throw new IllegalArgumentException(
                        "IllegalArgumentException ---> initSymbolicCharactor");
        }
    }

    public int depth() { // ���������
        if (mGenTable == null) {
            throw new NullPointerException("Generalized Table is null !.. ---> method depth");
        }
        return depth(mGenTable);
    }

    private int depth(Node node) {
        if (node == null || node.mTag == TAG_ITEM) {
            return 0;
        }
        int depHeader = 0, depTear = 0;
        depHeader = 1 + depth(node.mPh);
        depTear = depth(node.mPt);
        return depHeader > depTear ? depHeader : depTear;
    }

    public class Node {// �����ڵ�
        Node mPh; // �����ı�ڵ�
        Node mPt; // ������β�ڵ�
        int mTag; // mTag == 0 , Ժ�ӽڵ� ; mTag == 1 , ��ڵ� ��
        Object mData; // ����������ֵ

        public Node(Node ph, Node pt, int tag, Object data) {
            mPh = ph;
            mPt = pt;
            mTag = tag;
            mData = data;
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
    	Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        for(int i=0;i<n;i++) {
        	String p = in.nextLine();
        	int length=p.length();
        	p=p.substring(3,length);
        	GeneralizedTable gTab = new GeneralizedTable(p);
        	System.out.println(gTab.depth());
        }
    }
}