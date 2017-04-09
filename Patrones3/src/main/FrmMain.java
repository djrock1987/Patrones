package main;

import common.ImageCache;
import formats.ExtFileFilter;
import formats.FileFormat;
import formats.FileFormatFactory;
import formats.FileFormatReader;
import main.Canvas.Tool;
import plugins.PaintableFactory;
import plugins.PaintableType;
import plugins.PluginsReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class FrmMain extends JFrame {

  private Canvas client;

  private List<FileFormatFactory> fileFormatFactoryList;

    private JCheckBox paintableTypeCheckBox;

  // --------------------------------------------------------------------------------

    private FrmMain() {
    setLayout(new BorderLayout());

    client = new Canvas();
    add(client, BorderLayout.CENTER);

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.add(initToolBarPanel());
        toolbarPanel.add(initToolbar2());
        add(toolbarPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(640, 480);
    setVisible(true);
  }

  // --------------------------------------------------------------------------------

    public static void main(String[] args) {
        new FrmMain();
    }

    private JComponent initToolBarPanel() {

    ButtonGroup buttonGroup = new ButtonGroup();

    JToolBar toolBar = new JToolBar();

    // --------------------------------------------------------------------------------

    JToggleButton btnSelect = new JToggleButton( //
        new ImageIcon(ImageCache.getInstance().getSystemImage("images/cursor.png")));

    btnSelect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        client.setPaintableFactory(null);
        client.setTool(Tool.SELECT);
      }
    });
    toolBar.add(btnSelect);
    buttonGroup.add(btnSelect);

    // --------------------------------------------------------------------------------
    List<PaintableFactory> paintableFactoryList = //
    PluginsReader.fsRead(ClassLoader.getSystemResourceAsStream("plugins.txt"));

    for (final PaintableFactory paintableFactory : paintableFactoryList) {
      JToggleButton btnTool = new JToggleButton(paintableFactory.getToolIcon());
      btnTool.setToolTipText(paintableFactory.getToolName());

      btnTool.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          client.setPaintableFactory(paintableFactory);
          client.setTool(Tool.PLUGIN);
            client.setPaintableType(getCurrentPaintableType());
        }
      });
      toolBar.add(btnTool);
      buttonGroup.add(btnTool);
    }

    // --------------------------------------------------------------------------------

    JToggleButton btnDelete = new JToggleButton( //
        new ImageIcon(ImageCache.getInstance().getSystemImage("images/delete_icon.png")));

    btnDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        client.setPaintableFactory(null);
        client.setTool(Tool.DELETE);
      }
    });
    toolBar.add(btnDelete);
    buttonGroup.add(btnDelete);

    // --------------------------------------------------------------------------------

    JButton btnUndo = new JButton( //
        new ImageIcon(ImageCache.getInstance().getSystemImage("images/undo_button.png")));

    btnUndo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnUndoClicked();
      }
    });
    toolBar.add(btnUndo);

    // --------------------------------------------------------------------------------

    JButton btnRedo = new JButton( //
        new ImageIcon(ImageCache.getInstance().getSystemImage("images/redo_button.png")));

    btnRedo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnRedoClicked();
      }
    });
    toolBar.add(btnRedo);

    // --------------------------------------------------------------------------------

    fileFormatFactoryList = //
    FileFormatReader.fsRead(ClassLoader.getSystemResourceAsStream("formats.txt"));

    // --------------------------------------------------------------------------------

    JButton btnSave = new JButton( //
        new ImageIcon(ImageCache.getInstance().getSystemImage("images/save_icon.png")));

    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnSaveClicked();
      }
    });
    toolBar.add(btnSave);

    // --------------------------------------------------------------------------------

    JButton btnOpen = new JButton( //
        new ImageIcon(ImageCache.getInstance().getSystemImage("images/open_icon.png")));

    btnOpen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnOpenClicked();
      }
    });
    toolBar.add(btnOpen);

    // --------------------------------------------------------------------------------

    btnSelect.setSelected(true);

    return toolBar;
  }

  // --------------------------------------------------------------------------------

    private JComponent initToolbar2() {
        JToolBar toolBar = new JToolBar();

        JLabel paintableTypeLabel = new JLabel("¿vector?");
        toolBar.add(paintableTypeLabel);

        this.paintableTypeCheckBox = new JCheckBox();
        toolBar.add(this.paintableTypeCheckBox);

        return toolBar;
    }

  private FileFormatFactory getFileFormatFactory(String ext) {
    for (FileFormatFactory fileFormatFactory : fileFormatFactoryList) {
      if (fileFormatFactory.getExtensionName().equals(ext)) {
        return fileFormatFactory;
      }
    }

    return null;
  }

  private JFileChooser createFileChooser() {
    JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

    fileChooser.setAcceptAllFileFilterUsed(false);

    for (FileFormatFactory fileFormatFactory : fileFormatFactoryList) {
      fileChooser.addChoosableFileFilter(new ExtFileFilter( //
          fileFormatFactory.getExtensionName()));
    }

    return fileChooser;
  }

  private void btnSaveClicked() {
    JFileChooser fc = createFileChooser();

    int retVal = fc.showSaveDialog(this);

    if (retVal != JFileChooser.APPROVE_OPTION) {
      return;
    }

    File file = fc.getSelectedFile();

    ExtFileFilter extFileFilter = (ExtFileFilter) fc.getFileFilter();

    FileFormatFactory fileFormatFactory = getFileFormatFactory(extFileFilter.getExt());
      if (fileFormatFactory != null) {
          FileFormat/*    */fileFormat/*    */ = fileFormatFactory.create();

          if (!file.getAbsolutePath().endsWith(extFileFilter.getExt())) {
              file = new File( //
                      file.getAbsolutePath() + "." + extFileFilter.getExt());
          }

          try {
              fileFormat.save(file, client.getPaintableList());
          } catch (Exception e) {
              e.printStackTrace();
          }
    }
  }

    // --------------------------------------------------------------------------------

  private void btnOpenClicked() {
    JFileChooser fc = createFileChooser();

    int retVal = fc.showOpenDialog(this);

    if (retVal != JFileChooser.APPROVE_OPTION) {
      return;
    }

    File file = fc.getSelectedFile();

    ExtFileFilter extFileFilter = (ExtFileFilter) fc.getFileFilter();

    FileFormatFactory fileFormatFactory = getFileFormatFactory(extFileFilter.getExt());
      if (fileFormatFactory != null) {
          FileFormat/*    */fileFormat/*    */ = fileFormatFactory.create();

          if (!file.getAbsolutePath().endsWith(extFileFilter.getExt())) {
              file = new File( //
                      file.getAbsolutePath() + "." + extFileFilter.getExt());
          }

          try {
              client.getPaintableList().clear();
              fileFormat.load(file, client.getPaintableList());
          } catch (Exception e) {
              e.printStackTrace();
          }

          client.repaint();
      }
  }

  // --------------------------------------------------------------------------------

    private void btnUndoClicked() {
    client.undo();
  }

  // --------------------------------------------------------------------------------

    private void btnRedoClicked() {
    client.redo();
  }

    private PaintableType getCurrentPaintableType() {
        if (this.paintableTypeCheckBox.isSelected()) {
            return PaintableType.DRAWN;
        } else {
            return PaintableType.IMAGE;
        }
  }
}
