package airbnb;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {
  private Map<String, Integer> map = new HashMap<>();
  private Map<String, Runnable> hooks = new HashMap<>();

  public boolean create(String path, int content) {
    if (map.containsKey(path)) return false;
    int index = path.lastIndexOf('/');
    if (!map.containsKey(path.substring(0, index))) {
      return false;
    }
    map.put(path, content);
    return true;
  }

  public boolean set(String path, int content) {
    if (!map.containsKey(path)) return false;
    map.put(path, content);
    trigger(path);
    return true;
  }

  private void trigger(String path) {
    String cur = path;
    while (!cur.isEmpty()) {
      if (hooks.containsKey(cur)) hooks.get(cur).run();
      cur = cur.substring(0, cur.lastIndexOf('/'));
    }
  }

  public int get(String path) {
    if (!map.containsKey(path)) throw new RuntimeException("not exist");
    return map.get(path);
  }

  public boolean observe(String path, Runnable runnable) {
    if (!map.containsKey(path)) return false;
    hooks.put(path, runnable);
    return true;
  }
}
