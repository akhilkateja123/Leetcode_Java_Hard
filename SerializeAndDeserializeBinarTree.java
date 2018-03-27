package aws.amazon.practice;

import java.util.Arrays;
import java.util.LinkedList;

class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;

	TreeNode(int val) {
		this.val = val;
	}
}

public class SerializeAndDeserializeBinarTree {
	private static final String NN = "X";
	private static String SPLITTER = ",";

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(sb, root);
		return sb.toString();
	}

	private void buildString(StringBuilder sb, TreeNode root) {
		if (root == null) {
			sb.append(NN);
			sb.append(SPLITTER);
		} else {
			sb.append(root.val);
			sb.append(SPLITTER);
			buildString(sb, root.left);
			buildString(sb, root.right);
		}
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		LinkedList<String> nodes = new LinkedList<>();
		nodes.addAll(Arrays.asList(data.split(SPLITTER)));
		return buildTree(nodes);
	}

	private TreeNode buildTree(LinkedList<String> nodes) {
		String val = nodes.remove();
		if (val.equals(NN))
			return null;
		else {
			TreeNode node = new TreeNode(Integer.valueOf(val));
			node.left = buildTree(nodes);
			node.right = buildTree(nodes);
			return node;
		}
	}
}
