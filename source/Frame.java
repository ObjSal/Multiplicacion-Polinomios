import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame// implements KeyListener
{
	public static final long serialVersionUID = 1L;
	canvas cCanvas;
	
	DisplayMode displayMode;
	GraphicsEnvironment environment;
	
	Frame()
	{
		super("Multiplicacion Por Coeficientes Separados");
		
		/*environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		displayMode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		
		GraphicsDevice device;
		
		device = environment.getDefaultScreenDevice();
		
		setUndecorated(true);
		*/
   //     setResizable(false);
		/*
		device.setFullScreenWindow(this);
		
		try
		{
			if(device.isDisplayChangeSupported())
				device.setDisplayMode(displayMode);
			else
			{
				device.setFullScreenWindow(null);
				dispose();
				setUndecorated(false);
				setResizable(true);
				setSize(800,600);
			}
		}catch(Exception e){}
		*/
        setSize(815,600);
		//add(panel = new Panel());
		getContentPane().add(cCanvas = new canvas());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(cCanvas);
		setVisible(true);
		new Thread(cCanvas).start();
		//start();
	}
	/*
	void setTransCursos()
	{
		Image imgTrans = new ImageIcon(
			Toolkit.getDefaultToolkit().createImage(
				Main.class.getResource("/img/trans.png")
			)
		).getImage();
		Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().
		createCustomCursor(imgTrans, new Point(0, 0), "blank");
		setCursor(BLANK_CURSOR);
	}
	*/
	/*
	void start()
	{
		//setTransCursos();
		while(panel._state!=STATE.SALIR_APP)
		{
			//Thread.yield();
			panel.repaint();
			try{Thread.sleep(5);}catch(Exception e){}
		}
		//Mouse.show( );
		System.exit(0);
	}
	*/
}
