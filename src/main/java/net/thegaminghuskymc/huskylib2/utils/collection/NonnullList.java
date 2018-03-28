package net.thegaminghuskymc.huskylib2.utils.collection;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nonnull;
import java.util.AbstractList;
import java.util.List;

public class NonnullList<E> extends AbstractList<E> {

    private final List<E> delegate;
    private final E defaultElement;

    private NonnullList(List<E> delegate) {
        this.delegate = delegate;
        defaultElement = null;
    }

    private NonnullList(@Nonnull E defaultElement) {
        delegate = Lists.newArrayList();
        this.defaultElement = defaultElement;
    }

    private NonnullList() {
        delegate = Lists.newArrayList();
        defaultElement = null;
    }

    @Nonnull
    @Override
    public E get(int index) {
        return delegate.get(index);
    }

    @Override
    public E set(int index, E element) {
        Validate.notNull(element);
        return delegate.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        Validate.notNull(element);
        delegate.add(index, element);
    }

    @Override
    public E remove(int index) {
        return delegate.remove(index);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public void clear() {
        if (defaultElement == null) {
            super.clear();
        }
        else {
            for (int i = 0; i < size(); ++i) {
                set(i, defaultElement);
            }
        }
    }

    public static <E> NonnullList<E> newNonnullList(E defaultElement) {
        return new NonnullList<E>(defaultElement);
    }

    public static <E> NonnullList<E> newNonnullList() {
        return new NonnullList<E>();
    }

    public static <E> NonnullList<E> fromList(List<E> list) {
        return new NonnullList<E>(list);
    }

}