package com.airwallex.rpn.core.buffer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/12
 * @content:
 */
public abstract class AbstractStack<T, K> implements Stack<T, K> {
    protected HashMap<T, Deque<K>> dequeMap;

    public AbstractStack(HashMap<T, Deque<K>> dequeMap) {
        this.dequeMap = dequeMap;
    }

    public AbstractStack(int length) {
        this.dequeMap = new HashMap<>(length);
    }

    abstract Deque<K> newDeque();

    @Override
    public void push(final T identity, final K value) {
        if (!dequeMap.containsKey(identity)){
            dequeMap.put(identity, newDeque());
        }

        dequeMap.get(identity).push(value);
    }

    @Override
    public @Nullable K pop(final T identity) {
        if (!dequeMap.containsKey(identity)){
            return null;
        }

        return dequeMap.get(identity).pop();
    }

    @Override
    public void clear(@NotNull T identity) {
        if (!dequeMap.containsKey(identity)){
            return;
        }

        Deque<K> deque = dequeMap.get(identity);

        while(!deque.isEmpty()){
            deque.pop();
        }
    }

    @Override
    public @NotNull List<K> clearWithReturn(@NotNull T identity) {
        List<K> ret= new LinkedList<>();
        if (!dequeMap.containsKey(identity)){
            return ret;
        }

        Deque<K> deque = dequeMap.get(identity);

        while(!deque.isEmpty()){
            ret.add(deque.pop());
        }

        return ret;
    }

    @Override
    public void clean(T identity) {
        if (!dequeMap.containsKey(identity)){
            return;
        }

        // caution: make sure the deque has other no strong reference, otherwise might cause memory leak
        dequeMap.remove(identity);
    }

    @Override
    public int size(T identity) {
        if (!dequeMap.containsKey(identity)){
            return 0;
        }

        return dequeMap.get(identity).size();
    }

    @Override
    public boolean isEmpty(T identity) {
        if (!dequeMap.containsKey(identity)){
            return true;
        }

        return dequeMap.get(identity).isEmpty();
    }

    @Override
    public String toString(T identity) {
        if (!dequeMap.containsKey(identity)){
            return "";
        }

        Deque<K> deque = dequeMap.get(identity);

        StringJoiner stringJoiner = new StringJoiner(" ");
        Iterator<K> iterator = deque.descendingIterator();
        while(iterator.hasNext()){
            stringJoiner.add(iterator.next().toString());
        }

        return stringJoiner.toString();
    }
}
