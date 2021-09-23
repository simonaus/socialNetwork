public class UserInputParser {

    PostRepository postRepository = new PostRepository();
    FollowRepository followRepository = new FollowRepository();
    PostCommand postCommand = new PostCommand(postRepository);
    ReadCommand readCommand = new ReadCommand(postRepository);
    FollowCommand followCommand = new FollowCommand(followRepository);
    WallCommand wallCommand = new WallCommand(postRepository, followRepository);

    public UserInputParser() {
    }

    public UserInputParser(PostCommand postCommand) {
        this.postCommand = postCommand;
    }

    public UserInputParser(ReadCommand readCommand) {
        this.readCommand = readCommand;
    }

    public UserInputParser(FollowCommand followCommand) {
        this.followCommand = followCommand;
    }

    public UserInputParser(WallCommand wallCommand) {
        this.wallCommand = wallCommand;
    }

    public void parse(String input) {
        if (input.contains("->")) {
            postCommand.execute(input);
        } else if (input.contains("follows")) {
            followCommand.execute(input);
        } else if (input.contains("wall")) {
            wallCommand.execute(input);
        } else {
            readCommand.execute(input);
        }
    }
}
