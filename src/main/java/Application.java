public class Application {

    private static int numero;

    public static void main(String[] args) {
        var contador = new Contador();

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(contador);
            threads[i].start();
        }
    }

    public static class Contador implements Runnable {

        @Override
        public void run() {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            numero++;
            System.out.println(Thread.currentThread().getName() + ": " + numero);
        }
    }


}
