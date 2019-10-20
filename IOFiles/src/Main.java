import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String example = "ы";
        byte[] bytes = example.getBytes();
        System.out.println(Arrays.toString(bytes));

        String s = "Ы";
        byte[] b = s.getBytes();
        for (int i = 0; i < b.length; i++) {
            System.out.print(((int) b[i] ^ -1 << 8) + " ");
        }
    }

    private static void test() throws IOException {
        System.out.println("5.2 Потоки байт – Шаг 9");
        byte[] test = new byte[]{65, 13, 10, 10, 13};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(10);

        convert(new ByteArrayInputStream(test), outputStream);
        byte[] out = outputStream.toByteArray();
        for (int i : out) {
            System.out.println(i);
        }
    }

    private static void convert(InputStream inputStream, OutputStream outputStream) throws IOException {
        int buf1 = inputStream.read();
        int buf2;

        while (buf1 != -1) {
            buf2 = inputStream.read();
            if (!(buf2 == 10 && buf1 == 13)) {
                outputStream.write(buf1);
            }
            buf1 = buf2;
        }
        outputStream.flush();
    }

    public static InputStream getStream(byte[] data) {
        return new ByteArrayInputStream(data);
    }

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int result = 0;
        int n = inputStream.read();
        while (n > -1) {
            result = Integer.rotateLeft(result, 1) ^ n;
            n = inputStream.read();
        }
        return result;
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        String retval;
        Reader reader = new InputStreamReader(inputStream, charset);
        StringWriter r = new StringWriter();
        int b;
        while ((b = reader.read()) != -1) {
            r.write(b);
        }
        retval = r.toString();
        return retval;
    }

    public static void Scan() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH);
        double d;
        double sum = 0.0;
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                d = scanner.nextDouble();
                sum += d;
            } else {
                scanner.next();
            }
        }
        System.out.printf(Locale.ENGLISH, "%.6f", sum);
    }
//    public static Animal[] deserializeAnimalArray(byte[] data) {
//        Animal[] animals=null;
//        try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))){
//            Integer numAnimals = ois.readInt();
//            animals = new Animal[numAnimals];
//            for (int i = 0; i < numAnimals; i++) {
//                animals[i] = (Animal) ois.readObject();
//            }
//        } catch (EOFException e) {
//            throw new IllegalArgumentException(e);
//        } catch (ClassNotFoundException e) {
//            throw new IllegalArgumentException(e);
//        } catch (IOException e) {
//            throw new IllegalArgumentException(e);
//        } catch (ClassCastException e){
//            throw new IllegalArgumentException(e);
//        }
//        return animals;
//    }

}
