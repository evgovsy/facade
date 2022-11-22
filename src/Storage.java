interface Storage {
    int getFreeSectorAddress(int length);

    byte[] getData(int address, int length);

    void storeData(byte[] data, int address);
}