public class LeafNode extends TreeNode {
    String appKey;

    public LeafNode(String value, String appKey) {
        super(value);
        this.appKey = appKey;
    }
}
