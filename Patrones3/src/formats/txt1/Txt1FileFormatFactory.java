package formats.txt1;

import formats.FileFormat;
import formats.FileFormatFactory;

public class Txt1FileFormatFactory implements FileFormatFactory {

    private static Txt1FileFormatFactory instance;

    private Txt1FileFormatFactory() {
        // singleton
    }

    public static Txt1FileFormatFactory getInstance() {
        if (instance == null) {
            synchronized (Txt1FileFormatFactory.class) {
                if (instance == null) {
                    instance = new Txt1FileFormatFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public FileFormat create() {
        return new Txt1FileFormat();
    }

    @Override
    public String getExtensionName() {
        return "tx1";
    }
}
