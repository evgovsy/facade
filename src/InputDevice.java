class InputDevice implements Checkable  {
    public boolean check() {
        return true;
    }

    public byte[] getInput() {
        return "some input".getBytes();
    }
}