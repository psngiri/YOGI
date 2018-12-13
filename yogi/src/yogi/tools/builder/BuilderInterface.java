package yogi.tools.builder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;



public class BuilderInterface
{
    private static final String IO_DB = "IoDb";
    private static final String IO = "Io";
	protected JFrame            frame;
 
    public BuilderInterface() {
	// Force CapacityTreeViewer to come up in the Cross Platform L&F
	try {
	    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	    // If you want the System L&F instead, comment out the above line and
	    // uncomment the following:
	    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception exc) {
	    System.err.println("Error loading L&F: " + exc);
	}


	JPanel           panel = new JPanel(true);

	frame = new JFrame("Builder Interface");
	frame.getContentPane().add("Center", panel);
	frame.setBackground(Color.lightGray);

	JPanel builderPanel = new JPanel();
	/* Put the Tree in a scroller. */
	JScrollPane        sp = new JScrollPane();
	sp.setPreferredSize(new Dimension(500, 600));
	sp.getViewport().add(builderPanel);
//	ToolTipManager.sharedInstance().registerComponent(sp);
	final BuilderData builderData = new BuilderData();
	constructBuilderPanel(builderPanel, builderData);
	/* And show it. */
	panel.setLayout(new BorderLayout());
	panel.add("Center", sp);
	JButton button = new JButton("Build");
	panel.add("South", button);
	button.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			Builder builder = new Builder(builderData);
			builder.writeFiles();
		}
		
	});
	frame.addWindowListener( new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {System.exit(0);}});

	frame.pack();
	frame.setVisible(true);
    }

	private void constructBuilderPanel(JPanel builderPanel, final BuilderData builderData) {
		builderPanel.setLayout(new BoxLayout(builderPanel, BoxLayout.Y_AXIS));

		JTextField basePackageNameTextfield = createLabelTextfield(builderPanel, "Base Package Name :", builderData.getBasePackageName());
		addTextFieldListener(basePackageNameTextfield, new MyTextFieldActionListener(){
			@Override
			public void setValue(String value) {
				builderData.setBasePackageName(value);
			}
		});
		JTextField objectNameTextfield = createLabelTextfield(builderPanel, "Object Name :", builderData.getObjectName());
		final JTextField packageNameTextfield = createLabelTextfield(builderPanel, "Package Name :", builderData.getPackageName());
		addTextFieldListener(packageNameTextfield, new MyTextFieldActionListener(){
			@Override
			public void setValue(String value) {
				builderData.setPackageName(value);
			}
		});
		addTextFieldListener(objectNameTextfield, new MyTextFieldActionListener(){
			@Override
			public void setValue(String value) {
				builderData.setObjectName(value);
				packageNameTextfield.setText(builderData.getPackageName());
			}
		});
		JTextField sourceDirectoryTextfield = createLabelTextfield(builderPanel, "Source Directory :", builderData.getSourceDirectory());
		addTextFieldListener(sourceDirectoryTextfield, new MyTextFieldActionListener(){
			@Override
			public void setValue(String value) {
				builderData.setSourceDirectory(value);
			}
		});
		attachFileSelection(sourceDirectoryTextfield, builderData);
		JTextField baseTextfield = createLabelTextfield(builderPanel, "Base Class Name :", builderData.getBase().getName());
		addTextFieldListener(baseTextfield, new MyTextFieldActionListener(){
			@Override
			public void setValue(String value) throws Exception {
				try {
					builderData.setBase(value);
				} catch (ClassNotFoundException e1) {
					throw new Exception(builderData.getBase().getName());
				}
			}
		});
		JTextField baseManagerTextfield = createLabelTextfield(builderPanel, "Base Manager Class Name :", builderData.getBaseManager().getName());
		addTextFieldListener(baseManagerTextfield, new MyTextFieldActionListener(){
			@Override
			public void setValue(String value) throws Exception {
				try {
					builderData.setBaseManager(value);
				} catch (ClassNotFoundException e1) {
					throw new Exception(builderData.getBaseManager().getName());
				}
			}
		});
		JTextField baseAssistantTextfield = createLabelTextfield(builderPanel, "Base Assistant Class Name :", builderData.getBaseAssistant().getName());
		addTextFieldListener(baseAssistantTextfield, new MyTextFieldActionListener(){
			@Override
			public void setValue(String value) throws Exception {
				try {
					builderData.setBaseAssistant(value);
				} catch (ClassNotFoundException e1) {
					throw new Exception(builderData.getBaseManager().getName());
				}
			}
		});
		List<String> fileTypes = new ArrayList<String>(BuilderData.allFileTypes);
		Collections.sort(fileTypes, new FileTypeComparator());
		for(String fileType: fileTypes)
		{
			createRadioButton(builderPanel, builderData, fileType);
		}
		
	}

	private void attachFileSelection(final JTextField sourceDirectoryTextfield, final BuilderData builderData) {
		sourceDirectoryTextfield.setEditable(false);
		Container parent = sourceDirectoryTextfield.getParent();
		JButton button = new JButton("...");
		parent.add(button);
		button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    FileFilter filter = new FileFilter(){

					@Override
					public boolean accept(File f) {
						return f.isDirectory();
					}

					@Override
					public String getDescription() {
						return "Directory";
					}

			    };
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(sourceDirectoryTextfield);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       String directory = chooser.getSelectedFile().getAbsolutePath();
			       sourceDirectoryTextfield.setText(directory);
			       if(!directory.equals(builderData.getSourceDirectory()))
			    	   builderData.setSourceDirectory(directory);
			    }

			}
			
		});
		
	}

	private void createRadioButton(JPanel builderPanel, final BuilderData builderData, final String fileType) {
		JRadioButton radioButton = new JRadioButton(fileType);
		builderPanel.add(radioButton);
		radioButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				JRadioButton source = (JRadioButton) e.getSource();
				if(source.isSelected())
					builderData.convertFileType(fileType);
				else
					builderData.dontConvertFileType(fileType);
			}
			
		});
	}

	private JTextField createLabelTextfield(JPanel builderPanel, String label, String text) {
		JTextField textField;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(new JLabel(label));
		textField = new JTextField();
		panel.add(textField);
		textField.setText(text);
		builderPanel.add(panel);
		return textField;
	}
	
	private void addTextFieldListener(JTextField textField, MyTextFieldActionListener listener)
	{
		textField.addActionListener(listener);
		textField.addFocusListener(listener);
	}

    static public void main(String args[]) {
    	new BuilderInterface();
    }

    static abstract class MyTextFieldActionListener implements ActionListener, FocusListener
    {

		public void focusGained(FocusEvent e) {
		}
		public void focusLost(FocusEvent e) {
			action((JTextField) e.getSource());
		}
		public void actionPerformed(ActionEvent e) {
			action((JTextField) e.getSource());
		}
		private void action(JTextField  source) {
			String text = source.getText();
			if(text.trim().equals("")) return;
			try {
				setValue(text);
			} catch (Exception e1) {
				source.setText(e1.getMessage());
			}
		}
    	public abstract void setValue(String value) throws Exception;
    }
 
    static class FileTypeComparator implements Comparator<String>
	{
		public int compare(String o1, String o2) {
			if(o1.startsWith(IO_DB))
			{
				if(o2.startsWith(IO_DB))
				{
					return o1.compareTo(o2);
				}
				return 1;
			}else
			{
				if(o2.startsWith(IO_DB))
				{
					return -1;
				}
			}
			
			if(o1.startsWith(IO))
			{
				if(o2.startsWith(IO))
				{
					return o1.compareTo(o2);
				}
				return 1;
			}else
			{
				if(o2.startsWith(IO))
				{
					return -1;
				}
			}
			
			return o1.compareTo(o2);
		}

	}
    
}
