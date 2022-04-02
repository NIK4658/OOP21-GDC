package utility;

import java.util.Objects;

/**
 * A standard generic Pair, with getters, hashCode, equals, and toString well implemented. 
 */
public class Pair<X, Y> {
        
    private final X e1;
    private final Y e2;

    /**
     * Main constructor. 
     */
    public Pair(final X x, final Y y) {
        super();
        this.e1 = x;
        this.e2 = y;
    }

    public X get1() {
        return e1;
    }

    public Y get2() {
        return e2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<X, Y> other = (Pair<X, Y>) obj;
        return Objects.equals(e1, other.e1) && Objects.equals(e2, other.e2);
    }

    @Override
    public String toString() {
        return "Pair [e1=" + e1 + ", e2=" + e2 + "]";
    }
}
