import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository {

    Map<String, List<Post>> postDatabase = new HashMap<>();

    public void savePost(String user, String content) {
        Post post = new Post(content);

        if (!postDatabase.containsKey(user)) {
            postDatabase.put(user, new ArrayList<>());
        }
        postDatabase.get(user).add(post);
    }

    public Map<String, List<Post>> getPostDatabase() {
        return postDatabase;
    }


    public List<Post> getPostsByUser(String user) {
        List<Post> postList = postDatabase.get(user);
        return postList;
    }
}
