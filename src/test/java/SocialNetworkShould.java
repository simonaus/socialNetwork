import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SocialNetworkShould {

    @Test
    public void socialNetworkShouldProcessTheFourTypesOfUserInput() {
        Printer printer = new Printer();
        SocialNetwork socialNetwork = new SocialNetwork(printer, new UserInputParser(new PostCommand()));

        socialNetwork.userInput("Alice -> I love the weather today");
        socialNetwork.userInput("Alice");
        socialNetwork.userInput("Charlie -> I'm in New York today! Anyone want to have a coffee?");
        socialNetwork.userInput("Charlie follows Alice");
        socialNetwork.userInput("Charlie wall");

        verify(printer).print("I love the weather today (5 minutes ago)");
        verify(printer).print("Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)\n" +
                "Alice - I love the weather today (5 minutes ago)");
    }

    @Test
    public void socialMediaShouldSendOutputToPrinter() {
        Printer printer = mock(Printer.class);
        UserInputParser userInputParser = mock(UserInputParser.class);
        SocialNetwork socialNetwork = new SocialNetwork(printer, userInputParser);
        socialNetwork.userInput("Alice");
        verify(printer).print(any(String.class));
    }

    @Test
    void send_user_input_to_the_parser() {
        UserInputParser userInputParser = mock(UserInputParser.class);
        SocialNetwork socialNetwork = new SocialNetwork(new Printer(), userInputParser);
        socialNetwork.userInput("Alice -> I love the weather today");
        verify(userInputParser).parse("Alice -> I love the weather today");
    }

    @Test
    void userInputParser_should_send_post_commands_to_post_command_class() {
        PostCommand postCommand = mock(PostCommand.class);
        UserInputParser userInputParser = new UserInputParser(postCommand);
        userInputParser.parse("Alice -> I love the weather today");
        verify(postCommand).execute("Alice -> I love the weather today");
    }

    @Test
    void userInputParserShouldSendReadCommandsToReadCommandClass() {
        ReadCommand readCommand = mock(ReadCommand.class);
        UserInputParser userInputParser = new UserInputParser(readCommand);
        userInputParser.parse("Alice");
        verify(readCommand).execute("Alice");
    }

    @Test
    void userInputParserShouldSendFollowCommandsToFollowCommandClass() {
        FollowCommand followCommand = mock(FollowCommand.class);
        UserInputParser userInputParser = new UserInputParser(followCommand);
        userInputParser.parse("Charlie follows Alice"
        );
        verify(followCommand).execute("Charlie follows Alice");
    }

    @Test
    void userInputParserShouldSendWallCommandsToCommandClass() {
        WallCommand wallCommand = mock(WallCommand.class);
        UserInputParser userInputParser = new UserInputParser(wallCommand);
        userInputParser.parse("Charlie wall");
        verify(wallCommand).execute("Charlie wall");
    }

    @Test
    void postCommandShouldSavePostToThePostRepository() {
        PostRepository postRepository = mock(PostRepository.class);
        PostCommand postCommand = new PostCommand(postRepository);
        postCommand.execute("Alice -> I love the weather today");
        verify(postRepository).savePost("Alice", "I love the weather today");
    }

    @Test
    void postRepositoryShouldContainPostAfterItHasBeenSaved() {
        Map<String, List<Post>> postDatabase = new HashMap<>();
        Post post = new Post("I love the weather today");
        postDatabase.put("Alice", Arrays.asList(new Post("I love the weather today")));
        PostRepository postRepository = new PostRepository();
        postRepository.savePost("Alice", "I love the weather today");
        assertEquals(postDatabase, postRepository.getPostDatabase());
    }

    @Test
    void readCommandShouldRequestPostsToPrintFromRepository() {
        PostRepository postRepository = mock(PostRepository.class);
        ReadCommand readCommand = new ReadCommand(postRepository);
        readCommand.execute("Alice");
        verify(postRepository).getPostsByUser("Alice");
    }

    @Test
    void postRepository_should_retrieve_posts_by_user_when_readCommand_executes() {
        PostRepository postRepository = new PostRepository();
        List<Post> postsByUser = new ArrayList<>();
        postsByUser.add(new Post("I love the weather today"));

        postRepository.savePost("Alice", "I love the weather today");

        assertEquals(postsByUser, postRepository.getPostsByUser("Alice"));
    }
}