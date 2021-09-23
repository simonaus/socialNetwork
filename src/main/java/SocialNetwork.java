public class SocialNetwork {

    Printer printer;
    UserInputParser userInputParser;

    public SocialNetwork(Printer printer, UserInputParser userInputParser) {
        this.printer = printer;
        this.userInputParser = userInputParser;
    }

    public void userInput(String userInput) {
        userInputParser.parse(userInput);
        printer.print(userInput);
    }
}
