package flappyBirds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import pkg2dgamesframework.QueueList;

public class Chimney_group {
private QueueList<Chimney>chimneys;
private BufferedImage chimneyImage,chimneyImage2;
public static int SIZE=6;
private int topChimney=-350;
private int bottomChimney=200;
public Chimney getChimny(int i)
{
	return chimneys.get(i);
}
public int getRanDomY()
{
	Random random=new Random();
	int a=random.nextInt(10);
	return a*35;
}
public Chimney_group()
{
	try {
		chimneyImage=ImageIO.read(new File("Assets/chimney.png"));
		chimneyImage2=ImageIO.read(new File("Assets/chimney_.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	chimneys=new QueueList<Chimney>();
	Chimney cn;
	for(int i=0;i<SIZE/2;i++)
	{
		int dentaY=getRanDomY();
		cn=new Chimney(830+i*300, bottomChimney+dentaY, 74, 400);
		chimneys.push(cn);
		cn=new Chimney(830+i*300, topChimney+dentaY, 74, 400);
		chimneys.push(cn);
	}
}
public void resestChimneys()
{
	chimneys=new QueueList<Chimney>();
	Chimney cn;
	for(int i=0;i<SIZE/2;i++)
	{
		int dentaY=getRanDomY();
		cn=new Chimney(830+i*300, bottomChimney+dentaY, 74, 400);
		chimneys.push(cn);
		cn=new Chimney(830+i*300, topChimney+dentaY, 74, 400);
		chimneys.push(cn);
	}
}
public void update()
{
	for(int i=0;i<SIZE;i++)
	{
		chimneys.get(i).update();
	}
	if(chimneys.get(0).getPosX()<-74)
	{
		int dentaY=getRanDomY();
		Chimney cn;
		cn=chimneys.pop();
		cn.setPosX(chimneys.get(4).getPosX()+300);
		cn.setPosY(bottomChimney+dentaY);
		cn.setIsBehindBird(false);
		chimneys.push(cn);
		cn=chimneys.pop();
		cn.setPosX(chimneys.get(4).getPosX());
		cn.setPosY(topChimney+dentaY);

		cn.setIsBehindBird(false);
		chimneys.push(cn);
	}
}
public void paint(Graphics2D g2)
{
	for(int i=0;i<SIZE;i++)
	{
		if(i%2==0)
		{
		g2.drawImage(chimneyImage, (int)chimneys.get(i).getPosX(),(int)chimneys.get(i).getPosY(),null);
		}
		else
		{
			g2.drawImage(chimneyImage2, (int)chimneys.get(i).getPosX(),(int)chimneys.get(i).getPosY(),null);

		}
	}
}
}
