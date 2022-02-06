public class DynArray<T> {
    private int size = 0;
    private T[] array;

    public DynArray () {
        array = (T[]) new Object[1];
    }

    public static void main (String[] args) {
        DynArray<Integer> dyn = new DynArray<Integer>();
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //  for(int i:arr){ dyn.addFirst(i); }
        dyn.addLast(5);
        dyn.addFirst(2);
        dyn.removeLast();
        System.out.println(dyn.size);
        System.out.println(dyn);
    }

    public int size () {
        return this.size;
    }

    public int capacity () { return this.array.length; }

    public T get (int pos) {
        if ( pos < 0 | pos > size | array[pos] == null ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T copy = array[pos];
        return copy;
    }

    public T set (int pos, T e) {
        if ( pos < 0 | array[pos] == null ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T ret = array[pos];
        array[pos] = e; // kein prüfen der size erforderlich
        return ret;
    }

    public void addFirst (T e) {
        size++;
        setCapacity();
        for ( int i = size; i > 0; i-- ) {
            array[i] = array[i - 1];
        }
        array[0] = e;
    }

    public void addLast (T e) {
        array[size] = e;
        size++;
        setCapacity();
    }

    public T removeFirst () {
        if ( size <= 0 ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T first = array[0];
        array[0] = null;
        size--;
        setCapacity();
        return first;
    }

    public T removeLast () {
        if ( size <= 0 ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T last = array[size - 1];
        array[size - 1] = null;
        size--;
        setCapacity();
        return last;
    }

    public void setCapacity () {
        int cap = capacity();
        if ( cap == this.size ) {
            cap *= 2;
        }else if ( this.size == cap / 4 ) {
            cap /= 2;
        }
        T[] before = array;
        array = (T[]) new Object[cap];
        for ( int i = 0; i < this.size; i++ ) {
            array[i] = before[i];
        }
    }

    public String toString () {
        String erg = "[";
        for ( int i = 0; i < capacity(); i++ ) {
            if ( array[i] != null ) {
                erg += array[i] + ",";
            }
            else {
                erg += "_";
                if ( i < capacity() - 1 ) erg += ",";
            }
        }
        return erg + "]";
    }
}
