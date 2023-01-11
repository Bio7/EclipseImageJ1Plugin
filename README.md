# EclipseImageJ1Plugin
A repository for an Eclipse ImageJ1 plugin contributed from the Bio7 project.

### Installation:

**Important for MacOSX : Please add the option -Dprism.order=sw in the Eclipse.ini to avoid a crash when using JavaFX!**

**Important for Linux : Please add the option --launcher.GTK_version 2 (don't forget the linebreak before the version number) in the Eclipse.ini to avoid a crash when using JavaFX (JavaFX doesn't run with GTK3 on Linux)!**

Add the following URL as update site: 

**https://bio7.github.io/imagej/**

## New in 1.54b (1.54.b5):

1. Updated the ImageJ plugin to version 1.54b5
2. Added the latest  macro templates
3. Added a new editor drag and drop function to drop external multiple files and folders as editor string filepaths.
3. Fixed some minor bugs 

## New in 1.53t (1.53.t26):

1. Updated the ImageJ plugin to version 1.53t26
2. Added the latest  macro templates
3. Fixed some minor bugs 

## New in 1.53n (1.53.n7):

1. Updated the ImageJ plugin to version 1.53n7
2. Added the latest  macro templates
3. Fixed some minor bugs 

## New in 1.53m (1.53.m7):

1. Updated the ImageJ plugin to version 1.53m7
2. Improved the "Synchronize Windows" action for detached views
3. Added the latest  macro templates
4. Fixed some minor bugs 

## New in 1.53j (1.53.j43 (2)):

1. Improved the 'Detach Images' menu action
2. Added an post activation action for detached views ('Open All Images as Views' action bugfix for Windows)
3. Improved the layout action for orthogonal stack views (in detached views) 
4. Added an API method to invoke the 'Open All Images as Views' action
5. The ImageJ macro action 'setLocation'  will now change the size and location of detached views if available
5. Fixed some minor bugs 

## New in 1.53j (1.53.j43):

1. Updated the ImageJ plugin to version 1.53j43
2. Renamed the 'Detach Images' menu actions
2. Images can now be detached in a seperate view window ('Open All Images as Views')
3. Added 'Open All Images as Views' action preferences (coordinates, size, etc., to layout 
   several windows side by side, e.g., on a secondary screen)
4. Added the latest  macro templates
5. Fixed some minor bugs

## New in 1.53j (1.53.j35):

1. Updated the ImageJ plugin to version 1.53j35
2. Added the latest  macro templates

## New in 1.53j (1.53.j26):

1. Updated the ImageJ plugin to version 1.53j26
2. Detached images can now be closed in a macro by name or string argument
3. Improved the image close method. Now also closes detached images if opened
4. The color for composite images is now displayed in the status line
5. The image and color information for detached images is shown in the status line
6. Fixed the ImageWindow show method which opened a minimized JFrame, e.g., in batch mode
7. Added the latest  macro templates

### New in 1.53j (1.53.j7):

1. Updated ImageJ plugin to version 1.53j7
2. Improved the "Zoom to Selection" and Zoom "To" action for the embedded canvas in a tab
3. Improved the image close method (for the "Arrange Channels" action and some other plugins)
4. Improved the "Stack to Images" action now running in a job to avoid blocking UI threads
5. Simplified the "Images to Stack" action to run correctly in macros
6. Added a timelag for plot windows to avoid blocking threads on MacOSX
7. Added the latest  macro templates
 
### New in 1.53i (1.53.i25):

1. Updated ImageJ plugin to version 1.53i25
2. Improved the ImageJ macro editor speed. No more flickering editor markers
3. Added a editor hoover to display editor hex colors and an hoover action to set the hex color with a dialog.
4. Added a sort an collapse action to the macro editor outline view.
5. Added the image information (which is normally displayed in the image window) to the Eclipse status line if a tab is selected or a stack/composite slider is moved.
6. Added an action to change the tab title color according to the color of the selected composite image channel.
7. Added a new color action for the new ImageJ message display method (tab title color and toolbar color)
8. Improved the outline redraw action for MacOSX 

### New in 1.53h (1.53.h62):

