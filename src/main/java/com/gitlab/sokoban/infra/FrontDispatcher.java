package com.gitlab.sokoban.infra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*class SokobanDTO {
    public final List<List<String>> tiles;

    SokobanDTO(List<List<String>> tiles) {
        this.tiles = tiles;
    }
}

class Mapper {
    static SokobanDTO toDTO(Sokoban sokoban) {
        List<Square> squares = sokoban.getTiles();

        Size size = sokoban.getSize();

        Stream<Square> emptyTiles = IntStream.range(0, size.width)
                .mapToObj((x) ->
                        IntStream.range(0, size.height).mapToObj( (y) -> new Position(x, y)))
                .flatMap((o) -> o)
                .filter(position -> squares.stream().noneMatch(t -> t.position().equals(position)))
                .map((p) -> new Square(p, State.Empty));


        return new SokobanDTO((Stream.concat(emptyTiles, squares.stream()))
                .collect(Collectors.groupingBy((Square square) -> square.position().y)).values()
                .stream().sorted(Comparator.comparingInt(left -> left.get(0).position().y))
                .map(line -> line.stream().sorted(Comparator.comparingInt(tile -> tile.position().x)))
                .map((line) -> line.map((c) -> c.state().toString().toLowerCase()).collect(Collectors.toList()))
                .collect(Collectors.toList())
        );

    }
}*/

@Controller
@RequestMapping("/")
public class FrontDispatcher {
    //private final Play play;
    //private final GameResources resources;

    /*public FrontDispatcher(Play play, GameResources resources) {
        this.play = play;
        this.resources = resources;
    }*/

    /*@RequestMapping(path = "", method = RequestMethod.POST)
    String move(@RequestParam(value = "move", required = false) String move, Model model) {
        Sokoban current = play.invoke(Direction.valueOf(move));
        SokobanDTO dto = Mapper.toDTO(current);
        model.addAttribute("squares", dto.tiles);
        return "index.html";
    }*/

    /*@RequestMapping(path = "", method = RequestMethod.GET)
    String index(Model model) {
            Sokoban current = resources.get();
            SokobanDTO dto = Mapper.toDTO(current);
            model.addAttribute("squares", dto.tiles);
            return "index.html";
    }*/

    public FrontDispatcher() {
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    String move(@RequestParam(value = "move", required = false) String move, Model model) {

        model.addAttribute("squares", Arrays.asList(
                Arrays.asList("wall", "wall" , "wall", "wall"),
                Arrays.asList("wall", "empty" , "empty", "wall"),
                Arrays.asList("wall", "empty" , "empty", "wall"),
                Arrays.asList("wall", "wall" , "wall", "wall")

        ));
        return "index.html";
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    String index(Model model) {

        model.addAttribute("squares", Arrays.asList(
                Arrays.asList("wall", "wall" , "wall", "wall"),
                Arrays.asList("wall", "empty" , "empty", "wall"),
                Arrays.asList("wall", "empty" , "empty", "wall"),
                Arrays.asList("wall", "wall" , "wall", "wall")

        ));
        return "index.html";
    }
}


