package rs2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;

public class Jframe extends client implements ActionListener {

    private static JMenuItem menuItem;
    private JFrame frame;

    public Jframe(String args[]) {
        super();
        try {
            rs2.sign.signlink.startpriv(InetAddress.getByName(server));
            initUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void openUpWebSite(String url) {
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI(url));
        } catch (Exception e) {
        }
    }

    public void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);
            frame = new JFrame("Project Insanity");
            frame.setLayout(new BorderLayout());
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel gamePanel = new JPanel();

            gamePanel.setLayout(new BorderLayout());
            gamePanel.add(this);
            gamePanel.setPreferredSize(new Dimension(765, 503));

            JMenu fileMenu = new JMenu("File");

            String[] mainButtons = new String[]{"Project-Insanity.net", "-", "Exit"};

            for (String name : mainButtons) {
                JMenuItem menuItem = new JMenuItem(name);
                if (name.equalsIgnoreCase("-")) {
                    fileMenu.addSeparator();
                } else {
                    menuItem.addActionListener(this);
                    fileMenu.add(menuItem);
                }
            }

            JMenuBar menuBar = new JMenuBar();
            JMenuBar jmenubar = new JMenuBar();

            frame.add(jmenubar);
            menuBar.add(fileMenu);
            frame.getContentPane().add(menuBar, BorderLayout.NORTH);
            frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
            frame.pack();

            frame.setVisible(true); // can see the rs2.client
            frame.setResizable(false); // resizeable frame

            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public URL getCodeBase() {
        try {
            return new URL("http://" + server + "/cache");
        } catch (Exception e) {
            return super.getCodeBase();
        }
    }

    public URL getDocumentBase() {
        return getCodeBase();
    }

    public void loadError(String s) {
        System.out.println("loadError: " + s);
    }

    public String getParameter(String key) {
        return "";
    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        try {
            if (cmd != null) {
                if (cmd.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
                if (cmd.equalsIgnoreCase("Project-Insanity.net")) {
                    openUpWebSite("http://www.project-com.insanity.net/");
                }
            }
        } catch (Exception e) {
        }
    }
}