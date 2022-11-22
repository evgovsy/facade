class Monitor implements Checkable {
    public boolean check() {
        return true;
    }

    public void display(byte[] pixels) {
        System.out.println("Displaying pixels...");
    }
}