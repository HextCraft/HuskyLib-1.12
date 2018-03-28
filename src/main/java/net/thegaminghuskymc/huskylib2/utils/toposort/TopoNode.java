package net.thegaminghuskymc.huskylib2.utils.toposort;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Universal topological sorting, probably horrible.
 * This simple implementation follows the Wikipedia article on topological sorting.
 * <link>https://en.wikipedia.org/wiki/Topological_sort</link>
 * @param <E> The value type for this TopoNode
 */
public class TopoNode<E> {

    private E value;
    private final Set<TopoEdge<E>> edgesIn;
    private final Set<TopoEdge<E>> edgesOut;

    private TopoNode(E value) {
        this.value = value;
        edgesIn = Sets.newHashSet();
        edgesOut = Sets.newHashSet();
    }

    public Set<TopoEdge<E>> getEdgesIn() {
        return edgesIn;
    }

    public Set<TopoEdge<E>> getEdgesOut() {
        return edgesOut;
    }

    public TopoNode<E> addEdge(TopoNode<E> node) {
        TopoEdge edge = TopoEdge.of(this, node);
        edgesOut.add(edge);
        node.edgesIn.add(edge);
        return this;
    }

    public E getValue() {
        return value;
    }

    public static <E> TopoNode<E> of(E value) {
        return new TopoNode<E>(value);
    }

}