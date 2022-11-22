class CPU implements Checkable {
    public boolean check() {
        return true;
    }

    public byte[] executeInstructions(byte[] instructions) {
        System.out.println("Executing instructions...");
        return "resulting bytes".getBytes();
    }
}