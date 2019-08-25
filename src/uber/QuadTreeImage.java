package uber;

public class QuadTreeImage {
  static class QTreeNode {
    QTreeNode[] children;
    int size;
    int color;
    QTreeNode() {

    }
    QTreeNode(int color, int size) {
      this.color = color;
      this.size = size;
    }

    public boolean isLeaf() {
      return children == null;
    }
  }
  public QTreeNode createQuadTree(int[][] image) {
    return build(image, 0, 0, image.length);
  }

  private QTreeNode build(int[][] image, int i, int j, int len) {
    if (len == 1) return new QTreeNode(image[i][j], len);

    QTreeNode node = new QTreeNode();
    node.size = len;

    QTreeNode topLeft = build(image, i, j, len/2);
    QTreeNode topRight = build(image, i, j+len/2, len/2);
    QTreeNode bottomRight  = build(image, i + len/2, j+len/2, len/2);
    QTreeNode bottomLeft = build(image, i+len/2, j, len/2);

    if (topLeft.isLeaf() && topRight.isLeaf() && bottomRight.isLeaf() && bottomLeft.isLeaf() &&
      topLeft.color == topRight.color && topLeft.color == bottomRight.color && topLeft.color == bottomLeft.color) {
      node.color = topLeft.color;
    } else {
      node.children = new QTreeNode[4];
      node.children[0] = topLeft;
      node.children[1] = topRight;
      node.children[2] = bottomRight;
      node.children[3] = bottomLeft;
    }

    return node;
  }

  public static void main(String[] args) {
    QuadTreeImage in = new QuadTreeImage();
    QTreeNode node = in.createQuadTree(new int[][]{{2,2,3,3}, {2,2,3,3}, {4,5,5,5}, {5,6,5,5}});
    System.out.println();
  }
}

