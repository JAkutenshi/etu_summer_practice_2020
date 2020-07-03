import javax.print.attribute.standard.NumberUp;

public class binTree {
    private long info = 0;
    private binTree lt;
    private binTree rt;
    private Control control_object;
    public binTree(){
        this.lt = null;
        this.rt = null;
        control_object = new Control(true,0);
    }
    public long getInfo(){
        return this.info;
    }

    public binTree left(binTree b){
        return b.lt;
    }
    public binTree right(binTree b){
        return b.rt;
    }
    public binTree enterBT(binTree b,Node[] nodes,int index){
        binTree p;
        if (index == -1) {
            b = null;
            return b;
        }
        else {
            p = new binTree();
            p.info = nodes[index].getKey();
            p.lt = p.enterBT(p.lt,nodes,nodes[index].getLeft());
            p.rt = p.enterBT(p.rt,nodes,nodes[index].getRight());
        }
        b = p;
        return b;
    }
    public boolean inOrder(binTree b,long[] keys){
        if(b == null)
            return control_object.getFlag();
        if(!this.control_object.getFlag())
            return control_object.getFlag();
        if(b.left(b) != null && b.getInfo() <= b.left(b).getInfo()){
            this.control_object.changeFlag();
            return this.control_object.getFlag();
        }
        if(!inOrder(b.left(b),keys))
            this.control_object.changeFlag();
        if(!this.control_object.getFlag())
            return this.control_object.getFlag();
        keys[this.control_object.getIndex()] = b.getInfo();
        this.control_object.increaseIndex();
        if(!inOrder(b.right(b),keys))
            this.control_object.changeFlag();
        return this.control_object.getFlag();
    }
}
