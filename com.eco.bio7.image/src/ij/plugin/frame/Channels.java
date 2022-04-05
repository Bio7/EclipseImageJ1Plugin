package ij.plugin.frame;
import ij.*;
import ij.plugin.*;
import ij.gui.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JCheckBox;

/** Displays the ImageJ "Channels" dialog. */
public class Channels extends PlugInDialog implements PlugIn, ItemListener, ActionListener {

	private static String[] modes = {"Composite Sum", "Composite Max", "Composite Min",
		"Composite Invert", "Color", "Grayscale"};
	private static String[] menuItems = {"Make Composite", "Convert to RGB", "Split Channels", "Merge Channels...",
	 		"Edit LUT...", "Invert LUTs", "-", "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow", "Grays"};

	private static String moreLabel = "More "+'\u00bb';
	private Choice choice;
	private JCheckBox[] checkbox;
	private Button moreButton;
	private static Channels instance;
	private int id;
	private static Point location;
	private PopupMenu pm;

	public Channels() {
		super("Channels");
		if (instance!=null) {
			instance.toFront();
			return;
		}
		ImageJ ij = IJ.getInstance();
		WindowManager.addWindow(this);
		instance = this;
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		int y = 0;
		c.gridx = 0;
		c.gridy = y++;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		int margin = 32;
		if (IJ.isMacOSX())
			margin = 20;
		c.insets = new Insets(10, margin, 10, margin);
		choice = new Choice();
		for (int i=0; i<modes.length; i++)
			choice.addItem(modes[i]);
		choice.select(0);
		choice.addItemListener(this);
		add(choice, c);

		CompositeImage ci = getImage();
		int nCheckBoxes = ci!=null?ci.getNChannels():3;
		if (nCheckBoxes>CompositeImage.MAX_CHANNELS)
			nCheckBoxes = CompositeImage.MAX_CHANNELS;
		checkbox = new JCheckBox[nCheckBoxes];
		for (int i=0; i<nCheckBoxes; i++) {
			checkbox[i] = new JCheckBox("Channel "+(i+1), true);
			c.insets = new Insets(0, 25, i<nCheckBoxes-1?0:10, 5);
			c.gridy = y++;
			add(checkbox[i], c);
			checkbox[i].addItemListener(this);
			checkbox[i].addKeyListener(ij);
		}

		c.insets = new Insets(0, 15, 10, 15);
		c.fill = GridBagConstraints.NONE;
		c.gridy = y++;
		moreButton = new Button(moreLabel);
		moreButton.addActionListener(this);
		moreButton.addKeyListener(ij);
		add(moreButton, c);
		update();

		pm=new PopupMenu();
		GUI.scalePopupMenu(pm);
		for (int i=0; i<menuItems.length; i++)
			addPopupItem(menuItems[i]);
		add(pm);

		addKeyListener(ij);  // ImageJ handles keyboard shortcuts
		setResizable(false);
		GUI.scale(this);
		pack();
		if (location==null) {
			GUI.centerOnImageJScreen(this);
			location = getLocation();
		} else
			setLocation(location);
		show();
	}
	
	public void update() {
		CompositeImage ci = getImage();
		if (ci==null || checkbox==null)
			return;
		int n = checkbox.length;
		int nChannels = ci.getNChannels();
		if (nChannels!=n && nChannels<=CompositeImage.MAX_CHANNELS) {
			instance = null;
			location = getLocation();
			close();
			new Channels();
			return;
		}
		boolean[] active = ci.getActiveChannels();
		for (int i=0; i<checkbox.length; i++)
			checkbox[i].setSelected(active[i]);
		int index = 0;
		
		String cmode = ci.getProp("CompositeProjection");
		int cindex = 0;
		if (cmode!=null) {			
			if (cmode.contains("Max")||cmode.contains("max")) cindex=1;
			if (cmode.contains("Min")||cmode.contains("min")) cindex=2;
			if (cmode.contains("Invert")||cmode.contains("min")) cindex=3;
		}
		switch (ci.getMode()) {
			case IJ.COMPOSITE: index=cindex; break;
			case IJ.COLOR: index=4; break;
			case IJ.GRAYSCALE: index=5; break;
		}
		choice.select(index);
	}
	
	public static void updateChannels() {
		if (instance!=null)
			instance.update();
	}
	
	void addPopupItem(String s) {
		MenuItem mi=new MenuItem(s);
		mi.addActionListener(this);
		pm.add(mi);
	}

	CompositeImage getImage() {
		ImagePlus imp = WindowManager.getCurrentImage();
		if (imp==null || !imp.isComposite())
			return null;
		else
			return (CompositeImage)imp;
	}

