package leetcode;

import java.util.HashSet;
import java.util.Set;

public class A929UniqueEmailAddresses {
  public static int numUniqueEmails(String[] emails) {
    Set<String> exist = new HashSet<>();
    for (String email : emails) {
      exist.add(process(email));
    }
    return exist.size();
  }

  private static String process(String email) {
    String[] sub = email.split("@");
    String local = sub[0];
    int plus = local.indexOf("+");
    if (plus != -1) {
      local = local.substring(0, plus);
    }
    local = local.replaceAll("\\.","");
    return local + "@" + sub[1];
  }

  public static void main(String[] args) {
    A929UniqueEmailAddresses.numUniqueEmails(
      new String[]{"test.email+alex@leetcode.com","test.email.leet+alex@code.com"});
  }
}
