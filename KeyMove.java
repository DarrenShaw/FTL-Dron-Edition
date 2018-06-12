package gameplay;

import java.awt.event.*;
@SuppressWarnings("static-access")
public class KeyMove implements KeyListener
{
    public boolean right,left,up,down,space,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,
    q,r,s,t,u,v,w,x,y,z,one,two,three,four,five,six,seven,eight,nine,zero,enter;
    
	public void keyPressed(KeyEvent evt)
    {
        if(evt.getKeyCode()==evt.VK_DOWN)
            down=true;
        if(evt.getKeyCode()==evt.VK_UP)
            up=true;
        if(evt.getKeyCode()==evt.VK_LEFT)
            left=true;
        if(evt.getKeyCode()==evt.VK_RIGHT)
            right=true;
        if(evt.getKeyCode()==evt.VK_W)
            w=true;
        if(evt.getKeyCode()==evt.VK_A)
            a=true;
        if(evt.getKeyCode()==evt.VK_S)
            s=true;
        if(evt.getKeyCode()==evt.VK_D)
            d=true;
        if(evt.getKeyCode()==evt.VK_F)
            f=true;

        if(evt.getKeyCode()==evt.VK_B)
            b=true;
        if(evt.getKeyCode()==evt.VK_C)
            c=true;
        if(evt.getKeyCode()==evt.VK_E)
            e=true;
        if(evt.getKeyCode()==evt.VK_F)
            f=true;
        if(evt.getKeyCode()==evt.VK_G)
            g=true;
        if(evt.getKeyCode()==evt.VK_H)
            h=true;
        if(evt.getKeyCode()==evt.VK_I)
            i=true;
        if(evt.getKeyCode()==evt.VK_J)
            j=true;
        if(evt.getKeyCode()==evt.VK_K)
            k=true;
        if(evt.getKeyCode()==evt.VK_L)
            l=true;
        if(evt.getKeyCode()==evt.VK_M)
            m=true;
        if(evt.getKeyCode()==evt.VK_N)
            n=true;
        if(evt.getKeyCode()==evt.VK_O)
            o=true;
        if(evt.getKeyCode()==evt.VK_P)
            p=true;
        if(evt.getKeyCode()==evt.VK_Q)
            q=true;
        if(evt.getKeyCode()==evt.VK_R)
            r=true;
        if(evt.getKeyCode()==evt.VK_S)
            s=true;
        if(evt.getKeyCode()==evt.VK_T)
            t=true;
        if(evt.getKeyCode()==evt.VK_U)
            u=true;
        if(evt.getKeyCode()==evt.VK_V)
            v=true;
        if(evt.getKeyCode()==evt.VK_W)
            w=true;
        if(evt.getKeyCode()==evt.VK_X)
            x=true;
        if(evt.getKeyCode()==evt.VK_Y)
            y=true;
        if(evt.getKeyCode()==evt.VK_Z)
            z=true;
        if(evt.getKeyCode()==evt.VK_1)
            one=true;
        if(evt.getKeyCode()==evt.VK_2)
            two=true;
        if(evt.getKeyCode()==evt.VK_3)
            three=true;
        if(evt.getKeyCode()==evt.VK_4)
            four=true;
        if(evt.getKeyCode()==evt.VK_5)
            five=true;
        if(evt.getKeyCode()==evt.VK_6)
            six=true;
        if(evt.getKeyCode()==evt.VK_7)
            seven=true;
        if(evt.getKeyCode()==evt.VK_8)
            eight=true;
        if(evt.getKeyCode()==evt.VK_9)
            nine=true;
        if(evt.getKeyCode()==evt.VK_0)
            zero=true;
        if(evt.getKeyCode()==evt.VK_SPACE)
            space = true;
        if(evt.getKeyCode()==evt.VK_ENTER)
            enter = true;
    }

    public void keyReleased(KeyEvent evt)
    {
        if(evt.getKeyCode()==evt.VK_DOWN)
            down=false;
        if(evt.getKeyCode()==evt.VK_UP)
            up=false;
        if(evt.getKeyCode()==evt.VK_LEFT)
            left=false;
        if(evt.getKeyCode()==evt.VK_RIGHT)
            right=false;
        if(evt.getKeyCode()==evt.VK_W)
            w=false;
        if(evt.getKeyCode()==evt.VK_A)
            a=false;
        if(evt.getKeyCode()==evt.VK_S)
            s=false;
        if(evt.getKeyCode()==evt.VK_D)
            d=false;
        if(evt.getKeyCode()==evt.VK_F)
            f=false;

        if(evt.getKeyCode()==evt.VK_B)
            b=false;
        if(evt.getKeyCode()==evt.VK_C)
            c=false;
        if(evt.getKeyCode()==evt.VK_E)
            e=false;
        if(evt.getKeyCode()==evt.VK_F)
            f=false;
        if(evt.getKeyCode()==evt.VK_G)
            g=false;
        if(evt.getKeyCode()==evt.VK_H)
            h=false;
        if(evt.getKeyCode()==evt.VK_I)
            i=false;
        if(evt.getKeyCode()==evt.VK_J)
            j=false;
        if(evt.getKeyCode()==evt.VK_K)
            k=false;
        if(evt.getKeyCode()==evt.VK_L)
            l=false;
        if(evt.getKeyCode()==evt.VK_M)
            m=false;
        if(evt.getKeyCode()==evt.VK_N)
            n=false;
        if(evt.getKeyCode()==evt.VK_O)
            o=false;
        if(evt.getKeyCode()==evt.VK_P)
            p=false;
        if(evt.getKeyCode()==evt.VK_Q)
            q=false;
        if(evt.getKeyCode()==evt.VK_R)
            r=false;
        if(evt.getKeyCode()==evt.VK_S)
            s=false;
        if(evt.getKeyCode()==evt.VK_T)
            t=false;
        if(evt.getKeyCode()==evt.VK_U)
            u=false;
        if(evt.getKeyCode()==evt.VK_V)
            v=false;
        if(evt.getKeyCode()==evt.VK_W)
            w=false;
        if(evt.getKeyCode()==evt.VK_X)
            x=false;
        if(evt.getKeyCode()==evt.VK_Y)
            y=false;
        if(evt.getKeyCode()==evt.VK_Z)
            z=false;

        if(evt.getKeyCode()==evt.VK_SPACE)
            space = false;
        if(evt.getKeyCode()==evt.VK_1)
            one=false;
        if(evt.getKeyCode()==evt.VK_2)
            two=false;
        if(evt.getKeyCode()==evt.VK_3)
            three=false;
        if(evt.getKeyCode()==evt.VK_4)
            four=false;
        if(evt.getKeyCode()==evt.VK_5)
            five=false;
        if(evt.getKeyCode()==evt.VK_6)
            six=false;
        if(evt.getKeyCode()==evt.VK_7)
            seven=false;
        if(evt.getKeyCode()==evt.VK_8)
            eight=false;
        if(evt.getKeyCode()==evt.VK_9)
            nine=false;
        if(evt.getKeyCode()==evt.VK_0)
            zero=false;

        if(evt.getKeyCode()==evt.VK_ENTER)
            enter = false;
    }

    public void keyTyped(KeyEvent e){}

}
