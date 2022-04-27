public class Main
{
    public static void main(String[] args)
    {
        Prompter prompter = new Prompter();

        prompter.greetUser();
        do
        {
            prompter.prompt();
            prompter.processPrompt();
        }   while(!prompter.isFinishedBanking);
    }
}