package install;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class InstallJarToMaven {
    protected static String dFile;
    private String dGroupId;
    private String dArtifactId;
    private String dVersion;
    private JFrame frame = new JFrame("导入jar包");
    private JLabel[] labels = new JLabel[3];
    private JTextField[] textFields = new JTextField[3];
    private JButton choose = new JButton("选择jar包");
    private JButton install = new JButton("导入");

    public String getdFile() {
        return dFile;
    }

    public void setdFile(String path) {
        File file = new File(path);
        dFile = file.getName();
    }

    public String getdGroupId() {
        return dGroupId;
    }

    public void setdGroupId(String dGroupId) {
        this.dGroupId = dGroupId;
    }

    public String getdArtifactId() {
        return dArtifactId;
    }

    public void setdArtifactId(String dArtifactId) {
        this.dArtifactId = dArtifactId;
    }

    public String getdVersion() {
        return dVersion;
    }

    public void setdVersion(String dVersion) {
        this.dVersion = dVersion;
    }

    public InstallJarToMaven() {
        frame.add(choose);
        frame.add(install);
        frame.setLayout(null);
        frame.setBounds(400, 200, 800, 500);
        choose.setBounds(340, 40, 120, 30);
        install.setBounds(340, 250, 80, 30);
        String[] names = {"GroupId", "ArtifactId", "Version"};
        for (int i = 0; i < 3; i++) {
            textFields[i] = new JTextField(200);
            textFields[i].setBounds(200, 50 * i + 100, 500, 30);
            labels[i] = new JLabel(names[i]);
            labels[i].setBounds(40, 50 * i + 100, 100, 30);
            frame.add(labels[i]);
            frame.add(textFields[i]);
        }
        frame.setVisible(true);
        choose.addActionListener(new FileChooser());
        install.addActionListener(new InstallListener());
    }

    public static void main(String[] args) {
        new InstallJarToMaven();
    }

    private class InstallListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dGroupId = textFields[0].getText();
            dArtifactId = textFields[1].getText();
            dVersion = textFields[2].getText();
            if (dGroupId != null && dArtifactId != null && dVersion != null) {
                String cmd = "mvn install:install-file -Dfile=\"" + dFile + "\" -DgroupId=" + dGroupId + " -DartifactId=" + dArtifactId + " -Dversion=" + dVersion + " -Dpackaging=jar";
                System.out.println(cmd);
            } else {
                System.out.println("信息错误");
            }

        }
    }

}
