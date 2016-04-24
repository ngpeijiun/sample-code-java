package sample.concurrency.immutable;

/**
 * Created by eugenis on 25/4/16.
 */
public class ConcurrentRGB {

    private int red;
    private int green;
    private int blue;
    private String name;

    public ConcurrentRGB(int red, int green, int blue, String name) {
        this.set(red, green, blue, name);
    }

    public synchronized int getRGB() {
        return this.red << 16 | this.green << 8 | this.blue;
    }

    public synchronized String getName() {
        return this.name;
    }

    public void set(int red, int green, int blue, String name) {
        this.check(red, green, blue);

        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public synchronized void invert() {
        this.red = 255 - this.red;
        this.green = 255 - this.green;
        this.blue = 255 - this.blue;
        name = String.format("Inverse of %s", this.name);
    }

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 ||
                green < 0 || green > 255 ||
                blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

}
