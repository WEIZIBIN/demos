package github.weizibin.concurrency;

import java.util.concurrent.TimeUnit;

public class BlockingQueueImpl<T> implements BlockingQueue<T> {

    private static final int DEFAULT_SIZE = 64;

    private Object[] item;
    private int tail;
    private int head;
    private int size;

    public BlockingQueueImpl() {
        this(DEFAULT_SIZE);
    }

    public BlockingQueueImpl(int size) {
        item = new Object[size];
        tail = head = 0;
        this.size = size;
    }

    public void clear() {
        synchronized (this) {
            for (int i = 0; i < size; i++) {
                item[i] = null;
            }
            tail = head = 0;
        }
    }

    public T peek() {
        synchronized (this) {
            return (T) item[head];
        }
    }

    public void put(T t) throws InterruptedException {
        if (t == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            while (isFull()) {
                wait();
            }
            item[tail] = t;
            tail = (tail++) % size;
            notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (isEmpty()) {
                wait();
            }
            T t = (T) item[head];
            item[head] = null;
            head = (head + 1) % size;
            notifyAll();
            return t;
        }
    }

    public int size() {
        return size;
    }

    private boolean isEmpty() {
        synchronized (this) {
            return item[head] == null;
        }
    }

    private boolean isFull() {
        synchronized (this) {
            return item[tail] != null;
        }
    }

}

class Consumer<T> {
    public Consumer(BlockingQueue<T> queue) {

    }
}

class Provider<T> {

    private BlockingQueue<T> queue;

    public Provider(BlockingQueue<T> queue) {
        this.queue = queue;

    }

    private class ProvideTask<T> implements Runnable {
        @Override
        public void run() {
            
        }
    }

}

class Message {
    private String id;
    private String content;

    public Message(String id, String content) {
        this.id = id;
        this.content = content;
    }
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Object> blockingQueue = new BlockingQueueImpl<>(64);
        for (int i = 0; i < 65; i++) {
            blockingQueue.put(new Object());
        }
        for (int i = 0; i < 65; i++) {
            System.out.println(blockingQueue.(5, TimeUnit.SECONDS));
        }

    }
}

interface BlockingQueue<T> {
    void put(T t) throws InterruptedException;

    T poll() throws InterruptedException;

    T peek();

    int size();

    void clear();

}