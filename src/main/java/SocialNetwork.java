public class SocialNetwork {

    UserInputParser userInputParser;

    public SocialNetwork(UserInputParser userInputParser) {
        this.userInputParser = userInputParser;
    }

    public void userInput(String userInput) {
        userInputParser.parse(userInput);
    }
}
