package net.thegaminghuskymc.huskylib2.utils.toposort;

public class TopoSortException extends RuntimeException {

    public TopoSortException(String message, Object... params) {
        super(String.format(message, params));
    }

}