1. Updated ImageJ plugin to version 1.53.h62
2. Fixed and improved the outline navigation for MacOSX
3. Added new ImageJ macro templates
4. Improved template description
5. Fixed a corrupted button layout in the histogram canvas on MacOSX
6. Improved the debug start action


### New in 1.53.h (1.53.h30):

1. Updated ImageJ plugin to version 1.53.h30
2. Improved the context menu of the ROI Manager (using SWT)
3. Added new ImageJ macro templates
4. Improved template description

### New in 1.53.g (1.53.g57):

1. Updated ImageJ plugin to version 1.53.g57
2. Improved the main menu bar extensibility
3. Added new ImageJ macro templates
4. Improved template description 


### New in 1.53.f (1.53.f40):

1. Updated ImageJ plugin to version 1.53.f40 (Java 14 necessary!)
2. The main view menubar  can now be extended if a plugin uses the main menu of ImageJ
3. Replaced several AWT context and toolbar menus with SWT context menus (ImageJ toolbar, image canvas, ROI Manager Menu "More"
   and the Plots menu) which fixes several AWT glitches (Linux, HighDPI) and respects the Eclipse theme
   settings (e.g., dark menus on Windows)
4. Improved the debug display in the macro editor (color marker preferences, new arrow indicator icon)
5. Improved the plot button display (especially when using a dark theme)
6. Fixed several toolbar macro tool bugs and updated all macro tools
7. Fixed the context menu macro tools ("Restore Startup Tools" now working as it should and updates the context menu)
8. Added new macro function templates
9. Several bugfixes


### New in 1.53.e (1.53.e17):

1. Updated ImageJ plugin to version 1.53.e17
2. Added new default macro editor templates
3. Improved template description

### New in 1.53.e (1.53.e05):

1. Fixed a toolbar deadlock when view was attached and Eclipse reopened

### New in 1.53.e (1.53.e4):

1. Updated ImageJ plugin to version 1.53.e4
2. Added or improved new default macro editor templates
3. Removed deprecated MacAdapter
4. Improved 'Split Channels' for Mac

### New in 1.53.d (1.53.d7):

1. Updated ImageJ plugin to version 1.53.d7
2. Added new default macro editor templates
3. Improved Java 14 compiler support
4. Improved layout after crop operation

### New in 1.53.c (1.53.c62):

1. Updated ImageJ plugin to version 1.53.c62
2. Added new default macro editor templates
3. Added Java 14 compiler support
4. Fixed a deadlock in the HTMLDialog

### New in 1.53.c (1.53.c3):

1. Updated ImageJ plugin to version 1.53.c3
2. Added new default macro editor templates
3. Fixed a HyperStack converter bug

### New in 1.53.a (1.53a25):

1. Updated ImageJ plugin to version 1.53.a
2. Added new default macro editor templates

### New in 1.53.a (1.53a21):

1. Updated ImageJ plugin to version 1.53.a21
2. Added new default macro editor templates

### New in 1.52.v (1.52v19):

1. Updated ImageJ plugin to version 1.52v19
2. Added new default macro editor templates

### New in 1.52.u (1.52u33):

1. Updated ImageJ plugin to version 1.52u33
2. Fixed a version number display bug

### New in 1.52.u (1.52u29):

1. Updated ImageJ plugin to version 1.52u29
2. A change of the ImageJ plugin location ('Plugins Path') in the preferences now automatically updates the plugins classpath and menus (avoids to use the action 'Help->Refresh Menus' in a second step).
3. Fixed a Compiler path bug for Linux and MacOSX when using the Plugins->'Compile Java Eclipse' action to compile an ImageJ plugin from the current opened Java editor.

### New in 1.52.u (1.52u28):

1. Updated ImageJ plugin to version 1.52u28
2. Fixed a SWT memory bug not releasing native children of image tabs.
 
### New in 1.52.u (1.52u24):

1. Added a new thumbnail action to the context menu of the 'Navigator' view to open image and LUT files of a selected directory.
2. LUT's can now be displayed (preview) in the 'Thumbnails' view. The selected LUT will be applied on the current supported ImageJ image (grayscale).
3. The Image info is now displayed as a popup in the 'Thumbnails' view (if you hoover over an item).
4. Added a 'Detach All Images' action (menu 'Window') to transfer all opened tab images to a view (which can be moved and arranged like a separate window)
5. Improved dark theme for AWT on Windows, MacOSX and Linux.
6. Added custom text properties to allow future dark themes or disable dark theme color schemes.
7. Improved the canvas layout for HighDPI displays on Windows.
8. Added new default macro editor templates.
9. Improved the default AWT fonts (using OS fonts).

