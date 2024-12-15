package io.github.FeAuCo.algorithms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.FeAuCo.node_related.Node;
import io.github.FeAuCo.node_related.NodeTypes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static io.github.FeAuCo.Main.nodesToRender;

public class Djikstra {
    public static SpriteBatch batch;

    private static final ArrayList<Node> frontier = new ArrayList<>();
    private static ArrayList<Node> agents = new ArrayList<>();

    private static Node endNode = new Node(null, null);

    private static boolean end = false;
    private static boolean noRoute = false;

    public static void runAlg(ArrayList<Node[]> nodes, Node startNode) {
        agents.add(startNode);

        while (!end) {
            for (Node agent : agents) {
                if (updateFrontier(nodes, agent) != null) {
                    frontier.addAll(updateFrontier(nodes, agent));
                }

                if (!agent.getNodeType().equals(NodeTypes.START) && !agent.getNodeType().equals(NodeTypes.END)) {
                    nodes.get(agent.getIndices()[1])[agent.getIndices()[0]].setNodeType(NodeTypes.VISITED);
                    nodesToRender.add(nodes.get(agent.getIndices()[1])[agent.getIndices()[0]]);
                    batch.draw(new Texture(nodes.get(agent.getIndices()[1])[agent.getIndices()[0]].getTexture()), nodes.get(agent.getIndices()[1])[agent.getIndices()[0]].getCoordinates()[0], nodes.get(agent.getIndices()[1])[agent.getIndices()[0]].getCoordinates()[1]);
                }

                if (updateFrontier(nodes, agent) == null){
                    break;
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
            while (endNode.getPreviousNode() != null) {
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

                if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.END)){
                    if (!agent.getNodeType().equals(NodeTypes.START)) {
                        endNode = agent.getPreviousNode();
                    }
                    end = true;
                    return null;
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

                if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0]].getNodeType().equals(NodeTypes.END)){
                    if (!agent.getNodeType().equals(NodeTypes.START)) {
                        endNode = agent.getPreviousNode();
                    }
                    end = true;
                    return null;
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

                if (nodes.get(agent.getIndices()[1])[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.END)){
                    if (!agent.getNodeType().equals(NodeTypes.START)) {
                        endNode = agent.getPreviousNode();
                    }
                    end = true;
                    return null;
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

                if (nodes.get(agent.getIndices()[1])[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.END)){
                    if (!agent.getNodeType().equals(NodeTypes.START)) {
                        endNode = agent.getPreviousNode();
                    }
                    end = true;
                    return null;
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

                    if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.END)){
                        if (!agent.getNodeType().equals(NodeTypes.START)) {
                            endNode = agent.getPreviousNode();
                        }
                        end = true;
                        return null;
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

                    if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.END)){
                        if (!agent.getNodeType().equals(NodeTypes.START)) {
                            endNode = agent.getPreviousNode();
                        }
                        end = true;
                        return null;
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

                    if (nodes.get(agent.getIndices()[1] - 1)[agent.getIndices()[0] + 1].getNodeType().equals(NodeTypes.END)){
                        if (!agent.getNodeType().equals(NodeTypes.START)) {
                            endNode = agent.getPreviousNode();
                        }
                        end = true;
                        return null;
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

                    if (nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1].getNodeType().equals(NodeTypes.END)){
                        if (!agent.getNodeType().equals(NodeTypes.START)) {
                            endNode = agent.getPreviousNode();
                        }
                        end = true;
                        return null;
                    }

                    newFrontier.add(nodes.get(agent.getIndices()[1] + 1)[agent.getIndices()[0] - 1]);
                }
            }
        }

        return (ArrayList<Node>) newFrontier.stream().sorted(Comparator.comparing(Node::getValue)).collect(Collectors.toList());
    }
}
