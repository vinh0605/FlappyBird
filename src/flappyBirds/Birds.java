package flappyBirds;

import java.awt.Rectangle;
import java.io.File;

import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

public class Birds extends Objects {
	private float vt=0; 
	private boolean isflying=false;
	private Rectangle rect;
	private boolean isLive=true;
	public SoundPlayer flapSound,bupSound,getMoneyShound;
public  Birds(int x,int y,int w,int h)
{
	super(x,y,w,h);
	rect=new Rectangle(x,y,w,h);
	flapSound=new SoundPlayer(new File("Assets/flap.wav"));
	bupSound=new SoundPlayer(new File("Assets/bup.wav"));
	getMoneyShound=new SoundPlayer(new File("Assets/getpoint.wav"));
}
public void setLive(boolean b)
{
	if(isLive==true&&b==false)bupSound.play();
	isLive=b;
}
public boolean getLive()
{
	return isLive;
}
public void update(long deltatime)
{
	vt+=FlappyBird.g;
	this.setPosY(this.getPosY()+vt);
	this.rect.setLocation((int)this.getPosX(),(int)this.getPosY());
	if(vt<0) isflying=true;
	else isflying=false;
}
public Rectangle getRect()
{
	return rect;
}
public void setvt(float vt)
{
	this.vt=vt;
}
public void Fly()
{
	vt=-3;
	flapSound.play();
}
public boolean getIsflying()
{
	return isflying;
}
}