### New in 1.52.q (1.52q48):

1. Fixed a deadlock occuring  on MacoSX when detaching image tabs.
2. Fixed a PlotWindow deadlock when using the action "Close All".

### New in 1.52.q (1.52q46):

1. New debug view toolbar actions availabe to easily debug ImageJ macros (without loosing editor key focus on MacOSX when an image is created or opened).
2. Implemented all default ImageJ debug actions as toolbar actions (one action for 'Trace' and 'Fast Trace')
3. New debug functions available to set a variable value in debug mode.
4. New breakpoint actions available to set, edit, delete breakpoints and simple expression breakpoints (evaluate  boolean variable expression) in the ruler menu of the editor. Created breakpoints are automatically stored, too.
5. Extended the editor hoover to display the variable values when you hoover the editor. Array values will be displayed, too.
6. The default array value display table will be opened if you click on the debug view table column.
7. Implemented several text editor and toolbar changes to visualize the debugging process.
8. Fixed several bugs.

### New in 1.52q (1.52q45):

1. Fixed a PlotWindow deadlock

### New in 1.52q (1.52q44):

1. Updated ImageJ plugin to version 1.52q44
2. Improved the embedded and detached PlotWindows. Now all plot windows are simultaneously resized according to their parent canvas (embedded or detached).
3. Fixed a bug in the detach menu action (wrong id and panel settings)
4. Removed the '*' tab icon (usually indicating an unsaved editor file) from the detached custom view by deleting the 'ISaveablePart2' interface

### New in 1.52q (1.52q34):

1. Fixed a toolbar exception when the toolbar was embedded in the Image perspective and not saved detached (eclipse restart)
2. Fixed wrong tab colors

### New in 1.52q (1.52q30):

1. Updated ImageJ plugin to version 1.52q30
2. A new integrated Eclipse ImageJ macro debug view is available as an replacement for the debug dialog (close and reopen the perspective if you update this plugin to add the new view!)
3. Improved the Linux dark theme GUI for embedded AWT and Swing dialogs
4. Improved the ImageJ macro code completion in general
5. Editor defined variables are added to the code completion and shown according to their scope (function or macro defined variables are added when opening the code completion inside this scope. Function arguments are added, too)
6. Editor defined functions are added to code completion.
7. Editor defined function arguments are added as selectable templates
8. Comments and multiline comments of editor defined functions are shown in the context code completion view (must be defined above the function)


### New in 1.52p (1.52p61):

1. Updated ImageJ plugin to version 1.52p61
2. Added Java 12 compiler target
3. Added new macro code completion templates.

### New in 1.52p (1.52p36):

1. Updated ImageJ plugin to version 1.52p36
2. Improved code completion to find and set functions with fixed arguments.
3. Improved hoover information to list all functions with identical name.
4. Added new macro code completion templates.

### New in 1.52p (1.52p30):

1. Updated ImageJ plugin to version 1.52p30
2. Improved automatic layout correction for synchronized detached views (e.g., orthogonal views)
3. Improved titles of detached views to show correct titles and updates (e.g., coordinates of synchronized orthogonal views)
4. Improved the centering of dialogs in a multi-monitor setup (dialogs are centered in the monitor which contains the ImageJ view tabfolder)
5. Improved the code completion cursor location and arguments are now templates selectable with the tab key
6. Improved the code completion sorting of templates
7. Added a preference option to change the critera for code completion search to suggest function templates which contain the prefix
8. Added new file, directory dialogs to insert paths at the current cursor location.
9. Added a color and hex color dialog to select and insert color strings into the editor. Selected hex colors are displayed 
in the color dialog (if created before with the dialog)
10. Added new macro templates
11. Fixed several bugs (e.g., opened dialog option popups in macros, deadlock with SWT dialog in a running macro)

### New in 1.52m (1.52m28):

