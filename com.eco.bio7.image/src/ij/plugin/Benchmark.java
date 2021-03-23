package ij.plugin;
import java.awt.*;
import ij.*;
import ij.process.*;
import ij.gui.*;
import ij.text.*;
/** Implements the Plugins/Utilities/Run Benchmark command. 
	results and additional benchmarks are available at 
	"http://imagej.nih.gov/ij/plugins/benchmarks.html". */
public class Benchmark implements PlugIn {
	int size = 5000;
	int ops = 80;
	boolean showUpdates = false;
	int counter;

	public void run(String arg) {
		IJ.showStatus("Creating "+size+"x"+size+" 16-bit image");
		long t0 = System.currentTimeMillis();
		ImagePlus imp = IJ.createImage("Untitled", "16-bit noise", size, size, 1);
		ImageProcessor ip = imp.getProcessor();
		ip.snapshot();
		for (int i=0; i<9; i++) {
			ip.blurGaussian(40);
			ip.getStatistics();
			update(imp);
		}
		for (int i=0; i <4; i++) {
			ip.invert();
			update(imp);
		}
		for (int i=0; i <4; i++) {
			ip.flipVertical();
			update(imp);
		}
		ip.flipHorizontal(); update(imp);
		ip.flipHorizontal(); update(imp);
		for (int i=0; i <6; i++) {
			ip.smooth();
			update(imp);
		}
		ip.reset();
		for (int i=0; i <6; i++) {
			ip.sharpen();
			update(imp);
		}
		ip.reset();
		ip.smooth(); update(imp);
		ip.findEdges(); update(imp);
		ip.invert(); update(imp);
		ip.autoThreshold(); update(imp);
		ip.reset();
		ip.medianFilter(); update(imp);
		for (int i=0; i <360; i +=15) {
			ip.reset();
			ip.rotate(i);
			update(imp);
		}
		double scale = 1.5;
		for (int i=0; i <8; i++) {
			ip.reset();
			ip.scale(scale, scale);
			update(imp);
			scale = scale*1.5;
		}
		for (int i=0; i <12; i++) {
			ip.reset();
			scale = scale/1.5;
			ip.scale(scale, scale);
			update(imp);
		}
		double time = (System.currentTimeMillis()-t0)/1000.0;
		IJ.showStatus(IJ.d2s(time,1)+" seconds to perform "+counter+" operations on a "+size+"x"+size+" 16-bit image");
	}
	
	void update(ImagePlus imp) {
		if (showUpdates)
			imp.updateAndDraw();
		counter++;
		IJ.showStatus(counter + "/"+ops);
		IJ.showProgress(-counter, ops);
	}

	/*
	void showBenchmarkResults() {
		TextWindow tw = new TextWindow("ImageJ Benchmark", "", 450, 450);
		tw.setFont(new Font("Monospaced", Font.PLAIN, 12));
		tw.append("Time in seconds needed to perform 62 image processing");
		tw.append("operations on the 512x512 \"Mandrill\" image");
		tw.append("---------------------------------------------------------");
		tw.append(" 1.6   Pentium 4/3.0, WinXP  Java 1.3.1");
		tw.append(" 2.4   PPC G5/2.0x2, MacOSX  Java 1.3.1");
		tw.append(" 3.3   Pentium 4/1.4, Win2K  IE 5.0");
		tw.append(" 5.3   Pentium 3/750, Win98  IE 5.0");
		tw.append(" 5.6   Pentium 4/1.4, Win2K  JDK 1.3");
		tw.append(" 6.0   Pentium 3/750, Win98  Netscape 4.7");
		tw.append(" 8.6   PPC G4/400, MacOS     MRJ 2.2");
		tw.append("  11   Pentium 2/400, Win95  JRE 1.1.8");
		tw.append("  14   PPC G3/300, MacOS     MRJ 2.1");
		tw.append("  38   PPC 604/132, MacOS    MRJ 2.1");
		tw.append("  89   Pentium/100, Win95    JRE 1.1.6");
		tw.append("  96   Pentium/400, Linux    Sun JDK 1.2.2 (17 with JIT)");
		tw.append("");
	}
	*/

}