	public void itemStateChanged(ItemEvent e) {
		ImagePlus imp = WindowManager.getCurrentImage();
		if (imp==null) return;
		if (!imp.isComposite()) {
			int channels = imp.getNChannels();
			if (channels==1 && imp.getStackSize()<=4)
				channels = imp.getStackSize();
			if (imp.getBitDepth()==24 || (channels>1&&channels<CompositeImage.MAX_CHANNELS)) {
				GenericDialog gd = new GenericDialog(imp.getTitle());
				gd.addMessage("Convert to multi-channel composite image?");
				gd.showDialog();
				if (gd.wasCanceled())
					return;
				else
					IJ.doCommand("Make Composite");					
			} else {
				IJ.error("Channels", "A composite image is required (e.g., "+moreLabel+" Open HeLa Cells),\nor create one using "+moreLabel+" Make Composite.");
				return;
			}
		}
		if (!imp.isComposite()) return;
		CompositeImage ci = (CompositeImage)imp;
		Object source = e.getSource();
		if (source==choice) {
			int index = ((Choice)source).getSelectedIndex();
			String cstr = null;
			int cmode = IJ.COMPOSITE;
			switch (index) {
				case 0: cmode=IJ.COMPOSITE; cstr="Sum"; break;
				case 1: cmode=IJ.COMPOSITE; ; cstr="Max"; break;
				case 2: cmode=IJ.COMPOSITE; ; cstr="Min"; break;
				case 3: cmode=IJ.COMPOSITE; ; cstr="Invert"; break;
				case 4: cmode=IJ.COLOR; break;
				case 5: cmode=IJ.GRAYSCALE; break;
			}
			if (cstr!=null && !(cstr.equals("Sum")&&ci.getProp("CompositeProjection")==null))
				ci.setProp("CompositeProjection", cstr);
			ci.setMode(cmode);
			ci.updateAndDraw();
			if (cmode==IJ.COMPOSITE && ("Min".equals(cstr)||"Invert".equals(cstr)) && !imp.isInvertedLut()) {
				String msg =
					"You may need to run More "+'\u00bb'+" \"Invert LUTs\" to view\n"
					+"this image in \"Composite "+cstr+"\" mode. Note that\n"
					+"the \"Invert LUTs\" command only works with linear\n"
					+"LUTs that use one primary color.";
				IJ.showMessage(msg);
			}	
			if (Recorder.record) {
				String mode = null;
				if (Recorder.scriptMode()) {
					switch (index) {
						case 0: case 1: case 2: case 3: mode="IJ.COMPOSITE"; break;
						case 4: mode="IJ.COLOR"; break;
						case 5: mode="IJ.GRAYSCALE"; break;
					}
					cstr="\""+cstr+"\"";
					Recorder.recordCall("imp.setProp(\"CompositeProjection\", "+cstr+");");
					Recorder.recordCall("imp.setDisplayMode("+mode+");");
				} else {
					switch (index) {
						case 0: case 1: case 2: case 3: mode="composite"; break;
						case 4: mode="color"; break;
						case 5: mode="grayscale"; break;
					}
					Recorder.recordString("Property.set(\"CompositeProjection\", \""+cstr+"\");\n");
					Recorder.record("Stack.setDisplayMode", mode);
				}
			}
		} else if (source instanceof Checkbox) {
			for (int i=0; i<checkbox.length; i++) {
				JCheckBox cb = (JCheckBox)source;
				if (cb==checkbox[i]) {
					if (ci.getMode()==IJ.COMPOSITE) {
						boolean[] active = ci.getActiveChannels();
						active[i] = cb.isSelected();
						if (Recorder.record) {
							String str = "";
							for (int c=0; c<ci.getNChannels(); c++)
								str += active[c]?"1":"0";
							if (Recorder.scriptMode())
								Recorder.recordCall("imp.setActiveChannels(\""+str+"\");");
							else
								Recorder.record("Stack.setActiveChannels", str);
						}
					} else {
						imp.setPosition(i+1, imp.getSlice(), imp.getFrame());
						if (Recorder.record) {
							if (Recorder.scriptMode())
								Recorder.recordCall("imp.setC("+(i+1)+");");
							else
								Recorder.record("Stack.setChannel", i+1);
						}
					}
					ci.updateAndDraw();
					return;
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command==null) return;
		if (command.equals(moreLabel)) {
			Point bloc = moreButton.getLocation();
			pm.show(this, bloc.x, bloc.y);
		} else if (command.equals("Convert to RGB"))
			IJ.doCommand("Stack to RGB");
		else if (command.equals("Invert LUTs"))
			IJ.runMacroFile("ij.jar:InvertAllLuts", null);
		else
			IJ.doCommand(command);
	}
	
	/** Obsolete; always returns null. */
	public static Frame getInstance() {
		return null;
	}
		
	public void close() {
		super.close();
		instance = null;
		location = getLocation();
	}
	
}
