package io.github.FeAuCo.algorithms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.FeAuCo.node_related.Node;
import io.github.FeAuCo.node_related.NodeTypes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Djikstra {
    public static SpriteBatch batch;

    public static void run(ArrayList<Node[]> nodes, Node startNode) {
        ArrayList<Node> frontier = new ArrayList<>();
        ArrayList<Node> agents = new ArrayList<>();
        Node endNode = new Node(null, null);

        agents.add(startNode);

        boolean end = false;
        boolean noRoute = false;

        while (!end) {
            for (Node agent : agents) {
                if (agent.getNodeType().equals(NodeTypes.END)) {
                    endNode = agent.getPreviousNode();
                    end = true;
                    break;
                }

                frontier.addAll(updateFrontier(nodes, agent));

                if (!agent.getNodeType().equals(NodeTypes.START) && !agent.getNodeType().equals(NodeTypes.END)) {
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0]].setNodeType(NodeTypes.VISITED);
                }
            }

            if (frontier.isEmpty()) {
                noRoute = true;
                break;
            }

            agents = new ArrayList<>(frontier);

            frontier.clear();
        }

        if (!noRoute){
            while (true) {
                if (endNode.getPreviousNode() == null) {
                    break;
                }
                nodes.get(endNode.getIndices()[1])[endNode.getIndices()[0]].setNodeType(NodeTypes.PATH);
                endNode = endNode.getPreviousNode().clone();
            }
        }
    }

    private static ArrayList<Node> updateFrontier(ArrayList<Node[]> nodes, Node agent) {
        ArrayList<Node> newFrontier = new ArrayList<>();

        agent = agent.clone();
        if (!agent.getNodeType().equals(NodeTypes.START)) {
            agent.setPreviousNode(agent.getPreviousNode().clone());
        }

        if (agent.getCoordinates()[1] < 701){
            if (!nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.START)){

                if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getValue() > agent.getValue() + 1){
                    nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].setValue(agent.getValue() + 1);
                    nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].setPreviousNode(agent);
                }

                newFrontier.add(nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]]);
            }
        }

        if (agent.getCoordinates()[1] > 8){
            if (!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.START)){

                if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getValue() > agent.getValue() + 1){
                    nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].setValue(agent.getValue() + 1);
                    nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].setPreviousNode(agent);
                }

                newFrontier.add(nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]]);
            }
        }

        if (agent.getCoordinates()[0] < 867){
            if (!nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.START)){

                if (nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getValue() > agent.getValue() + 1){
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].setValue(agent.getValue() + 1);
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].setPreviousNode(agent);
                }

                newFrontier.add(nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1]);
            }
        }

        if (agent.getCoordinates()[0] > 6){
            if (!nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.START)){

                if (nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getValue() > agent.getValue() + 1){
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].setValue(agent.getValue() + 1);
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].setPreviousNode(agent);
                }

                newFrontier.add(nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1]);
            }
        }


        if (agent.getCoordinates()[1] < 701 && agent.getCoordinates()[0] < 867){
            if (!nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.START)){

                if (!nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER)
                    ||
                    !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER)){

                    if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getValue() > agent.getValue() + (float) 1.4){
                        nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].setValue(agent.getValue() + (float) 1.4);
                        nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].setPreviousNode(agent);
                    }

                    newFrontier.add(nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1]);
                }
            }
        }

        if (agent.getCoordinates()[1] > 8 && agent.getCoordinates()[0] > 6){
            if (!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.START)){

                if (!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER)
                    ||
                    !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER)){

                    if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].getValue() > agent.getValue() + (float) 1.4){
                        nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].setValue(agent.getValue() + (float) 1.4);
                        nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].setPreviousNode(agent);
                    }

                    newFrontier.add(nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1]);
                }
            }
        }

        if (agent.getCoordinates()[1] > 8 && agent.getCoordinates()[0] < 867){
            if (!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.START)){

                if ((!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER)
                    ||
                    !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER))
                    ){

                    if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getValue() > agent.getValue() + (float) 1.4){
                        nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].setValue(agent.getValue() + (float) 1.4);
                        nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].setPreviousNode(agent);
                    }

                    newFrontier.add(nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1]);
                }
            }
        }

        if (agent.getCoordinates()[1] < 701 && agent.getCoordinates()[0] > 6){
            if (!nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.START)){

                if (!nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER)
                    ||
                    !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER)
                    ){

                    if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].getValue() > agent.getValue() + (float) 1.4){
                        nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].setValue(agent.getValue() + (float) 1.4);
                        nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].setPreviousNode(agent);
                    }

                    newFrontier.add(nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1]);
                }
            }
        }

        return (ArrayList<Node>) newFrontier.stream().sorted(Comparator.comparing(Node::getValue)).collect(Collectors.toList());
    }
}
