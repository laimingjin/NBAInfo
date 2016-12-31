import numpy
from scipy.stats import t
from scipy.stats import chi2
def get_numbers():
    nlist=numpy.loadtxt('c:\perBefore.txt')
    mlist=numpy.loadtxt('c:\perAfter.txt')
    N=len(nlist)
    A=[]
    for i in range(0,N):
        A.append(nlist[i]-mlist[i])
    return A
arr=get_numbers()
narray=numpy.array(arr)
sum1=narray.sum()
narray2=narray*narray
sum2=narray2.sum()
N=len(arr)
def get_mean():
    mean=sum1/N
    return mean
def get_var():
    sum=0
    for i in range(0,N):
        sum=sum+(arr[i]-get_mean())**2
    var=sum/(N-1)
    return var

print get_mean()
print get_var()
print get_mean()-(t.ppf(0.65,N-1))*get_var()/(N**0.5)

print get_mean()+(t.ppf(0.65,N-1))*get_var()/(N**0.5)
print chi2.ppf(0.65,N-1)
print (N-1)*get_var()*get_var()/chi2.ppf(0.65,N-1)**2
print (N-1)*get_var()*get_var()/chi2.ppf(0.35,N-1)**2
