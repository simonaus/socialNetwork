public class ReadCommand implements Command {

    PostRepository postRepository = new PostRepository();

    public ReadCommand(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ReadCommand() {
    }

    @Override
    public void execute(String userInput) {
        postRepository.getPostsByUser(userInput);
    }
}
