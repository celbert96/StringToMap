import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeNode node = new TreeNode("root");
        buildTreeFromPath("branch1.child1.value", "hi", ".", node);
        buildTreeFromPath("branch1.child1.value2", "hi1", ".", node);
        buildTreeFromPath("branch1.child2.value3", "hi2", ".", node);
        buildTreeFromPath("branch1.child2.value3", "overwrite test", ".", node);
        buildTreeFromPath("branch1.child3", "hi3", ".", node);
        buildTreeFromPath("branch1.child1.cool", "coolbeans", ".", node);
        buildTreeFromPath("branch2.child1.cool.cool1", "coolbeans", ".", node);
        buildTreeFromPath("branch2.child1.cool.cool2", "coolbeans", ".", node);
        buildTreeFromPath("branch3.child1.cool.cool3", "coolbeans", ".", node);
        //traverseTree(node);
        System.out.println(treeToJson(node, new HashMap<>()));
    }

    public static void buildTreeFromPath(String path, String leafValue, String delimiter, TreeNode root) {
        if(!path.contains(delimiter)) {
            root.nodes.add(new LeafNode(path, leafValue));
            return;
        }
        String key = path.substring(0, path.indexOf(delimiter));
        path = path.substring(path.indexOf(delimiter) + 1);

        for (int i = 0; i < root.nodes.size(); i++) {
            if(root.nodes.get(i).value.equals(key)) {
                buildTreeFromPath(path, leafValue, delimiter, root.nodes.get(i));
                return;
            }
        }

        TreeNode newNode = new TreeNode(key);
        root.nodes.add(newNode);

        buildTreeFromPath(path, leafValue, delimiter, newNode);
    }

    public static void traverseTree(TreeNode root) {
        System.out.println(root.value);
        if(root.nodes.isEmpty()) {
            return;
        }

        for(int i = 0; i < root.nodes.size(); i++) {
            traverseTree(root.nodes.get(i));
        }
    }

    public static HashMap<String, Object> treeToJson(TreeNode node, HashMap<String, Object> map) {
        if(node.nodes.isEmpty()) {
            return map;
        }

        for(int i = 0; i < node.nodes.size(); i++) {
            TreeNode currentNode = node.nodes.get(i);
            if(currentNode.nodes.isEmpty()) {
                LeafNode leafNode = (LeafNode) node.nodes.get(i);
                map.put(currentNode.value, leafNode.appKey);
            } else {
                map.put(currentNode.value, treeToJson(currentNode, new HashMap<>()));
            }
        }

        return map;
    }
}