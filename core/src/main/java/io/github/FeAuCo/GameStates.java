package io.github.FeAuCo;

import io.github.FeAuCo.node_related.Node;

public class GameStates {
    private static String choiceAlgorithmState;

    private static Node StartNode;
    private static boolean placeBarrier;
    private static boolean placedEnd;

    public static boolean isPlacedEnd() {
        return placedEnd;
    }

    public static void setPlacedEnd(boolean placedEnd) {
        GameStates.placedEnd = placedEnd;
    }

    public static boolean isPlaceBarrier() {
        return placeBarrier;
    }

    public static void setPlaceBarrier(boolean placeBarrier) {
        GameStates.placeBarrier = placeBarrier;
    }

    public static Node getStartNode() {
        return StartNode;
    }

    public static void setStartNode(Node startNode) {
        StartNode = startNode;
    }

    public static String getChoiceAlgorithmState() {
        return choiceAlgorithmState;
    }

    public static void setChoiceAlgorithmState(String choiceAlgorithmState) {
        GameStates.choiceAlgorithmState = choiceAlgorithmState;
    }
}
