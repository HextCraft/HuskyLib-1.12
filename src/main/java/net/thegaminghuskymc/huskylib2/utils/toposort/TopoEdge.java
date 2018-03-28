package net.thegaminghuskymc.huskylib2.utils.toposort;

/**
 * Universal topological sorting, probably horrible.
 * This simple implementation follows the Wikipedia article on topological sorting.
 * <link>https://en.wikipedia.org/wiki/Topological_sort</link>
 * @param <E> The value type for this TopoEdge
 */
public class TopoEdge<E> {

    private final TopoNode<E> from;
    private final TopoNode<E> to;

    private TopoEdge(TopoNode<E> from, TopoNode<E> to) {
        this.from = from;
        this.to = to;
    }

    public TopoNode<E> getFrom() {
        return from;
    }

    public TopoNode<E> getTo() {
        return to;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if(o instanceof TopoEdge) {
            TopoEdge<E> edge = (TopoEdge<E>)o;
            return edge.from == from && edge.to == to;
        }

        return false;
    }

    public static <E> TopoEdge of(TopoNode<E> from, TopoNode<E> to) {
        return new TopoEdge(from, to);
    }

}