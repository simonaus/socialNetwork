import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SocialNetworkShould {

    @Test
    public void socialNetworkShouldProcessTheFourTypesOfUserInput() {
        Printer printer = new Printer();
        SocialNetwork socialNetwork = new SocialNetwork(printer);

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
        SocialNetwork socialNetwork = new SocialNetwork(printer);
        socialNetwork.userInput("Alice");
        verify(printer).print(any(String.class));
    }
}