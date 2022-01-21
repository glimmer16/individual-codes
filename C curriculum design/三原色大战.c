#include<stdio.h>
#include<conio.h>
#include<graphics.h>
#include<stdlib.h>   //生成随机数头文件
#include <time.h>
#include<MMSystem.h>
#define NULL 0
#pragma comment(lib,"Winmm.lib")
int LEFT(int temp,int m,int n);
int RIGHT(int temp,int m,int n);
void LM(int m,int n,int p);
int move(int m,int n,int p);
void GAME();
int Game2(int n);
int LEFT(int temp,int m,int n)
{
	int location=0,k=0;
	if(m==0&&n>0&&n<=540&&k==0)
	{
		n-=temp*60;	
		if(n<0)
		{
			m+=(-n);
			n=0;
		}
	}
	else if(m>=0&&m<660&&n==0)
	{
		m+=temp*60;
		if(m>660)
		{
			n+=(m-660);
			m=660;
		}
	}
	else if(m==660&&n>=0&&n<540)
	{
		n+=temp*60;
		if(n>540)
		{
			m-=(n-540);
			n=540;
		}
	}
	else if(m>0&&m<=660&&n==540)
	{
		m-=temp*60;
		if(m<0||m==0&&n==540)
		{
			m=0;
			location+=10000000;
		}
	}
	location+=m*1000+n;
	return location;
}
int RIGHT(int temp,int m,int n)
{
	int location=0,k=0;
	if(m==660&&n>=0&&n<540)
	{
		n+=temp*60;
		if(n>540)
		{
			m-=(n-540);
			n=540;
		}
	}
	else if(m>0&&m<=660&&n==540)
	{
		m-=temp*60;
		if(m<0)
		{
			n-=(-m);
			m=0;
		}
	}
	else if(m==0&&n>0&&n<=540&&k==0)
	{
		n-=temp*60;	
		if(n<0)
		{
			m+=(-n);
			n=0;
		}
	}
	else if(m>=0&&m<660&&n==0)
	{
		m+=temp*60;
		if(m>660||m==660&&n==0)
		{
			m=660;
			location+=10000000;
		}
	}
	location+=m*1000+n;
	return location;
}
int score(int temp,int m,int n,int p)
{
	int score=0;
	if(p==1)
	{
		if((n==0&&(m==600||m==240))||(m==0&&n==180)||(n==540&&(m==60||m==420))||(m==660&&n==360))
		{
			score=score+2;
		}
		else if((n==0&&(m==420||m==360||m==60))||(m==0&&(n==60||n==360||n==420))||(n==540&&(m==240||m==300||m==600))||(m==660&&(n==480||n==180||n==120)))
		{
			score=score+1;
		}
		else
		{
			score=score-1;
		}
	}
	if(p==2)
	{
		if((n==0&&(m==540||m==180))||(m==0&&n==240)||(n==540&&(m==120||m==480))||(m==660&&n==300))
		{
			score=score+2;
		}
		else if((n==0&&(m==300||m==360))||(m==0&&(n==60||n==120||n==480||n==420))||(n==540&&(m==300||m==360))||(m==660&&(n==480||n==420||n==120||n==60)))
		{
			score=score+1;
		}
		else
		{
			score=score-1;
		}
	}
	if(p==3)
	{
		if((n==0&&(m==480||m==120))||(m==0&&n==300)||(n==540&&(m==180||m==540))||(m==660&&n==240))
		{
			score=score+2;
		}
		else if((n==0&&(m==420||m==300||m==60))||(m==0&&(n==120||n==360||n==480))||(n==540&&(m==240||m==360||m==600))||(m==660&&(n==420||n==180||n==60)))
		{
			score=score+1;
		}
		else
		{
			score=score-1;
		}
	}
	if(p==4)
	{
		score=score+1;
	}
	return score;
}
int random1()
{
	int a,b,n=0;
	MOUSEMSG m;
	while(true)
    {
	FlushMouseMsgBuffer();
	m=GetMouseMsg();
	switch(m.uMsg)
	{
		case WM_LBUTTONDOWN:
		if(MouseHit&&n==0)
		{
			        n=1;
					srand((unsigned)time(NULL));
					a=rand()%6+1;
					settextcolor(BLACK);
					settextstyle(35,20,_T("微软雅黑"));
					setbkcolor(WHITE);
					outtextxy(100,210,_T("步数:"));
					TCHAR f1[5];
					_stprintf(f1, _T("%d"),a);
					outtextxy(120,260, f1);	
					if(MouseHit&&n==1)
						return a;
		}
	}
    }
}
int random2()
{
	int a,b,n=0;
	MOUSEMSG m;
	while(true)
    {
	FlushMouseMsgBuffer();
	m=GetMouseMsg();
	switch(m.uMsg)
	{
		case WM_LBUTTONDOWN:
		if(MouseHit&&n==0)
		{
					n=1;
				    srand((unsigned)time(NULL));
					b=rand()%6+1;
					settextcolor(BLACK);
					settextstyle(35,20,_T("微软雅黑"));
					setbkcolor(WHITE);
					outtextxy(500,300,_T("步数:"));
					TCHAR f2[5];
					_stprintf(f2, _T("%d"),b);
					outtextxy(520,350, f2);	
					if(MouseHit&&n==1)
						return b;
		}

	}
    }
}
void LM(int m,int n,int p)
{
	IMAGE img[10];
	if(p==1)
	{
		loadimage(&img[0],_T("qi red.jpg"));
		putimage(m,n,&img[0]);
	}
	if(p==2)
	{
		loadimage(&img[1],_T("qi yellow.jpg"));
		putimage(m,n,&img[1]);
	}
	if(p==3)
	{
		loadimage(&img[2],_T("qi blue.jpg"));
		putimage(m,n,&img[2]);
	}
	if(p==4)
	{
		loadimage(&img[3],_T("qi black.jpg"));
		putimage(m,n,&img[3]);
	}
}
int move(int m,int n,int p)
{
	MOUSEMSG h;
	IMAGE img[10];
	if(p==1)
	{
		loadimage(&img[0],_T("qi red.jpg"));
		putimage(m,n,&img[0]);
	}
	if(p==2)
	{
		loadimage(&img[1],_T("qi yellow.jpg"));
		putimage(m,n,&img[1]);
	}
	if(p==3)
	{
		loadimage(&img[2],_T("qi blue.jpg"));
		putimage(m,n,&img[2]);
	}
	if(p==4)
	{
		loadimage(&img[3],_T("qi black.jpg"));
		putimage(m,n,&img[3]);
	}	
	return 0;
}
int Game1(int n)
{
	int s1=0,s2=0,k=0;
	MOUSEMSG m;
	loadimage(NULL,"rule.jpg");
	while(k!=1)
	{
		m=GetMouseMsg();
		switch(m.uMsg)
		{
			case WM_LBUTTONDOWN:
			if(MouseHit)
			{	
				loadimage(NULL,"player1.jpg");
				while(true)
				{
					FlushMouseMsgBuffer();
					m=GetMouseMsg();
					switch(m.uMsg)
					{
						case WM_LBUTTONDOWN:
						if(m.x>50&&m.x<190&&m.y>408&&m.y<500)
						{	
							s1=1;
							k=1;
						    return s1;
						}
						else if(m.x>284&&m.x<430&&m.y>408&&m.y<500)
						{	
							s1=2;
							k=1;											    
							return s1;
						}
						else if(m.x>540&&m.x<677&&m.y>408&&m.y<500)
						{	
							s1=3;					    
							k=1;				
							return s1;
						}
						break;
					}
				}break;	
			}
		}
	}
}
int Game2(int n)
{
	int s2=0,k=0;
	loadimage(NULL,"player2.jpg");
	FlushMouseMsgBuffer();
	MOUSEMSG m;
				while(k!=2)
				{
					FlushMouseMsgBuffer();
					m=GetMouseMsg();
					switch(m.uMsg)
					{
						case WM_LBUTTONDOWN:
						if(m.x>50&&m.x<190&&m.y>408&&m.y<500)
						{	
							s2=1;
							k=2;
						    return s2;
						}
						else if(m.x>284&&m.x<430&&m.y>408&&m.y<500)
						{	
							s2=2;
							k=2;
						    return s2;
						}
						else if(m.x>540&&m.x<677&&m.y>408&&m.y<500)
						{	
							s2=3;
							k=2;
						    return s2;
						}
						break;	
					}
				}		
}
void GameWin1()
{
	settextcolor(RED);
	setfont(26,0,_T("楷体"));
	setfillstyle(BLACK);

	outtextxy(550,245,_T("恭喜玩家一获胜!"));
	outtextxy(550,275,_T("按任意键返回"));
	getch();
}
void GameWin2()
{
	settextcolor(RED);
	setfont(26,0,_T("楷体"));
	setfillstyle(BLACK);

	outtextxy(550,245,_T("恭喜玩家二获胜!"));
	outtextxy(550,275,_T("按任意键返回"));
	getch();
}
void main()
{
	initgraph(720,600);
	int k=0,p1=0,p2=0,n=0,temp1,temp2,sum1=0,sum2=0,m1=0,n1=540,m2=660,n2=0,location1,location2,a,b,score1=0,score2=0,pm1[20],pn1[20],pm2[20],pn2[20],i1=0,i2=0,j,ul;
	IMAGE img[10];
	loadimage(NULL,"jiemian.jpg");
	mciSendString(_T("open background.mp3 Alias music"), NULL, 0, NULL); //打开背景音乐
	FlushMouseMsgBuffer();
	MOUSEMSG m;
	while(true)
	{
		m = GetMouseMsg();
		switch(m.uMsg)
		{
			case WM_LBUTTONDOWN:
			if(m.x>397 && m.x<630 &&m.y>155 && m.y<245&&n==0)
			{				
				p1=Game1(p1);
                outtextxy(500,50,_T("玩家1第二次点击 选择角色"));
				n=1;
				break;
			}
			if(m.x>0&&m.x<720&&m.y>0&&m.y<600&&n==1)
			{
				p2=Game2(p2);
				outtextxy(500,50,_T("玩家2第二次点击 选择角色"));
				n=2;
				break;
			}
			if(m.x>397 && m.x<630 &&m.y>338 && m.y<428&&n==0)
			{				
				p1=Game1(p1);
                outtextxy(500,50,_T("玩家1第二次点击 选择角色"));
				p2=4;
				n=2;
				break;
			}
			if(m.x>397 && m.x<630 &&m.y>428 && m.y<518&&n==0)
			{				
				p1=4;
				p2=4;
				n=2;
				break;
			}
			if(m.x>0&&m.x<720&&m.y>0&&m.y<600&&n==2)
			{
				n=3;
				loadimage(NULL,"qipan.jpg");

				LM(0,540,p1);
				LM(660,0,p2);
				break;
			}
			if(m.x>0&&m.x<720&&m.y>0&&m.y<600&&n==3)
			{

				    if(p1==1)
				{
                     loadimage(&img[4],_T("ge red.jpg"));
		             putimage(m1,n1,&img[4]);
				}
				    if(p1==2)
				{
                     loadimage(&img[5],_T("ge yellow.jpg"));
		             putimage(m1,n1,&img[5]);
				}
				    if(p1==3)
				{
                     loadimage(&img[6],_T("ge blue.jpg"));
		             putimage(m1,n1,&img[6]);
				}
				    if(p1==4)
				{
                     loadimage(&img[7],_T("ge.jpg"));
		             putimage(m1,n1,&img[7]);
				}				
				temp1=random1();
				location1=LEFT(temp1,m1,n1);
				m1=location1/1000;
				n1=location1-m1*1000;
				if((m1==0&&n1==0)||(m1==660&&n1==540))
				{
					p1=4;
					settextcolor(RED);
	                setfont(50,0,_T("微软雅黑"));
	                setfillstyle(BLACK);
	                bar(200,240,470,320);	
	                rectangle(200,240,470,320);
	                outtextxy(270,245,_T("进化！！!"));
				}
				pm1[i1]=m1;
				pn1[i1]=n1;
				i1++;
				for(j=0;j<i2;j++)
				{
					if(p1!=4)
					{
						if(m1==pm2[j]&&n1==pn2[j])
					    score1=score1-2;
					}
				}
				a=move(m1,n1,p1);
				score1=score1+score(temp1,m1,n1,p1);
					settextcolor(BLACK);
					settextstyle(35,20,_T("微软雅黑"));
					setbkcolor(WHITE);
				    bar(290,125,350,185);	
	                rectangle(290,125,350,185);
					TCHAR f2[5];
					_stprintf(f2, _T("%d"),score1);
					outtextxy(290,125, f2);	
				n=4;
				if(location1>10000000&&(score1>score2||score1==score2))
				{	
					
	                 if(p1==1)
				  {
                     loadimage(NULL,"winred.jpg");
				  }
				    if(p1==2)
				  {
                     loadimage(NULL,"winyellow.jpg");
				  }
				    if(p1==3)
				  {
                     loadimage(NULL,"winblue.jpg");
				  }
					if(p1==4)
					{
						loadimage(NULL,"winblack.jpg");
					}
					GameWin1();
					n=0;
				k=0;p1=0;p2=0;sum1=0;sum2=0;m1=0;n1=540;m2=660;n2=0;score1=0;score2=0;i1=0;i2=0;
				loadimage(NULL,"jiemian.jpg");

				}
				if(location1>10000000&&score1<score2)
				{	
		             if(p2==1)
				  {
                     loadimage(NULL,"winred.jpg");
				  }
				    if(p2==2)
				  {
                     loadimage(NULL,"winyellow.jpg");
				  }
				    if(p2==3)
				  {
                     loadimage(NULL,"winblue.jpg");
				  }	
					if(p2==4)
					{
						loadimage(NULL,"winblack.jpg");
					}
					GameWin2();
					n=0;
				k=0;p1=0;p2=0;sum1=0;sum2=0;m1=0;n1=540;m2=660;n2=0;score1=0;score2=0;i1=0;i2=0;
				loadimage(NULL,"jiemian.jpg");

				}
			}
			if(m.x>0&&m.x<720&&m.y>0&&m.y<600&&n==4)
			{

				    if(p2==1)
				{
                     loadimage(&img[4],_T("ge red.jpg"));
		             putimage(m2,n2,&img[4]);
				}
				    if(p2==2)
				{
                     loadimage(&img[5],_T("ge yellow.jpg"));
		             putimage(m2,n2,&img[5]);
				}
				    if(p2==3)
				{
                     loadimage(&img[6],_T("ge blue.jpg"));
		             putimage(m2,n2,&img[6]);
				}
				    if(p2==4)
				{
                     loadimage(&img[7],_T("ge.jpg"));
		             putimage(m2,n2,&img[7]);
				}
				temp2=random2();
				location2=RIGHT(temp2,m2,n2);
				m2=location2/1000;
				n2=location2-m2*1000;
				if((m2==0&&n2==0)||(m2==660&&n2==540))
				{
					p2=4;
					settextcolor(RED);
	                setfont(50,0,_T("微软雅黑"));
	                setfillstyle(BLACK);
	                bar(200,240,470,320);	
	                rectangle(200,240,470,320);
	                outtextxy(270,245,_T("进化！！!"));
				}
				pm2[i2]=m2;
				pn2[i2]=n2;
				i2++;
				for(j=0;j<i1;j++)
				{
					if(p2!=4)
					{
						if(m2==pm1[j]&&n2==pn1[j])
					    score2=score2-2;
					}
				}
				a=move(m2,n2,p2);
				score2=score2+score(temp2,m2,n2,p2);
					settextcolor(BLACK);
					settextstyle(35,20,_T("微软雅黑"));
					setbkcolor(WHITE);
					bar(580,440,640,490);	
	                rectangle(580,440,640,490);
					TCHAR g2[5];
					_stprintf(g2, _T("%d"),score2);
					outtextxy(580,440, g2);
				n=3;
				if(location2>10000000&&score1>score2)
				{	
	                 if(p1==1)
				  {
                     loadimage(NULL,"winred.jpg");
				  }
				    if(p1==2)
				  {
                     loadimage(NULL,"winyellow.jpg");
				  }
				    if(p1==3)
				  {
                     loadimage(NULL,"winblue.jpg");
				  }	
					if(p1==4)
					{
						loadimage(NULL,"winblack.jpg");
					}
					GameWin1();
					n=0;
				k=0;p1=0;p2=0;sum1=0;sum2=0;m1=0;n1=540;m2=660;n2=0;score1=0;score2=0;i1=0;i2=0;
				loadimage(NULL,"jiemian.jpg");

				}
				if(location2>10000000&&(score1<score2||score1==score2))
				{	
					
	                 if(p2==1)
				  {
                     loadimage(NULL,"winred.jpg");
				  }
				    if(p2==2)
				  {
                     loadimage(NULL,"winyellow.jpg");
				  }
				    if(p2==3)
				  {
                     loadimage(NULL,"winblue.jpg");
				  }
					if(p2==4)
					{
						loadimage(NULL,"winblack.jpg");
					}
					GameWin2();
					n=0;
				k=0;p1=0;p2=0;sum1=0;sum2=0;m1=0;n1=540;m2=660;n2=0;score1=0;score2=0;i1=0;i2=0;
				loadimage(NULL,"jiemian.jpg");
			
				}

			}
			if(m.x>397&&m.x<630&&m.y>250&&m.y<338)
			{
				return;
				break;
			}
			else if(m.uMsg==WM_LBUTTONDOWN&&m.x>=630&&m.x<=720&&m.y>=0&&m.y<=50&&k==0) 
			{
				mciSendString(_T("play music from 0 repeat"), NULL, 0, NULL);
				outtextxy(630,50,_T("开始播放"));
				k=1;
				break;
			}
	        else if(m.uMsg==WM_LBUTTONDOWN&&m.x>=630&&m.x<=720&&m.y>=0&&m.y<=50&&k==1) 
	        {
		         mciSendString(_T("stop music"), NULL, 0, NULL);   
                 outtextxy(630,50,_T("停止播放"));
		         k=0;
				 break;
	        }
		}	
	}
	closegraph();
	getch();
}
