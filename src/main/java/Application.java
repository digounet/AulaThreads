import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Application {

    private static int numero;

    public static void main(String[] args) throws InterruptedException {

        //threads();
        //serial();1 + 2 + 3 + 4
        //var soma = IntStream.range(1, 5).sum();
        var s = "   Pablo Rodrifgo ";
        System.out.println("Soma" + s.strip()) ;
        //paralelismo();
    }

    private static void serial() {
        Instant ini = Instant.now();

        try {
            Thread.sleep(5000);
            System.out.println("Duração de 5s");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(7000);
            System.out.println("Duração de 7s");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Instant fim = Instant.now();
        Duration duration = Duration.between(ini, fim);
        System.out.println("Duração total: " + duration.toSeconds() + "s");
    }

    private static void paralelismo() throws InterruptedException {

        Instant ini = Instant.now();

        Contador contador = new Contador();

        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(contador);
        es.execute(contador);
        es.execute(contador);
        es.shutdown();
        es.awaitTermination(50, TimeUnit.SECONDS);

        Instant fim = Instant.now();
        Duration duration = Duration.between(ini, fim);
        System.out.println("Duração total: " + duration.toSeconds() + "s");
    }

    private static void threads() {
        var contador = new Contador();
        Thread[] threads = new Thread[5];

        Instant ini = Instant.now();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(contador);
            threads[i].start();
        }
    }

    public static class Contador implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (this) {
                numero++;
                System.out.println(Thread.currentThread().getName() + ": " + numero);
            }

        }
    }


}
