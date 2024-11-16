package io.github.FeAuCo.algorithms;

import io.github.FeAuCo.node_related.Node;
import io.github.FeAuCo.node_related.NodeTypes;

import java.util.ArrayList;

public class Djikstra {
    private static ArrayList<Node> frontier;
    private static Node agent;
    private static ArrayList<Node> agents;

    private static void run(ArrayList<Node[]> nodes, Node startNode){
        agent = startNode;
        boolean end = false;


        while (!end){


        }
    }

    private static void extendFrontier(ArrayList<Node[]> nodes, Node agent){
        if (agent.getCoordinates()[1] < 701){
            if (!nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.VISITED) ||
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER)){

                nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].setPreviousNode(agent);
                frontier.add(nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]]);
            }
        }

        if (agent.getCoordinates()[1] > 8){
            if (!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.VISITED) ||
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER)){

                nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].setPreviousNode(agent);
                frontier.add(nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]]);
            }
        }

        if (agent.getCoordinates()[0] < 867){
            if (!nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.VISITED) ||
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER)){

                nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].setPreviousNode(agent);
                frontier.add(nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1]);
            }
        }

        if (agent.getCoordinates()[0] > 6){
            if (!nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.VISITED) ||
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER)){

                nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].setPreviousNode(agent);
                frontier.add(nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1]);
            }
        }
    }
}
