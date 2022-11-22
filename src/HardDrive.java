class HardDrive implements Checkable, Storage {
    private final byte[] storage = new byte[10 * 1024 * 1024];

    public boolean check() {
        return true;
    }

    public int getFreeSectorAddress(int length) {
        return 0; // some address
    }

    public byte[] getData(int address, int length) {
        return "some data".getBytes();
    }

    public void storeData(byte[] data, int address) {
        System.out.println("Storing data...");
    }
}