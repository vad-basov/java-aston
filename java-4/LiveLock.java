public class LiveLock {

    private static volatile boolean worker1Busy = false;
    private static volatile boolean worker2Busy = false;

    private static volatile int worker1Attempts = 0;
    private static volatile int worker2Attempts = 0;

    public static void main(String[] args) {
        System.out.println("Демонстрация LiveLock");
        System.out.println("Запуск двух потоков, которые могут вызвать LiveLock...");

        Thread worker1 = new Thread(() -> {
            while (worker1Attempts < 10) {
                worker1Attempts++;
                System.out.println("Работник 1: Попытка " + worker1Attempts + " - проверяю, занят ли работник 2");

                if (!worker2Busy) {
                    System.out.println("Работник 1: Работник 2 не занят, но я вежливо уступаю");
                    worker1Busy = true;

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }

                    worker1Busy = false;
                    System.out.println("Работник 1: Освободил ресурс");
                } else {
                    System.out.println("Работник 1: Работник 2 занят, жду...");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            System.out.println("Работник 1: Завершил работу после " + worker1Attempts + " попыток");
        }, "Worker-1");

        Thread worker2 = new Thread(() -> {
            while (worker2Attempts < 10) {
                worker2Attempts++;
                System.out.println("Работник 2: Попытка " + worker2Attempts + " - проверяю, занят ли работник 1");

                if (!worker1Busy) {
                    System.out.println("Работник 2: Работник 1 не занят, но я вежливо уступаю");
                    worker2Busy = true;

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }

                    worker2Busy = false;
                    System.out.println("Работник 2: Освободил ресурс");
                } else {
                    System.out.println("Работник 2: Работник 1 занят, жду...");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            System.out.println("Работник 2: Завершил работу после " + worker2Attempts + " попыток");
        }, "Worker-2");

        worker1.start();
        worker2.start();

        try {
            worker1.join();
            worker2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Результат");
        System.out.println("Работник 1 выполнил " + worker1Attempts + " попыток");
        System.out.println("Работник 2 выполнил " + worker2Attempts + " попыток");
    }
} 