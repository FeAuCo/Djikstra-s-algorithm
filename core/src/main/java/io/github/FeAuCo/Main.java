package io.github.FeAuCo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.FeAuCo.node_related.Node;
import io.github.FeAuCo.node_related.NodeTypes;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private boolean fieldDone = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        batch.begin();
        if (!fieldDone) {
            ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
            createField();
            fieldDone = true;
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }


    private void createField(){
        int coordinateX = 6;
        int coordinateY = 8;
        int[] coordinates = {6, 8};
        ArrayList<Node> nodes = new ArrayList<>();

        while (coordinateY != 722){
            while (coordinateX != 888) {
                coordinates[0] = coordinateX;

                Node node = new Node(NodeTypes.EMPTY, coordinates);
                node.setValue(0);
                nodes.add(node);
                batch.draw(new Texture(node.getTexture()), coordinateX, coordinateY);
                coordinateX += 21;
            }
            coordinateX = 6;
            coordinateY += 21;
            coordinates[1] = coordinateY;
        }
    }
}

