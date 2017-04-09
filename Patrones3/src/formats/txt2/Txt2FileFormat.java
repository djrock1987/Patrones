package formats.txt2;

import formats.FileFormat;
import plugins.Paintable;
import plugins.PaintableFactory;
import plugins.PaintableType;

import java.io.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

public class Txt2FileFormat implements FileFormat {

  public void load(File file, List<Paintable> paintableList) throws Exception {

    Pattern p = Pattern.compile(" ");

    BufferedReader rd = new BufferedReader(new FileReader(file));

    String line;

    while ((line = rd.readLine()) != null) {
      line = line.trim();

      if (line.equals("") || line.startsWith("#")) {
        continue;
      }

      String[] data = p.split(line);

        Class<?> clasz = Class.forName(data[4]);
        Method method = clasz.getMethod("getInstance");
        PaintableFactory pf = (PaintableFactory) method.invoke(null);

        PaintableType paintableType = PaintableType.valueOf(data[5]);
        Paintable paintable = null;
        switch (paintableType) {
            case DRAWN:
                paintable = pf.createDrawnPaintable( //
                        Integer.parseInt(data[0]), //
                        Integer.parseInt(data[1]), //
                        Integer.parseInt(data[2]), //
                        Integer.parseInt(data[3]));
                break;
            case IMAGE:
                paintable = pf.createImagePaintable( //
                        Integer.parseInt(data[0]), //
                        Integer.parseInt(data[1]), //
                        Integer.parseInt(data[2]), //
                        Integer.parseInt(data[3]));
                break;
        }

      paintableList.add(paintable);
    }

    rd.close();
  }

  public void save(File file, List<Paintable> paintableList) throws Exception {
    PrintWriter pw = new PrintWriter(new FileWriter(file));

    for (Paintable paintable : paintableList) {
        if (paintable.getPaintableFactory() != null) {
            pw.print(paintable.getX1());
            pw.print(" ");
            pw.print(paintable.getY1());
            pw.print(" ");
            pw.print(paintable.getX2());
            pw.print(" ");
            pw.print(paintable.getY2());
            pw.print(" ");
            pw.print(paintable.getPaintableFactory().getClass().getName());
            pw.print(" ");
            pw.print(paintable.getPaintableType());
            pw.println();
        }
    }

    pw.close();
  }
}
