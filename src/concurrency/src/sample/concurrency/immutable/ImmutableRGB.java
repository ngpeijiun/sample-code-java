package sample.concurrency.immutable;

/**
 * Created by eugenis on 25/4/16.
 */
public class ImmutableRGB {

    private final int red;
    private final int green;
    private final int blue;
    private final String name;

    public ImmutableRGB(int red, int green, int blue, String name) {
        this.check(red, green, blue);

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public int getRGB() {
        return this.red << 16 | this.green << 8 | this.blue;
    }

    public String getName() {
        return this.name;
    }

    public ImmutableRGB invert() {
        return new ImmutableRGB(255 - this.red,
                255 - this.green,
                255 - this.blue,
                String.format("Inverse of %s", this.name));
    }

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 ||
                green < 0 || green > 255 ||
                blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

}
