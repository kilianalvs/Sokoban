package com.gitlab.sokoban.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Map {

    private Integer size;
    private List<Square> squares;

    private boolean isWall(Square square){

        return square.getState() == State.Wall;
    }

    private boolean inSide(){

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return Objects.equals(size, map.size) && Objects.equals(squares, map.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, squares);
    }
}
