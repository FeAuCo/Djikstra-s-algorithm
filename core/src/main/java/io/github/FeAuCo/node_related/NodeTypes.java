package io.github.FeAuCo.node_related;

public enum NodeTypes {
    START("start_node_dot.png"),
    EMPTY("empty_node_gray.png"),
    VISITED("visited_node_green.png"),
    BARRIER("barrier_node_brown.png"),
    END("end_node_cross.png");

    final String texture;

    NodeTypes(String texture){
        this.texture = texture;
    }

    public String getTexture() {
        return texture;
    }
}