1. Updated ImageJ plugin to version 1.52m28
2. Implemented dynamic plot scaling when the image tabsize changes
3. Reset Plot action scales plot to default values (context menu "Plot Size")
4. Plot fullscreen scaling support
5. Detached tab plot scaling support
6. Now tabs are visible selected when a window or image is selected (before the selection occured, too but invisible for speed reasons)
7. Implemented the Window components menu to make hidden components visible again (ROI Manager, Threshold, etc. - apparently works on MacOSX and Windows only)
8. Implemented Refresh Menus (if you create and compile a new plugin in the plugins folder with Eclipse (dynamically without restart) you can refresh the menu to update the plugins menu dynamically without a restart!)
9. Implemented the "Help->Examples->Autorun Examples" checkbox menu (true or false)
10. Restored the modal dialogs on Windows
11. Added latest macro API templates

### New in 1.52m (1.52m20):

1. Updated ImageJ version 1.52m (1.52m20)
2. Added new macro function definitions
3. Zoom factor is displayed in the image tabs
4. Workaround a SWT key focus loss with ImageJ on Linux (occurred on Ubuntu 18.10)
5. Fixed an exception when adding an image ("Add Slice")to a stack of size 1
6. Detached images are now closed, too when using the "File->Close All" view menu action

### New in 1.52m (1.52m12):

1. Updated ImageJ version 1.52m (1.52m12)
2. Improved the ImageJ canvas (Plot canvas and Image canvas are now opened as intended)
3. The ImageJ canvas replace method now works as intended (see, e.g., IJ_webcam capture plugin)
4. Improved plot canvas (zooming, actions, etc. are now working as intended)
5. Plot action buttons are now added below each plot (see screenshot below)
6. Added new actions (in the plugins menu) to interpret the current opened Eclipse editor sources (BeanShell, JavaScript, Jython and ImageJ macro) using the ImageJ interpreters (using, e.g., PyDev for Jython).
7. Added a new Java compile action (in the plugins menu) to compile the current opened Eclipse editor source (see screenshot below). To compile dynamically with ImageJ please use or adjust the ImageJ plugins path in the Eclipse preferences.
8. MouseWheel actions are now working as intended (use STRG+MouseWheel to zoom!)
9. Improved stability for drag and drop actions of multiple image files
10. Improved stability for MacOSX
11. Reorganized Eclipse plugins and features
12. ImageJ2 can now be optionally installed and is not bundled by default (now optional dependent of ImageJ). Customization is possible (plugin can be populated with maven command in Eclipse)
13. Added the latest ImageJ macro function templates
14. JavaFX (embed Swing in JavaFX and SWT panel) can now be optionally installed (but is not necessary by default)
15. Removed the outdated Albireo plugin dependency
16. Added key listeners for the tab when all images are closed (ImageJ keys!)
17. Improved the ImageJ macro hoover (workaround for SWT_AWT)
18. Added a workaround for repaint issues in MacOSX when switching perspectives with SWT_AWT
19. Added a workaround for MacOSX to receive key events again after focus lost (sometimes you need to press the mouse two times when the perspectives have been switched)
20. Fixed several rare occuring deadlock events on MacOSX

### New in 1.52h (1.52h5):

1. Updated to ImageJ 1.52h (1.52h5)
2. Added a new rename action (rename all words like the current selection - multiple cursor)
3. Improved dark theme with popular Eclipse darkest dark theme and default dark theme
4. Editor colors are automatically switched when using the dark theme (or darkest dark theme)
5. ImageJ toolbar improved for the dark theme
6. Added a new ImageJ detach image menu in the �Window� menu of the ImageJ-Canvas view
7. Added all new ImageJ macro API functions to the code completion of the macro editor
8. Improved the ImageJ MacOSX touchpad resizing of the ImageJ panel
9. Improved the default font size for Swing components on Retina displays (e.g., ImageJ components - can be changed in the preferences)
10. Added some new macro source actions (Block Selection, Find And Replace, etc. - submenu Text ) to the macro editor

### New in 1.52c (1.52c12):

1. Updated to ImageJ 1.52c (1.52c12)
2. Updated ImageJ2 libraries (pom-scijava 20.0.0)
3. Added recent macro API commands to code completion
4. Fixed some minor bugs (hoover offset, Linux awt dialogs etc.)

