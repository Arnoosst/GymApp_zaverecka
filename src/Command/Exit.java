package Command;

public class Exit extends Command{
    @Override
    public void execute() {
        System.out.println("Closing app");

    }

    @Override
    public boolean exit() {
        return true;
    }
}
