package io.github.FeAuCo;

import io.github.FeAuCo.node_related.Node;

public class GameStates {
    private static String choiceAlgorithmState;

    private static boolean visitDiagonalNodes;

    private static Node StartNode;
    private static Node BarrierNode;
    private static Node EndNode;

    public static Node getBarrierNode() {
        return BarrierNode;
    }

    public static void setBarrierNode(Node barrierNode) {
        BarrierNode = barrierNode;
    }

    public static Node getEndNode() {
        return EndNode;
    }

    public static void setEndNode(Node endNode) {
        EndNode = endNode;
    }

    public static Node getStartNode() {
        return StartNode;
    }

    public static void setStartNode(Node startNode) {
        StartNode = startNode;
    }

    public static boolean isVisitDiagonalNodes() {
        return visitDiagonalNodes;
    }

    public static void setVisitDiagonalNodes(boolean visitDiagonalNodes) {
        GameStates.visitDiagonalNodes = visitDiagonalNodes;
    }

    public static String getChoiceAlgorithmState() {
        return choiceAlgorithmState;
    }

    public static void setChoiceAlgorithmState(String choiceAlgorithmState) {
        GameStates.choiceAlgorithmState = choiceAlgorithmState;
    }
}
