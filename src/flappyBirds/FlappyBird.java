package flappyBirds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

public class FlappyBird extends GameScreen {

	private BufferedImage birds;
	private Animation bird_animation;
	public static float g=0.15f;
	private Birds bird;
	private Ground ground;
	private Chimney_group chimnygroup;
	private int BEGIN_SCREEN=0;
	private int GAMEPLAY_SCREEN=1;
	private int GAMEOVER_SCREEN=2;
	private int Point=0;
	private int currentScreen=BEGIN_SCREEN;
	Font f=new Font("Arial", Font.BOLD, 40);
	
	public FlappyBird()
	{
		super(800,600);
		
		try {
			birds=ImageIO.read(new File("Assets/bird_sprite.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bird_animation=new Animation(70);
		AFrameOnImage f;
		f=new AFrameOnImage(0, 0, 60, 60);
		bird_animation.AddFrame(f);
		f=new AFrameOnImage(60,0, 60, 60);
		bird_animation.AddFrame(f);
		f=new AFrameOnImage(120, 0, 60, 60);
		bird_animation.AddFrame(f);
		f=new AFrameOnImage(60, 0, 60, 60);
		bird_animation.AddFrame(f);
		bird=new Birds(350, 250, 50, 50);
		ground=new Ground();
		chimnygroup=new Chimney_group();
		BeginGame();
	}
	public static void main(String[] args) {
		new FlappyBird();
	}
private void resetGame()
{
	bird.setPos(350, 250);
	bird.setvt(0);
	bird.setLive(true);
	Point=0;
	chimnygroup.resestChimneys();
}
	@Override
	public void GAME_UPDATE(long deltaTime) {
		
		if(currentScreen==BEGIN_SCREEN)
		{
			resetGame();
		}
		else if(currentScreen==GAMEPLAY_SCREEN)
		{
			if(bird.getLive())
			bird_animation.Update_Me(deltaTime);
			bird.update(deltaTime);
			ground.Update();
			chimnygroup.update();
			for(int i=0;i<chimnygroup.SIZE;i++)
	        {
	        if(bird.getRect().intersects(chimnygroup.getChimny(i).getRect()))
	        {
	        	bird.setLive(false);
	        }
	        }

			for(int i=0;i<chimnygroup.SIZE;i++)
			{
	if(bird.getPosX()>chimnygroup.getChimny(i).getPosX()&&!chimnygroup.getChimny(i).getIsBehindBird()&&i%2==0)
			{
				Point++;
				bird.getMoneyShound.play();
				chimnygroup.getChimny(i).setIsBehindBird(true);
			}
			}
			
	        if(bird.getPosY()+bird.getH()>ground.getGround())currentScreen=GAMEOVER_SCREEN;
	        
		}
		else
		{
			
		}
		
		
	}

	@Override
	public void GAME_PAINT(Graphics2D g2) {
		g2.setFont(f);
		g2.setColor(Color.decode("#b8daef"));
		g2.fillRect(0, 0, MASTER_WIDTH, MASTER_HEIGHT);
		chimnygroup.paint(g2);
        ground.Paint(g2);

		if(bird.getIsflying())
		bird_animation.PaintAnims((int)bird.getPosX(), (int)bird.getPosY(), birds, g2, 0, -1);
		else
			bird_animation.PaintAnims((int)bird.getPosX(), (int)bird.getPosY(), birds, g2, 0, 0);
        
        if(currentScreen==BEGIN_SCREEN)
        {
        	g2.setColor(Color.BLUE);
        	g2.drawString("START", 300, 400);
        	g2.drawString("(PRESS SPACE)", 220, 470);
        }
        if(currentScreen==GAMEOVER_SCREEN)
        {
        	g2.setColor(Color.RED);
        	g2.drawString("GAME OVER",300, 300);
        	g2.drawString("POINT : "+Point,300, 400);
        	g2.drawString("(PRESS SPACE TO RETURN)",150, 470);
        }
    	g2.setColor(Color.YELLOW);
        g2.drawString("POINT : "+Point, 20, 50);
	}

	@Override
	public void KEY_ACTION(KeyEvent e, int Event) {
		if(Event==KEY_PRESSED)
		{
			if(currentScreen==BEGIN_SCREEN)
			{
				currentScreen=GAMEPLAY_SCREEN;
			}
			else if(currentScreen==GAMEPLAY_SCREEN)
			{
				if(bird.getLive())
				bird.Fly();
			}
			else if(currentScreen==GAMEOVER_SCREEN)
			{
			currentScreen=BEGIN_SCREEN;	
			}
		}
	}
}
