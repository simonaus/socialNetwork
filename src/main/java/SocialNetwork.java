public class SocialNetwork {

    Printer printer;

    public SocialNetwork(Printer printer) {
        this.printer = printer;
    }

    public void userInput(String userInput) {
        printer.print(userInput);
    }
}
