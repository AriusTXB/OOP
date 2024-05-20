package GameComponent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import GameObjects.Bullet;
import GameObjects.Effect;
import GameObjects.Player;
import GameObjects.Ufo;
import GameObjects.Sound.Sound;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class PanelGame extends JComponent 
{
    private int count=0;
    private Graphics2D G2;
    private BufferedImage image;
    private int width;
    private int height;
    private Thread thread;
    private boolean START = true;
    private Key key;
    private int Enemy=100;
    private Player player;
    private List<Bullet> bullets;
    private List<Ufo> Ufos;
    private List<Effect> boomEffects;
    private int shotTime;
    private Sound sound;


    public void START()
    {
        width = getWidth();
        height = getHeight();
        image = new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
        G2 = image.createGraphics();
        G2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        G2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        thread = new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                while(START)
                {
                    long START_TIME = System.nanoTime();
                    DrawBackground();
                    DrawGame();
                    Render();
                    long TIME = System.nanoTime()-START_TIME;
                        if(TIME < START_TIME)
                        {
                            long delay = (TARGET_TIME-TIME)/1000000000;
                            Delay(delay);
                        }
                }
            }    
        });
        initObjectGame();
        initKeyboard();
        initBullets();
        thread.start();
        }
    private void resetGame()
    {
        Ufos.clear();
        count = 0;
        bullets.clear();
        player.changeLocation(630,620);
        player.reset();
        Enemy = 100;
    }
    private void Delay (long speed)
    {
        try
        {
            Thread.sleep(speed);
        }
        catch (InterruptedException ex )
            {
                System.err.println(ex);
            }
    }

    private void initObjectGame()
    {
        sound = new Sound();
        player = new Player ();
        player.changeLocation(630,620);
        Ufos = new ArrayList<>();
        sound.play_music();
        boomEffects = new ArrayList<>();
        new Thread (new Runnable()
        {
            @Override
            public void run()
            {
                while(START)
                    {
                        addUFO();
                        if(Enemy>=64)
                            {Delay((long)(1500*Math.log(Enemy)));}
                        else
                            {Delay(188);}
                    }
            }
        }).start();
    }

    private void initKeyboard()
    {
        key= new Key();
        requestFocus();
        
        addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed (KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_A)
                        key.setKey_left(true);
                else if(e.getKeyCode() == KeyEvent.VK_D)
                        key.setKey_right(true);
                else if(e.getKeyCode() == KeyEvent.VK_W)
                        key.setKey_up(true);
                else if(e.getKeyCode() == KeyEvent.VK_L)
                        key.setKey_l(true);
                else if(e.getKeyCode() == KeyEvent.VK_K)
                        key.setKey_k(true);
                else if(e.getKeyCode() == KeyEvent.VK_S)
                        key.setKey_down(true);
                else if(e.getKeyCode() == KeyEvent.VK_ENTER)
                        key.setKey_enter(true);
                
            }
            @Override
            public void keyReleased(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_A)
                        key.setKey_left(false);
                else if(e.getKeyCode() == KeyEvent.VK_D)
                        key.setKey_right(false);
                else if(e.getKeyCode() == KeyEvent.VK_W)
                        key.setKey_up(false);
                else if(e.getKeyCode() == KeyEvent.VK_L)
                        key.setKey_l(false);
                else if(e.getKeyCode() == KeyEvent.VK_K)
                        key.setKey_k(false);
                else if(e.getKeyCode() == KeyEvent.VK_S)
                        key.setKey_down(false);
                else if(e.getKeyCode() == KeyEvent.VK_ENTER)
                        key.setKey_enter(false);
            }
        }
        );
        new Thread(new Runnable() 
        {
            @Override
            public void run()
            {
                float s=1f;
                while(START)
                {
                    if(player.isAlive()&&count==0&&Enemy!=0)
                        {
                            float angle = player.getAngle();
                            if(key.isKey_left()==true)
                                angle-=s;
                            if(key.isKey_right()==true)
                                angle+=s;
                            if(key.isKey_up())
                                {
                                    player.speedUp();
                                }
                            else if(key.isKey_down())
                                {
                                    player.speedNeg();
                                }
                            else
                                {
                                    player.speedDown();
                                }
                            if (key.isKey_l()||key.isKey_k())
                                {
                                if(shotTime == 0)
                                {
                                    if(key.isKey_k())
                                        {
                                            bullets.add(0,new Bullet(player.getX(), player.getY(), player.getAngle(), 10, 0.2f));
                                        }
                                    if(key.isKey_l())
                                        {
                                            bullets.add(0,new Bullet(player.getX(), player.getY(), player.getAngle(), 5, 0.4f));
                                        }
                                    sound.sound_shoot();
                                }
                                shotTime++;
                                if(shotTime == 10)
                                    {
                                        shotTime = 0;
                                    }   
                                }
                            player.update();
                            player.changeAngle(angle);
                        }
                        else
                            {
                                if(Enemy==0)
                                    {Ufos.clear();}
                                if(key.isKey_enter())
                                {
                                    resetGame();
                                }
                            }   
                    for(int i=0;i<Ufos.size();i++)
                        {
                            Ufo ufo = Ufos.get(i);
                            if(ufo != null)
                                {
                                    ufo.update();
                                    if(!ufo.check(width,height))
                                        {
                                            Ufos.remove(ufo);
                                            count++;
                                        }
                                    else
                                        {
                                            if(player.isAlive())
                                                {
                                                    checkPlayer(ufo);
                                                }
                                        }
                                }
                        }
                    Delay(5);
                    
                }
            }
        }).start();
    }
    
    private void initBullets()
    {
        bullets = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                while(START)
                    {
                        for(int i=0;i<bullets.size();i++)
                            {
                                Bullet bullet = bullets.get(i);
                                if(bullet!= null)
                                    {
                                        bullet.update();
                                        checkBullet(bullet);
                                        if(!bullet.check(width, height))
                                            {
                                                bullets.remove(bullet);
                                            }
                                    }
                                else
                                    {
                                        bullets.remove(bullet);
                                    }
                            }
                        for(int i=0;i<boomEffects.size();i++)
                            {
                                Effect boomEffect = boomEffects.get(i);
                                if (boomEffect!=null)
                                    {
                                        boomEffect.update();
                                        if(!boomEffect.check())
                                            {
                                                boomEffects.remove(boomEffect);
                                            }
                                    }
                                else
                                    {
                                        boomEffects.remove(boomEffect);
                                    }
                            }
                        Delay(1);
                    }
            }
        }).start();
    }
    
    private void checkBullet (Bullet bullet)
        {
            for(int i = 0; i<Ufos.size(); i++)
                {
                    Ufo ufo = Ufos.get(i);
                    if (ufo!=null)
                        {
                         Area area = new Area(bullet.getShape());
                         area.intersect(ufo.getShape());
                         if(!area.isEmpty())
                            {
                                boomEffects.add(new Effect(bullet.getX(), bullet.getY(), 3, 5, 60, 0.5f, new Color(242, 68, 68)));
                                //Ufos.remove(ufo);
                                if(!ufo.updateHP(bullet.getSize()))
                                    {
                                        Enemy--;
                                        Ufos.remove(ufo);
                                        sound.sound_destroy();
                                        double x = ufo.getX()+Ufo.UFO_SIZE/2;
                                        double y = ufo.getY()+Ufo.UFO_SIZE/2;
                                        boomEffects.add(new Effect(x, y, 5, 5, 50, 0.05f, new Color(0, 0, 0)));
                                        boomEffects.add(new Effect(x, y, 5, 5, 75, 0.1f, new Color(244, 55, 0)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 100, 0.3f, new Color(255, 166, 0)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 150, 0.2f, new Color(255, 255, 255)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 100, 0.5f, new Color(255, 34, 0)));

                                    }
                                else
                                {
                                    sound.sound_hit();
                                }
                                bullets.remove(bullet);
                            }
                        }
                }
            
        }

        private void checkPlayer (Ufo ufo)
        {
                    if (ufo!=null)
                        {
                         Area area = new Area(player.getShape());
                         area.intersect(ufo.getShape());
                         if(!area.isEmpty())
                            {
                                if(!ufo.updateHP(200))
                                    {
                                        Ufos.remove(ufo);
                                        sound.sound_destroy();
                                        double x = ufo.getX()+Ufo.UFO_SIZE/2;
                                        double y = ufo.getY()+Ufo.UFO_SIZE/2;
                                        Enemy--;
                                        boomEffects.add(new Effect(x, y, 5, 5, 50, 0.05f, new Color(0, 0, 0)));
                                        boomEffects.add(new Effect(x, y, 5, 5, 75, 0.1f, new Color(244, 55, 0)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 100, 0.3f, new Color(255, 166, 0)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 150, 0.2f, new Color(255, 255, 255)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 100, 0.5f, new Color(255, 34, 0)));
                                    }
                                    if(!player.updateHP(20))
                                    {
                                        player.setAlive(false);
                                        sound.sound_destroy();
                                        double x = player.getX()+Player.PLAYER_SIZE/2;
                                        double y = player.getY()+Player.PLAYER_SIZE/2;
                                        boomEffects.add(new Effect(x, y, 5, 5, 50, 0.05f, new Color(0, 0, 0)));
                                        boomEffects.add(new Effect(x, y, 5, 5, 75, 0.1f, new Color(244, 55, 0)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 100, 0.3f, new Color(255, 166, 0)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 150, 0.2f, new Color(255, 255, 255)));
                                        boomEffects.add(new Effect(x, y, 10, 5, 100, 0.5f, new Color(255, 34, 0)));
                                    }
                            }
                        }
            
        }
    private void addUFO()
    {
    if(Enemy!=0)
        {   
            Random ran = new Random();
            int locationX = ran.nextInt(1350);
            Ufo ufo = new Ufo();
            ufo.changeLocation(locationX, HEIGHT);
            ufo.changeAngle(0);
            Ufos.add(ufo);
        }
    }
    private void DrawBackground()
    {
        Image Background = new ImageIcon(getClass().getResource("/GameImages/BackGround4.jpg")).getImage();
        G2.drawImage(Background, null, getFocusCycleRootAncestor());
    } 

    private void DrawGame()
    {
        if(player.isAlive())
            {player.draw(G2);}
        for (int i = 0; i<bullets.size(); i++)
            {
                Bullet bullet = bullets.get(i);
                if(bullet!=null)
                    {
                        bullet.draw(G2);
                    }
            }
            
            for (int i = 0; i<Ufos.size(); i++)
            {
                Ufo ufo = Ufos.get(i);
                if(ufo!=null)
                    {
                        ufo.draw(G2);
                    }
            }
        for(int i=0;i<boomEffects.size();i++)
            {
                Effect boomEffect = boomEffects.get(i);
                if (boomEffect!=null)
                    {
                        boomEffect.draw(G2);
                    }
            }
            G2.setColor(Color.WHITE);
            G2.setFont(getFont().deriveFont(Font.BOLD, 15f));
            G2.drawString("Enemies left: " + Enemy, 10, 20);
            if (!player.isAlive()||count!=0) {
                G2.setColor(new Color(255,51,51));
                String text = "GAME OVER";
                String textKey = " \" You failed to protect the Earth...\" ";
                String textKey2 = "Press \"Enter\" to try again in another timeline.";
                G2.setFont(getFont().deriveFont(Font.BOLD, 50f));
                FontMetrics fm = G2.getFontMetrics();
                Rectangle2D r2 = fm.getStringBounds(text, G2);
                double textWidth = r2.getWidth();
                double textHeight = r2.getHeight();
                double x = (width - textWidth) / 2;
                double y = (height - textHeight) / 2;
                G2.drawString(text, (int) x, (int) y + fm.getAscent());
                G2.setColor(Color.WHITE);
                G2.setFont(getFont().deriveFont(Font.BOLD, 20f));
                fm = G2.getFontMetrics();
                r2 = fm.getStringBounds(textKey, G2);
                textWidth = r2.getWidth();
                textHeight = r2.getHeight();
                x = (width - textWidth) / 2;
                y = (height - textHeight) / 2;
                G2.drawString(textKey, (int) x, (int) y + fm.getAscent() + 50);
                r2 = fm.getStringBounds(textKey2, G2);
                textWidth = r2.getWidth();
                textHeight = r2.getHeight();
                x = (width - textWidth) / 2;
                y = (height - textHeight) / 2;
                G2.drawString(textKey2, (int) x, (int) y + fm.getAscent() + 70);
            }
            if (Enemy==0) {
                G2.setColor(new Color(61,237,151));
                String text = "YOU WON";
                String textKey = " \" Congratulation soldier ! You have accomplish the mission !\"";
                String textKey2 = "Them aliens are scared but they will comeback for sure!";
                String textKey3 = "The reinforcements is going to take care of the rest though. Press \"ENTER\" if you want to join them !";
                G2.setFont(getFont().deriveFont(Font.BOLD, 50f));
                FontMetrics fm = G2.getFontMetrics();
                Rectangle2D r2 = fm.getStringBounds(text, G2);
                double textWidth = r2.getWidth();
                double textHeight = r2.getHeight();
                double x = (width - textWidth) / 2;
                double y = (height - textHeight) / 2;
                G2.drawString(text, (int) x, (int) y + fm.getAscent());
                G2.setColor(Color.WHITE);
                G2.setFont(getFont().deriveFont(Font.BOLD, 20f));
                fm = G2.getFontMetrics();
                r2 = fm.getStringBounds(textKey, G2);
                textWidth = r2.getWidth();
                textHeight = r2.getHeight();
                x = (width - textWidth) / 2;
                y = (height - textHeight) / 2;
                G2.drawString(textKey, (int) x, (int) y + fm.getAscent() + 50);
                r2 = fm.getStringBounds(textKey2, G2);
                textWidth = r2.getWidth();
                textHeight = r2.getHeight();
                x = (width - textWidth) / 2;
                y = (height - textHeight) / 2;
                G2.drawString(textKey2, (int) x, (int) y + fm.getAscent() + 70);
                r2 = fm.getStringBounds(textKey3, G2);
                textWidth = r2.getWidth();
                textHeight = r2.getHeight();
                x = (width - textWidth) / 2;
                y = (height - textHeight) / 2;
                G2.drawString(textKey3, (int) x, (int) y + fm.getAscent() + 90);
            }
        
    }

    private void Render()
    {
        Graphics G = getGraphics();
        G.drawImage(image, 0, 0, null);
        G.dispose();
    }

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

}
