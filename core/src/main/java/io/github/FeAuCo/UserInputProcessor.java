package io.github.FeAuCo;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import io.github.FeAuCo.node_related.Node;
import io.github.FeAuCo.node_related.NodeTypes;

import java.util.ArrayList;

import static io.github.FeAuCo.Main.nodes;
import static io.github.FeAuCo.algorithms.Djikstra.batch;

public class UserInputProcessor implements InputProcessor {
    public static ArrayList<Node> nodesToRender = new ArrayList<>();

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
        if (GameStates.getStartNode() == null || !GameStates.isPlacedEnd()){
            for (Node[] nodeArray : nodes){
                for (Node node : nodeArray){
                    if (node.getCoordinates()[0] <= screenX && node.getCoordinates()[0] + 16 >= screenX
                        &&
                        node.getCoordinates()[1] <= screenY && node.getCoordinates()[1] + 16 >= screenY){

                        if (GameStates.getStartNode() == null) {
                            node.setNodeType(NodeTypes.START);
                            node.setValue(0);
                            GameStates.setStartNode(node.clone());
                        }
                        else {
                            node.setNodeType(NodeTypes.END);
                            GameStates.setPlacedEnd(true);
                        }

                        if (GameStates.isPlaceBarrier()){
                            node.setNodeType(NodeTypes.BARRIER);
                        }

                        batch.draw(new Texture(node.getTexture()), node.getCoordinates()[0], node.getCoordinates()[1]);
                        nodesToRender.add(node.clone());
                    }
                }
            }
        }

        batch.end();
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
