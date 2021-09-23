public class UserInputParser {

    PostCommand postCommand = new PostCommand();
    ReadCommand readCommand = new ReadCommand();
    FollowCommand followCommand = new FollowCommand();
    WallCommand wallCommand = new WallCommand();

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
        if (input.contains("->")) postCommand.execute(input);
        if (input.contains("follows")) followCommand.execute(input);
        if (input.contains("wall")) wallCommand.execute(input);
        readCommand.execute(input);
    }
}