### New in 1.51u (1.51u54):

1. Updated to ImageJ 1.51u (1.51u54)
2. Fixed lost keyboard focus of the ImageJ panel on MacOSX
3. Improved preferences for Swing fonts (to decrease on Retina displays)
4. Several other fixes for MacOSX
5. Added description for font corrections for MacOSX (see bottom of this page!)
6. Improved drag and drop of images on the ImageJ panel

### New in 1.51u20:

1. Cluttered MACOSX menus are now closed (added from opened application windows and frames). Some default added menus are disabled in the Bio7 preferences by default
2. Code completion (template) action now displays the macro documentation and context information when typing
3. Improved code completion layout (context information) 
4. Added debug context menu actions to the macro editor
5. Added some other context menu actions, too ('Get Macro Recorder Text', 'Evaluate Line')



### New in 1.51s24:

1. Code completion (templates) action now displays the macro documentation when typing
2. Images from macros are now opened fast (before had a 2s delay)
3. Macros can now be executed within an external ImageJ instance in a separate Java process if enabled in the preferences (Windows only!)
4. Added JavaFX library reference for Java9

### New in 1.51p3:
1. Improved grammar for nested functions in loop and if statements
2. Added more code completion templates
3. Added a new action to insert text of the macro recorder (CTRL+SHIFT+R or CMD+SHIFT+R)

### New in 1.51p (new macro editor available!):

In this relase a macro editor and ImageJ perspective have been added to the ImageJ plugin. The editor has the following features:

1. Dynamic error detection (grammar derived from the ECMA grammar by Bart Kiers)
2. Toolbar action to execute ImageJ macros
3. Syntax highlightening
4. Debugging support (reusing and implementing interfaces and methods from @Wayne - same keyboard shortcuts - see animation below)
5. Code completion (with browser help)
6. Code templates (add you own templates)
7. Info popups (text hoover)
8. Outline view (var variables, variables, methods and macros)
9. Automatic closing of braces, parentheses and strings
Automatic indention of functions,loops, etc.
10. Code folding
11. Code formatting (based on the Eclipse-javascript-formatter by Sebastian Moran)
12. Resize fonts functions (with Ctrl++ or CMD++, CTRL+- or CMD +-)
13. Font preferences
14. Mark occurences (scope independant)
15. A copy function for this forum

### New in 1.51n:

1. Updated ImageJ to 1.51k.
2. Added a fake editor to register and open standard image files from the Navigator or Project Explorer, etc.
3. Fixed several bugs to convert image types

Please note that the update site has changed (due to a cert. problem with on sourceforge) to:

https://bio7.github.io/imagej/

Uninstall the old plugins before you install from the new location!

### New in 1.51k:

1. Updated ImageJ to 1.51k.

2. Reworked the ImageJ plugin view menus. Now all Imagej menus and nested submenus are displayed correctly!

3. Added a menu switch (menu 'Window') to open images in a Swing in JavaFX in SWT tab (future replacement for SWT_AWT). Images can subsequently be opened in fullscreen with: F1 = primary monitor true fullscreen, F2 = primary monitor fullscreen, F3 = secondary monitor fullscreen, F4 = tertiary monitor fullscreen, F5 = quartary monitor fullscreen.

4. Plugins, scripts and macros are now displayed in their defined menus or submenus.

5. All ImageJ Menus are updated dynamically to recognize copied scripts and macros instantly.

6. Added two preferences to define the install location of plugins and macros.

7. Improved plugin compatibility by going back to AWT in some dialogs.

### Information:

**Important for MacOSX : Please add the option -Dprism.order=sw in the Eclipse.ini to avoid a crash when using JavaFX!**

**Important for Linux : Please add the option --launcher.GTK_version 2 (don't forget the linebreak before the version number) in the Eclipse.ini to avoid a crash when using JavaFX (JavaFX doesn't run with GTK3 on Linux)!**

Add the following URL as update site: 

**https://bio7.github.io/imagej/**

The important views to open are the ImageJ-Canvas which essentially implements the ImageJ interface and the toolbar. You find them in Eclipse under: Window->Show View->Other. First open the ImageJ-Canvas!

