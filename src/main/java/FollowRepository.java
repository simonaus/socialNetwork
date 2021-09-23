import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowRepository {

    Map<String, List<String>> followDatabase = new HashMap<>();

    public void saveFollow(String follower, String followee) {
        if (!followDatabase.containsKey(follower)) {
            followDatabase.put(follower, new ArrayList<>());
        }
        followDatabase.get(follower).add(followee);
    }
}
