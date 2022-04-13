package ij.plugin.frame;
import ij.*;
import ij.plugin.*;
import ij.gui.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JCheckBox;

/** Displays the ImageJ "Channels" dialog. */
public class Channels extends PlugInDialog implements PlugIn, ItemListener, ActionListener {

	private static final String[] modes = {"Composite", "Color", "Grayscale", "---------", "Composite Max",
		"Composite Min", "Composite Invert"};
	private static final int COMP=0, COLOR=1, GRAY=2, DIVIDER=3, MAX=4, MIN=5, INVERT=6;
	private static String[] menuItems = {"Make Composite", "Convert to RGB", "Split Channels", "Merge Channels...",
		"Edit LUT...", "Invert LUTs", "-", "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow", "Grays"};

	public static final String help = "<html>"
	+"<h1>Composite Display Modes</h1>"
	+"<font size=+1>"
	+"<ul>"
	+"<li> <u>Composite</u> - Converts the channels to RGB and sums. RGB values are clipped to 255, which can cause saturation in areas with overlapping bright signals.<br>"
	+"<li> <u>Composite Max</u> - Converts the channels to RGB and uses maximum intensity projection.<br>"
	+"<li> <u>Composite Min</u> - Inverts the LUTs (if needed), converts the channels to RGB and uses minimum intensity projection.<br>"
	+"<li> <u>Composite Invert</u> - Inverts the LUTs (if needed), converts the channels to RGB and sums.<br>"
	+" <br>"
	+"The macro at http://wsr.imagej.net/macros/CompositeProjection.ijm uses the \"Invert LUTs\", \"Split Channels\", \"RGB Color\", \"Images to Stack\" and \"Z Project\" commands to attempt to duplicate these four display modes.<br>"
	+"</font>";

	private static String moreLabel = "More "+'\u00bb';
	private Choice choice;
	private JCheckBox[] checkbox;
	private Button helpButton, moreButton;
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
		c.gridwidth = 2;
		c.gridy = y++;
		Panel panel = new Panel();
		int hgap = IJ.isMacOSX()?1:5;
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT,hgap,0));
		helpButton = new Button("Help");//new Button("Help");
		helpButton.addActionListener(this);
		helpButton.addKeyListener(ij);
		panel.add(helpButton, c);
		add(panel, c);
		moreButton = new Button(moreLabel);//new Button(moreLabel);
		moreButton.addActionListener(this);
		moreButton.addKeyListener(ij);
		panel.add(moreButton, c);
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
		int cindex = COMP;
		if (cmode!=null) {			
			if (cmode.contains("Max")||cmode.contains("max")) cindex=MAX;
			if (cmode.contains("Min")||cmode.contains("min")) cindex=MIN;
			if (cmode.contains("Invert")||cmode.contains("invert")) cindex=INVERT;
		}
		switch (ci.getMode()) {
			case IJ.COMPOSITE: index=cindex; break;
			case IJ.COLOR: index=COLOR; break;
			case IJ.GRAYSCALE: index=GRAY; break;
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
				case COMP: cmode=IJ.COMPOSITE; cstr="Sum"; break;
				case COLOR: cmode=IJ.COLOR; break;
				case GRAY: cmode=IJ.GRAYSCALE; break;
				case DIVIDER: cmode=IJ.COMPOSITE; cstr="Sum"; break;
				case MAX: cmode=IJ.COMPOSITE; ; cstr="Max"; break;
				case MIN: cmode=IJ.COMPOSITE; ; cstr="Min"; break;
				case INVERT: cmode=IJ.COMPOSITE; ; cstr="Invert"; break;
			}
			if (cstr!=null && !(cstr.equals("Sum")&&ci.getProp("CompositeProjection")==null))
				ci.setProp("CompositeProjection", cstr);
			//IJ.log(cmode+" "+cstr+" "+imp.isInvertedLut());
			if (cmode==IJ.COMPOSITE && (("Min".equals(cstr)||"Invert".equals(cstr)) && !imp.isInvertedLut())
			|| ("Max".equals(cstr)||"Sum".equals(cstr)) && imp.isInvertedLut())
				IJ.runMacroFile("ij.jar:InvertAllLuts", null);	
			ci.setMode(cmode);
			ci.updateAndDraw();
			if (Recorder.record) {
				String mode = null;
				if (index!=DIVIDER && Recorder.scriptMode()) {
					switch (index) {
						case COMP: case MAX: case MIN: case INVERT: mode="IJ.COMPOSITE"; break;
						case COLOR: mode="IJ.COLOR"; break;
						case GRAY: mode="IJ.GRAYSCALE"; break;
					}
					cstr="\""+cstr+"\"";
					Recorder.recordCall("imp.setProp(\"CompositeProjection\", "+cstr+");");
					Recorder.recordCall("imp.setDisplayMode("+mode+");");
				} else {
					switch (index) {
						case COMP: case MAX: case MIN: case INVERT: mode="composite"; break;
						case COLOR: mode="color"; break;
						case GRAY: mode="grayscale"; break;
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
		Object source = e.getSource();
		if (source==helpButton) {
			new HTMLDialog("Channels", help, false);
			return;
		}
		String command = e.getActionCommand();
		if (command==null) return;
		if (command.equals(moreLabel)) {
			Point bloc = moreButton.getLocation();
			pm.show(this, bloc.x, bloc.y);
		} else if (command.equals("Convert to RGB"))
			IJ.doCommand("Stack to RGB");
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
