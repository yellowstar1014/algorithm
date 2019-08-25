package airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TwoDIterator implements Iterator<Integer> {
  private Iterator<List<Integer>> i;
  private Iterator<Integer> j;

  public TwoDIterator(List<List<Integer>> vec2d) {
    this.i = vec2d.iterator();
    this.j = null;
  }

  public boolean hasNext() {
    while ((j == null || !j.hasNext()) && i.hasNext()) {
      j = i.next().iterator();
    }
    return j!= null || j.hasNext();
  }

  public Integer next() {
    return j.next();
  }

  public void remove() {
    j.remove();
  }

  public static void main(String args[])
  {
    List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2));
    List<Integer> list2 = new ArrayList<>(Arrays.asList(3));
    List<List<Integer>> vec2d = new ArrayList<>();
    vec2d.add(list1);
    vec2d.add(list2);
    //1,2
    //3
    TwoDIterator myIter = new TwoDIterator(vec2d);
    System.out.println(myIter.hasNext());
    System.out.println(myIter.next());
    System.out.println(myIter.hasNext());
    myIter.remove();
    System.out.println(myIter.next());
    for (int i = 0; i < vec2d.size(); i++) {
      if (vec2d.get(i) == null) continue;
      for (int j = 0; j < vec2d.get(i).size(); j++) {
        System.out.println("List" + i + ":" + vec2d.get(i).get(j));
      }

    }
    System.out.println(myIter.hasNext());
    System.out.println(myIter.next());
  }
}
