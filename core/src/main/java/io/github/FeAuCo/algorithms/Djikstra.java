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

        agents.add(startNode);

        boolean end = false;

        while (!end){
            for (Node agent : agents){
                if (agent.getNodeType().equals(NodeTypes.END)){
                    end = true;
                    break;
                }

                frontier.addAll(updateFrontier(nodes, agent));

                if (!agent.getNodeType().equals(NodeTypes.START) && !agent.getNodeType().equals(NodeTypes.END)) {
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0]].setNodeType(NodeTypes.VISITED);
                }
            }

            agents.clear();

            if (frontier.isEmpty()){
                // NO ROUTE
                break;
            }

            for (Node node : frontier){
                if (node.getPreviousNode() != null) {
                    node.setPreviousNode(node.getPreviousNode().clone());
                }
                agents.add(node.clone());
            }

            frontier.clear();
        }


//        Node agent = nodes.get(4)[0].getPreviousNode();
//
//        while (true){
//            if (agent.getPreviousNode() == null){
//                break;
//            }
//            nodes.get(agent.getIndices()[1])[agent.getIndices()[0]].setNodeType(NodeTypes.PATH);
//            agent = agent.getPreviousNode().clone();
//        }
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

                nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].setPreviousNode(agent);

                if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getValue() > agent.getValue() + 1){
                    nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].setValue(agent.getValue() + 1);
                }

                newFrontier.add(nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]]);
            }
        }

        if (agent.getCoordinates()[1] > 8){
            if (!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.START)){

                nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].setPreviousNode(agent);

                if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getValue() > agent.getValue() + 1){
                    nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].setValue(agent.getValue() + 1);
                }

                newFrontier.add(nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]]);
            }
        }

        if (agent.getCoordinates()[0] < 867){
            if (!nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.START)){

                nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].setPreviousNode(agent);

                if (nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getValue() > agent.getValue() + 1){
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].setValue(agent.getValue() + 1);
                }

                newFrontier.add(nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1]);
            }
        }

        if (agent.getCoordinates()[0] > 6){
            if (!nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.START)){

                nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].setPreviousNode(agent);

                if (nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getValue() > agent.getValue() + 1){
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].setValue(agent.getValue() + 1);
                }

                newFrontier.add(nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1]);
            }
        }


        if (agent.getCoordinates()[1] < 701 && agent.getCoordinates()[0] < 867){
            if (!nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.START)){

                if (!nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER)
                    &&
                    !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER)){

                    nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].setPreviousNode(agent);

                    if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getValue() > agent.getValue() + (float) 1.4){
                        nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].setValue(agent.getValue() + (float) 1.4);
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
                    &&
                    !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER)){

                    nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].setPreviousNode(agent);

                    if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].getValue() > agent.getValue() + (float) 1.4){
                        nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].setValue(agent.getValue() + (float) 1.4);
                    }

                    newFrontier.add(nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1]);
                }
            }
        }

        if (agent.getCoordinates()[1] > 8 && agent.getCoordinates()[0] < 867){
            if (!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.VISITED) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER) &&
                !nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.START)){

                if (!nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.BARRIER)
                    &&
                    !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.BARRIER)){

                    nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].setPreviousNode(agent);

                    if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getValue() > agent.getValue() + (float) 1.4){
                        nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].setValue(agent.getValue() + (float) 1.4);
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
                    &&
                    !nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.BARRIER)){

                    nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].setPreviousNode(agent);

                    if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].getValue() > agent.getValue() + (float) 1.4){
                        nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].setValue(agent.getValue() + (float) 1.4);
                    }

                    newFrontier.add(nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1]);
                }
            }
        }

        return (ArrayList<Node>) newFrontier.stream().sorted(Comparator.comparing(Node::getValue)).collect(Collectors.toList());
    }
}
