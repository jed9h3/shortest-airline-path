public class Node {
    double distance;
    City vertix;
    boolean known ;
    Node pNode;
    LinkList adj;
    public Node(City c) {
        this.vertix=c;
        distance=Double.MAX_VALUE;
        known=false;
        pNode=null;
        adj=new LinkList();
    }
}
