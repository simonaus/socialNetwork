public class PostCommand implements Command {

    PostRepository postRepository = new PostRepository();

    public PostCommand(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostCommand() {
    }

    @Override
    public void execute(String userInput) {
        String[] arguments = userInput.split(" -> ");
        postRepository.savePost(arguments[0], arguments[1]);
    }
}
