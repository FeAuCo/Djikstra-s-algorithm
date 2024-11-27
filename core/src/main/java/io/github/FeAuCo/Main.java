package io.github.FeAuCo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.FeAuCo.node_related.Node;
import io.github.FeAuCo.node_related.NodeTypes;

import java.util.ArrayList;

import static io.github.FeAuCo.algorithms.Djikstra.batch;
import static io.github.FeAuCo.algorithms.Djikstra.run;

public class Main extends ApplicationAdapter {
    private int fieldRenderCount = 0;
    static ArrayList<Node[]> nodes = new ArrayList<>();

    @Override
    public void create() {
        batch = new SpriteBatch();
        createField();

        Gdx.graphics.setContinuousRendering(false);

        Gdx.graphics.requestRendering();

        batch.begin();


        batch.end();
    }

    @Override
    public void render() {
        batch.begin();
        if (fieldRenderCount != 2) {
            ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
            Gdx.graphics.requestRendering();

            fieldRenderCount += 1;
        }
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static void renderNodes(SpriteBatch batch){
        for (Node[] nodeArray : nodes){
            for (Node node : nodeArray){
                Gdx.graphics.requestRendering();
                batch.draw(new Texture(node.getTexture()), node.getCoordinates()[0], node.getCoordinates()[1]);
            }
        }
    }

    private static void createField(){
        int coordinateX = 6;
        int coordinateY = 8;
        int nodeIndexX = 0;
        int nodeIndexY = 0;
        int[] coordinates = {6, 8};
        Node[] innerNodes = new Node[42];

        while (coordinateY != 722){
            coordinates[1] = coordinateY;

            while (coordinateX != 888) {
                coordinates[0] = coordinateX;

                int[] indices = {nodeIndexX, nodeIndexY};

                Node node = new Node(coordinates, indices);

                innerNodes[nodeIndexX] = node;
                nodeIndexX += 1;

                coordinateX += 21;

                coordinates = coordinates.clone();
            }
            nodes.add(innerNodes);

            coordinateX = 6;
            coordinateY += 21;

            innerNodes = innerNodes.clone();

            nodeIndexX = 0;
            nodeIndexY += 1;
        }
    }
}

