#include<bits/stdc++.h>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    long n;
    cin>>n;
    
    cin.ignore();
    
    string s;
    getline(cin,s);
    
    int count = 0;
    
    for(int i=0;i<n;i++)
    {
        if (s[i]=='0')
        count++;
        else
        count--;
    }
    
    cout<<abs(count);
    
    return 0;
}