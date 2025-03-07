/*
七段数码管是常用显示设备，通过发光二极管的点亮和关闭来显示不同数字（0~9）。
为便于描述，七段管分别用字母编号，依次为ABCDEFG（如图所示），数码管被点亮设为1，关闭为0。
因此，数字0的控制码表示为1111110，表示A~F管点亮，G管关闭，写成16进制数字就是0x7E。
根据以上描述，编程实现如下功能（两小题写成一个程序）：
(1) 输入一个整数M（0≤M<10），输出其对应的16进制控制码（控制码真值表如下所示）；
(2) 接上题。现在将每段数码管的开或关都定义为一次操作，例如数字1变为2是5次操作（A、C、D、E、G变化）。
	在(1)的基础上，让用户继续输入一个N（0≤N < 10），编程输出由M变为N需要经过多少次操作？
样例输入：1  2
样例输出：30  5
（输出表示：数字1的控制码为0x30，数字1变为数字2需5次操作）
*/

#include <stdio.h>
int main()
{
    int a[] = {0x7E, 0x30, 0x6D, 0x79, 0x33,
    0x5B, 0x5F, 0x70, 0x7F, 0x7B};
    int M;
    //第一部分,使用if...else或者switch即可
    scanf("%d", &M);
    printf("%X  ", a[M]);

    int i, N, c, m, n;
    scanf("%d", &N);

    c = 0; //初始化2分

	//将M，N转为2进制，比较不同的位有几个
	m = a[M];
	n = a[N];
    for(i=0;i<7;i++) 
    {
        if (m%2 != n%2)
            c++;
        m = m/2;
        n = n/2;
    }
    printf("%d", c);
    return 0;
}
