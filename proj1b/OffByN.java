public class OffByN implements CharacterComparator{
    private int size;

    public void OffByN(int N) {
        size = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == size || diff == -size) {
            return true;
        } else {
            return false;
        }
    }
}
