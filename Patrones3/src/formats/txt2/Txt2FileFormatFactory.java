package formats.txt2;

import formats.FileFormat;
import formats.FileFormatFactory;

public class Txt2FileFormatFactory implements FileFormatFactory {

    private static Txt2FileFormatFactory instance;

    private Txt2FileFormatFactory() {
        // singleton
    }

    public static Txt2FileFormatFactory getInstance() {
        if (instance == null) {
            synchronized (Txt2FileFormatFactory.class) {
                if (instance == null) {
                    instance = new Txt2FileFormatFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public FileFormat create() {
        return new Txt2FileFormat();
    }

    @Override
    public String getExtensionName() {
        return "tx2";
    }
}
