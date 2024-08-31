/*

输入两个字符串S和T（S和T均不含空格，且长度均小于80），编程实现如下功能（两小题写成一个程序）：
(1) 找出字符串S中的最小字符，输出其所在下标index，如果有多个最小字符，必须输出最右边的那个index；
(2) 接上题。将T整串插入到(1)中index的后面，S串的原内容后移。注意必须修改S串的内容，最后再输出S串，直接在输出中拼接字符串不给分。
样例输入：20182018  1128
样例输出：5   201820112818

*/
#include <stdio.h>
#include <string.h>
int main()
{
    char S[160]={0}; //大小≥160
    char T[80]={0};  //大小≥80
    int min,index,i,ls,lt,dst,src;
    scanf("%s%s", S, T);
    min = S[0]; 
    index = 0; 
    for(i=1;S[i]!='\0';i++) //循环终止i<strlen(S)也可
    {
        if (S[i] <= min) 
        {
            min = S[i];
            index = i;
        }
    }

    printf("%d  ", index);

    //S串后移
    ls = strlen(S);
    lt = strlen(T);
    dst = ls + lt - 1;
    src = ls - 1;
    for(i=0;i<ls-index-1;i++) 
    {
        S[dst] = S[src]; 
        dst--;
        src--;
    }

    //T串插入
    dst = index+1;
    for(i=0;i<lt;i++) 
    {
        S[dst] = T[i];
        dst++;
    }

    printf("%s", S);

    return 0;
}
