package io.github.FeAuCo;

import io.github.FeAuCo.algorithms.Djikstra;

import static io.github.FeAuCo.Main.nodes;

public class Game {
    public static void start(){
        switch (GameStates.getChoiceAlgorithmState()) {
            case "djikstra" -> Djikstra.run(nodes, GameStates.getStartNode());
//            case "bfs" -> BFS.run(nodes, GameStates.getStartNode());
//            case "dfs" -> DFS.run(nodes, GameStates.getStartNode());
//            case "a*" -> A.run(nodes, GameStates.getStartNode());
        }
    }
}
