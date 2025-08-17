public class Deadlock {

    // Два ресурса (объекты для блокировки)
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        System.out.println("Демонстрация DeadLock");
        System.out.println("Запуск двух потоков, которые могут вызвать DeadLock...");

        // Первый поток
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Поток 1: Заблокировал resource1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Поток 1: Пытается заблокировать resource2...");
                synchronized (resource2) {
                    System.out.println("Поток 1: Заблокировал resource2");
                }
            }
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Поток 2: Заблокировал resource2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Поток 2: Пытается заблокировать resource1...");
                synchronized (resource1) {
                    System.out.println("Поток 2: Заблокировал resource1");
                }
            }
        }, "Thread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Программа завершена (если вы видите это сообщение, DeadLock не произошел)");
    }
} 