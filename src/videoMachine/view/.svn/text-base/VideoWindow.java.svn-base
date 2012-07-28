package videoMachine.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class VideoWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -812524517506370439L;
	
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	
	public VideoWindow(){
		
		this.setPreferredSize(new Dimension(640, 480));
		this.pack();
		
		try
		{
			NativeLibrary.addSearchPath(
				RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC"
		    );
			
			NativeLibrary.addSearchPath(
				RuntimeUtil.getLibVlcLibraryName(), "/home/linux/vlc/install/lib"
		    );
			
			NativeLibrary.addSearchPath(
				RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib"
			);
			
			Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(this, "Unable to load VLC");
		}
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected MediaPlayerFactory onGetMediaPlayerFactory() {
                return super.onGetMediaPlayerFactory();
            }

            @Override
            protected String[] onGetMediaPlayerFactoryArgs() {
                return super.onGetMediaPlayerFactoryArgs();
            }

            @Override
            protected FullScreenStrategy onGetFullScreenStrategy() {
                return super.onGetFullScreenStrategy();
            }

            @Override
            protected void onBeforeRelease() {
                super.onBeforeRelease();
            }

            @Override
            protected void onAfterRelease() {
                super.onAfterRelease();
            }

            // Override whatever listener methods you're interested in...

            @Override
            public void videoOutput(MediaPlayer mediaPlayer, int newCount) {
                super.videoOutput(mediaPlayer, newCount);
                //System.out.println("videoOutput");
            }

            @Override
            public void playing(MediaPlayer mediaPlayer) {
                super.playing(mediaPlayer);
                //System.out.println("Playing");
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                super.error(mediaPlayer);
                //System.out.println("Error");
            }
		};
		
		this.setContentPane(mediaPlayerComponent);
		//this.setVisible(false);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				mediaPlayerComponent.getMediaPlayer().stop();
			}
		});
	}
	
	public void playVideo(String url){
		
		if (!this.isVisible())
			this.setVisible(true);
		
		mediaPlayerComponent.getMediaPlayer().playMedia("file:///" + url);
	}

}