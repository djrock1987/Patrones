package formats;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FileFormatReader {

    private FileFormatReader() {
        // Empty
    }

    // --------------------------------------------------------------------------------

    public static List<FileFormatFactory> fsRead(InputStream is) {
        try {
            return exRead(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------------------------------------

    private static List<FileFormatFactory> exRead(InputStream is) throws Exception {
        List<FileFormatFactory> ret = new ArrayList<>();

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        String line;

        while ((line = rd.readLine()) != null) {
            line = line.trim();

            if (line.equals("") || line.startsWith("#")) {
                continue;
            }

            Class<?> clazz = Class.forName(line);
            Method method = clazz.getMethod("getInstance");
            FileFormatFactory fileFormatFactory = (FileFormatFactory) method.invoke(null);

            ret.add(fileFormatFactory);
        }

        return ret;
    }
}
