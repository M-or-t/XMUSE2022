/*

A、B、C、D是四个不相等的非零数字，满足以下等式：AB*CD=BA*DC，例如：12*63=21*36
编程找出所有满足以上条件的式子，数字相同的等式不重复显示
如12*63=21*36与63*12=36*21两个等式仅显示一次。
将结果以每行5个式子的形式输出显示在屏幕上，并输出满足条件的式子的个数。

*/
#include <stdio.h>
int main()
{
   int a,b,c,d;
   int cnt=0;
   for(a=1;a<=9;a++)
   {
    if(a==b)continue;
    for(b=1;b<=9;b++)
    {
      if(a==b)continue;
      for(c=1;c<=9;c++)
      {
        if ((c==a)||(c==b))continue;
        for(d=1;d<=9;d++)  
        {
          if ((d==a)||(d==b)||(d==c)) continue; 
          if((10*a+b)*(10*c+d)==(10*b+a)*(10*d+c)) 
            if ((10*a+b)<(10*c+d))
           {
             printf("%d%d*%d%d=%d%d*%d%d   ",a,b,c,d,b,a,d,c);
             cnt++; 
             if(cnt%5==0) printf("\n");
           }
          }
        }
      }
    }
   }
    printf("cnt=%d",cnt); 
    return 0;
}
