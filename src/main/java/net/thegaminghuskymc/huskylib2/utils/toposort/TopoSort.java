package net.thegaminghuskymc.huskylib2.utils.toposort;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Universal topological sorting, probably horrible.
 * This simple implementation follows the Wikipedia article on topological sorting.
 * <link>https://en.wikipedia.org/wiki/Topological_sort</link>
 * @param <E> The value type for this TopoSort
 */
public class TopoSort<E> {

    private List<TopoNode<E>> unsortedNodes = Lists.newArrayList();
    private List<TopoNode<E>> sortedNodes = Lists.newArrayList();

    public void addNode(TopoNode<E> node) {
        unsortedNodes.add(node);
    }

    public void addNodes(Collection<TopoNode<E>> nodes) {
        unsortedNodes.addAll(nodes);
    }

    public ImmutableList<TopoNode<E>> sort() {
        Set<TopoNode<E>> s = Sets.newHashSet();

        for(int i = 0; i < unsortedNodes.size(); i++) {
            TopoNode<E> n = unsortedNodes.get(i);

            if(n.getEdgesIn().size() == 0) {
                s.add(n);
            }
        }

        Iterator<TopoNode<E>> iterator = s.iterator();

        while(!s.isEmpty()) {
            TopoNode<E> n = s.iterator().next();
            s.remove(n);
            sortedNodes.add(n);

            for(Iterator<TopoEdge<E>> i = n.getEdgesOut().iterator(); i.hasNext();) {
                TopoEdge<E> e = i.next();
                TopoNode<E> m = e.getTo();
                i.remove();
                m.getEdgesIn().remove(e);

                if(m.getEdgesIn().isEmpty()) {
                    sortedNodes.add(m);
                }
            }
        }

        boolean cycle = false;

        for(TopoNode<E> n : unsortedNodes) {
            if(!n.getEdgesIn().isEmpty()) {
                cycle = true;
                break;
            }
        }

        if(cycle) {
            throw new TopoSortException("Could not finish sorting, cycle detected!");
        }
        else {
            return ImmutableList.copyOf(sortedNodes);
        }
    }

    public ImmutableList<TopoNode<E>> getUnsortedNodes() {
        return ImmutableList.copyOf(unsortedNodes);
    }

}