package chapter_7;

public class AudioTester {

	public static void main(String[] args) {
		for (int n =0; n<5; n++){
			SoundFX.COLLIDE.play();
			try
			{
				Thread.sleep(1000);
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
