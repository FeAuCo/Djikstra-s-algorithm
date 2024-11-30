package io.github.FeAuCo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.FeAuCo.algorithms.Djikstra;
import io.github.FeAuCo.node_related.Node;
import io.github.FeAuCo.node_related.NodeTypes;

import java.util.ArrayList;

import static io.github.FeAuCo.algorithms.Djikstra.batch;
import static io.github.FeAuCo.algorithms.Djikstra.run;

public class Main extends ApplicationAdapter {
    private static Stage stage;

    private int fieldRenderCount = 0;
    static ArrayList<Node[]> nodes = new ArrayList<>();

    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(false);

        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        createField();
        createButtons();

//        switch (GameStates.getChoiceAlgorithmState()) {
//            case "djikstra" -> Djikstra.run(nodes, GameStates.getStartNode());
//////            case "bfs" -> BFS.run(nodes, GameStates.getStartNode());
//////            case "dfs" -> DFS.run(nodes, GameStates.getStartNode());
//////            case "a*" -> A.run(nodes, GameStates.getStartNode());
//        }
    }

    @Override
    public void render() {
        Gdx.graphics.requestRendering();
        stage.act();
        stage.draw();

        batch.begin();
        if (fieldRenderCount != 2) {
            ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
            renderNodes();
            fieldRenderCount += 1;
        }
        batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    public static void renderNodes(){
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

    private static void createButtons(){
        Skin skinDjikstra = new Skin(Gdx.files.internal("skins/djikstra.json"));
        Skin skinBfs = new Skin(Gdx.files.internal("skins/bfs.json"));
        Skin skinDfs = new Skin(Gdx.files.internal("skins/dfs.json"));
        Skin skinA = new Skin(Gdx.files.internal("skins/a.json"));

        Gdx.input.setInputProcessor(stage);

        Button buttonDjikstra = new Button(skinDjikstra);
        buttonDjikstra.setPosition(10,10);
        stage.addActor(buttonDjikstra);

        buttonDjikstra.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStates.setChoiceAlgorithmState("djikstra");
            }
        });


        Button buttonBfs = new Button(skinBfs);
        buttonBfs.setPosition(10,10);
        stage.addActor(buttonBfs);

        buttonBfs.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStates.setChoiceAlgorithmState("bfs");
            }
        });


        Button buttonDfs = new Button(skinDfs);
        buttonDfs.setPosition(10,10);
        stage.addActor(buttonDfs);

        buttonBfs.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStates.setChoiceAlgorithmState("dfs");
            }
        });


        Button buttonA = new Button(skinA);
        buttonA.setPosition(10,10);
        buttonA.setWidth(48);
        buttonA.setHeight(32);
        stage.addActor(buttonA);

        buttonA.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStates.setChoiceAlgorithmState("a*");
            }
        });
    }
}

