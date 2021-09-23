public class FollowCommand implements Command{

    FollowRepository followRepository = new FollowRepository();

    public FollowCommand() {
    }

    public FollowCommand(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public void execute(String userInput) {
        String[] arguments = userInput.split(" follows ");
        followRepository.saveFollow(arguments[0], arguments[1]);
    }
}
