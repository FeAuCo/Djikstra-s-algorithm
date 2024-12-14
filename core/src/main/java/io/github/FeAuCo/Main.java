package io.github.FeAuCo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.FeAuCo.algorithms.Djikstra;
import io.github.FeAuCo.node_related.Node;

import java.util.ArrayList;

import static io.github.FeAuCo.UserInputProcessor.nodesToRender;
import static io.github.FeAuCo.algorithms.Djikstra.batch;

public class Main extends ApplicationAdapter {
    private static Stage stage;

    private final static UserInputProcessor inputProcessor = new UserInputProcessor();
    private static boolean isInputProcessorSet = false;

    private static int fieldRenderCount = 0;
    static ArrayList<Node[]> nodes = new ArrayList<>();

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        
        createButtons();
        createField();
    }

    @Override
    public void render() {
        super.render();
        batch.begin();

        for (Node node : nodesToRender) {
            batch.draw(new Texture(node.getTexture()), node.getCoordinates()[0], node.getCoordinates()[1]);
        }

        if (GameStates.getChoiceAlgorithmState() != null) {
            if (!isInputProcessorSet) {
                Gdx.input.setInputProcessor(inputProcessor);
                isInputProcessorSet = true;
            }
            if (GameStates.getStartNode() != null && GameStates.isPlacedEnd()) {
                switch (GameStates.getChoiceAlgorithmState()) {
                    case "djikstra" -> Djikstra.run(nodes, GameStates.getStartNode());
                    ////            case "bfs" -> BFS.run(nodes, GameStates.getStartNode());
                    ////            case "dfs" -> DFS.run(nodes, GameStates.getStartNode());
                    ////            case "a*" -> A.run(nodes, GameStates.getStartNode());
                }
            }
        }

        stage.act();
        stage.draw();

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
        buttonDjikstra.setPosition(50,737);
        stage.addActor(buttonDjikstra);


        Button buttonBfs = new Button(skinBfs);
        buttonBfs.setPosition(300,737);
        stage.addActor(buttonBfs);


        Button buttonDfs = new Button(skinDfs);
        buttonDfs.setPosition(550,737);
        stage.addActor(buttonDfs);


        Button buttonA = new Button(skinA);
        buttonA.setPosition(800,737);
        buttonA.setWidth(48);
        buttonA.setHeight(32);
        stage.addActor(buttonA);


        buttonDjikstra.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStates.setChoiceAlgorithmState("djikstra");
                buttonDjikstra.setVisible(false);
                buttonBfs.setVisible(false);
                buttonDfs.setVisible(false);
                buttonA.setVisible(false);
            }
        });

        buttonBfs.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStates.setChoiceAlgorithmState("bfs");
                buttonDjikstra.setVisible(false);
                buttonBfs.setVisible(false);
                buttonDfs.setVisible(false);
                buttonA.setVisible(false);
            }
        });

        buttonDfs.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStates.setChoiceAlgorithmState("dfs");
                buttonDjikstra.setVisible(false);
                buttonBfs.setVisible(false);
                buttonDfs.setVisible(false);
                buttonA.setVisible(false);
            }
        });

        buttonA.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStates.setChoiceAlgorithmState("a*");
                buttonDjikstra.setVisible(false);
                buttonBfs.setVisible(false);
                buttonDfs.setVisible(false);
                buttonA.setVisible(false);
            }
        });
    }
}

