package airbnb;

import java.util.*;

public class TravelBuddy {
  class Buddy {
    int similarity;
    Set<String> wishList;
    String name;

    Buddy(String name, Set<String> wishList, int similarity) {
      this.name = name;
      this.wishList = wishList;
      this.similarity = similarity;
    }
  }

  public List<Buddy> buddies;

  public List<String> recommendCities(Set<String> myWishList, Map<String, Set<String>> friendsWishList, int k) {
    List<Buddy> buddies = new ArrayList<>();
    this.buddies = buddies;
    for (String name : friendsWishList.keySet()) {
      Set<String> wishList = friendsWishList.get(name);
      Set<String> intersection = new HashSet<>(wishList);
      intersection.retainAll(myWishList);
      int similarity = intersection.size();
      buddies.add(new Buddy(name, wishList, similarity));
    }

    List<String> recommend = new ArrayList<>();

    Collections.sort(buddies, (a,b) -> b.similarity-a.similarity);

    for (int i = 0; i < buddies.size(); i++) {
      Set<String> diff = new HashSet<>(buddies.get(i).wishList);
      diff.removeAll(myWishList);
      if (diff.size() <= k) {
        recommend.addAll(diff);
        k -= diff.size();
      } else {
        Iterator<String> it = diff.iterator();
        while (k-- > 0) {
          recommend.add(it.next());
        }
        break;
      }
    }
    return recommend;
  }

  public static void main(String args[])
  {
    Set<String> myList = new HashSet<>(Arrays.asList("A", "B", "C", "D"));

    Set<String> peter = new HashSet<>(Arrays.asList("A", "B", "E", "F"));
    Set<String> john = new HashSet<>(Arrays.asList("A", "B", "D", "G"));
    Set<String> casy = new HashSet<>(Arrays.asList("X", "B", "A", "D", "Q"));
    Set<String> jason = new HashSet<>(Arrays.asList("A", "B", "C", "D", "P", "Q"));
    Set<String> ken = new HashSet<>(Arrays.asList("A", "X", "Y", "Z"));

    Map<String, Set<String>> friendLists = new HashMap<>();
    friendLists.put("peter", peter);
    friendLists.put("john", john);
    friendLists.put("casy", casy);
    friendLists.put("jason", jason);
    friendLists.put("ken", ken);


    TravelBuddy solution = new TravelBuddy();

    List<String> cities = solution.recommendCities(myList, friendLists, 5);

    List<Buddy> buddies = solution.buddies;
    for (int i = 0; i < buddies.size(); i++) {
      Buddy b = buddies.get(i);
      System.out.println("Name: " + b.name + " sim: " + b.similarity);
    }

    for (String city : cities) {
      System.out.println(city);
    }
  }
}
