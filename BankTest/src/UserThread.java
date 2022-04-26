public class UserThread extends Thread {

private User u;

public UserThread(User u, String name) {
    super(name);
    this.u = u;
}

@Override
public void run() {
    BankTest.runSimulation(this.u);
}
}