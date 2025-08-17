public class AlternatingThreads {

    private static final Object lock = new Object();

    private static volatile boolean isThread1Turn = true;

    private static volatile int thread1Count = 0;
    private static volatile int thread2Count = 0;

    public static void main(String[] args) {
        System.out.println("Программа с чередующимися потоками");
        System.out.println("Поток 1 выводит '1', Поток 2 выводит '2'");
        System.out.println("Начинаем с '1'...");
        System.out.println();

        // Поток 1 - выводит "1"
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!isThread1Turn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    System.out.print("1 ");
                    thread1Count++;

                    isThread1Turn = false;

                    lock.notify();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }, "Thread-1");

        // Поток 2 - выводит "2"
        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isThread1Turn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    System.out.print("2 ");
                    thread2Count++;

                    isThread1Turn = true;

                    lock.notify();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }, "Thread-2");

        // Запускаем потоки
        thread1.start();
        thread2.start();

    }
} 