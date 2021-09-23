import java.util.List;

public class ReadCommand implements Command {

    PostRepository postRepository;
    Printer printer = new Printer();

    public ReadCommand(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ReadCommand(PostRepository postRepository, Printer printer) {
        this.postRepository = postRepository;
        this.printer = printer;
    }

    @Override
    public void execute(String userInput) {
        List<Post> posts = postRepository.getPostsByUser(userInput);
        for (Post post: posts) {
            printer.print(post.getContent());
        }
    }
}
