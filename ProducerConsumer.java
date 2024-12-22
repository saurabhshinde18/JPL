import java.util.LinkedList;

class SharedBuffer {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 2;

    public synchronized void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            while (list.size() == capacity) {
                wait();
            }
            System.out.println("Producer produced: " + value);
            list.add(value++);
            notify();
            Thread.sleep(1000);
        }
    }

    public synchronized void consume() throws InterruptedException {
        while (true) {
            while (list.isEmpty()) {
                wait();
            }
            int value = list.removeFirst();
            System.out.println("Consumer consumed: " + value);
            notify();
            Thread.sleep(1000);
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            try {
                buffer.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                buffer.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
