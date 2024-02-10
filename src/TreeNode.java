import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public String value;
    public List<TreeNode> nodes;

    public TreeNode(String value) {
        this.value = value;
        this.nodes = new ArrayList<>();
    }
}

