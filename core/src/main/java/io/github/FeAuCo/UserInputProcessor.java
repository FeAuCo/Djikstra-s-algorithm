package io.github.FeAuCo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import io.github.FeAuCo.node_related.Node;
import io.github.FeAuCo.node_related.NodeTypes;

import static io.github.FeAuCo.Main.nodes;
import static io.github.FeAuCo.Main.nodesToRender;
import static io.github.FeAuCo.algorithms.Djikstra.batch;

public class UserInputProcessor implements InputProcessor {
    private boolean dragging;

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = 800 - screenY;

        batch.begin();
        if (!GameStates.isPlacedEnd() && button == Input.Buttons.LEFT){
            for (Node[] nodeArray : nodes){
                for (Node node : nodeArray){
                    if (node.getCoordinates()[0] <= screenX && node.getCoordinates()[0] + 16 >= screenX
                        &&
                        node.getCoordinates()[1] <= screenY && node.getCoordinates()[1] + 16 >= screenY){

                        if (GameStates.getStartNode() == null) {
                            node.setNodeType(NodeTypes.START);
                            node.setValue(0);
                            GameStates.setStartNode(node.clone());
                            nodesToRender.add(node.clone());
                        }
                        else if (!GameStates.isPlaceBarrier()) {
                            if (!node.getNodeType().equals(NodeTypes.START)) {
                                node.setNodeType(NodeTypes.END);
                                GameStates.setPlacedEnd(true);
                                nodesToRender.add(node.clone());
                            }
                        }
                        else {
                            if (!node.getNodeType().equals(NodeTypes.START) && GameStates.isPlacedBarrier()) {
                                node.setNodeType(NodeTypes.END);
                                GameStates.setPlacedEnd(true);
                                nodesToRender.add(node.clone());
                            }
                        }

                        if (GameStates.getStartNode() != null && GameStates.isPlaceBarrier() && !GameStates.isPlacedBarrier()){
                            node.setNodeType(NodeTypes.BARRIER);
                            nodesToRender.add(node.clone());
                            GameStates.setPlacedBarrier(true); //
                        }
                    }
                }
            }
        }

        if (GameStates.getStartNode() != null && !GameStates.isPlacedEnd() && button == Input.Buttons.RIGHT){
            GameStates.setPlaceBarrier(true);
        }

        if (GameStates.getStartNode() != null && !GameStates.isPlacedEnd() && GameStates.isPlaceBarrier() && button == Input.Buttons.RIGHT){
            GameStates.setPlacedBarrier(true);
        }

        batch.end();

        dragging = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!dragging){
            return false;
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return true;
    }
}
