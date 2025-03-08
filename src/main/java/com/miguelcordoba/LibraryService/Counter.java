package com.miguelcordoba.LibraryService;

public class Counter {
        private static int counter = 1;
        private static int limit = 100;

        public static void main(String[] args) throws InterruptedException {
            countTo100();
        }
    public static void countTo100() throws InterruptedException {

        Object lock  = new Object();

        Thread odd = new Thread(
                () -> {
                    while(counter <= limit){
                        synchronized (lock) {
                            if(counter % 2 != 0) {
                                System.out.println("odd: " + counter);
                                counter++;
                                lock.notify();
                            } else {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
        );

        Thread even = new Thread(() -> {
            while(counter <= limit){
                synchronized (lock) {
                    if(counter % 2 == 0) {
                        System.out.println("even: " + counter);
                        counter++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        odd.start();
        even.start();
    }

}
