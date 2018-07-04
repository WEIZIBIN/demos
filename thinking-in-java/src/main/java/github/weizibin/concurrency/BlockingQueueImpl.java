package github.weizibin.concurrency;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Override
    public int size() {
        return tail > head ? tail - head :
                tail == head ? item[tail] == null ?  0 : size : size - head + tail;
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
            tail = (tail + 1) % size;
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

class MessageConsumer {
    private BlockingQueue<Message> queue;
    private ExecutorService executorService;

    public MessageConsumer(BlockingQueue<Message> queue) {
        this.queue = queue;
        executorService= Executors.newSingleThreadExecutor();
        executorService.submit(new MessageConsumeTask());
    }

    private class MessageConsumeTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Message message = queue.poll();
                    System.out.println("" + Thread.currentThread() + " " + message + " dequeue");
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class MessageProvider {

    private BlockingQueue<Message> queue;
    private ExecutorService executorService;
    private int count;

    public MessageProvider(BlockingQueue<Message> queue) {
        this.queue = queue;
        executorService= Executors.newSingleThreadExecutor();
        executorService.submit(new MessageProvideTask());
    }

    private Message provide() {
        return new Message(String.valueOf(new Date().getTime()), "Message" + (++count));
    }

    private class MessageProvideTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                Message message = provide();
                try {
                    queue.put(message);
                    System.out.println("" + Thread.currentThread() + " " + message + " enqueue");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
class QueueInspector<T> {

    private BlockingQueue<T> queue;
    private ExecutorService executorService;

    public QueueInspector(BlockingQueue<T> queue) {
        this.queue = queue;
        executorService= Executors.newSingleThreadExecutor();
        executorService.submit(new QueueInspectTask());
    }

    private class QueueInspectTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(String.format("%s queue size[%s]",
                            Thread.currentThread(), queue.size(), queue.peek()));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

class Message {
    private String timestamp;
    private String content;

    public Message(String timestamp, String content) {
        this.timestamp = timestamp;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp='" + timestamp + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Message> blockingQueue = new BlockingQueueImpl<>(16);
        new MessageProvider(blockingQueue);
        new MessageConsumer(blockingQueue);
        new MessageConsumer(blockingQueue);
        new QueueInspector<Message>(blockingQueue);
    }
}

interface BlockingQueue<T> {
    void put(T t) throws InterruptedException;

    T poll() throws InterruptedException;

    T peek();

    void clear();

    int size();
}