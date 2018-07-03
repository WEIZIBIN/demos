package github.weizibin.concurrency;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class BlockingQueue<T> extends BlockingQueueAdapter<T> {

    private static final int DEFAULT_SIZE = 64;

    private Object[] item;
    private int tail;
    private int head;
    private int size;

    public BlockingQueue() {
        this(DEFAULT_SIZE);
    }

    public BlockingQueue(int size) {
        item = new Object[size];
        tail = head = 0;
        this.size = size;
    }
    
    public boolean add(T t) {
        if (offer(t)) {
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            if (item[tail] == null) {
                item[tail] = t;
                tail = (tail + 1) % size;
                notifyAll();
                return true;
            } else {
                return false;
            }
        }
    }

    public void put(T t) throws InterruptedException {
        if (t == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            while (item[tail] != null) {
                wait();
            }
            item[tail] = t;
            tail = (tail++) % size;
            notifyAll();
        }
    }

    public boolean offer(T t, long timeout, TimeUnit unit) throws InterruptedException {
        if (t == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            if (item[tail] != null) {
                wait(unit.toMillis(timeout));
            }
            if (offer(t)) {
                notifyAll();
                return true;
            } else {
                return false;
            }
        }
    }

    public T take() throws InterruptedException {
        synchronized (this) {
            while (item[head] == null) {
                wait();
            }
            T t = (T) item[head];
            item[head] = null;
            head = (head + 1) % size;
            notifyAll();
            return t;
        }
    }

    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        synchronized (this) {
            if (item[head] == null) {
                wait(unit.toMillis(timeout));
            }
            T t = (T) item[head];
            if (t != null) {
                item[head] = null;
                head = (head + 1) % size;
                notifyAll();
            }
            return t;
        }
    }

    public synchronized boolean isEmpty() {
        synchronized (this) {
            return item[head] == null;
        }
    }

    public synchronized boolean isFull() {
        synchronized (this) {
            return item[tail] = null;
        }
    }

    public int remainingCapacity() {
        return 0;
    }

    public int drainTo(Collection<? super T> c) {
        return 0;
    }

    public int drainTo(Collection<? super T> c, int maxElements) {
        return 0;
    }
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Object> blockingQueue = new BlockingQueue<Object>();
        for (int i = 0; i < 65; i++) {
            System.out.println(blockingQueue.offer(new Object()));
        }
        for (int i = 0; i < 65; i++) {
            System.out.println(blockingQueue.poll(5, TimeUnit.SECONDS));
        }

    }
}

abstract class BlockingQueueAdapter<T> implements java.util.concurrent.BlockingQueue<T> {

    public int size() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public T remove() {
        throw new UnsupportedOperationException();
    }

    public T poll() {
        throw new UnsupportedOperationException();
    }

    public T element() {
        throw new UnsupportedOperationException();
    }

    public T peek() {
        throw new UnsupportedOperationException();
    }
}