**Window->Show View->Other->ImageJ->ImageJ-Canvas (ImageJ panel and menu)**

**Window->Show View->Other->ImageJ->ImageJ-Toolbar (ImageJ toolbar)**

The ImageJ toolbar can also be opened in the ImageJ-Canvas (ImageJ 'Window' menu).

There is also a self-created thumbnails browser (not speed optimized) for ImageJ default formats included (Thumbnails):

***Window->Show View->Other->ImageJ Extra->Thumbnails***

Ignore the 'Image' view in the same section which is used to show a detached image (opened with a mouse right-click on a image tab).

Preferences can be found under "Preferences ImageJ".

Plugins can be installed in the ImageJ plugins folder (see the plugin folder inside of the Eclipse ImageJ plugin). They will be added to the plugin menu automatically.

A right-click on an image tab will open the displayed image in an extra view for, e.g., side by side views (x,y,z views). They must be closed separately. 

Fullscreen in default SWT_AWT mode is supported for the primary monitor (Press F2 to open and Esc to abort).

Drag and drop of images and plugins should work, too (Drag them on the view or the ImageJ toolbar to open or install!)

### First start: 

Open the ImageJ perspective (***Window->Perspective->Open perspective->Other->ImageJ Edit*** - or use the toolbar action).

To create an ImageJ macro first create a general eclipse project (***File->New->Project->General Project*** - Other Project types are allowed, too!) and then create an ImageJ macro in the project folder (***New->Other->ImageJ->ImageJ Macro File***).

The ImageJ toolbar can be opened in the ImageJ-Canvas view menu (***Window->ImageJ-Toolbar***)

Images can be dropped in a folder of Eclipse and can be opened from within Eclipse (special image icon available when image extension is detected) in the Eclipse ImageJ view. Unknown image formats (default extensions are used only) can be opened with (***Open with->Other->ImageJ Image Editor***) or simply by a drag and
drop action.

#### Key Commands (also visible in the editor context menu):

1. Insert text at selected line from Macro Recorder: (CTRL+SHIFT+R or CMD+SHIFT+R)
2. Debug (CTRL+D or CMD+D)
3. Debug Abort (CTRL+X or CMD+X)
4. Debug Step  (CTRL+E or CMD+E)
5. Debug Trace (CTRL+T or CMD+T)
6. Debug Run To (CTRL+SHIFT+E or CMD+SHIFT+E)
7. Debug Run To Completion (CTRL+SHIFT+X or CMD+SHIFT+X)
8. Evaluate Line (CTRL+Y or CMD+Y)


#### MacOSX (increase fonts):

To increase the fonts on MacOSX in Eclipse globally add the following lines to the default MacOSX
theme css file in Eclipse (/Contents/Eclipse/plugins/org.eclipse.ui.themes_xxx/css/e4_default_mac.css or /Contents/Eclipse/plugins/org.eclipse.ui.themes_xxx/css/e4-dark_mac.css):

For the default CSS (the color attribute is for the text color!):

	{ 
		color:  #000000; 
		font-size: 14px;      
	}
	
For the dark CSS:

	{ 	      
		font-size: 14px;      
	}
	
For a fine control  of the general font size of Eclipse and MacOSX in general use the Tinker software available here:

<https://www.bresink.com/osx/TinkerTool-de.html>


Finer control (two steps):

To increase the fonts on MacOSX delete the argument (twice available) in the Eclipse.ini
file (Contents/Eclipse/eclipse.ini - see: https://wiki.eclipse.org/Eclipse.ini -):

-Dorg.eclipse.swt.internal.carbon.smallFonts
 
To increase only the ImageJ and Thumbnails view menus font add the following lines to the default MacOSX
theme css file in Eclipse (/Contents/Eclipse/plugins/org.eclipse.ui.themes_xxx/css/e4_default_mac.css):

	#com-eco-bio7-imagej.MToolBar {   
		font-size: 14px;
	}
	
	#com-eco-bio7-thumbnails.MToolBar {   
		font-size: 14px;		
	}

Swing fonts on Retina displays (if necessary):

To decrease oversized Swing fonts on Retina displays go to the ImageJ preferences (Preferences ImageJ->Font Correction) and add a negative value (-8 works as a good correction)

