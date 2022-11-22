import java.util.ArrayList;

// This is basically our facade
class Computer {
    private static final byte[] startupInstructions = ("" +
            "mov $1, %rax mov $1, %rdi mov $message, %rsi mov $13, %rdx syscall").getBytes();
    private static final byte[] shutdownInstructions = ("" +
            "%rsi mov $13, %rdx syscall, mov $1, %rax mov $1, %rdi mov $message").getBytes();
    private static final int osAddress = 0;
    private static final int osLength = 1024 * 512;

    private final CPU cpu = new CPU();
    private final RAM ram = new RAM();
    private final HardDrive hardDrive = new HardDrive();
    private final Monitor monitor = new Monitor();
    private final InputDevice inputDevice = new InputDevice();

    public static void main(String[] args) throws Exception {
        Computer computer = new Computer();
        computer.startup();
        computer.doWork();
        computer.shutdown();
    }

    public void startup() throws Exception {
        System.out.println("Doing checks...");
        doChecks();

        System.out.println("Executing startup instructions...");
        cpu.executeInstructions(startupInstructions);

        System.out.println("Retrieving the OS from the hard drive...");
        byte[] os = hardDrive.getData(osAddress, osLength);

        System.out.println("Booting the OS...");
        byte[] result = cpu.executeInstructions(os);

        System.out.println("Showing picture on the monitor...");
        monitor.display(result);
    }

    public void doWork() {
        System.out.println("Getting user input...");
        byte[] input = inputDevice.getInput();

        System.out.println("Executing instructions provided by the user...");
        byte[] result = cpu.executeInstructions(input);

        System.out.println("Displaying the result...");
        monitor.display(result);
    }

    public void shutdown() {
        System.out.println("Executing shutdown instructions...");
        cpu.executeInstructions(shutdownInstructions);
    }

    private void doChecks() throws Exception {
        ArrayList<Checkable> components = new ArrayList<>();
        components.add(cpu);
        components.add(ram);
        components.add(hardDrive);
        components.add(monitor);
        components.add(inputDevice);

        for (Checkable component : components) {
            if (!component.check()) {
                throw new Exception("Faulty component: " + component);
            }
        }
    }
}