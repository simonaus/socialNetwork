import java.util.ArrayList;
import java.util.List;

public class WallCommand implements  Command{

    private PostRepository postRepository;
    private FollowRepository followRepository;
    private Printer printer = new Printer();

    public WallCommand(PostRepository postRepository, FollowRepository followRepository) {
        this.postRepository = postRepository;
        this.followRepository = followRepository;
    }

    public WallCommand(PostRepository postRepository, FollowRepository followRepository, Printer printer) {
        this.postRepository = postRepository;
        this.followRepository = followRepository;
        this.printer = printer;
    }

    @Override
    public void execute(String userInput) {
        String user = userInput.split(" wall")[0];
        List<String> followees = followRepository.getFollowersByUser(user);
        List<Post> posts = new ArrayList<>();
        List<Post> postsByWallOwner = postRepository.getPostsByUser(user);
        for (Post post : postsByWallOwner) {
            posts.add(post);
        }
        for (String followee : followees) {
            List<Post> postsByUsers = postRepository.getPostsByUser(followee);
            for (Post post : postsByUsers)
                posts.add(post);
        }
        for (Post post : posts) {
            printer.print(post.getContent());
        }
    }
}
