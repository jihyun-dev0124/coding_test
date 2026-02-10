package programmers.hash;

import java.util.Objects;

public class Music {
    private Integer idx;
    private String name;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Music music = (Music) object;
        return Objects.equals(idx, music.idx) && Objects.equals(name, music.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, name);
    }
}
